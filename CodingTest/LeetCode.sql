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

-------------------------
--1407. Top Travellers
    
SELECT name, case when distance is null then 0 else sum(distance) end as travelled_distance
FROM Users a
    LEFT JOIN Rides b
    ON a.id = b.user_id
GROUP BY a.id
ORDER BY 2 desc, 1;

--1158. Market Analysis I
    
with temp_01 as
(
    SELECT *
    FROM orders
    WHERE YEAR(order_date) = 2019
)
SELECT user_id as buyer_id, join_date, count(order_id) as orders_in_2019
FROM users a
    LEFT JOIN temp_01 b ON a.user_id = b.buyer_id
GROUP BY user_id;

-- 1327. List the Products Ordered in a Period**
    
SELECT product_name, sum(unit) as unit
FROM products a
    JOIN orders b ON a.product_id = b.product_id
WHERE YEAR(order_date) = 2020 AND MONTH(order_date) = 2
GROUP BY 1
HAVING sum(unit) >= 100;

-- 610. Triangle Judgement

SELECT *, 
    (case when x >= y+z or y >= x+z or z >= x+y then "No"
    else "Yes" end) as triangle
FROM triangle;

--------------------------
-- 1321. Restaurant Growth
 with temp_01 as
(
    SELECT visited_on, sum(amount) as day_amount
    FROM customer
    GROUP BY 1
),
temp_02 as
(
    SELECT visited_on,
    min(visited_on) over() as first_day,
    sum(day_amount) over(rows between 6 preceding and current row) as amount
    FROM temp_01 
)

SELECT visited_on, amount, round(amount/7, 2) as average_amount
FROM temp_02
WHERE visited_on >= date_add(first_day, interval 6 day);
    

-- 1050. Actors and Directors Who Cooperated At Least Three Times
    
SELECT actor_id, director_id
FROM actordirector
GROUP BY 1, 2
HAVING count(timestamp) >= 3;
    
-- 1280. Students and Examinations
with temp_01 as
(
    SELECT *
    FROM students a
        CROSS JOIN subjects b
    ), temp_02 as
(
    SELECT student_id, subject_name, count(subject_name) as attended_exams
    FROM examinations
    GROUP BY 1, 2
    )

SELECT a.student_id, student_name, a.subject_name, ifnull(attended_exams, 0) as attended_exams
FROM temp_01 a
    LEFT JOIN temp_02 b
    ON a.student_id = b.student_id AND a.subject_name = b.subject_name
ORDER BY 1, 3;


-- 570. Managers with at Least 5 Direct Reports
    
SELECT name
FROM employee a
        JOIN (SELECT managerID FROM employee
        WHERE managerId is not null
        GROUP BY managerId
        HAVING count(id) >= 5) b
        ON a.id = b.managerId;

-------------------------------------------
-- 1045. Customers Who Bought All Products

SELECT customer_id
FROM customer
GROUP BY customer_id
HAVING count(distinct product_key) = (SELECT count(*) FROM product);

-- 1164. Product Price at a Given Date

with temp_01 as
(
    SELECT *, max(change_date) over(partition by product_id) as std_date
    FROM products
    WHERE change_date <= str_to_date('2019-08-16', '%Y-%m-%d')
), temp_02 as
(
    SELECT *, min(change_date) over(partition by product_id) as min_date
    FROM products
)

SELECT product_id, new_price as price
FROM temp_01
WHERE change_date = std_date
UNION
SELECT product_id, 10 as price
FROM temp_02
WHERE min_date > str_to_date('2019-08-16', '%Y-%m-%d');

-- 1075. Project Employees I

SELECT project_id, round(avg(experience_years), 2) as average_years
FROM project a 
    JOIN employee b 
    ON a.employee_id = b.employee_id
GROUP BY 1;

--------------------------------
--1193. Monthly Transactions I
    
with temp_01 as
(
    SELECT *, case when state = "approved" then 1 else 0 end as state_num,
    case when state = "approved" then amount else 0 end as app_amount
    FROM transactions
)

SELECT date_format(a.trans_date, '%Y-%m') as month, a.country, count(a.trans_date) as trans_count, sum(state_num) as approved_count, sum(a.amount) as trans_total_amount, sum(app_amount) as approved_total_amount
FROM transactions a 
    JOIN temp_01 b
    ON a.id = b.id
GROUP BY 1, 2;

-- 1341. Movie Rating

with temp_01 as
(
    SELECT name, count(a.user_id) 
    FROM movierating a 
        JOIN users b 
        ON a.user_id = b.user_id
    GROUP BY a.user_id
    ORDER BY 2 desc, 1
    LIMIT 1
),
temp_02 as
(
    SELECT title, avg(rating)
    FROM movierating a
        JOIN movies b
        ON a.movie_id = b.movie_id
    WHERE YEAR(created_at) = 2020 AND MONTH(created_at) = 2
    GROUP BY 1
    ORDER BY 2 desc, 1
    LIMIT 1
)

