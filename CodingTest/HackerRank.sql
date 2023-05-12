
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