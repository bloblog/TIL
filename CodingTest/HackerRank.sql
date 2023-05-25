
https://www.hackerrank.com/domains/sql?filters[status][]=unsolved&filters[status][]=solved

-- Weather Observation Station 5

SELECT min(city), length(city)
FROM station
WHERE length(city) = (SELECT MAX(length(city)) FROM station)
GROUP BY length(city)
UNION 
SELECT min(city), length(city)
FROM station
WHERE length(city) = (SELECT MIN(length(city)) FROM station)
GROUP BY length(city)
ORDER BY 1;
-----------------------------------
-- Weather Observation Station 8

SELECT distinct city
FROM station
WHERE city regexp '^[aeiou]' and city regexp '[aeiou]$';
    
-- Weather Observation Station 12
-- and 안 쓰고 정규식 사용하기

SELECT distinct city
FROM station
WHERE city regexp '^[^aeiou].*[^aeiou]$';
    
-- Type of Triangle

SELECT case when a >= b+c or b >= a+c or c >= a+b then "Not A Triangle" 
        when a=b and b=c then "Equilateral"
        when a<>b and b<>c and a<>c then "Scalene"
        else "Isosceles" end
        -- when (a<>b or b<>c or c<>a) and (a=b or b=c or a=c) 
FROM triangles;
    
-- Occupations
-- 테케는 통과하긴 했는데… professor 인원이 가장 많다고 확신할 수 없지 않나??

with temp_01 as
(
    SELECT *, row_number() over(partition by occupation order by name) as ord
    FROM occupations
    )
SELECT a.name, b.name, c.name, d.name
FROM (SELECT * FROM temp_01 WHERE occupation = 'Doctor') a
    RIGHT JOIN (SELECT * FROM temp_01 WHERE occupation = 'Professor') b
    ON a.ord = b.ord
    LEFT JOIN (SELECT * FROM temp_01 WHERE occupation = 'Singer') c
    ON b.ord = c.ord
    LEFT JOIN (SELECT * FROM temp_01 WHERE occupation = 'Actor') d
    ON b.ord = d.ord;

--------------------
-- New Companies

SELECT a.company_code, max(founder), 
    count(distinct b.lead_manager_code), 
    count(distinct c.senior_manager_code), 
    count(distinct d.manager_code), 
    count(distinct employee_code)
FROM company a
    LEFT JOIN lead_manager b
    ON a.company_code = b.company_code
    LEFT JOIN senior_manager c
    ON a.company_code = c.company_code
    LEFT JOIN manager d
    ON a.company_code = d.company_code
    LEFT JOIN employee e
    ON a.company_code = e.company_code
GROUP BY 1
ORDER BY 1;
    
-- Binary Tree Nodes

SELECT N, 
    case when P is null then 'Root' 
    when N in (SELECT distinct P FROM BST) then "Inner"
    else "Leaf" end
FROM BST
ORDER BY 1;
    

-- The PADS
-- MySQL은 union 정렬 문제, concat 함수 문제 때문에 MS SQL로 진행

SELECT concat(name, case when occupation = "Actor" then "(A)"
                        when occupation = "Doctor" then "(D)"
                        when occupation = "Professor" then "(P)"
                        else "(S)" end)
FROM occupations 
ORDER BY name

SELECT concat("There are a total of ", count(name), " ", lower(occupation), "s.")
FROM occupations
GROUP BY occupation
ORDER BY count(name), occupation;

-----------------------------
-- The Blunder
-- ceil은 자릿수 지정하지 않음! 주의

with temp_01 as
(
    SELECT *, replace(salary, 0, "") as mistake
    FROM employees
    )
SELECT ceil(avg(salary)-avg(mistake))
FROM temp_01;
    
-- Top Earners

SELECT max(months*salary), count(*)
FROM employee
WHERE months*salary = (SELECT max(months*salary) FROM employee);

------------------------------------
-- Weather Observation Station 19

SELECT format(sqrt(
    power(max(long_w) - min(long_w), 2) + power(max(lat_n) - min(lat_n), 2)
    ), 4)
FROM station;
    
-- Weather Observation Station 20
-- Wrong Answer

with temp_01 as
(
    SELECT lat_n, row_number() over(order by lat_n) as rnk
    FROM station
    )
SELECT round(case when length(lat_n) % 2 = 1 then 
        (SELECT lat_n FROM temp_01 WHERE rnk = ceil(length(lat_n)/2))
    else (SELECT avg(lat_n) FROM temp_01 WHERE rnk in (length(lat_n)/2, length(lat_n)/2+1))
    end, 4)
FROM temp_01
LIMIT 1;
    
-- 23.05.17 재도전
-- length() 함수 쓰면 문자의 길이를 리턴한다. row 수 아님!
-- max값 즉 총 row 개수 임시테이블로 따로 계산
-- 총 개수가 짝수든 홀수든 포괄할 수 있는 조건 달아야 한다.

with temp_01 as
(
    SELECT lat_n, row_number() over(order by lat_n) as rnk
    FROM station
    ), temp_02 as
    (
        SELECT *, max(rnk) over() as len
        FROM temp_01
        )

SELECT round(avg(lat_n), 4) 
FROM temp_02
WHERE len/2 <= rnk 
    and rnk <= len/2+1;

----------------------
-- Top Competitors

SELECT a.hacker_id, max(name)
FROM submissions a
    JOIN challenges b
    ON a.challenge_id = b.challenge_id
    JOIN difficulty c 
    ON b.difficulty_level = c.difficulty_level
    JOIN hackers d
    ON a.hacker_id = d.hacker_id
