

insert into MEMBER (m_email,m_name,m_nickname,m_mobile,m_pw
)values ('test1','김종관','아이디','010-123-1234','1234');

rollback;

select * from member;


select m_email from MEMBER 
where m_name = '김종관'
and    m_mobile = '010-9980-0553';

select m_pw from MEMBER 
where m_name = '김종관'
and    m_mobile = '010-9980-0553'
and    m_email = 'kjkwan8846@gmail.com';



select m_email,m_name,m_nickname from MEMBER 
where m_pw = 'qwe123asd'
and    m_email = 'kjkwan8846@gmail.com';

