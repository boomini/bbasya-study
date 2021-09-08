use world;


-- 1번 도시명 kabul이 속한 국가의 이름은?

select c2.code , c2.name
from city c1 inner join country c2
on c1.CountryCode=c2.code
where c1.name='kabul';

-- 2번 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가이름으로 오름차순 정렬한다(8건)

select c2.name, c1.Language, c1.Percentage
from countrylanguage c1 inner join country c2
on c1.CountryCode = c2.code
where c1.Percentage= 100.0 and c1.IsOfficial =true
order by c2.name;


-- 3번 도시명 amsterdam 에서 사용되는 주요 언어와 amsterdam이 속한 국가는?

select c1.name , c3.Language  , c2.Name
from city c1 inner join country c2
on c1.CountryCode= c2.Code
inner join countrylanguage c3
on c2.Code= c3.CountryCode
where c1.name='amsterdam'  and c3.IsOfficial =true;


-- 4번 국가명이 united로 시작하는 국가의 정보와 수도의 이름 , 인구를 함께 출력하시오
-- 단 수도 정보가 없다면 출력하지 않는다 (3건)

select c2.name, c2.capital , c1.name as "수도", c1.population as "수도인구"
from city c1 inner join country c2
on c1.CountryCode = c2.code and c2.capital=c1.ID
where c2.Capital is not null and c2.name like 'United%';


-- 5번 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오
-- 단 수도 정보가 없다면 수도 없으라고 출력한다 (4건)

select c1.name, c1.capital , 
case when c1.Capital is null then '수도없음'
    else c2.name
    end as "수도"
, case when c1.Capital is null then '수도없음'
    else c2.population
    end as "수도인구"
from country c1 left outer join city c2 
on c1.Code= c2.CountryCode and c1.capital = c2.id
where c1.name like 'United%';



-- 6번 국카 코드 che의 공식 언어 중 가장 사용률이 높은 언어보다 사용율이 높은 공식언어를 사용
-- 하는 국가는 몇 곳인가?

select count(code)
from country c inner join countrylanguage l
on c.code= l.CountryCode
where l.IsOfficial='T' and  l.percentage > (select max(Percentage)
						from countrylanguage 
						where Countrycode='che' and IsOfficial='T'
					);
                    
                    
-- 7번 국가명 'south korea'의 공식언어는?

select l.Language
from country c inner join  countrylanguage l
on c.code=l.countrycode
where l.IsOfficial='T' and c.name='South Korea';

-- 8번 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오 (3건)

select c1.name, c1.code ,count(c2.id)
from country c1 inner join city c2
on c2.CountryCode= c1.code
where c1.name like 'Bo%'
group by c1.name;

-- 9번 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오.
-- 도시가 없을 경우는 단독이라고 표시 (4건)
   

	select c1.name, case when 
	c2.ID is null then '단독'
	else count(c2.ID)
	end as '도시개수'
	from country c1 left outer join city c2
	on c1.code= c2.CountryCode
	where c1.name like 'Bo%'
	group by c1.name;


-- 10번 인구가 가장 많은 도시는 어디인가?

	
	select countrycode, name, population
	from city
	where Population= ( select max(population)   
						from city
						);
                        
-- 11번 가장 인구가 작은 도시의 이름, 인구수,국가를 출력하시오
   

	select c2.name, c2.code, c1.name, c1.population
    from city c1 Inner join country c2
    on c1.countrycode=c2.code
    where c1.Population = (
							select min(Population)
                            from city
                            );
                            
-- 12번 KOR의 seoul보다 인구가 많은 도시들을 출력하시오


   select countrycode, name, population
   from city
   where population > (  select population
   from city
   where name='Seoul' and countrycode =( select code
					   from country
                        where name='South Korea'));
   

-- 13번 san Miguel 이라는 도시에 사는 사람들이 사용하는 공식언어는?

select CountryCode, language
from countrylanguage
where IsOfficial='T'  and countrycode in (select countrycode
			 from city
             where name='San Miguel');
   

-- 14번 국가 코드와 해당 국가의 최대 인구를 출력하시오 . 국가코드로 정렬한다(232건)

select CountryCode,max(population) as "max_pop"
from city
group by countryCode;


-- 15번 국가별로 가장 인구가 많은 도시의 정보를 출력하시오(232건)

select CountryCode,name, max(population) as population
from city
group by countryCode;


-- 16번 국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오(239건)

select c2.code , c2.name, c1.name, max(c1.population) as population
from city c1 right outer join country c2
on c1.countrycode=c2.code
group by c2.code;


-- 17번 위 쿼리의 내용이 자주 사용된다. 재사용을 위해 쿼리의 내용을 summary라는 이름의 view로 생성하시오

create view summary as
select c2.code as code, c2.name as co_name, c1.name as ci_name, max(c1.population) as 
population
from city c1 right outer join country c2
on c1.countrycode=c2.code
group by c2.code;


-- 18번 summary에서 KOR의 대표도시를 조회하시오

select code, co_name, ci_name, population
from summary
where ci_name='Seoul';

