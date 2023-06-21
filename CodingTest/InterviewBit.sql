
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