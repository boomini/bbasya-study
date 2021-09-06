use world;
-- 1. 상품정보를 저장할 수 있는 테이블을 구성하여 보자.
-- (상품코드, 상품명, 상품가격 등
CREATE TABLE Product
	(pcode int(4) PRIMARY KEY,
     pname VARCHAR(10),
     price int(10));
     
select * from Product;

-- 2. 상품 데이터를 5개 이상 저장하는 SQL을 작성하여 보자.
-- ( 상품명에 TV, 노트북 포함 하도록 하여 5개 이상)

insert into Product values (1, "TV", 10000);
insert into Product values (2, "노트북", 20000);
insert into Product values (3, "냉장고", 40000);
insert into Product values (4, "책상", 50000);
insert into Product values (5, "데스크탑", 10000);

-- 3. 상품을 세일하려고 한다. 15% 인하된 가격의 상품 정보를 출력하세요.
select * , round((price * 0.75),0) as "할인가격" from Product;

-- 4. TV 관련 상품을 가격을 20% 인하하여 저장하세요. 그 결과를 출력하세요.
update Product set price = price * 0.8 where pname= "TV";

-- 5. 저장된 상품 가격의 총 금액을 출력하는 SQL 문장을 작성하세요.
SELECT SUM(price) from Product;

SELECT * FROM Product;

