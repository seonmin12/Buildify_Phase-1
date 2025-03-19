use wmsdb;

DELIMITER //

CREATE PROCEDURE UpdateUserinfo(
    IN p_client_id VARCHAR(10),
    IN p_update_option INT,
    IN p_new_value VARCHAR(255),
    OUT rtncode INT
)
BEGIN
    DECLARE sql_query VARCHAR(255);

    -- 동적 SQL을 위한 변수 선언
    DECLARE v_query TEXT;

    -- 예외 발생 시 오류 코드 반환
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            SET rtncode = 500; -- SQL 오류 발생 시 500 반환
        END;

    -- 잘못된 옵션 값 검증
    IF p_update_option NOT IN (1, 2, 3, 4, 5) THEN
        SET rtncode = 400; -- 잘못된 입력
    ELSE
        -- 업데이트할 필드 선택
        CASE p_update_option
            WHEN 1 THEN SET sql_query = 'UPDATE user SET user_name = ? WHERE client_id = ?';
            WHEN 2 THEN SET sql_query = 'UPDATE user SET user_phone = ? WHERE client_id = ?';
            WHEN 3 THEN SET sql_query = 'UPDATE user SET user_email = ? WHERE client_id = ?';
            WHEN 4 THEN SET sql_query = 'UPDATE user SET user_adress = ? WHERE client_id = ?';
            WHEN 5 THEN SET sql_query = 'UPDATE user SET user_pw = ? WHERE client_id = ?';
            END CASE;

        -- 동적 SQL 실행을 위해 변수 설정
        SET @query = sql_query;
        SET @p_new_value = p_new_value;
        SET @p_client_id = p_client_id;

        -- SQL 실행
        PREPARE stmt FROM @query;
        EXECUTE stmt USING @p_new_value, @p_client_id;
        DEALLOCATE PREPARE stmt;

        -- 성공 코드 반환
        SET rtncode = 200;
    END IF;
END //

DELIMITER ;

