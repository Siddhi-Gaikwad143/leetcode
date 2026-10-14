# Write your MySQL query statement below
select d.name As Department,
e.name As Employee,
e.salary As Salary
from Employee e
Join Department d
on e.departmentId=d.id
where 3>(select count(distinct e2.salary)from Employee e2 where e2.departmentId=e.departmentId and e2.salary>e.salary);