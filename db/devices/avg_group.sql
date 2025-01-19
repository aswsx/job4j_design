SELECT people.name, AVG(devices.price)
FROM people
         JOIN devices_people ON people.id = devices_people.people_id
         JOIN devices ON devices_people.device_id = devices.id
GROUP BY people.name
ORDER BY people.name;