SELECT name as results
FROM temp_01
UNION
SELECT title as results
FROM temp_02;

-- 1517. Find Users With Valid E-Mails

SELECT *
FROM users
WHERE mail REGEXP "^[a-zA-Z][a-zA-Z0-9._-]*@leetcode\\.com";

-- 1084. Sales Analysis III

with temp_01 as
(
    SELECT product_id, count(*) as sold_cnt
    FROM sales
    GROUP BY 1
    ),
temp_02 as
(
    SELECT product_id, count(*) as sold_cnt_2019_1Q
    FROM sales
    WHERE YEAR(sale_date) = 2019 AND MONTH(sale_date) IN (1,2,3)
    GROUP BY 1
    )

SELECT a.product_id, product_name
FROM temp_01 a 
    JOIN temp_02 b 
    ON a.product_id = b.product_id
    JOIN product c
    ON a.product_id = c.product_id
WHERE sold_cnt = sold_cnt_2019_1Q;

------------------------------
-- 550. Game Play Analysis IV

with temp_01 as
(
    SELECT player_id, min(event_date) over(partition by player_id) as event_date, lead(event_date) over(partition by player_id order by event_date) as return_date
    FROM activity
)

SELECT ROUND(count(distinct player_id) / (SELECT count(distinct player_id) FROM activity), 2) as fraction
FROM temp_01
WHERE datediff(return_date, event_date) <= 1;

