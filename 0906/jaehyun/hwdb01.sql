

-- 1번 테이블 구성 
create table product
( 
code int(4) primary key,
pname VARCHAR(10),
price int(10));


-- 2번 상품 데이터 5개 이상 저장 
insert into product(code,pname,price)
values(1111,'삼성노트북',1000000);

insert into product(code,pname,price)
values(1112,'LG노트북',2000000);

insert into product(code,pname,price)
values(1113,'LGTV',3000000);

insert into product(code,pname,price)
values(1114,'삼성TV',4000000);

insert into product(code,pname,price)
values(1115,'삼성TV',5000000);

  
  -- 3번 15% 인하된 가격의 상품 정보를 출력 
  select code, pname, price*0.85
  from product;
 
 -- 4번 TV관련 상품을 가격을 20% 인하하여 저장  
update product
set price=price*0.8
where pname like '%TV%';


-- 5번 저장된 상품 가격의 총 금액을 출력하는 SQL 문장을 작성 
 select sum(price) as "총금액"
  from product;
