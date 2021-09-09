use scott;

select * from emp;
select * from dept;

-- 1. 부서위치가 CHICAGO인 모든 사원에 대해 이름,업무,급여 출력하는 SQL을 작성하세요
select e.ENAME, e.JOB, e.SAL
from emp e inner join dept d
on e.DEPTNO = d.DEPTNO
where d.LOC = 'CHICAGO';

-- 2. 부하직원이 없는 사원의 사원번호,이름,업무,부서번호 출력하는 SQL을 작성하세요.
select m.EMPNO, m.ENAME, m.JOB, m.DEPTNO
from emp m left outer join emp e
on m.EMPNO = e.MGR
where e.EMPNO is null;

-- 3. BLAKE와 같은 상사를 가진 사원의 이름,업무,상사번호 출력하는 SQL을 작성하세요.
select e.ENAME, e.JOB, e.MGR
from emp e
where e.MGR = ( select e.MGR from emp e where e.ENAME = 'BLAKE') and e.ENAME != 'BLAKE';

-- 4. 입사일이 가장 오래된 사람 5명을 검색하세요.
select *
from emp e
order by e.HIREDATE
limit 5;

-- 5. JONES 의 부하 직원의 이름, 업무, 부서명을 검색하세요.
select e.ENAME, e.JOB, d.DNAME
from emp e join dept d
on e.DEPTNO = d.DEPTNO
where e.MGR = ( select e.EMPNO from emp e where e.ENAME = 'JONES');
