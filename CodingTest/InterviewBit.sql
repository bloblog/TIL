
**** SQL Programming - InterviewBit ****
(https://www.interviewbit.com/courses/databases/sql-queries)

-- 5'th Highest Mark
-- 윈도우 함수 지원 안됨
    
SELECT MIN(MARKS) as MARKS
FROM (SELECT MARKS
    FROM STUDENTS
    ORDER BY MARKS desc
    LIMIT 5) A;
    
-- Chess Tournament

SELECT Name
FROM Matches a
    JOIN Players b
    ON a.Id = b.Id
GROUP BY a.Id
HAVING sum(Result) >= 1 
    AND count(*) - sum(Result) <= 1;
    
-- I hate Vowels

SELECT Name
FROM (SELECT Id, Name, 
        CASE WHEN LEFT(Name, 1) IN ('A','E','I','O','U') THEN LEAST(Marks1, Marks2)
        ELSE GREATEST(Marks1, Marks2) END as Mark
    FROM Students
    ORDER BY Mark desc) A;

-- Birthdays
-- GROUP_CONCAT 함수 사용하기
SELECT GROUP_CONCAT(Name) as Names
FROM Students
GROUP BY BirthDate
ORDER BY BirthDate;

-- Minimum GPA

SELECT CONCAT(DepartmentName, "," ,Name, ",", GPA) as A
FROM (SELECT DepartmentId, MIN(GPA) as min_gpa
    FROM Students
    GROUP BY 1) a
    LEFT JOIN Students b 
    ON a.DepartmentId = b.DepartmentId AND a.min_gpa = b.GPA
    LEFT JOIN Departments c
    ON b.DepartmentId = c.DepartmentId
ORDER BY DepartmentName;
    
-- Number of offers

SELECT IFNULL(cnt, 0) as Job_Offers
FROM Students a
    LEFT JOIN (SELECT Id, count(*) as cnt
                FROM Jobs
                WHERE MONTH(Date) = 11
                GROUP BY Id) b 
    ON a.Id = b.Id 
ORDER BY a.Id;

-- Movie Character

SELECT CONCAT(director_first_name, director_last_name) as director_name, movie_title
FROM movies a
    JOIN movies_directors b
    ON a.movie_id = b.movie_id
    JOIN directors c 
    ON b.director_id = c.director_id
WHERE a.movie_id IN (SELECT movie_id
                    FROM movies_cast
                    WHERE role = "SeanMaguire");
                        
-- Short Films

SELECT movie_title, movie_year
        , concat(director_first_name, director_last_name) as director_name
    , concat(actor_first_name, actor_last_name) as actor_name, role 
FROM movies a
    JOIN movies_directors b
    ON a.movie_id = b.movie_id
    JOIN directors c
    ON b.director_id = c.director_id
    JOIN movies_cast d
    ON a.movie_id = d.movie_id
    JOIN actors e
    ON d.actor_id = e.actor_id   
WHERE movie_time = (SELECT MIN(movie_time)
                    FROM movies);
    
-- Study Deletion

DELETE FROM STUDY WHERE Age between 19 and 22;
SELECT *
FROM STUDY;
    
-- Actors and their Movies

SELECT movie_title
FROM movies a
    JOIN movies_cast b
    ON a.movie_id = b.movie_id
WHERE actor_id IN (SELECT actor_id
                    FROM movies_cast
                    GROUP BY actor_id
                    HAVING count(movie_id) >= 2);

-- Tournament 2.0

SELECT ROUND(sum(Result)/count(Result), 4) as `Percentage Wins`
FROM Matches a
    LEFT JOIN Team1 b
    ON a.PlayerId1 = b.Id 
    LEFT JOIN Team2 c
    ON a.PlayerId2 = c.Id
WHERE c.Cheater = 0
GROUP BY Date
ORDER BY Date;

-- Top Performer

SELECT (CASE WHEN Rating < 6 THEN NULL ELSE Name END) as Names, Rating
FROM EMPLOYEE a
    JOIN EVALUATION b
    ON a.Points BETWEEN b.Lower AND b.Upper
ORDER BY Rating desc, Name;

-- Same Countries

SELECT a.Name as "Labourer1", b.Name as "Labourer2", a.Country as "Country"
FROM LABOURERS a
    JOIN LABOURERS b
    ON a.Country = b.Country AND a.ID != b.ID
ORDER BY 1, 2;
    

-- Role Ordering
-- 참고 사이트 (https://schatz37.tistory.com/12)

SELECT MIN(CASE WHEN Role = 'Healer' THEN Player END) as `MIN(Healer)`,
    MIN(CASE WHEN Role = 'Attacker' THEN Player END) as `MIN(Attacker)`,
    MIN(CASE WHEN Role = 'Defender' THEN Player END) as `MIN(Defender)`,
    MIN(CASE WHEN Role = 'Tactician' THEN Player END) as `MIN(Tactician)`
FROM (select a.Role, a.Player, 1
        , sum(1) as idx 
    from GAMERS a 
        join GAMERS b 
        on (a.Role = b.Role and a.Player >= b.Player)
    group by 1,2,3) A
GROUP BY idx;
