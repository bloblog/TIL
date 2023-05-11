
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