WHERE a.score = c.score
GROUP BY hacker_id
HAVING count(a.challenge_id) > 1
ORDER BY count(a.challenge_id) desc, hacker_id;
    
-- The Report (MS SQL 활용)

SELECT name, grade, marks
FROM students a
    JOIN grades b
    ON a.marks between b.min_mark and b.max_mark
WHERE grade >= 8
ORDER BY 2 desc, 1;

SELECT NULL, grade, marks
FROM students a
    JOIN grades b
    ON a.marks between b.min_mark and b.max_mark
WHERE grade < 8
ORDER BY 2 desc, 3;
    
-- Ollivander's Inventory
-- syntax error

with temp_01 as
(
    SELECT age, power, min(coins_needed) as coin
    FROM wands a
        JOIN wands_property b
        ON a.code = b.code
    WHERE is_evil = 0 
    GROUP BY 1, 2
    )
SELECT y.id, y.age, coins_needed, y.power
FROM temp_01 x
    JOIN (SELECT * FROM wands a JOIN wands_property b ON a.code = b.code) y
    ON x.age = y.age and x.power = y.power and x.coin = y.coins_needed
ORDER BY power desc, age desc;

-- 23.05.18 성공
-- Ollivander's Inventory (MS SQL 활용)

with temp_01 as
(
    SELECT id, age, power, coins_needed, min(coins_needed) over(partition by age, power) as min_coin
    FROM wands a 
        JOIN wands_property b 
        ON a.code = b.code
    WHERE is_evil = 0
    )
SELECT id, age, coins_needed, power
FROM temp_01
WHERE coins_needed = min_coin
ORDER BY power desc, age desc;

------------------------------------
-- Contest Leaderboard (MS SQL 활용)
with temp_01 as
(
    SELECT hacker_id, max(score) as max_score
    FROM submissions
    GROUP BY hacker_id, challenge_id
    )
    
SELECT a.hacker_id, max(name), sum(max_score)
FROM temp_01 a
    JOIN hackers b 
    ON a.hacker_id = b.hacker_id
GROUP BY a.hacker_id
HAVING sum(max_score) != 0
ORDER BY 3 desc, 1;

--------------------

-- Challenges

with temp_01 as
(
    SELECT hacker_id, count(challenge_id) as total
    FROM challenges
    GROUP BY 1
    )

SELECT a.hacker_id, name, total
FROM temp_01 a
    JOIN hackers b
    ON a.hacker_id = b.hacker_id
WHERE total = (SELECT max(total) FROM temp_01)
UNION ALL
SELECT max(a.hacker_id), max(name), total
FROM temp_01 a
    JOIN hackers b
    ON a.hacker_id = b.hacker_id
GROUP BY total
HAVING count(*) = 1
ORDER BY 3 desc, 1; 

-- Placements

with temp_01 as
(
    SELECT a.id, friend_id, b.salary as my_sal, c.salary as fr_sal
    FROM friends a
        JOIN packages b
        ON a.id = b.id
        JOIN packages c
        ON a.friend_id = c.id
    )
SELECT name
FROM temp_01 x
    JOIN students y
    ON x.id = y.id
WHERE my_sal < fr_sal
ORDER BY fr_sal;

--------------------------------------------------
-- Symmetric Pairs
-- x, y가 같은 경우 본인을 제외하고 JOIN 해야 하는 게 포인트
    
with temp_01 as
(
    SELECT x, y
    FROM functions
    GROUP BY x, y
    HAVING count(*)>=2 or (count(*)=1 and x != y)
    )

SELECT distinct a.x, a.y
FROM functions a
    JOIN temp_01 b
    ON a.y = b.x and a.x = b.y
WHERE a.x <= a.y
ORDER BY a.x;

-------------------------
-- SQL Project Planning

with temp_01 as
(
    SELECT case when lag(end_date) over(order by start_date) != start_date or lag(end_date) over(order by end_date) is null then start_date end is_start,
    case when lead(start_date) over(order by start_date) != end_date or lead(start_date) over(order by start_date) is null then end_date end is_end
    FROM projects
    ), temp_02 as
    (
        SELECT distinct 
            case when is_start is not null then is_start else lag(is_start) over() end as project_start,
            case when is_end is not null then is_end else lead(is_end) over() end as project_end
        FROM temp_01
        WHERE is_start is not null or is_end is not null
        )
SELECT *
FROM temp_02
ORDER BY datediff(project_end, project_start), 1;

------------------------------
-- 15 Days of Learning SQL
-- MS SQL 활용

with temp_01 as
(
    SELECT submission_date, hacker_id, count(distinct hacker_id) as cnt
    FROM submissions
    GROUP BY submission_date, hacker_id
    ), temp_02 as
    (
        SELECT submission_date, hacker_id, 
            sum(cnt) over(partition by hacker_id order by submission_date) as sum
        FROM temp_01
        ), temp_03 as
        (
            SELECT submission_date, hacker_id, count(submission_id) as sub
            FROM submissions
            GROUP BY submission_date, hacker_id
            ), temp_04 as
            (
                SELECT submission_date, hacker_id, 
                    row_number() over(partition by submission_date order by sub desc, hacker_id) as rnk
                FROM temp_03
            )

SELECT a.submission_date, allday, a.hacker_id, name
FROM temp_04 a
    JOIN (SELECT submission_date, count(*) as allday
            FROM temp_02
            WHERE DAY(submission_date) = sum
            GROUP BY submission_date) b
    ON a.submission_date = b.submission_date
    JOIN hackers c
    ON a.hacker_id = c.hacker_id
WHERE rnk = 1
ORDER BY 1;