SELECT t1.name AS "Boys", t2.name AS "Girls"
FROM teens t1
         CROSS JOIN teens t2
WHERE t1.gender != t2.gender
  AND t1.gender LIKE 'm';