use world;

alter database world
default character set utf8mb4 collate utf8mb4_general_ci;
-- 2. city, country, countrylanguage 테이블의 구조를 파악하시오.
select * from city;
select * from country;
select * from countrylanguage;

-- 3. country table 에서 code 가 KOR 인 자료를 조회하시오.
select * from country where code="KOR";

-- 4. country 에서 gnp 변동량(gnp-gnpold)이 양수인 국가에 대해 gnp 변동량의 오름차순으로 정렬하시오.(115 건)
select code, name, GNP, GNPOld, (GNP-GNPOld) as "gnp 변동량" from country where (GNP-GNPOld)>0 order by (GNP-GNPOld);

-- 5. country table 에서 continent 를 중복 없이 조회하시오. continent 의 길이로 정렬한다. (7 건)
select distinct continent from country;

-- 6. country 에서 asia 대륙에 속하는 국가들의 정보를 아래와 같이 출력하시오. name 으로 정렬한다.(51 건)
select concat(name,"은" , region,"에 속하며 인구는 ", population,"명 이다") as 정보  from country where Continent = "Asia" order by name; 
-- nume은 region에 속하며 인구는 population명이다.

select * from country;

-- 7. country 에서 독립 년도에 대한 기록이 없고 인구가 10000 이상인 국가의 정보를 인구의 오름차순으로 출력하시오.(29 건)
select name, continent, gnp, population from country where IndepYear is null and Population >= 10000 order by Population;

-- 8. country 에서 인구가 1 억<=x<=2 억 인 나라를 인구 기준으로 내림차순 정렬해서 상위 3 개만 출력하시오.
select code, name, Population from country where Population between 100000000 and 200000000 order by population desc LIMIT 3;

-- 9. country 에서 800, 1810, 1811, 1901, 1901 에 독립한 나라를 독립년 기준으로 오름차순 출력하시오. 단 독립 년이 같다면 code 를 기준으로 내림차순 한다.(7 건)
select code, name, indepyear from country where indepyear in (800,1810,1811,1901) order by indepyear, code desc;

-- 10. country 에서 region 에 asia 가 들어가고 name 의 두 번째 글자가 ‘o’인 정보를 출력하시오.(4 건)
select code, name, region from country where region LIKE "%asia%" and name LIKE "_o%";

-- 11. '홍길동'과 'hong'의 글자 길이를 각각 출력하시오.
select char_length('홍길동') as "한글" , char_length('hong') as "영문";

-- 12. country 에서 governmentform 에 republic 이 들어있고 name 의 길이가 10 이상인 자료를 name 길이의 내림차순으로 상위 10 개만 출력하시오. (10 건)
select code, name, governmentform from country where governmentform LIKE "%republic%" and length(name) >= 10 order by length(name) desc LIMIT 10;

-- 13. country 에서 code 가 모음으로 시작하는 나라의 정보를 출력하시오. 이때 name 의 오름차순으로 정렬하고 3 번 부터 3 개만 출력한다
select code, name from country Where code LIKE "%A%" or "%e%" or "%i%" or "%o%" or "%u%" order by name LIMIT 3,3; 

-- 14. country 에서 name 을 맨 앞과 맨 뒤에 2 글자를 제외하고 나머지는 *로 처리해서 출력하시오.(239 건)
select replace(name, substring(name,3,length(name)-4),REPEAT("*",(length(name)-4))) from country;

-- 15. country 에서 region 을 중복 없이 가져오는데 공백을 _로 대체하시오. region 의 길이로 정렬한다.(25 건
select distinct replace(region," ", "_") from country; 

select distinct region from country;

-- 16. country 에서 인구가 1 억 이상인 국가들의 1 인당 점유면적(surfacearea/population)을 반올림해서 소숫점 3 자리로 표현하시오. 1 인당 점유 면적의 오름차순으로 정렬한다.(10 건)
select name, surfacearea, population, round(surfacearea/population,3) as "1인당 점유면적"  from country where population >= 100000000 order by (surfacearea/population);

-- 17. 현재 날짜와 올해가 몇 일이 지났는지,100 일 후는 몇일인지 출력하시오.(아래는 2020 년 기준예시)
select curdate() as 오늘, DAYOFYEAR(curdate()) as "올해 지난 날", date_add(curdate(), INTERVAL 100 DAY) AS "100일 후" from dual;

-- 18. country 에서 asia 에 있는 나라 중 희망 수명이 있는 경우에 기대 수명이 80 초과면 장수국가, 60 초과면 일반국가, 그렇지 않으면 단명국가라고 표현하시오. 기대 수명으로 정렬한다.(51 건)
SELECT name, continent, LifeExpectancy,
		case when LifeExpectancy > 80 then '장수국가'
			when LifeExpectancy > 60 then "일반국가"
			else "단명국가"
		end "구분"
from country
where LifeExpectancy is not null
		and continent = "Asia"
order by LifeExpectancy;

-- 19. country 에서 (gnp-gnpold)를 gnp 향상이라고 표현하시오. 단 gnpold 가 없는 경우 신규라고 출력하고 name 으로 정렬한다.(239 건)
select name, gnp, gnpold, if(gnpold, (gnp-gnpold),"신규" ) as "gnp 향상"
from country
order by name;

-- 20. 2020 년 어린이 날이 평일이면 행복, 토요일 또는 일요일이면 불행이라고 출력하시오.
select if(DAYNAME("2020-05-05") ="Saturday" ||DAYNAME("2020-05-05") ="Sunday" , "불행", "행복");

select weekday("2020-05-05"), if((weekday("2020-05-05")>=0 and weekday("2020-05-05")<=4),"행복", "불행")  as "행복여부";
