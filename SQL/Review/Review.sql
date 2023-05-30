/*상품 리뷰 데이터 분석*/

-- https://www.kaggle.com/datasets/nicapotato/womens-ecommerce-clothing-reviews
-- 첫번째 칼럼 삭제하거나 칼럼명 입력 후 다시 저장
-- data import 시키는 두 가지 방법

-- 널값 처리된 덤프파일 깃허브에 있음
/*<1>-부서별 평점 분포 계산*/

SELECT *
FROM mydata.dataset2;

-- [Q1-어느 부서의 상품이 좋은 평가 혹은 나쁜 평가를 받았을까?]
-- [1-부서별 평균 평점 계산-DIVISION NAME기준]
SELECT `Division Name`, -- 작은 따옴표 아님 주의! 물결~ 버튼 Shift 누르지 않고 입력 GRAVE
-- 안에 띄어쓰기가 있어서 이 부호 쓰는 것. 띄어쓰기 없으면 그냥 쓴다
		AVG(rating) AVG_RATE
FROM mydata.dataset2
GROUP BY 1
ORDER BY 2 DESC;

-- [2-부서별 평균 평점 계산-DEPARTMENT NAME기준]
SELECT `Department Name`, -- 작은 따옴표 아님 주의! 물결~ 버튼 Shift 누르지 않고 입력 GRAVE
		AVG(rating) AVG_RATE
FROM mydata.dataset2
GROUP BY 1
ORDER BY 2 DESC; -- Trend 부서만 조금 낮네? 왜 낮을까?

-- [3-부서별 평균 평점 계산-Trend팀의 평점 3점 이하 리뷰] 따로 봐보자
SELECT *
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
	AND Rating <= 3; -- 리뷰 보니까 사이즈 문제가 있구나 알 수 있다

-- [Q1.1-연령별 분포로 볼 수 있을까?] -- 세부적으로 봐보자
-- [4. 3번 결과에 case문 적용]
SELECT CASE 
			WHEN age BETWEEN 0 AND 9 THEN '0009'
			WHEN age BETWEEN 10 AND 19 THEN '1019'
			WHEN age BETWEEN 20 AND 29 THEN '2029'
			WHEN age BETWEEN 30 AND 39 THEN '3039'
			WHEN age BETWEEN 40 AND 49 THEN '4049'
			WHEN age BETWEEN 50 AND 59 THEN '5059'
			WHEN age BETWEEN 60 AND 69 THEN '6069'
			WHEN age BETWEEN 70 AND 79 THEN '7079'
			WHEN age BETWEEN 80 AND 89 THEN '8089'
			WHEN age BETWEEN 90 AND 99 THEN '9099'
END ageband,
	age
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
	AND Rating <= 3;

-- [5-3번 결과에 FLOOR함수 적용] 
SELECT FLOOR(age/10)*10 ageband, -- 버림. 66세의 경우 10으로 나눈 후 소수점 버리고 10곱하면 60대 라고 나옴
	age
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
	AND Rating <= 3; -- 잘 나눠졌는지 확인

-- [6-Trend팀의 평점 3점 이하 리뷰의 연령별 분포] 
SELECT FLOOR(age/10)*10 ageband,
	COUNT(*) cnt
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
	AND Rating <= 3
GROUP BY 1 -- 나이대별로 그룹핑
ORDER BY 2 DESC; -- 40대의 안좋은 리뷰가 많구나

-- [Q1.2-평점 낮은 리뷰가 많다고 가장 많은 불만이 있다고 할 수 있을까?] 40대의 많은 안좋은 리뷰=불만이 가장 많은 나잇대도 40대? 그냥 많이 사서 그런걸수도
-- [7-1-Trend팀의 전체 연령별 리뷰 수] -- 비중 확인 위해 전체 리뷰수 먼저 파악
SELECT FLOOR(age/10)*10 ageband,
	COUNT(*) cnt
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
GROUP BY 1
ORDER BY 2 DESC;

-- [7-2-Trend팀의 연령별 평점 낮은 리뷰 비중] -- 비율 파악
SELECT FLOOR(age/10)*10 ageband,
	CONCAT(ROUND(100.0 *
		COUNT(CASE WHEN rating <= 3  THEN 1 END) / -- 평점이 낮으면 1. 1만 세어주면 낮은 평점 준 사람 셀수있음
		COUNT(*), 2), '%') AS rating_ratio
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
GROUP BY 1
ORDER BY 1;

-- [Q1.3-주로 어떤 complain일까?]
-- [8-40대 3점 이하 Trend팀 리뷰 살펴보기] 
SELECT *
FROM mydata.dataset2
WHERE `Department Name` = 'Trend'
	AND rating <= 3
    AND age BETWEEN 40 AND 49 
LIMIT 10;

/*<2>-주요 complain 확인*/

