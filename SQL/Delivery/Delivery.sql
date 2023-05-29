/*배송 데이터 분석*/

-- https://www.kaggle.com/competitions/instacart-market-basket-analysis
-- 실습을 위한 작은 데이터셋으로 진행 ==> instacart_dataset-master.zip

/*<1>-현황 파악 & 지표 추출*/
-- 1) 전체 주문 건수
-- 2) 구매자 수
-- 3) 상품별 주문 건수
-- 4) 장바구니에 가장 먼저 넣는 상품 10개
-- 5) 시간별 주문 건수
-- 6) 첫 구매 후 다음 구매까지 걸린 평균 일수
-- 7) 주문 건당 평균 구매 상품 수(UPT, Unit Per Transaction)
-- 8) 인당 평균 주문 건수
-- 9) 재구매율이 가장 높은 상품 10개
-- 10) Depertment별 재구매율이 가장 높은 상품 10개

-- [1-전체 주문 건수] 중복 있는지 확인
SELECT COUNT(*) all_cnt,
	COUNT(DISTINCT order_id) d_cnt
FROM instacart.orders; -- order id에 중복이 없구나(두 숫자가 같아서)

-- [2-구매자 수] 중복 있는지 확인
SELECT COUNT(*) all_cnt,
	COUNT(DISTINCT user_id) d_cnt
FROM instacart.orders; -- 여기는 중복이 있구나(한 구매자가 여러번 구매?)

-- [3-상품별 주문 건수]
SELECT *
FROM instacart.order_products__prior A
LEFT JOIN instacart.products B -- product name 가져오려고 조인
ON A.product_id = B.product_id;

SELECT B.product_name,
	COUNT(A.order_id) order_cnt -- 상품별로 개수 센다
FROM instacart.order_products__prior A
LEFT JOIN instacart.products B -- 위 구문과 같은 조인
ON A.product_id = B.product_id
GROUP BY 1; -- 상품별로 알 수 있게 한다

-- 이제 테이블 특징 알아야 한다 
-- [4-장바구니에 가장 먼저 넣는 상품 10개] 가장 필요한 상품이다!
SELECT *
FROM instacart.order_products__prior;

SELECT product_id,
	CASE WHEN add_to_cart_order = 1 THEN 1 ELSE 0 END add_1st -- 제일 처음 담긴 애들만 1, 아니면 0으로
FROM instacart.order_products__prior;

SELECT product_id,
	SUM(CASE WHEN add_to_cart_order = 1 THEN 1 ELSE 0 END) add_1st_cnt -- sum하면 제일 처음 담긴 애들만 계산됨(아닌건 0이니까)
FROM instacart.order_products__prior
GROUP BY 1; -- 상품 아이디 별로 제일먼저 담긴 횟수 계산->아래 구문 서브쿼리로

SELECT *,
	ROW_NUMBER() OVER(ORDER BY add_1st_cnt DESC) rnk -- 랭킹 매긴다->FROM 절에 서브쿼리로 들어간다
FROM (SELECT product_id,
		SUM(CASE WHEN add_to_cart_order = 1 THEN 1 ELSE 0 END) add_1st_cnt
	FROM instacart.order_products__prior
	GROUP BY 1) A;

SELECT *
FROM (SELECT *,
		ROW_NUMBER() OVER(ORDER BY add_1st_cnt DESC) rnk
	FROM (SELECT product_id,
			SUM(CASE WHEN add_to_cart_order = 1 THEN 1 ELSE 0 END) add_1st_cnt
		FROM instacart.order_products__prior
		GROUP BY 1) A) B
WHERE rnk BETWEEN 1 AND 10; -- 10개만 뽑는다

SELECT product_id,
	SUM(CASE WHEN add_to_cart_order = 1 THEN 1 ELSE 0 END) add_1st_cnt
FROM instacart.order_products__prior
GROUP BY 1
ORDER BY 2 DESC
LIMIT 10; -- 등수 컬럼 안만들고 그냥 상위 10개 뽑음

-- [5-시간별 주문 건수] 
SELECT order_hour_of_day,
	COUNT(order_id) order_cnt -- 시간별로 주문 아이디 카운트
FROM instacart.orders
GROUP BY 1
ORDER BY 1;

-- [6-첫 구매 후 다음 구매까지 걸린 평균 일수] 
SELECT AVG(days_since_prior_order) avg_recency
FROM instacart.orders
WHERE order_number = 2; -- 두번째 주문건 기준으로 해서 첫구매와 날짜 차이 평균 구한다 

-- [7-주문 건당 평균 구매 상품 수(UPT)] 
SELECT COUNT(product_id) / COUNT(DISTINCT order_id) upt -- 다른 주문이어도 프로덕트아이디가 같을수도 있어서 distinct안함
FROM instacart.order_products__prior;

