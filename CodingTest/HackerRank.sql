
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