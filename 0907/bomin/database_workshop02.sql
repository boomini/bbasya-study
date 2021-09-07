use world;


select * from city;
select * from countrylanguage;
select * from country;

-- 1. 도시명 kabul 이 속한 국가의 이름은?
select ci.CountryCode, co.Name
from city ci join country co
on ci.CountryCode = co.Code
where ci.Name = "kabul";

-- 2. 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가 이름으로 오름차순 정렬한다.(8 건)
select co.name, cl.language, cl.percentage
from Country co join countrylanguage cl
on co.Code = cl.CountryCode
where cl.percentage = "100.0"
and cl.isOfficial = "T";

-- 3. 도시명 amsterdam 에서 사용되는 주요 언어와 amsterdam 이 속한 국가는?
select ci.name, cl.language ,co.name
from city ci join countrylanguage cl
on ci.countryCode = cl.countryCode
and cl.IsOfficial = "T"
join country co
on ci.countryCode = co.Code
where ci.name = "amsterdam" ;

-- 4. 국가명이 united 로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 출력하지 않는다. (3 건)

select co.name, ci.id, ci.name 수도, ci.population 수도인구
from country co join city ci
on co.capital = ci.id
where co.name LIKE "United%";

-- 5. 국가명이 united 로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 수도 없음이라고 출력한다. (4 건)
select co.name, ci.id, ifnull(ci.name,"수도없음") 수도, ifnull(ci.population,"수도없음") 수도인구
from country co left outer join city ci
on co.capital = ci.id
where co.name LIKE "United%";

-- 6. 국가 코드 che 의 공식 언어 중 가장 사용률이 높은 언어보다 사용율이 높은 공식언어를 사용하는국가는 몇 곳인가?
select count(*)
from countrylanguage
where percentage > (
					select max(percentage) from countrylanguage
					where upper(countrycode) = upper("che")
					)
	and isofficial = "T";

-- 7. 국가명 south korea 의 공식 언어는?
select language
from countrylanguage
where countrycode = (
					select code
					from country
					where upper(name) = upper("south korea")
					)
		and isofficial = "T";

-- 8. 국가 이름이 bo 로 시작하는 국가에 속한 도시의 개수를 출력하시오. (3 건
select  co.name, ci.countrycode, count(*) 도시개수
from city ci join country co
on ci.Countrycode = co.code 
where countrycode in (
					SELECT code
					FROM country
					where name like "bo%"
					)
group by countrycode;



-- 9. 국가 이름이 bo 로 시작하는 국가에 속한 도시의 개수를 출력하시오. 도시가 없을 경우는 단독이라고 표시한다.(4 건

select a.name, a.code, if(count(ci.id)=0,"단독",count(ci.id)) 도시개수
from (
		select distinct name, code
		from country
		where name like "bo%"
		) a
        left join city ci
        on a.code = ci.countrycode
group by a.code;

-- 10. 인구가 가장 많은 도시는 어디인가?
select countrycode, name, population 
from city
where population = (select max(population) from city);


-- 11. 가장 인구가 적은 도시의 이름, 인구수, 국가를 출력하시오.
select country.name, city.countrycode, city.name, city.population 
from city join country
on city.countrycode = country.code
where city.population = (select min(population) from city);

-- 12. KOR 의 seoul 보다 인구가 많은 도시들을 출력하시오.
select countrycode,  name, population
from city
where population > (
					select population
					from city 
					where upper(name)= upper("seoul")
                    );

-- 13. San Miguel 이라는 도시에 사는 사람들이 사용하는 공식 언어는?
select countrycode, language
from countrylanguage
where countrycode in (
					select countrycode
					from city
					where upper(name) = upper("San miguel")
					)
	and isofficial = "T";
    
-- 14. 국가 코드와 해당 국가의 최대 인구수를 출력하시오. 국가 코드로 정렬한다.(232 건
select countrycode, max(population)
from city
group by countrycode;

select * from city where countrycode = "ABW";

-- 15. 국가별로 가장 인구가 많은 도시의 정보를 출력하시오. 국가 코드로 정렬한다.(232 건)
select countrycode, name, population
from city
where (countrycode, population) in (
			select countrycode, max(population) 
			from city c1 
			group by countrycode
            )
order by countrycode;


-- 16. 국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오.(239 건
select co.code, co.name, a.name, a.population
from country co left join (
							select countrycode, name, population
							from city
							where (countrycode, population) in (
										select countrycode, max(population) 
										from city c1 
										group by countrycode
										)
							order by countrycode
                            ) a
on co.code = a.countrycode;

-- 17. 위 쿼리의 내용이 자주 사용된다. 재사용을 위해 위 쿼리의 내용을 summary 라는 이름의 view 로생성하시오.
create view summary as (select co.code 국가코드, co.name 나라, a.name 지역, a.population 인구
from country co left join (
							select countrycode, name, population
							from city
							where (countrycode, population) in (
										select countrycode, max(population) 
										from city c1 
										group by countrycode
										)
							order by countrycode
                            ) a
on co.code = a.countrycode);

select * from summary;


-- 18. summary 에서 KOR 의 대표도시를 조회하시오
select * from summary where 국가코드 = "KOR";