-- [Q2-부서별로 평점 낮은 주요 10개 상품의 리뷰 확인해볼까?]
-- [9-부서별 상품별 평균 평점 계산]
SELECT `Department Name`,
		`Clothing ID`,
        AVG(rating) avg_rate
FROM mydata.dataset2
GROUP BY 1, 2;

-- [10-부서별 낮은 평점 순위 생성]
SELECT *,
		ROW_NUMBER() OVER (PARTITION BY `Department Name` -- 순위만드는 함수
							ORDER BY avg_rate) rnk
FROM (SELECT `Department Name`, -- 서브쿼리로 새 테이블 만듦
			`Clothing ID`,
			AVG(rating) avg_rate
		FROM mydata.dataset2
		GROUP BY 1, 2) A;

-- [11-평점 낮은 1~10위 조회]
SELECT *
FROM (SELECT *,
			ROW_NUMBER() OVER (PARTITION BY `Department Name`
								ORDER BY avg_rate) rnk
	FROM (SELECT `Department Name`,
				`Clothing ID`,
				AVG(rating) avg_rate
			FROM mydata.dataset2
			GROUP BY 1, 2) A) A -- 위의 쿼리 또 서브쿼리로 넣음. 테이블 이름 A로 지정(이름은 상관없는데 필수지정)
WHERE rnk <= 10;

-- [12-11결과 테이블 생성] -- 부서별로 낮은 평점 조회가능
CREATE TEMPORARY TABLE mydata.stat AS
SELECT *
FROM (SELECT *,
			ROW_NUMBER() OVER (PARTITION BY `Department Name` -- 파티션을 나눠야해서 over 붙임
								ORDER BY avg_rate) rnk
	FROM (SELECT `Department Name`,
				`Clothing ID`,
				AVG(rating) avg_rate
			FROM mydata.dataset2
			GROUP BY 1, 2) A) A
WHERE rnk <= 10;

-- [13-생성된 임시테이블로 상품 조회]
SELECT `Clothing ID`
FROM mydata.stat
WHERE `Department Name` = 'Bottoms';

-- [14. 13결과에 해당하는 리뷰 내용 조회] -- clothing id로 리뷰 조회
SELECT *
FROM mydata.dataset2
WHERE `Clothing ID` IN (SELECT `Clothing ID`
						FROM mydata.stat
						WHERE `Department Name` = 'Bottoms')
ORDER BY `Clothing ID`;

-- TF--IDF : 일반적인 용어빈도수 구하는 TF. IDF는 한 항목에서 자주 나오는(문법요소들) 것을 제외. 
-- 이걸 통해 실제 키워드 알 수 있다. 파이썬에서 주로 활용.

/*<3>-연령별 worst 부서*/

-- [Q3-리뷰 데이터 기반으로 worst 부서에 대한 프로모션(할인쿠폰)을 진행해볼까?]
-- [15-연령별 부서별 가장 낮은 점수 계산]
SELECT `Department Name`, -- 부서별 
		FLOOR(age/10)*10 ageband, -- 연령별
        AVG(rating) avg_rating
FROM mydata.dataset2
WHERE rating IS NOT null
GROUP BY 1, 2;

-- [16-15결과 기반으로 순위 생성]
SELECT *,
		ROW_NUMBER() OVER(PARTITION BY ageband ORDER BY avg_rating) rnk -- 연령별로 어느 부서 평이 안좋은지
FROM (SELECT `Department Name`,
				FLOOR(age/10)*10 ageband,
				AVG(rating) avg_rating
		FROM mydata.dataset2
        WHERE rating IS NOT null
		GROUP BY 1, 2) A;

-- [17-16결과에서 1위 조회] 가장 평 안좋은 부서?
SELECT *
FROM (SELECT *,
				ROW_NUMBER() OVER(PARTITION BY ageband ORDER BY avg_rating) rnk
		FROM (SELECT `Department Name`,
						FLOOR(age/10)*10 ageband,
						AVG(rating) avg_rating
				FROM mydata.dataset2
                WHERE rating IS NOT null
				GROUP BY 1, 2) A) A
WHERE rnk = 1;

/*<4>-사이즈 complain*/

-- [Q4-특정 문제(ex.사이즈)에 대해서 살펴볼까?]
-- [18-'size'가 포함된 리뷰의 수]
SELECT `Review Text`,
		CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END size_yn -- 사이즈 관련 키워드 들어가면 1 아니면 0인 테이블 생성
FROM mydata.dataset2;

SELECT SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size, -- 관련 리뷰 1로 두고 계산
		COUNT(*) n_total -- 사이즈 관련 컴플레인 수 세기
FROM mydata.dataset2;

