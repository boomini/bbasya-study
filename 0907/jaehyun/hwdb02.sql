
-- 1번 부서위치가 CHICAGO인 모든 사원에 대해 이름, 업무, 급여 출력하는 SQL을 작성하시오 

select ename,job,sal
from emp
where deptno = ( 
                   select deptno
	      from dept
	      where loc='CHICAGO'
                  );
                  

-- 2번 부하직원이 없는 사원의 사원번호, 이름 , 업무 , 부서번호 출력하는 SQL을 작성하시오

select  empno,ename,job, deptno
from emp
where empno not in (
                  select mgr 
                  from EMP
                  WHERE mgr is not null
                  );
               


-- 3번 BLAKE와 같은 상사를 가진 사원의 이름 ,업무 ,상사번호 출력하는 SQL을 작성
  select ename, job, mgr
  from EMP
  where mgr=   ( 
                select mgr 
                from EMP
                WHERE ename='BLAKE'
                );
                
-- 4번 입사일이 가장 오래된 사람 5명을 검색하세요
select ename, hiredate
from EMP
order by hiredate
Limit 5;

-- 5번 JONES 의 부하 직원의 이름, 업무 , 부서명을 검색하세요
select e.ename, e.job, d.dname
from emp e inner join dept d
on e.deptno=d.deptno
where mgr= ( select empno
           from emp
          where ename='JONES'
            );
                
                
                     
