drop table product;

-- 1.상품정보를 저장할 수 있는 테이블을 구성하여 보자
CREATE TABLE Product(
pcode int(4) PRIMARY KEY,
pname VARCHAR(30),
pkind VARCHAR(10),
price int(10));

-- 2. 상품 데이터를 5 개 이상 저장하는 SQL 을 작성하여 보자
-- (상품명에 TV, 노트북 포함 하도록 하여 5 개 이상)
Insert into product (pcode, pname, pkind, price)
values('1','Samsung Smart TV','TV','400000'),
	('2','LG HD TV','TV','300000'),
    ('3','Samsung Smart 노트북','노트북','1000000'),
    ('4','LG Gram 노트북','노트북','920000'),
    ('5','Apple MACBOOK 노트북','노트북','1100000');

select *
from product;

-- 3. 상품을 세일하려고 한다 . 15% 인하된 가격의 상품 정보를 출력하세요 
select *,(price*0.85) "할인된 가격"
from product;

--  4. TV관련 상품을 가격을 20% 인하하여 저장하세요. 그 결과를 출력하세요
update product set price = price*0.8
where pname like '%TV%';

select *
from product;

-- 5. 저장된 상품 가격의 총 금액을 출력하는 SQL 문장을 작성하세요
select sum(price) "총 금액"
from product; 