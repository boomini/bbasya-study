use world;

select *
from city;

select *
from country
order by name;

select *
from countrylanguage;

select @@autocommit;
set autocommit = false;


-- 1. country에서 전체 자료의 수와 독립 연도가 있는 자료의 수를 각각 출력하시오.
select count(*) 전체, count(IndepYear) "독립 연도 보유"
from country;

-- 2. country에서 기대 수명의 합계, 평균, 최대, 최소를 출력하시오. 평균은 소수점 2자리로 반올림한다.
select sum(lifeexpectancy) 합계, round(avg(lifeexpectancy),2) 평균, max(lifeexpectancy) 최대,min(lifeexpectancy) 최소
from country;

-- 3. country에서 continent 별 국가의 개수와 인구의 합을 구하시오. 국가 수로 정렬 처리한다.(7건)
select continent, count(code),sum(population)
from country
group by continent
order by count(code) desc;

-- 4. country에서 대륙별 국가 표면적의 합을 구하시오. 면적의 합으로 내림차순 정렬하고 상위 3건만 출력한다.
select continent, sum(SurfaceArea) '표면적 합'
from country
group by Continent
order by sum(SurfaceArea) desc
limit 0,3;

-- 5. country에서 대륙별로 인구가 50,000,000이상인 나라의 gnp 총 합을 구하시오. 합으로 오름차순 정렬한다.(5건)
select continent, sum(gnp) 'gnp 합'
from country
where Population>=50000000
group by Continent
order by sum(gnp);

-- 6. country에서 대륙별로 인구가 50,000,000이상인 나라의 gnp 총 합을 구하시오. 이때 gnp의 합이 5,000,000 이상인 것만 구하시오.
select continent, sum(gnp) 'gnp 합'
from country
where Population>=50000000
group by Continent
having sum(gnp)>=5000000;

-- 7. country에서 연도별로 10개 이상의 나라가 독립한 해는 언제인가?
select indepyear, count(country.code) "독립 국가 수"
from country
where indepyear is not null
group by indepyear
having count(country.code)>=10;

-- 8. country에서 국가별로 gnp와 함께 전세계 평균 gnp, 대륙 평균 gnp를 출력하시오.(239건)
select continent, name, gnp, avg(gnp) over() "전세계 평균", avg(gnp) over(partition by continent) "대륙 평균" 
from country;

-- 9. countrylanguage에 countrycode='AAA', language='외계어', isOfficial='F', percentage=10을 추가하시오.
-- 값을 추가할 수 없는 이유를 생각하고 필요한 부분을 수정해서 다시 추가하시오.
insert into countrylanguage
values ('AAA', '외계어', 'F','10');
# countrycode는 country 테이블을 참조하는  F.K이므로 country 테이블에 있는 값만 사용할 수 있다.
insert into countrylanguage values('KOR', '외계어', 'F', 10);

-- 10. countrylanguage에 countrycode='ABW', language='Dutch', isOfficial='F', percentage=10을 추가하시오. # 값을 추가할 수 없는 이유를 생각하고 필요한 부분을 수정해서 다시 추가하시오.
insert into country
values('ABW', 'Dutch', 'F', 10);
# countrycode와 language가 조합키로 P.K를 구성하므로 이미 있는 값은 추가될 수 없다.
insert into countrylanguage values('ABW', 'Dutch2', 'F', 10);

-- 11. country에 다음 자료를 추가하시오. # Code='TCode', Region='TRegion',Code2='TC' 
# 값을 추가할 수 없는 이유를 생각하고 필요한 부분을 수정해서 다시 추가하시오.
insert into country
values('Tcode','Tregion','TC');
# Code는 최대 3글자만 들어갈 수 있고 name은 not null이고 default가 없으므로 값이 꼭 필요하다.
insert into country (Code, name, Region,  Code2) values('TC1','Tname', 'TRegion', 'TC');

-- 12. city에서 id가 2331인 자료의 인구를 10% 증가시킨 후 조회하시오.
update city
set population = population*1.1
where id=2331;

select *
from city
where id = 2331;

-- 13. country에서 code가 'USA'인 자료를 삭제하시오. 
# 삭제가 안되는 이유를 생각하고 성공하려면 어떤 절차가 필요한지 생각만 하시오.
delete 
from country
where code='USA';
# country의 code는 F.K로 참조되고 있으므로 먼저 참조하고 있는 테이블의 데이터를 삭제후 country 데이터를 삭제해야 한다.

-- 14. 이제 까지의 DML 작업을 모두 되돌리기 위해 rollback 처리하시오.
rollback;

-- 15. ssafy_ws_5th라는 이름으로 새로운 schema를 생성하시오.
use ssafy_ws_5th;

-- 16. 만약 user라는 테이블이 존재한다면 삭제하시오.
DROP DATABASE IF EXISTS user;

-- 17. ssafy_ws_5th에 다음 조건을 만족하는 테이블을 생성하시오. # 테이블명: user
create table user(
	id varchar(50) primary key,
    name varchar(100) not null default'익명',
    pass varchar(100) not null
);
desc user;

-- 18. user 테이블에 다음의 자료를 추가하시오.
insert into user 
values('ssafy', '1234', '김싸피'), ('hong','5678','홍길동'), ('test','test','테스트');

-- 19. id가 test인 계정의 pass를 id@pass 형태로 변경하고 조회하시오.
update user 
set pass = concat(id,'@',pass)
where id = 'test';

select *
from user
where id = 'test';

-- 20. id가 test인 계정의 자료를 삭제하고 조회하시오.
delete 
from user
where id = 'test';

select *
from user;

-- 21. 현재까지의 내용을 영구 저장하기 위해서 commit 처리하시오.
commit;

