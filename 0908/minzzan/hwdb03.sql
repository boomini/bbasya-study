use scott;

select *
from emp;


-- 1. emp 와 dept Table 을 JOIN 하여 이름 , 급여 , 부서명을 검색하세요
select ename, sal, DNAME
from emp join dept
where emp.deptno = dept.deptno;

-- 2.이름이 KING’ 인 사원의 부서명을 검색하세요
select dname
from dept
where deptno = (select deptno
				from emp
				where ename = 'KING');

-- 3.dept Table 에 있는 모든 부서를 출력하고 , emp Table 에 있는 DATA 와 JOIN 하여
-- 모든 사원의 이름 , 부서번호 , 부서명 , 급여를 출력 하라
# outer join 하기
select ename, emp.deptno, dept.DNAME, emp.SAL
from dept left outer join emp
on dept.DEPTNO = emp.deptno;

-- 4.emp Table 에 있는 empno 와 mgr 을 이용하여 서로의 관계를 다음과 같이 출력되도록 쿼리를 작성하세요 . ‘SCOTT 의 매니저는 JONES 이다’
select concat(e.ename,'의 매니저는', m.ename,'이다')
from emp e, emp m
where e.mgr = m.empno;

-- 5.'scott' 의 직무와 같은 사람의 이름 , 부서명 , 급여 , 직무를 검색하세요
select ename, DNAME, sal, job
from emp, dept
where emp.DEPTNO = dept.DEPTNO
and emp.job = (
select job
from emp
where ename = 'scott');

-- 6.'scott' 가 속해 있는 부서의 모든 사람의 사원번호 , 이름 , 입사일 , 급여를 검색하세요
select empno, ename, hiredate, sal
from emp
where deptno = (
select deptno
from emp
where ename = 'scott');

-- 7.전체 사원의 평균급여보다 급여가 많은 사원의 사원번호 , 이름 부서명 , 입사일 , 지역 , 급여를 검색하세요
select empno, ename, hiredate, dept.loc, sal
from emp,dept
where emp.deptno = dept.deptno
and sal> (select avg(sal) from emp);


-- 8. 30 번 부서와 같은 일을 하는 사원의 사원번호 , 이름 , 부서명, 지역 , 급여를 급여가 많은 순으로 검색하세요
select empno, ename, dept.dname, dept.loc, sal
from emp, dept
where emp.deptno = dept.deptno
and job in (
select job
from emp
where deptno = 30)
order by SAL desc;

-- 9. 10 번 부서 중에서 30 번 부서에는 없는 업무를 하는 사원의 사원번호 , 이름 , 부서명 , 입사일 , 지역을 검색하세요
select empno, ename, dept.dname, hiredate, dept.loc
from emp, dept
where emp.deptno = dept.deptno
and emp.deptno = 10
and job not in(
select job
from emp
where deptno = 30);

-- 10. KING’ 이나 JAMES' 의 급여와 같은 사원의 사원번호 , 이름 급여를 검색하세요
select empno, ename, sal
from emp
where sal in (select sal
			from emp
			where ename = 'KING'
            or ename = 'JAMES');

-- 11. 급여가 30 번 부서의 최고 급여보다 높은 사원의 사원번호 이름 , 급여를 검색하세요
select ename, sal
from emp
where sal>(
select max(sal)
from emp
where deptno = 30);

-- 12. 이름 검색을 보다 빠르게 수행하기 위해 emp 테이블 ename 에 인덱스를 생성하시오
create index idx_ename on emp (ename);

-- 13. 이름이 'ALLEN' 인 사원의 입사연도가 같은 사원들의 이름과 급여를 출력하세요
select ename, sal 
from emp
where date_format(hiredate,'%Y') = (select date_format(hiredate,'%Y')
									from emp
									where ename = 'ALLEN');

-- 14. 부서별 급여의 합계를 출력하는 View 를 작성하세요
drop view sal_view;

create view sal_view as
select sum(sal), DEPTNO
from emp
group by deptno;
 
select *
from sal_view;

-- 15. 14 번에서 작성된 View 를 이용하여 부서별 급여의 합계가 큰 1~3 순위를 출력하세요
select *
from sal_view
order by 'sum(sal)' desc;
