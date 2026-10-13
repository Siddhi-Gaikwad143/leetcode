# Write your MySQL query statement below
select d.name As Department,
e.name As Employee,
e.salary As Salary
from Employee e join Department d
on e.departmentId=d.id
where(e.departmentId,e.salary)in(select departmentId,max(salary)from employee
group by departmentId);