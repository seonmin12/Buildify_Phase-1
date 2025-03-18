use wmsdb;

DROP PROCEDURE DB_ADMIN_READONE;

# 관리자 1명 조회 프로시저(ID 검색)
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_READONE(IN admin_id_input varchar(15))
BEGIN
    select *
    from admin
    where admin.admin_id = admin_id_input;

END $$
DELIMITER ;

# 관리자 1명 조회 프로시저(NUMBER 검색)
DROP PROCEDURE DB_ADMIN_NUMBER_READONE;
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_NUMBER_READONE(IN admin_number_input varchar(15))
BEGIN
    select *
    from admin
    where admin.admin_number = admin_number_input;

END $$
DELIMITER ;

# 고객 1명 조회 프로시저(ID 검색)
DELIMITER $$
CREATE PROCEDURE DB_USER_READONE(IN user_id_input varchar(15))
BEGIN
    select *
    from user
    where user.client_id = user_id_input;

END $$
DELIMITER ;


# 고객 승인 프로시저
DELIMITER $$
CREATE PROCEDURE DB_USER_APPROVE(IN client_id_input varchar(15))
BEGIN
    UPDATE user SET user_status = 1 WHERE client_id = client_id_input;

END $$
DELIMITER ;
DROP PROCEDURE DB_USER_UPDATE_PHONE;
# 고객 정보 업데이트(연락처)
DELIMITER $$
CREATE PROCEDURE DB_USER_UPDATE_PHONE(IN new_phone INTEGER,IN client_id_input varchar(15))
BEGIN
    UPDATE user SET user_phone = new_phone WHERE client_id = client_id_input;

END $$
DELIMITER ;

# 고객 정보 업데이트(이메일)
DELIMITER $$
CREATE PROCEDURE DB_USER_UPDATE_EMAIL(IN new_email varchar(15),IN client_id_input varchar(15))
BEGIN
    UPDATE user SET user_email = new_email WHERE client_id = client_id_input;

END $$
DELIMITER ;

# 고객 정보 업데이트(주소)
DELIMITER $$
CREATE PROCEDURE DB_USER_UPDATE_ADDRESS(IN new_adress varchar(15),IN client_id_input varchar(15))
BEGIN
    UPDATE user SET user_adress = new_adress WHERE client_id = client_id_input;

END $$
DELIMITER ;

UPDATE admin SET admin_phone = ? WHERE admin_number = ?;
UPDATE admin SET admin_email = ? WHERE admin_number = ?;
UPDATE admin SET admin_adress = ? WHERE admin_number = ?;

# 관리자 정보 업데이트(연락처)
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_UPDATE_PHONE(IN new_phone INTEGER,IN admin_number_input varchar(15))
BEGIN
    UPDATE admin SET admin_phone = new_phone WHERE admin_number = admin_number_input;

END $$
DELIMITER ;

# 관리자 정보 업데이트(이메일)
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_UPDATE_EMAIL(IN new_email VARCHAR(30),IN admin_number_input varchar(15))
BEGIN
    UPDATE admin SET admin_email = new_email WHERE admin_number = admin_number_input;

END $$
DELIMITER ;

# 관리자 정보 업데이트(주소)
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_UPDATE_address(IN new_adress VARCHAR(30),IN admin_number_input varchar(15))
BEGIN
    UPDATE admin SET admin_adress = new_adress WHERE admin_number = admin_number_input;

END $$
DELIMITER ;
