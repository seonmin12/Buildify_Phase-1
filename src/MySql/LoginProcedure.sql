use wmsdb;

# 관리자 1명 조회 프로시저(ID 검색)
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_READONE(IN admin_id_input varchar(15))
BEGIN
    select *
    from admin
    where admin.admin_id = admin_id_input;

END $$
DELIMITER ;