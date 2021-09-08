-- 1번 EMP와 DEPT TABLE을 JOIN 하여 이름, 급여, 부서명을 검색하세요 

select e.ENAME , e.SAL , d.DNAME 
from emp e inner join dept d
on e.DEPTNO=d.DEPTNO;

-- 2번 이름이 ‘KING’인 사원의 부서명을 검색하세요.

select d.DNAME
from emp e inner join dept d
on e.DEPTNO=d.DEPTNO
where e.ENAME='KING';

-- 3번 dept Table에 있는 모든 부서를 출력하고, emp Table에 있는 DATA와 JOIN하여
-- 모든 사원의 이름, 부서번호, 부서명, 급여를 출력 하라.

select e.ENAME, e.SAL, d.DNAME
from emp e right outer join dept d
on e.DEPTNO=d.DEPTNO;

-- 4번 emp Table에 있는 empno와 mgr을 이용하여 서로의 관계를 다음과 같이 출력되도
-- 록 쿼리를 작성하세요. ‘SCOTT의 매니저는 JONES이다’.

select concat(e.ename,'의 매니저는',m.ename,'이다')
from emp m inner join emp e
on m.EMPNO= e.MGR;

-- 5번 'SCOTT'의 직무와 같은 사람의 이름, 부서명, 급여, 직무를 검색하세요.

select e.ename, d.DNAME, e.sal, e.JOB
from emp e inner join dept d
on e.DEPTNO=d.DEPTNO
where  e.JOB =( select JOB
		       from emp
			    where ename='SCOTT');

-- 6번 'SCOTT’가 속해 있는 부서의 모든 사람의 사원번호, 이름, 입사일, 급여를 검색하세요.
 select EMPNO,ENAME,HIREDATE,SAL
 from emp
 where deptno= ( select deptno
				from emp
                 where ename='SCOTT');
                 
-- 7번 전체 사원의 평균급여보다 급여가 많은 사원의 사원번호, 이름,부서명, 입사일, 지역, 급여를 검색하세요.

select e.EMPNO, e.ename, d.DNAME, e.HIREDATE, d.LOC, e.sal
from emp e inner join dept d
on e.DEPTNO= d.deptno
where sal > ( select avg(sal)
                from emp
                );
                
-- 8번  30번 부서와 같은 일을 하는 사원의 사원번호, 이름, 부서명,지역, 급여를 급여가많은 순으로 검색하세요.

select  e.EMPNO,e.ENAME, d.DNAME, d.LOC, e.sal
from emp e inner join dept d
on e.DEPTNO= d.DEPTNO
where  e.job in (select JOB
				from emp
				where deptno=30)
order by e.sal desc;

-- 9번  10번 부서 중에서 30번 부서에는 없는 업무를 하는 사원의 사원번호, 이름, 부서명, 입사일, 지역을 검색하세요.

select e.EMPNO, e.ENAME, d.DNAME, e.HIREDATE, d.LOC, e.DEPTNO
from emp e inner join dept d
on e.deptno= d.DEPTNO
where e.DEPTNO=10 and JOB NOT IN (select JOB
								from emp
								where deptno=30);




-- 10번 ‘KING’이나 ‘JAMES'의 급여와 같은 사원의 사원번호, 이름,급여를 검색하세요.

  select EMPNO, ENAME, sal
  from emp
  where sal in (
                 select sal
                from emp
                where ename= 'JAMES' or ename= 'KING'
				 
				);
                
                
-- 11번   급여가 30번 부서의 최고 급여보다 높은 사원의 사원번호,이름, 급여를 검색하세요.

select EMPNO, ENAME, sal
from emp
where sal > ( select max(sal)
              from emp
			   where DEPTNO=30);
-- 12번 이름 검색을 보다 빠르게 수행하기 위해 emp 테이블 ename에 인덱스를 생성하시오.
CREATE INDEX name
On emp (ename);
 
 -- 13번 이름이 'ALLEN'인 사원의 입사연도가 같은 사원들의 이름과 급여를 출력하세요.

	select *
    from emp
    where year(HIREDATE) = ( select year(HIREDATE)
					  from emp
					   where ename='ALLEN');
                       
                       select *
                       from emp
                       order by HIREDATE;
                       
  -- 14번  부서별 급여의 합계를 출력하는 View를 작성하세요.
    
  create view sum as
  select deptno,sum(sal) as total
  from emp
  group by deptno;
  
  -- 15번  14번에서 작성된 View를 이용하여 부서별 급여의 합계가 큰 1~3순위를 출력하세요.
  
  select *
  from sum
  order by total desc;
  
  

  

               