-- [8-인당 평균 주문 건수] 
SELECT COUNT(order_id) / COUNT(DISTINCT user_id) avg_order_cnt
FROM instacart.orders;

-- [9-재구매율이 가장 높은 상품 10개]
-- [9-1-상품별 재구매율 계산]
SELECT product_id,
	SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio -- 카운트 안쓰는 이유? 카운트는 0도 세기 때문
FROM instacart.order_products__prior
GROUP BY 1;

-- [9-2-재구매율로 순위(rank) 열 생성하여 top10 구하기]
SELECT *
FROM (SELECT *,
		ROW_NUMBER() OVER(ORDER BY ret_ratio DESC) rnk -- 방법1
	FROM (SELECT product_id,
			SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio -- 재구매한 상품은 테이블에 1로 들어가있었다
		FROM instacart.order_products__prior
		GROUP BY 1) A) B
WHERE rnk BETWEEN 1 AND 10;

SELECT product_id,
	SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio
FROM instacart.order_products__prior
GROUP BY 1
ORDER BY 2 DESC 
LIMIT 10; -- 방법2

-- [10-Department별 재구매율이 가장 높은 상품 10개] 부서별이 아니라 재구매율 높은 상품의 부서이름까지 불러오는 거 아닌가..?
SELECT *
FROM instacart.order_products__prior A
LEFT JOIN instacart.products B
ON A.product_id = B.product_id -- 상품 아이디 가져와서
LEFT JOIN instacart.departments C
ON B.department_id = C.department_id; -- 부서 이름 가져올 수 있게 조인

SELECT C.department,
	A.product_id,
    SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio
FROM instacart.order_products__prior A
LEFT JOIN instacart.products B
ON A.product_id = B.product_id
LEFT JOIN instacart.departments C
ON B.department_id = C.department_id
GROUP BY 1, 2;

SELECT *,
	ROW_NUMBER() OVER(ORDER BY ret_ratio DESC) rnk
FROM (SELECT C.department,
		A.product_id,
		SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio
	FROM instacart.order_products__prior A
	LEFT JOIN instacart.products B
	ON A.product_id = B.product_id
	LEFT JOIN instacart.departments C
	ON B.department_id = C.department_id
	GROUP BY 1, 2) D;

SELECT *
FROM (SELECT *,
		ROW_NUMBER() OVER(ORDER BY ret_ratio DESC) rnk
	FROM (SELECT C.department,
			A.product_id,
			SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio
		FROM instacart.order_products__prior A
		LEFT JOIN instacart.products B
		ON A.product_id = B.product_id
		LEFT JOIN instacart.departments C
		ON B.department_id = C.department_id
		GROUP BY 1, 2) D) E
WHERE rnk BETWEEN 1 AND 10;

/*<2>-구매자 분석*/
-- 10분위 분석 => 전체를 10분위(10개의 그룹)으로 나누어 각 분위 수에 해당하는 집단의 성질을 나타내는 방법

-- [11-10분위 분석]
-- [11-1-주문 건수에 따른 순위 생성]
SELECT user_id,
	COUNT(DISTINCT order_id) order_cnt
FROM instacart.orders
GROUP BY 1;

SELECT *,
	ROW_NUMBER() OVER(ORDER BY order_cnt DESC) rnk
FROM (SELECT user_id,
		COUNT(DISTINCT order_id) order_cnt
	FROM instacart.orders
	GROUP BY 1) A;

-- [11-2-고객별 분위 수를 매기기 위해 전체 고객 수 계산]
SELECT COUNT(DISTINCT user_id)
FROM (SELECT user_id,
		COUNT(DISTINCT order_id) order_cnt
	FROM instacart.orders
	GROUP BY 1) A;

-- [11-3-316명씩 분위 수 매기기 & 임시 테이블로 저장] -- 전체 고객수의 10분의 1을 기준으로
SELECT *,
	CASE WHEN rnk BETWEEN 1 AND 316 THEN 'Q01'
		WHEN rnk BETWEEN 317 AND 632 THEN 'Q02'
		WHEN rnk BETWEEN 633 AND 948 THEN 'Q03'
		WHEN rnk BETWEEN 949 AND 1264 THEN 'Q04'
		WHEN rnk BETWEEN 1265 AND 1580 THEN 'Q05'
		WHEN rnk BETWEEN 1581 AND 1895 THEN 'Q06'
		WHEN rnk BETWEEN 1896 AND 2211 THEN 'Q07'
		WHEN rnk BETWEEN 2212 AND 2527 THEN 'Q08'
		WHEN rnk BETWEEN 2528 AND 2843 THEN 'Q09'
		WHEN rnk BETWEEN 2844 AND 3159 THEN 'Q10' END quantile -- 주문건수에 따라 분위 매김(근데 이 데이터는 건수 편차가 심하지 않아 의미 약함)
