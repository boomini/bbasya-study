use scott;

select * from emp;
select * from dept;

-- 1. emp와 dept Table을 JOIN하여 이름, 급여, 부서명을 검색하세요.
select e.ENAME, e.SAL, d.DNAME
from emp e join dept d
on e.DEPTNO = d.DEPTNO;

-- 2. 이름이 ‘KING’인 사원의 부서명을 검색하세요.
select d.DNAME
from emp e join dept d
on e.DEPTNO = d.DEPTNO
where e.ENAME = 'KING';

-- 3. dept Table에 있는 모든 부서를 출력하고, emp Table에 있는 DATA와 JOIN하여
-- 모든 사원의 이름, 부서번호, 부서명, 급여를 출력 하라.
select e.ENAME, e.DEPTNO, d.DNAME, e.SAL 
from dept d left outer join emp e
on d.DEPTNO = e.DEPTNO;

-- 4. emp Table에 있는 empno와 mgr을 이용하여 서로의 관계를 다음과 같이 출력되도록 쿼리를 작성하세요. 
-- ‘SCOTT의 매니저는 JONES이다’.
select concat(e.ENAME, '의 매니저는 ', m.ENAME, '이다.') "사원 - 매니저"
from emp e join emp m
on e.MGR = m.EMPNO;

-- 5. 'SCOTT'의 직무와 같은 사람의 이름, 부서명, 급여, 직무를 검색하세요.
select e.ENAME, d.DNAME, e.SAL, e.JOB
from emp e join dept d
on e.DEPTNO = d.DEPTNO
where e.JOB = ( select e.JOB from emp e where e.ENAME = 'SCOTT') and e.ENAME != 'SCOTT';

-- 6. 'SCOTT’가 속해 있는 부서의 모든 사람의 사원번호, 이름, 입사일, 급여를 검색하세요.
select e.EMPNO, e.ENAME, e.HIREDATE, e.SAL
from emp e
where e.DEPTNO = (select e.DEPTNO from emp e where e.ENAME = 'SCOTT');

-- 7. 전체 사원의 평균급여보다 급여가 많은 사원의 사원번호, 이름,부서명, 입사일, 지역, 급여를 검색하세요.
select e.EMPNO, e.ENAME, d.DNAME, e.HIREDATE, d.LOC, e.SAL
from emp e join dept d
on e.DEPTNO = d.DEPTNO
where e.SAL > (select avg(e.SAL) from emp e)
order by e.SAL desc;

-- 8. 30번 부서와 같은 일을 하는 사원의 사원번호, 이름, 부서명,지역, 급여를 급여가많은 순으로 검색하세요.
select e.EMPNO, e.ENAME, d.DNAME, d.LOC, e.SAL
from emp e join dept d
on e.DEPTNO = d.DEPTNO
where e.JOB in (select e.JOB from emp e where e.DEPTNO = 30)
order by e.SAL desc;

-- 9. 10번 부서 중에서 30번 부서에는 없는 업무를 하는 사원의 사원번호, 이름, 부서명, 입사일, 지역을 검색하세요.
select e.EMPNO, e.ENAME, d.DNAME, e.HIREDATE, d.LOC
from emp e join dept d
on e.DEPTNO = d.DEPTNO
where e.DEPTNO = 10 and e.JOB not in (select e.JOB from emp e where e.DEPTNO =30);

-- 10.‘KING’이나 ‘JAMES'의 급여와 같은 사원의 사원번호, 이름,급여를 검색하세요.
select e.EMPNO, e.ENAME, e.SAL
from emp e
where e.SAL in (select e.SAL from emp e where e.ENAME = 'KING' or e.ENAME = 'JAMES');

-- 11. 급여가 30번 부서의 최고 급여보다 높은 사원의 사원번호,이름, 급여를 검색하세요.
select e.EMPNO, e.ENAME, e.SAL
from emp e
where e.SAL > (select e.SAL from emp e where e.DEPTNO=30 order by e.SAL desc limit 1)
order by e.SAL;

-- 12. 이름 검색을 보다 빠르게 수행하기 위해 emp 테이블 ename에 인덱스를 생성하시오.
create index name
on emp (ENAME);

-- 13. 이름이 'ALLEN'인 사원의 입사연도가 같은 사원들의 이름과 급여를 출력하세요.
select e.ENAME, e.SAL
from emp e
where year(e.HIREDATE) = (select year(e.HIREDATE) from emp e where e.ENAME = 'ALLEN');

-- 14. 부서별 급여의 합계를 출력하는 View를 작성하세요.
create view SumSalary as
select d.DNAME, sum(e.SAL) "SALSUM"
from emp e right outer join dept d
on e.DEPTNO = d.DEPTNO
group by e.DEPTNO;

drop view SumSalary;
select * from SumSalary;

-- 15. 14번에서 작성된 View를 이용하여 부서별 급여의 합계가 큰 1~3순위를 출력하세요.
select *
from SumSalary
order by SALSUM desc
limit 3;