-- [19-사이즈 상세 구분 리뷰의 수] -- 사이즈 세분화(큰지 작은지)
SELECT SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size,
		SUM(CASE WHEN `Review Text` LIKE '%large%' THEN 1 ELSE 0 END) n_large,
		SUM(CASE WHEN `Review Text` LIKE '%loose%' THEN 1 ELSE 0 END) n_loose,
		SUM(CASE WHEN `Review Text` LIKE '%small%' THEN 1 ELSE 0 END) n_small,
		SUM(CASE WHEN `Review Text` LIKE '%tight%' THEN 1 ELSE 0 END) n_tight,
        SUM(1) n_total
FROM mydata.dataset2;

-- [20-카테고리별로 사이즈 상세 구분 수치 확인]
SELECT `Department Name`,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size,
		SUM(CASE WHEN `Review Text` LIKE '%large%' THEN 1 ELSE 0 END) n_large,
		SUM(CASE WHEN `Review Text` LIKE '%loose%' THEN 1 ELSE 0 END) n_loose,
		SUM(CASE WHEN `Review Text` LIKE '%small%' THEN 1 ELSE 0 END) n_small,
		SUM(CASE WHEN `Review Text` LIKE '%tight%' THEN 1 ELSE 0 END) n_tight,
        SUM(1) n_total
FROM mydata.dataset2
GROUP BY 1;

-- [21-연령별 카테고리별 사이즈 상세 구분 수치 확인]
SELECT 	FLOOR(age/10)*10 ageband,
		`Department Name`,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size,
		SUM(CASE WHEN `Review Text` LIKE '%large%' THEN 1 ELSE 0 END) n_large,
		SUM(CASE WHEN `Review Text` LIKE '%loose%' THEN 1 ELSE 0 END) n_loose,
		SUM(CASE WHEN `Review Text` LIKE '%small%' THEN 1 ELSE 0 END) n_small,
		SUM(CASE WHEN `Review Text` LIKE '%tight%' THEN 1 ELSE 0 END) n_tight,
        SUM(1) n_total
FROM mydata.dataset2
GROUP BY 1, 2
ORDER BY 1, 2;

-- [22-연령별 카테고리별 사이즈 상세 구분 비중 확인]
SELECT 	FLOOR(age/10)*10 ageband,
		`Department Name`,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) / SUM(1) ratio_size,
		SUM(CASE WHEN `Review Text` LIKE '%large%' THEN 1 ELSE 0 END) / SUM(1) ratio_large,
		SUM(CASE WHEN `Review Text` LIKE '%loose%' THEN 1 ELSE 0 END) / SUM(1) ratio_loose,
		SUM(CASE WHEN `Review Text` LIKE '%small%' THEN 1 ELSE 0 END) / SUM(1) ratio_small,
		SUM(CASE WHEN `Review Text` LIKE '%tight%' THEN 1 ELSE 0 END) / SUM(1) ratio_tight
FROM mydata.dataset2
GROUP BY 1, 2
ORDER BY 1, 2;

/*<5>-상품별 특정 문제 리뷰*/

-- [Q5-특정 문제(ex.사이즈)가 있는 상품은 어떤 것일까?]
-- [23-상품별 사이즈 리뷰 수]
SELECT `Clothing ID`,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size
FROM mydata.dataset2
GROUP BY 1;

-- [24-상품별 사이즈 상세 구분 리뷰 수] 비중
SELECT 	`Clothing ID`,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) / SUM(1) ratio_size,
		SUM(CASE WHEN `Review Text` LIKE '%large%' THEN 1 ELSE 0 END) / SUM(1) ratio_large,
		SUM(CASE WHEN `Review Text` LIKE '%loose%' THEN 1 ELSE 0 END) / SUM(1) ratio_loose,
		SUM(CASE WHEN `Review Text` LIKE '%small%' THEN 1 ELSE 0 END) / SUM(1) ratio_small,
		SUM(CASE WHEN `Review Text` LIKE '%tight%' THEN 1 ELSE 0 END) / SUM(1) ratio_tight
FROM mydata.dataset2
GROUP BY 1
ORDER BY 3;

-- [25-테이블 생성하여 상품개발팀이나 디자인팀에 공유]
CREATE TABLE mydata.size_stat AS
SELECT 	`Clothing ID`,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) n_size,
		SUM(CASE WHEN `Review Text` LIKE '%size%' THEN 1 ELSE 0 END) / SUM(1) ratio_size,
		SUM(CASE WHEN `Review Text` LIKE '%large%' THEN 1 ELSE 0 END) / SUM(1) ratio_large,
		SUM(CASE WHEN `Review Text` LIKE '%loose%' THEN 1 ELSE 0 END) / SUM(1) ratio_loose,
		SUM(CASE WHEN `Review Text` LIKE '%small%' THEN 1 ELSE 0 END) / SUM(1) ratio_small,
		SUM(CASE WHEN `Review Text` LIKE '%tight%' THEN 1 ELSE 0 END) / SUM(1) ratio_tight
FROM mydata.dataset2
GROUP BY 1;