FROM (SELECT *,
		ROW_NUMBER() OVER(ORDER BY order_cnt DESC) rnk
	FROM (SELECT user_id,
			COUNT(DISTINCT order_id) order_cnt
		FROM instacart.orders
		GROUP BY 1) A) B;

SELECT *,
	CASE WHEN rnk <= 316 THEN 'Q01'
		WHEN rnk <= 632 THEN 'Q02' -- elif 와 같기 때문에 316초과중에서 632이하 범위만 포함한다
		WHEN rnk <= 948 THEN 'Q03'
		WHEN rnk <= 1264 THEN 'Q04'
		WHEN rnk <= 1580 THEN 'Q05'
		WHEN rnk <= 1895 THEN 'Q06'
		WHEN rnk <= 2211 THEN 'Q07'
		WHEN rnk <= 2527 THEN 'Q08'
		WHEN rnk <= 2843 THEN 'Q09'
		WHEN rnk <= 3159 THEN 'Q10' END quantile
FROM (SELECT *,
		ROW_NUMBER() OVER(ORDER BY order_cnt DESC) rnk
	FROM (SELECT user_id,
			COUNT(DISTINCT order_id) order_cnt
		FROM instacart.orders
		GROUP BY 1) A) B;

CREATE TEMPORARY TABLE instacart.user_quantile AS -- 임시테이블
SELECT *,
	CASE WHEN rnk <= 316 THEN 'Q01'
		WHEN rnk <= 632 THEN 'Q02'
		WHEN rnk <= 948 THEN 'Q03'
		WHEN rnk <= 1264 THEN 'Q04'
		WHEN rnk <= 1580 THEN 'Q05'
		WHEN rnk <= 1895 THEN 'Q06'
		WHEN rnk <= 2211 THEN 'Q07'
		WHEN rnk <= 2527 THEN 'Q08'
		WHEN rnk <= 2843 THEN 'Q09'
		WHEN rnk <= 3159 THEN 'Q10' END quantile 
FROM (SELECT *,
		ROW_NUMBER() OVER(ORDER BY order_cnt DESC) rnk
	FROM (SELECT user_id,
			COUNT(DISTINCT order_id) order_cnt
		FROM instacart.orders
		GROUP BY 1) A) B;

-- [11-4-분위 수별 주문 건수]
SELECT quantile,
	SUM(order_cnt) order_cnt
FROM instacart.user_quantile
GROUP BY 1;

-- [11-5-분위 수별 주문 비중 분포]
SELECT SUM(order_cnt) 
FROM instacart.user_quantile;

SELECT quantile,
	SUM(order_cnt) / 3220 ratio
FROM instacart.user_quantile
GROUP BY 1;
-- 결론: 몇명의 소비자가 주문을 헤비하게 하는 게 아닌 고루고루 비슷하다

/*<3>-상품 분석*/

-- [12-재구매 비중이 높은 상품]
-- [12-1-상품별 재구매 비중과 주문 건수 계산]
SELECT product_id,
	SUM(reordered) / SUM(1) reorder_rate, -- 재구매 비중. sum(1)=count(*)
    COUNT(DISTINCT order_id) order_cnt -- 주문 건수
FROM instacart.order_products__prior
GROUP BY 1
ORDER BY 2 DESC;

-- [12-2-주문 건수가 일정 건수(10건) 이하인 상품 제외]
SELECT product_id,
	SUM(reordered) / SUM(1) reorder_rate, -- 재구매 비중
    COUNT(DISTINCT order_id) order_cnt -- 주문 건수
FROM instacart.order_products__prior
GROUP BY 1
HAVING COUNT(DISTINCT order_id) > 10; -- 그룹바이 된 상태에서 조건을 거는 거라서 having사용

-- [12-3-어떤 상품들이 재구매율이 높은지 확인] -- 이름 가져와라
SELECT A.product_id,
	B.product_name,
	SUM(reordered) / SUM(1) reorder_rate, -- 재구매 비중
    COUNT(DISTINCT order_id) order_cnt -- 주문 건수
FROM instacart.order_products__prior A
LEFT JOIN instacart.products B
ON A.product_id = B.product_id
GROUP BY 1, 2
HAVING COUNT(DISTINCT order_id) > 10;

