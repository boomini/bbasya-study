/* 상품 정보를 저장할 products table 생성*/
CREATE TABLE products
( pcode int(4) PRIMARY KEY,
pname VARCHAR(10),
price int(10));

/* products 테이블에 상품 데이터 저장 */
insert into products (pcode, pname, price)
values ('1234', '삼성 노트북9', '1500000'),
		('3248', 'LG gram2', '1670000'),
        ('2393', '삼성 TV10', '4450000'),
        ('3950', 'LG TV3', '4400000'),
        ('9910', '삼성 비스포크', '2400000'),
        ('5421', 'LG 디오스', '2290000');

/* 15% 인하된 가격으로 상품 정보 출력*/
select pcode, pname, round((price*0.85),0) '15% dc price'
from products;

/* TV 관련 상품 가격 20% 할인 */
update products
set price = (price * 0.8)
where pname like '%TV%';

/* TV 관련 상품 가격 20% 할인하여 update 후 결과 출력*/
select * from products;

/* 저장된 상품 가격의 총 금액 출력*/
select sum(price) 'total price'
from products;
