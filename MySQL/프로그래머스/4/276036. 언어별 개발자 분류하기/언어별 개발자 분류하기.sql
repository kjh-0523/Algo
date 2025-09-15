-- 코드를 작성해주세요

# SELECT NAME, CATEGORY, CONV(CODE, 10, 2)
# FROM SKILLCODES;
SELECT 
    CASE 
        WHEN SUM(S.CATEGORY = 'Front End') > 0
        AND SUM(S.NAME = 'Python') > 0
        THEN 'A'
        
        WHEN SUM(S.NAME = 'C#') > 0
        THEN 'B'
        
        WHEN SUM(S.CATEGORY = 'Front End') > 0
        THEN 'C'
    END AS GRADE,
    D.ID, D.EMAIL
FROM DEVELOPERS D
JOIN SKILLCODES S
  ON (D.SKILL_CODE & S.CODE) > 0
GROUP BY D.ID, D.EMAIL
HAVING GRADE IS NOT NULL
ORDER BY GRADE, D.ID