/*<4>-다음 구매까지의 소요 기간과 재구매 관계*/
-- 고객이 자주 재구매하는 상품은 그렇지 않은 상품보다 일정한 주기를 가질 것이다.(기저귀나 생수나...)
-- 재구매율이 높은 순서대로 상품을 10가지 그룹으로 구분
-- 각 그룹에서의 구매 소요 기간의 분산(Variance)
-- 분산이 낮을수록 데이터가 평균에 모이게 되고, 분산이 클수록 관측치는 평균에서 멀리 분포

-- [13-1-상품별 재구매율이 높은 순서대로 순위 생성]
SELECT *
FROM (SELECT *,
			ROW_NUMBER() OVER(ORDER BY ret_ratio DESC) rnk
	FROM (SELECT product_id,
			SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio -- 재구매율
		FROM instacart.order_products__prior
		GROUP BY 1) A) B;
        
-- [13-2-각 상품을 10개의 그룹으로 나누기] => 10분위 분석과 동일 방법
SELECT COUNT(DISTINCT product_id) -- 전체 상품개수 먼저 구하기
FROM instacart.order_products__prior;

SELECT B.product_id,
	CASE WHEN rnk <= 929 THEN 'Q01' -- 전체상품개수/10 단위로
		WHEN rnk <= 1858 THEN 'Q02'
		WHEN rnk <= 2786 THEN 'Q03'
		WHEN rnk <= 3715 THEN 'Q04'
		WHEN rnk <= 4644 THEN 'Q05'
		WHEN rnk <= 5573 THEN 'Q06'
		WHEN rnk <= 6502 THEN 'Q07'
		WHEN rnk <= 7430 THEN 'Q08'
		WHEN rnk <= 8359 THEN 'Q09'
		WHEN rnk <= 9288 THEN 'Q10' END rnk_grp        
FROM (SELECT *,
			ROW_NUMBER() OVER(ORDER BY ret_ratio DESC) rnk
	FROM (SELECT product_id,
			SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio -- 재구매율
		FROM instacart.order_products__prior
		GROUP BY 1) A) B
GROUP BY 1, 2;

CREATE TEMPORARY TABLE instacart.product_repurchase_quantile AS -- 원래 형태가 이런가..?
SELECT B.product_id,
	CASE WHEN rnk <= 929 THEN 'Q01'
		WHEN rnk <= 1858 THEN 'Q02'
		WHEN rnk <= 2786 THEN 'Q03'
		WHEN rnk <= 3715 THEN 'Q04'
		WHEN rnk <= 4644 THEN 'Q05'
		WHEN rnk <= 5573 THEN 'Q06'
		WHEN rnk <= 6502 THEN 'Q07'
		WHEN rnk <= 7430 THEN 'Q08'
		WHEN rnk <= 8359 THEN 'Q09'
		WHEN rnk <= 9288 THEN 'Q10' END rnk_grp        
FROM (SELECT *,
			ROW_NUMBER() OVER(ORDER BY ret_ratio DESC) rnk
	FROM (SELECT product_id,
			SUM(CASE WHEN reordered = 1 THEN 1 ELSE 0 END) / COUNT(*) ret_ratio -- 재구매율
		FROM instacart.order_products__prior
		GROUP BY 1) A) B
GROUP BY 1, 2;

-- [13-3-각 분위 수별 재구매 소요 기간의 분산 구하기]
SELECT product_id,
	days_since_prior_order -- 소요기간
FROM instacart.order_products__prior A
INNER JOIN instacart.orders B
ON A.order_id = B.order_id;

CREATE TEMPORARY TABLE instacart.order_products__prior2 AS
SELECT product_id,
	days_since_prior_order
FROM instacart.order_products__prior A
INNER JOIN instacart.orders B
ON A.order_id = B.order_id;

SELECT A.rnk_grp,
	A.product_id,
    VARIANCE(days_since_prior_order) var_days -- 분산 구하는 함수
FROM instacart.product_repurchase_quantile A
LEFT JOIN instacart.order_products__prior2 B
ON A.product_id = B.product_id -- 위 두개 테이블 조인
GROUP BY 1, 2
ORDER BY 1;

-- [13-4-각 분위 수별 재구매 소요 기간의 분산 중위 수 계산] => median 함수가 없으므로 avg로 대체
SELECT rnk_grp,
	AVG(var_days) avg_var_days
FROM (SELECT A.rnk_grp,
		A.product_id,
		VARIANCE(days_since_prior_order) var_days
	FROM instacart.product_repurchase_quantile A
	LEFT JOIN instacart.order_products__prior B
	ON A.product_id = B.product_id
    LEFT JOIN instacart.orders C
	ON B.order_id = C.order_id
    GROUP BY 1, 2) D
GROUP BY 1
ORDER BY 1; -- 분산이 들쭉날쭉하다(재구매율이 높든 낮든 수치가 애매하다) -> 재구매가 높다고 해서 일정한 주기가 있다고 할 수 없다

