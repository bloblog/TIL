[LeetCode SQL 코딩테스트](https://leetcode.com/study-plan/sql/?progress=x4yafwp3)

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

------------------------------------------------
-- 175. Combine Two Tables

SELECT firstName, lastName, city, state
FROM Person a
    LEFT JOIN Address b
    ON a.personId = b.personID;
    
-- 1581. Customer Who Visited but Did Not Make Any Transactions

SELECT customer_id, count(customer_id) as count_no_trans
FROM visits a
    LEFT JOIN transactions b
    ON a.visit_id = b.visit_id
WHERE transaction_id IS null
GROUP BY 1;
    
-- 1148. Article Views I

SELECT DISTINCT(author_id) as id
FROM views
WHERE author_id = viewer_id
ORDER BY 1;

-- 197. Rising Temperature
    
with temp_01 as
(
    SELECT DATE_ADD(recordDate, INTERVAL 1 day) as date, 
    temperature as prev_temp
    FROM weather
)

SELECT id
FROM weather a
LEFT JOIN temp_01 b
ON a.recordDate = b.date
WHERE temperature > prev_temp;
    
-- 607. Sales Person

SELECT name
FROM salesperson
WHERE sales_id NOT IN (SELECT sales_id
                        FROM orders a
                        JOIN company b
                            ON a.com_id = b.com_id
                        WHERE name = "RED"); 

----------------------------------------------
--1141. User Activity for the Past 30 Days I
    
SELECT date_format(activity_date, "%Y-%m-%d") as day, 
        count(distinct user_id) as active_users
FROM activity
WHERE activity_date <= str_to_date("2019-07-27", "%Y-%m-%d")
    AND activity_date > date_sub(str_to_date("2019-07-27", "%Y-%m-%d"), interval 30 day)
GROUP BY 1;

-- 다른 방법 시도 : 속도 더 빠름
    
with temp_01 as
(
    SELECT *, str_to_date("2019-07-27", "%Y-%m-%d") as std
    FROM activity
)

SELECT date_format(a.activity_date, "%Y-%m-%d") as day, 
    count(distinct a.user_id) as active_users
FROM activity a
    JOIN temp_01 b
    ON a.user_id = b.user_id
WHERE a.activity_date <= std
    AND a.activity_date > date_sub(std, interval 30 day)
GROUP BY 1;
    
-- 1693. Daily Leads and Partners
    
SELECT date_id, make_name, count(distinct lead_id) as unique_leads, 
    count(distinct partner_id) as unique_partners 
FROM dailysales
GROUP BY 1, 2;

-- 1729. Find Followers Count
    
SELECT user_id, count(distinct follower_id) as followers_count
FROM followers
GROUP BY 1;

-- 1378. Replace Employee ID With The Unique Identifier
    
SELECT unique_id, name
FROM employees a
    LEFT JOIN employeeUNI b
    ON a.id = b.id;

-- 1393. Capital Gain/Loss
    
with temp_01 as
(
    SELECT *, (CASE WHEN operation = "Buy" THEN -price
                WHEN operation = "Sell" THEN price END) as gainloss
    FROM stocks
)

SELECT stock_name, sum(gainloss) as capital_gain_loss
FROM temp_01
GROUP BY stock_name;
-----------------------------------------------------
-- 586. Customer Placing the Largest Number of Orders
    
with temp_01 as
(
    SELECT customer_number, count(customer_number) as cnt
    FROM orders
    GROUP BY 1
)

SELECT customer_number
FROM temp_01
WHERE cnt = (SELECT max(cnt) FROM temp_01);
    
-- 511. Game Play Analysis I
    
SELECT player_id, min(event_date) as first_login
FROM activity
GROUP BY 1;
    
-- 1890. The Latest Login in 2020
    
SELECT user_id, max(time_stamp) as last_stamp
FROM logins
WHERE YEAR(time_stamp) = 2020
GROUP BY 1;
    
-- 1741. Find Total Time Spent by Each Employee
    
SELECT event_day as day, emp_id, sum(out_time-in_time) as total_time
FROM employees
GROUP BY 1, 2;

-- 1251. Average Selling Price

SELECT a.product_id, round(sum(price*units)/sum(units), 2) as average_price
FROM unitssold a
    JOIN prices b
    ON a.product_id = b.product_id
    AND purchase_date BETWEEN start_date AND end_date
GROUP BY 1;
    
--1068. Product Sales Analysis I
    
SELECT product_name, year, price
FROM sales a
    JOIN product b
    ON a.product_id = b.product_id;

-- 1204. Last Person to Fit in the Bus
    
with temp_01 as
(
    SELECT *, sum(weight) over (order by turn) as total_weight
    FROM Queue
)

SELECT person_name
FROM temp_01
WHERE total_weight <= 1000
ORDER BY turn desc
LIMIT 1;