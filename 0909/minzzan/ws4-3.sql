use ssafybook;

select *
from book;

# 데이터 입력
insert into book
values(111,"조용준","자바 프로그래밍", 25000,"자바 기본","java.jpg"),
	  (222,"사무국","즐거운 싸피생활", 0, "싸피 생활 안내", "ssafy.jpg");

insert into user
values("hong","홍길동",1234,"jang","장길산"),
	  ("jang","장길산",2345,null,null),
      ("lim","임꺽정",3456,"jang","장길산");

insert into satisfaction
values(111,10,"좋은 책","hong"),
	  (111,8,"도움됨","jang"),
      (111,9,"재밋다","lim"),
      (222,10,"강추","hong"),
      (222,9,"핵 재미","lim");
      
      commit;
      
# 확인용 출력
select *
from book,satisfaction,user
where book.isbn = satisfaction.isbn
and user.id = satisfaction.id
order by book.isbn;

desc book;
desc satisfaction;
desc user;
