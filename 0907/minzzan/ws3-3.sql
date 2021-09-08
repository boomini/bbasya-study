use world;

select *
from city;

select *
from country
order by name;

select *
from countrylanguage;

-- 1. 도시명 kabul이 속한 국가의 이름은?
select city.CountryCode, country.name
from city, country
where city.countrycode = country.code
and city.Name = 'kabul';

-- 2. 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가 이름으로 오름차순 정렬한다.(8건)
select country.name, countrylanguage.Language, countrylanguage.Percentage
from countrylanguage, country
where countrylanguage.countrycode = country.code
and countrylanguage.percentage = 100
and countrylanguage.IsOfficial = 'T'
order by country.name;

-- 3. 도시명 amsterdam에서 사용되는 주요 언어와 amsterdam이 속한 국가는?
select city.name, Language,country.Name
from countrylanguage, country, city
where countrylanguage.CountryCode = country.Code 
and countrylanguage.countrycode = city.CountryCode
and IsOfficial = 'T' 
and city.name = 'amsterdam';

-- 4. 국가명이 united 로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 
-- 단 수도 정보가 없다면 출력하지 않는다. (3 건) -- 헷갈림
select co.name, co.Capital, ci.name 수도, ci.population 수도인구
from country co join city ci
on co.capital = ci.id
where co.name LIKE "United%";

-- 5. 국가명이 united 로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 
-- 단 수도정보가 없다면 수도 없음이라고 출력한다. (4 건)
select co.name, co.Capital, ifnull(ci.name, '수도없음') 수도, ifnull(ci.population, '수도없음') 수도인구 
from country co left outer join city ci
on co.capital = ci.id
where co.name LIKE "United%";

-- 6. 국가 코드 che 의 공식 언어 중 가장 사용률이 높은 언어보다 사용율이 높은 공식언어를 사용하는
-- 국가는 몇 곳인가?
select count(distinct countryCode)
from countrylanguage
where IsOfficial='T'
and Percentage>(select percentage
				from countrylanguage
				where CountryCode='che'
				and IsOfficial = 'T'
				order by percentage desc
				limit 0,1);

-- 7. 국가명 south korea 의 공식 언어는?
select language
from countrylanguage, country
where countrylanguage.CountryCode = country.code
and country.Name = 'south korea'
and countrylanguage.IsOfficial='T';

-- 8. 국가 이름이 bo 로 시작하는 국가에 속한 도시의 개수를 출력하시오. (3 건)
select country.name, countrycode code, count(country.name)
from city, country
where city.CountryCode = country.code
and country.Name like 'bo%'
group by country.name;

-- 9. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. 도시가 없을 경우는 단독 이라고 표시한다.(4건)
select country.name, country.code, if(count(city.name)='0','단독',count(city.name)) 도시개수
from city right outer join country
on city.CountryCode = country.code
where country.Name like 'bo%'
group by country.name;

-- 10. 인구가 가장 많은 도시는 어디인가?
select countrycode, name, population
from city
order by population desc
limit 0,1;

-- 11. 가장 인구가 적은 도시의 이름, 인구수, 국가를 출력하시오.
select country.name, country.code, city.name, city.population
from city, country
where city.CountryCode = country.code
order by population
limit 0,1;

-- 12. KOR의 seoul보다 인구가 많은 도시들을 출력하시오.
select countrycode, name,Population
from city
where population > (select Population
					from city
					where city.CountryCode='KOR'
                    and city.name = 'seoul');
                    
-- 13. San Miguel 이라는 도시에 사는 사람들이 사용하는 공식 언어는?
select countrycode, Language
from countrylanguage
where isofficial = 'T'
and countrycode in (select countrycode
from city
where name = 'San Miguel');

-- 14. 국가 코드와 해당 국가의 최대 인구수를 출력하시오. 국가 코드로 정렬한다.(232건)
select countrycode, max(Population) max_pop
from city
group by countrycode
order by countrycode;

-- 15. 국가별로 가장 인구가 많은 도시의 정보를 출력하시오. 국가 코드로 정렬한다.(232건)
select countrycode, name, max(Population) max_pop
from city
group by countrycode
order by countrycode;

-- 16. 국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오.(239건) -- 몰라
select country.code, country.name,city.name, max(city.population)
from city right outer join country
on city.countrycode = country.code
group by city.countrycode;

select co.code, co.name, a.name, a.population
from country co left join (
                            select countrycode, name, max(population) population 
                            from city c1 
                            group by countrycode
                            order by countrycode
                            ) a
on co.code = a.countrycode;

-- 17. 위 쿼리의 내용이 자주 사용된다. 재사용을 위해 위 쿼리의 내용을 summary라는 이름의 view로 생성하시오.
drop view summary;

create view summary as
select co.code, co.name co_name, a.name ci_name, a.population
from country co left join (
                            select countrycode, name, max(population) population 
                            from city c1 
                            group by countrycode
                            order by countrycode
                            ) a
on co.code = a.countrycode;

select *
from summary;

-- summary에서 KOR의 대표도시를 조회하시오.
select *
from summary
where code = 'KOR';


-- 제작문제 -- 
-- 정부체제(GovernmentForm)가 Republic이고 인구수가 1000만 이상인 국가의 수도 이름, 수도 인구, 공식 언어를 함께 출력하시오.
select city.name, city.Population, countrylanguage.Language
from city join countrylanguage
where countrylanguage.IsOfficial = 'T' and
city.CountryCode = countrylanguage.CountryCode and
city.countrycode in (
select Code
from country
where governmentform = 'Republic' and population>= 10000000);

-- Europe에 속한 국가들 중 독립 년도가 빠른 국가 상위 5개를 출력하시오.

-- 공식 언어가 English인 국가 중 언어 사용율이 0.0인 국가의 수도 인구수를 출력하시오.


