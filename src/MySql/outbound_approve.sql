use wmsdb;
select *
from outbound;

#출고 요청 전체 승인 프로시저
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ALL_APPROVE()
BEGIN
    UPDATE outbound SET outbound.status = 1 where outbound.status = 0;
end $$
DELIMITER ;

#출고 요청 1개 승인 프로시저(출고아이디기준)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_APPROVE(IN outbound_input VARCHAR(30))
BEGIN
    UPDATE outbound SET outbound.status = 1 where outbound.status = 0
                                              and outbound.outbound_number = outbound_input;
end $$
DELIMITER ;

#출고 요청 1개 승인 프로시저(클라이언트 ID 기준)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_ID_APPROVE(IN outbound_input VARCHAR(10))
BEGIN
    UPDATE outbound SET outbound.status = 1 where outbound.status = 0
                                              and outbound.client_id = outbound_input;
end $$
DELIMITER ;

#출고 요청 1개 거절 프로시저(출고아이디기준)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_RETURN(IN outbound_input VARCHAR(30))
BEGIN
    UPDATE outbound SET outbound.status = 2 where outbound.status = 0
                                              and outbound.outbound_number = outbound_input;
end $$
DELIMITER ;

#출고 요청 1개 거절 프로시저(클라이언트 ID 기준)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_ID_RETURN(IN outbound_input VARCHAR(10))
BEGIN
    UPDATE outbound SET outbound.status = 2 where outbound.status = 0
                                              and outbound.client_id = outbound_input;
end $$
DELIMITER ;

#출고 요청 테이블 전체 조회
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ALL_SEARCH()
BEGIN
    SELECT * FROM outbound;
end $$
DELIMITER ;

#출고 요청 테이블 고객별 조회(클라이언트 ID 검색)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_SEARCH(IN client_id_input VARCHAR(10))
BEGIN
    SELECT * FROM outbound
    WHERE outbound.client_id = client_id_input;
end $$
DELIMITER ;

#출고 요청 취소(고객본인메뉴,로그인 객체 id 매칭 및 출고번호 검색)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_CANCEL(IN id VARCHAR(10),IN input VARCHAR(30))
BEGIN
    DELETE FROM outbound
    WHERE outbound.outbound_number = input
    and outbound.client_id = id;
end $$
DELIMITER ;

#회원 출고 승인 리스트 조회(클라이언트 아이디 검색)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_SEARCH_APPROVE(IN input VARCHAR(10))
BEGIN
   SELECT *
       FROM outbound
           WHERE client_id = input
   and status = 1;
end $$
DELIMITER ;

#회원 출고 거절 리스트 조회(클라이언트 아이디 검색)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_SEARCH_RETURN(IN input VARCHAR(10))
BEGIN
    SELECT *
    FROM outbound
    WHERE client_id = input
      and status = 2;
end $$
DELIMITER ;

#회원 출고 대기 리스트 조회(클라이언트 아이디 검색)
DELIMITER $$
CREATE PROCEDURE OUTBOUND_SEARCH_PENDING(IN input VARCHAR(10))
BEGIN
    SELECT *
    FROM outbound
    WHERE client_id = input
      and status = 0;
end $$
DELIMITER ;