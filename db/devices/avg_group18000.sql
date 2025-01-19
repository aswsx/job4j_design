SELECT people.name, AVG(devices.price)
FROM people
         JOIN devices_people dp ON people.id = dp.people_id
         JOIN devices d ON devices_people.device_id = d.id
GROUP BY people.name
HAVING AVG(devices.price) > 18000
ORDER BY people.name;