[LeetCode 링크](https://leetcode.com/studyplan/top-sql-50/)

-- 1683. Invalid Tweets

SELECT tweet_id
FROM tweets
WHERE length(content) > 15;

-- 1661. Average Time of Process per Machine

with temp_01 as
(
    SELECT machine_id, process_id, timestamp as start_time
    FROM activity
    WHERE activity_type = "start"
), temp_02 as
(
    SELECT machine_id, process_id, timestamp as end_time
    FROM activity
    WHERE activity_type = "end"
)

SELECT a.machine_id, ROUND(sum(end_time-start_time)/count(a.process_id), 3) as processing_time
FROM temp_01 a
    JOIN temp_02 b
    ON a.machine_id = b.machine_id AND a.process_id = b.process_id
GROUP BY 1;

-----------------------
-- 577. Employee Bonus

SELECT name, bonus
FROM employee a
    LEFT JOIN bonus b
    ON a.empId = b.empId
WHERE bonus < 1000 OR bonus is null;

-- 1934. Confirmation Rate
with temp_01 as
(
    SELECT user_id, count(*) as confirm
    FROM confirmations
    WHERE action = 'confirmed'
    GROUP BY 1
    )
SELECT a.user_id, 
    case when confirm is null then 0 else ROUND(confirm/count(action), 2) end as confirmation_rate
FROM signups a 
    LEFT JOIN confirmations b
    ON a.user_id = b.user_id
    LEFT JOIN temp_01 c
    ON a.user_id = c.user_id
GROUP BY 1;

-- 620. Not Boring Movies
SELECT *
FROM cinema
WHERE id % 2 = 1 AND description <> 'boring'
ORDER BY rating desc;

-------------------------------------------------
-- 1633. Percentage of Users Attended a Contest
    
SELECT contest_id, ROUND(count(user_id) / (SELECT count(user_id) FROM users) * 100, 2) as percentage
FROM register
GROUP BY 1
ORDER BY 2 desc, 1;

-- 1211. Queries Quality and Percentage

with temp_01 as
(
    SELECT query_name, count(*) as poor_num
    FROM queries 
    WHERE rating < 3 
    GROUP BY 1
    )
SELECT a.query_name, ROUND(sum(rating / position) / count(*), 2) as quality, IFNULL(ROUND(poor_num / count(*) * 100, 2), 0) as poor_query_percentage
FROM queries a LEFT JOIN temp_01 b ON a.query_name = b.query_name
GROUP BY 1;

-- 1174. Immediate Food Delivery II

with temp_01 as
(
    SELECT *, min(order_date) over(partition by customer_id order by order_date) as first_order, (case when order_date = customer_pref_delivery_date then 1 else 0 end) as imm_or_sch
    FROM delivery
    -- GROUP BY customer_id
)
SELECT ROUND(sum(imm_or_sch) / count(*) * 100, 2) as immediate_percentage
FROM temp_01
WHERE order_date = first_order;

----------------------------------------------------------
-- 2356. Number of Unique Subjects Taught by Each Teacher

SELECT teacher_id, count(distinct subject_id) as cnt
FROM teacher
GROUP BY 1;

-- 1070. Product Sales Analysis III

-- 집계함수 사용

with temp_01 as
(
    SELECT *, min(year) as first_year
    FROM sales
    GROUP BY product_id
)

SELECT a.product_id, first_year, a.quantity, a.price
FROM sales a JOIN temp_01 b ON a.product_id = b.product_id
WHERE a.year = first_year;

-- 윈도우 함수 사용

with temp_01 as
(
    SELECT *, min(year) over(partition by product_id order by year) as first_year
    FROM sales
)

SELECT product_id, first_year, quantity, price
FROM temp_01
WHERE year = first_year;

-- 596. Classes More Than 5 Students

SELECT class
FROM (SELECT *, count(student) as cnt FROM courses GROUP BY class) a
WHERE cnt >= 5;

------------------------------
-- 619. Biggest Single Number

with temp_01 as
(
    SELECT num, count(*) as cnt
    FROM mynumbers
    GROUP BY 1
    HAVING cnt = 1
    )

SELECT IFNULL(MAX(num), null) as num
FROM temp_01;

-- 1731. The Number of Employees Which Report to Each Employee

with temp_01 as
(
    SELECT reports_to, count(employee_id) as reports_count, round(avg(age),0) as average_age
    FROM employees
    WHERE reports_to is not null
    GROUP BY 1
    )
SELECT employee_id, name, reports_count, average_age
FROM employees a 
    JOIN temp_01 b 
    ON a.employee_id = b.reports_to
ORDER BY 1;

-- 1789. Primary Department for Each Employee

with temp_01 as
(
    SELECT *, count(department_id) over(partition by employee_id) as belonged_cnt
    FROM employee
    )
SELECT employee_id, department_id
FROM temp_01
WHERE belonged_cnt = 1 or primary_flag = "Y";

----------------------------
-- 180. Consecutive Numbers
    
with temp_01 as
(
    SELECT *, lag(num) over(ORDER BY id) as lag_num, 
        lead(num) over(ORDER BY id) as lead_num
    FROM logs
    ), temp_02 as (
    SELECT id, num, lag_num, lead_num, 
        case when num = lag_num AND lag_num = lead_num then 1 else 0 end as is_con
    FROM temp_01
    )

SELECT distinct num as ConsecutiveNums
FROM temp_02
WHERE is_con = 1;

-- 1907. Count Salary Categories
    
with temp_01 as
(
    SELECT *, 
        case when income > 50000 then "High Salary" 
        when income between 20000 and 50000 then "Average Salary"
        else "Low Salary" end as category
    FROM accounts
)
SELECT 'Low Salary' as category, count(*) as accounts_count
FROM temp_01
WHERE category = 'Low Salary'
UNION
SELECT 'Average Salary', count(*)
FROM temp_01
WHERE category = 'Average Salary'
UNION
SELECT 'High Salary', count(*)
FROM temp_01
WHERE category = 'High Salary';

-- 1978. Employees Whose Manager Left the Company
    
SELECT employee_id
FROM employees
WHERE manager_id not in (SELECT employee_id FROM employees)
    AND salary < 30000
ORDER BY 1;

------------------------
-- 626. Exchange Seats
    
with temp_01 as
(
SELECT *, lag(id) over(order by id) as lag_id, IFNULL(lead(id) over(order by id), id) as lead_id
FROM seat
), temp_02 as
(SELECT id, student, case when id % 2 = 1 then lead_id else lag_id end as swap
FROM temp_01)

SELECT swap as id, student
FROM temp_02
ORDER BY 1;

-- 602. Friend Requests II: Who Has the Most Friends
    
with temp_01 as
(
    SELECT requester_id as id, count(requester_id) as cnt
    FROM requestaccepted
    GROUP BY 1
    UNION ALL
    SELECT accepter_id, count(accepter_id)
    FROM requestaccepted
    GROUP BY 1
    )
SELECT id, sum(cnt) as num
FROM temp_01
GROUP BY 1
ORDER BY 2 desc
LIMIT 1;

-- 585. Investments in 2016
    
SELECT ROUND(sum(tiv_2016), 2) as tiv_2016
FROM insurance a
WHERE tiv_2015 in (SELECT tiv_2015 FROM insurance GROUP BY 1 HAVING count(tiv_2015) > 1)
    AND (lat, lon) not in (SELECT lat, lon FROM insurance GROUP BY 1, 2 HAVING count(*) > 1);

-- 더 효율 좋은 버전
with temp_01 as
(
    SELECT tiv_2015
    FROM insurance 
    GROUP BY tiv_2015 
    HAVING count(tiv_2015) > 1
    ), temp_02 as
    (
    SELECT lat, lon
    FROM insurance 
    GROUP BY lat, lon
    HAVING count(*) > 1
    )

SELECT round(sum(tiv_2016), 2) as tiv_2016
FROM insurance
WHERE tiv_2015 in (SELECT * FROM temp_01) AND (lat, lon) not in (SELECT * FROM temp_02);

-- 185. Department Top Three Salaries
    
with temp_01 as
(
    SELECT *, dense_rank() over(partition by departmentID order by salary desc) as rnk
    FROM employee
)

SELECT b.name as Department, a.name as Employee, salary as Salary
FROM temp_01 a JOIN department b ON a.departmentId = b.id
WHERE rnk <= 3;