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