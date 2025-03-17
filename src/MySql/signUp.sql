USE wmsdb;

DROP PROCEDURE IF EXISTS SignUp;

DELIMITER //

CREATE PROCEDURE SignUp(
    IN p_user_name VARCHAR(20),
    IN p_user_phone VARCHAR(15),
    IN p_user_email VARCHAR(30),
    IN p_user_address VARCHAR(30),
    IN p_business_number VARCHAR(30),
    IN p_user_id VARCHAR(15),
    IN p_user_pw VARCHAR(15),
    IN p_user_status TINYINT(1),
    IN p_user_ware_size DECIMAL(10,2),
    IN p_user_ware_use DECIMAL(10,2),
    OUT rtncode INT  -- 반환 코드
)
BEGIN
    DECLARE userExists INT;
    DECLARE generated_client_id VARCHAR(36);  -- UUID를 저장할 변수

    -- 예외 발생 시 오류 코드 반환
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            SET rtncode = 500;  -- 기타 SQL 오류 발생 시 500 반환
        END;

    -- 중복된 user_id 확인
    SELECT COUNT(*) INTO userExists FROM user WHERE user_id = p_user_id;

    IF userExists > 0 THEN
        SET rtncode = 400;  -- 중복된 user_id가 존재하면 실패 (400);
    ELSE
        -- UUID 생성
        SET generated_client_id = UUID();

        INSERT INTO user (
            client_id, user_name, user_phone, user_email, user_adress,
            business_number, user_enterday, user_id, user_pw,
            user_status, user_ware_size, user_ware_use
        ) VALUES (
                     generated_client_id, p_user_name, p_user_phone, p_user_email, p_user_address,
                     p_business_number, NOW(), p_user_id, p_user_pw,
                     p_user_status, p_user_ware_size, p_user_ware_use
                 );

        -- 삽입 성공 시 200 반환
        SET rtncode = 200;
    END IF;
END //

DELIMITER ;

ALTER TABLE user
    MODIFY COLUMN business_number VARCHAR(30) NOT NULL;
