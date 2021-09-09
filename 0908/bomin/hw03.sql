use scott;
-- 1. emp와 dept Table을 JOIN하여 이름, 급여, 부서명을 검색하세요.
-- 14
select e.ename, e.sal, d.dname
from emp e join dept d
on e.deptno = d.deptno;

-- 2. 이름이 ‘KING’인 사원의 부서명을 검색하세요.
select d.dname
from emp e join dept d
on e.deptno = d.deptno
where upper(ename) = upper("king");

-- 3. dept Table에 있는 모든 부서를 출력하고, emp Table에 있는 DATA와 JOIN하여 모든 사원의 이름, 부서번호, 부서명, 급여를 출력 하라.
-- 15
select a.ename, a.sal, a.ename, d.deptno, d.dname
from dept d left join (
					select e.ename, e.sal, d.dname, d.deptno
					from emp e join dept d
					on e.deptno = d.deptno
                    ) a
on d.deptno = a.deptno;

-- 4. emp Table에 있는 empno와 mgr을 이용하여 서로의 관계를 다음과 같이 출력되도록 쿼리를 작성하세요. ‘SCOTT의 매니저는 JONES이다’.
-- 13
select concat(e.ename, "의 매니저는 ", m.ename, "이다.")
from emp e join emp m 
on e.mgr = m.empno;

-- 5. 'SCOTT'의 직무와 같은 사람의 이름, 부서명, 급여, 직무를 검색하세요.
-- 2
select e.ename, d.dname, e.sal, e.job
from emp e join dept d
on e.deptno = d.deptno
where job = (
				select job
				from emp
				where upper(ename) = upper("scott")
                );
        
-- 6. 'SCOTT’가 속해 있는 부서의 모든 사람의 사원번호, 이름, 입사일, 급여를 검색하세요.
-- 5
select empno, ename, hiredate, sal
from emp
where deptno = (
				select deptno
				from emp
				where upper(ename) = upper("scott")
				)
;

-- 7. 전체 사원의 평균급여보다 급여가 많은 사원의 사원번호, 이름,부서명, 입사일, 지역, 급여를 검색하세요
-- 6
select e.ename, d.dname, e.hiredate, d.loc, e.sal
from emp e join dept d
on e.deptno = d.deptno
where e.sal > (
				select avg(sal)
				from emp
				)
;

-- 8. 30번 부서와 같은 일을 하는 사원의 사원번호, 이름, 부서명,지역, 급여를 급여가 많은 순으로 검색하세요
-- 11
select e.empno, e.ename, d.dname, d.loc, e.sal
from emp e join dept d
on e.deptno = d.deptno
where job in (
				select distinct job
				from emp 
				where deptno = "30"
				)
order by sal desc;

-- 9. 10번 부서 중에서 30번 부서에는 없는 업무를 하는 사원의 사원번호, 이름, 부서명, 입사일, 지역을 검색하세요.
-- 1
select e.empno, e.ename, d.dname, d.loc, e.hiredate
from emp e join dept d
on e.deptno = d.deptno
where job not in (
				select distinct job
				from emp 
				where deptno = "30"
				)
		and e.deptno = "10"
order by sal desc;

select * from emp where deptno = "10";
select * from emp where ename = "SCOTT";
select * from emp where deptno = "30";

-- 10.‘KING’이나 ‘JAMES'의 급여와 같은 사원의 사원번호, 이름,급여를 검색하세요.
-- 2
select ename, sal
from emp
where sal in (
				select sal
				from emp
                where upper(ename) = upper("king")
					or upper(ename) = upper("james")
				);

-- 11. 급여가 30번 부서의 최고 급여보다 높은 사원의 사원번호,이름, 급여를 검색하세요.
-- 4
select empno, ename, sal
from emp
where sal > (
			select max(sal) from emp where deptno = 30
			)
;

-- 12. 이름 검색을 보다 빠르게 수행하기 위해 emp 테이블 ename에 인덱스를 생성하시오.
create index index_name
oN emp(ename);

SHOW index from emp;


-- 13. 이름이 'ALLEN'인 사원의 입사연도가 같은 사원들의 이름과 급여를 출력하세요.
-- 10
select ename, sal
from emp
where date_format(hiredate, "%y") = (
				select date_format(hiredate, "%y")
                from emp
                where upper(ename) = upper("ALLEN")
                );

SELECT HIREDATE , ENAME FROM EMP order by hiredate;
-- 14 부서별 급여의 합계를 출력하는 View를 작성하세요.
create view checksum
as (
	select d.dname, ifnull(sum(sal),0) 급여합계
    from emp e right join dept d
    on e.deptno = d.deptno
    group by d.deptno
	);
    
select * from checksum;

-- 15 14번에서 작성된 View를 이용하여 부서별 급여의 합계가 큰 1~3순위를 출력하세요
select * from checksum order by 급여합계 desc limit 3 ;