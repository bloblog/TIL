-- 595. Big Countries
SELECT name, population, area
FROM World
WHERE area >= 3000000 OR population >= 25000000;

-- 1757. Recyclable and Low Fat Products
SELECT product_id
FROM Products
WHERE low_fats = "Y" AND recyclable = "Y";

-- 584. Find Customer Referee
SELECT name
FROM Customer
WHERE id NOT IN (SELECT id FROM Customer WHERE referee_id = 2);

-- 183. Customers Who Never Order
SELECT name as Customers
FROM customers
WHERE id not IN (SELECT b.id
                FROM customers b
                    JOIN orders a
                    ON a.customerId = b.id);

-- 1873. Calculate Special Bonus
SELECT employee_id, 
    (case when (employee_id % 2 = 1) and (left(name, 1) <> "M") then salary
    else 0 end) as bonus
FROM employees
ORDER BY 1;

-- 627. Swap Salary (UPDATE문 사용)
UPDATE Salary
SET sex = case when sex = 'f' then 'm' else 'f' end;

-- 196. Delete Duplicate Emails (DELETE문 사용)
DELETE FROM Person
WHERE id not in 
	(SELECT ID FROM (SELECT min(id) as ID FROM Person GROUP BY email) temp_01);

---------------------------------

-- 1667. Fix Names in a Table
-- 문자열 합치기 → concat() 함수
-- length() 함수 → 파이썬의 len()과 헷갈리지 않도록 주의!

SELECT user_id, CONCAT(UPPER(LEFT(name, 1)), LOWER(RIGHT(name, length(name)-1))) as name
FROM Users
ORDER BY 1;

-- 1484. Group Sold Products By The Date
-- 그룹별로 문자열 합치기 → group_concat() 

SELECT sell_date, count(distinct product) as num_sold, 
    	group_concat(distinct product separator ',') as products
FROM activities
GROUP BY 1
ORDER BY 1;

--1527. Patients With a Condition

SELECT patient_id, patient_name, conditions
FROM Patients
WHERE conditions LIKE 'DIAB1%'
  OR conditions LIKE '% DIAB1%';

-- 1965. Employees With Missing Information

-- employee(name) 기준으로 조인해서 salary null 값인 id 찾기
SELECT a.employee_id
FROM employees a
    LEFT JOIN salaries b
    ON a.employee_id = b.employee_id
WHERE salary IS null
UNION 
-- salary 기준으로 조인해서 name null 값인 id 찾기
SELECT a.employee_id
FROM salaries a
    LEFT JOIN employees b
    ON a.employee_id = b.employee_id
WHERE name IS null
ORDER BY 1;

-- 1795. Rearrange Products Table

SELECT product_id, "store1" as store, store1 as price
FROM products
WHERE store1 IS NOT null
UNION
SELECT product_id, "store2" as store, store2 as price
FROM products
WHERE store2 IS NOT null
UNION
SELECT product_id, "store3" as store, store3 as price
FROM products
WHERE store3 IS NOT null
ORDER BY 1;

-- 608. Tree Node
    
SELECT id, 
    (CASE WHEN p_id IS null THEN "Root" 
    WHEN id IN (SELECT distinct(p_id) FROM Tree) THEN "Inner"
    ELSE "Leaf" END) as type
FROM Tree;

-- 176. Second Highest Salary
    
SELECT (CASE WHEN count(id) = 0 THEN null
        ELSE max(salary) END) as SecondHighestSalary
FROM employee
WHERE salary != (SELECT max(salary) FROM employee);