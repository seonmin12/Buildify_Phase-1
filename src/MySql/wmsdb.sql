DROP DATABASE IF EXISTS wmsdb;
CREATE DATABASE IF NOT EXISTS wmsdb;
USE wmsdb;

-- table 생성

CREATE TABLE `user`
(
    `client_id`       VARCHAR(255) NOT NULL,
    `user_name`       VARCHAR(20)  NOT NULL,
    `user_phone`      VARCHAR(15)  NOT NULL,
    `user_email`      VARCHAR(30)  NOT NULL,
    `user_adress`     VARCHAR(30)  NOT NULL,
    `business_number` VARCHAR(30)  NOT NULL,
    `user_enterday`   DATE         NOT NULL,
    `user_id`         VARCHAR(15)  NOT NULL,
    `user_pw`         VARCHAR(100) NOT NULL,
    `user_status`     TINYINT(1) NOT NULL,
    `user_ware_size`  DECIMAL(10, 2) NULL,
    `user_ware_use`   DECIMAL(10, 2) NULL,
    PRIMARY KEY (`client_id`)
);

CREATE TABLE `admin`
(
    `admin_number`   VARCHAR(255) NOT NULL,
    `admin_role`     VARCHAR(10)  NOT NULL,
    `admin_name`     VARCHAR(20)  NOT NULL,
    `admin_email`    VARCHAR(30)  NOT NULL,
    `admin_enterday` VARCHAR(30)  NOT NULL,
    `admin_adress`   VARCHAR(30) NULL,
    `admin_phone`    VARCHAR(15)  NOT NULL,
    `admin_id`       VARCHAR(15)  NOT NULL,
    `admin_pw`       VARCHAR(15)  NOT NULL,
    PRIMARY KEY (`admin_number`)
);

CREATE TABLE `product`
(
    `prod_id`       VARCHAR(10)    NOT NULL,
    `brand`         VARCHAR(20)    NOT NULL,
    `prod_name`     VARCHAR(30)    NOT NULL,
    `prod_price`    INTEGER NULL,
    `prod_code`     INTEGER NULL,
    `prod_category` VARCHAR(20)    NOT NULL,
    `prod_size`     DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`prod_id`)
);

CREATE TABLE `warehouse_area`
(
    `ware_id`         VARCHAR(10) NOT NULL,
    `ware_name`       VARCHAR(20) NOT NULL,
    `ware_location`   VARCHAR(10) NOT NULL,
    `ware_total_size` INT         NOT NULL DEFAULT 0,
    PRIMARY KEY (`ware_id`)
);

CREATE TABLE `inventory`
(
    `prod_id`          VARCHAR(10) NOT NULL,
    `client_id`        VARCHAR(10) NOT NULL,
    `quantity`         INT         NOT NULL DEFAULT 0,
    `ware_id`          VARCHAR(10) NOT NULL,
    `last_inbound_day` DATE NULL,
    `last_outbound_day` DATE NULL,
    PRIMARY KEY (`prod_id`)
);

CREATE TABLE `inbound`
(
    `inbound_id`      VARCHAR(255) NOT NULL,
    `prod_id`         VARCHAR(30)  NOT NULL,
    `client_id`       VARCHAR(255) NOT NULL,
    `quantity`        INT          NOT NULL,
    `Inbound_status`  INT          NOT NULL DEFAULT 0,
    `req_inbound_day` DATE NULL,
    `ware_id`         VARCHAR(10) NULL,
    PRIMARY KEY (`inbound_id`, `prod_id`)
);

CREATE TABLE `outbound`
(
    `outbound_id`      VARCHAR(30) NOT NULL,
    `prod_id`          VARCHAR(10) NOT NULL,
    `client_id`        VARCHAR(10) NOT NULL,
    `quantity`         INT         NOT NULL,
    `status`           INT         NOT NULL DEFAULT 0,
    `req_outbound_day` DATE NULL,
    `ware_id`          VARCHAR(10) NOT NULL,
    PRIMARY KEY (`outbound_id`, `prod_id`)
);

CREATE TABLE `warehouse`
(
    `ware_id`   VARCHAR(10) NOT NULL,
    `client_id` VARCHAR(10) NOT NULL,
    `prod_id`   VARCHAR(10) NOT NULL,
    `capacity`  INT         NOT NULL DEFAULT 0,
    PRIMARY KEY (`ware_id`)
);

ALTER TABLE `outbound`
    ADD CONSTRAINT `FK_product_TO_outbound_1`
        FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`);

ALTER TABLE `inbound`
    ADD CONSTRAINT `FK_product_TO_inbound_1`
        FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`);

ALTER TABLE `warehouse`
    ADD CONSTRAINT `FK_warehouse_area_TO_warehouse_1`
        FOREIGN KEY (`ware_id`) REFERENCES `warehouse_area` (`ware_id`);

-- raw data insert

insert into admin values('001', '총관리자',
                         '총관리자1', 'admin@gamil.com',
                         now(), '강남구 삼성동',
                         '01023123333', 'admin01', 'admin123!');
## id = admin01 비밀번호 = admin123

insert into user values ('3237643866','이동휘',010-7795-3546,'sale@gmail.com',
                         '경기도 고양시 덕양구','123-34-21321','2025-03-20',
                         'SalesTeam1','9b0e309f3a3b04f440a18da3f8ad41af52302bc733198ffcdd8907afe1a3b13b:5Z1wfwCSO+I42QxSc3RGpA==',
                         0,120.20,0.00);
## id = SalesTeam1 비밀번호 = !dlehdgnl3546

INSERT INTO product (prod_id, brand, prod_name, prod_price, prod_code, prod_category, prod_size)
VALUES
    ('30943a9fb3', 'LOGITECH', 'G102', 21000, 111110111, '마우스', 0.30),
    ('b780d08581', 'GALAXY', 'MainBoard100', 150000, 11010101, '메인보드', 0.30),
    ('ef76f8fa9e', 'ASUS', 'GTX 1040', 400000, 111011011, '그래픽카드', 0.20);


INSERT INTO inbound (inbound_id, prod_id, client_id, quantity, Inbound_status, req_inbound_day, ware_id)
VALUES
    ('2427ffecc3', 'ef76f8fa9e', '3237643866', 500, 0, '2025-03-20', 'ware1'),
    ('3a5472de1b', 'b780d08581', '3237643866', 100, 0, '2025-03-20', 'ware1'),
    ('8b633a6415', '30943a9fb3', '3237643866', 200, 0, '2025-03-20', 'ware1');




# user

# 1. 유저(입점업체) 회원가입 프로시저
DROP PROCEDURE IF EXISTS SignUp;
DELIMITER $$
CREATE PROCEDURE SignUp(
    IN p_user_name VARCHAR (20),
    IN p_user_phone VARCHAR (15),
    IN p_user_email VARCHAR (30),
    IN p_user_address VARCHAR (30),
    IN p_business_number VARCHAR (30),
    IN p_user_id VARCHAR (15),
    IN p_user_pw VARCHAR (100),
    IN p_user_status TINYINT(1),
    IN p_user_ware_size DECIMAL (10,2),
    IN p_user_ware_use DECIMAL (10,2),
    OUT rtncode INT
)
BEGIN
    DECLARE
        userExists INT;
    DECLARE
        generated_client_id VARCHAR(36);


    #예외 발생 시 오류 코드 반환
    DECLARE
        CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            SET
                rtncode = 500;
        END;


    # 중복된 user_id 확인
    SELECT COUNT(*)
    INTO userExists
    FROM user
    WHERE user_id = p_user_id;

    IF
        userExists > 0 THEN
        SET rtncode = 400;
    ELSE
        # UUID 생성
        SET generated_client_id = LEFT(HEX(MD5(UUID())), 10);

        INSERT INTO user (client_id, user_name, user_phone, user_email, user_adress,
                          business_number, user_enterday, user_id, user_pw,
                          user_status, user_ware_size, user_ware_use)
        VALUES (generated_client_id, p_user_name, p_user_phone, p_user_email, p_user_address,
                p_business_number, NOW(), p_user_id, p_user_pw,
                p_user_status, p_user_ware_size, p_user_ware_use);


        #삽입 성공 시 200 반환
        SET rtncode = 200;
    END IF;
END $$
DELIMITER ;

#2. 유저(입점업체) 정보 변경 프로시저
DROP PROCEDURE IF EXISTS UpdateUserinfo;
DELIMITER $$
CREATE PROCEDURE UpdateUserinfo(
    IN p_client_id VARCHAR (10),
    IN p_update_option INT,
    IN p_new_value VARCHAR (255),
    OUT rtncode INT
)
BEGIN
    DECLARE
        sql_query VARCHAR(255);


    # 예외 발생 시 오류 코드 반환
    DECLARE
        CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            SET
                rtncode = 500;
        END;


    # 잘못된 옵션 값 검증
    IF p_update_option NOT IN (1, 2, 3, 4, 5) THEN
        SET rtncode = 400;
    ELSE
        # 업데이트할 필드 선택
        CASE p_update_option
            WHEN 1 THEN SET sql_query = 'UPDATE user SET user_name = ? WHERE client_id = ?';
            WHEN 2 THEN SET sql_query = 'UPDATE user SET user_phone = ? WHERE client_id = ?';
            WHEN 3 THEN SET sql_query = 'UPDATE user SET user_email = ? WHERE client_id = ?';
            WHEN 4 THEN SET sql_query = 'UPDATE user SET user_adress = ? WHERE client_id = ?';
            WHEN 5 THEN SET sql_query = 'UPDATE user SET user_pw = ? WHERE client_id = ?';
            END
                CASE;


        # 동적 SQL 실행을 위해 변수 설정
        SET @query = sql_query;
        SET
            @p_new_value = p_new_value;
        SET
            @p_client_id = p_client_id;


        # SQL 실행
        PREPARE stmt FROM @query;
        EXECUTE stmt USING @p_new_value, @p_client_id;
        DEALLOCATE PREPARE stmt;


        # 성공 코드 반환
        SET rtncode = 200;
    END IF;
END $$
DELIMITER ;


#admin

# 1. 현재 유저에게 할당된 창고 용적률 구하는 프로시저
DROP PROCEDURE IF EXISTS useware;
DELIMITER $$
CREATE PROCEDURE useware()
BEGIN
    SELECT SUM(user.user_ware_size) AS '현재 창고 할당량'
    FROM user
    WHERE user_status = 1;
END $$
DELIMITER ;

# 2. 현재 할당 가능한 창고 면적
# 창고사이즈 - 현재 승인된 회원에게 할당중인 창고 평수 계산
DROP PROCEDURE IF EXISTS USE_AVAILABLE_WARE;
DELIMITER $$
CREATE PROCEDURE USE_AVAILABLE_WARE()
BEGIN
    DECLARE
        ware_size INT DEFAULT (SELECT warehouse_area.ware_total_size
                               FROM wmsdb.warehouse
                                        JOIN wmsdb.warehouse_area
                                             ON wmsdb.warehouse_area.ware_id = wmsdb.warehouse.ware_id);
    DECLARE
        user_use INT DEFAULT (SELECT SUM(user.user_ware_size)
                              FROM user
                              WHERE user_status = 1);
    SELECT (ware_size - user_use);
END $$
DELIMITER ;


# 3. 관리자 1명 조회 프로시저(ID 검색)
DROP PROCEDURE IF EXISTS DB_ADMIN_READONE;
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_READONE(IN admin_id_input VARCHAR (15))
BEGIN
    SELECT *
    FROM admin
    WHERE admin.admin_id = admin_id_input;
END $$
DELIMITER ;


#4. 관리자 1명 조회 프로시저(NUMBER 검색)
DROP PROCEDURE IF EXISTS DB_ADMIN_NUMBER_READONE;
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_NUMBER_READONE(IN admin_number_input VARCHAR (15))
BEGIN
    SELECT *
    FROM admin
    WHERE admin.admin_number = admin_number_input;
END $$
DELIMITER ;


# 5. 고객 1명 조회 프로시저(ID 검색)
DROP PROCEDURE IF EXISTS DB_USER_READONE;
DELIMITER $$
CREATE PROCEDURE DB_USER_READONE(IN user_id_input VARCHAR (15))
BEGIN
    SELECT *
    FROM user
    WHERE user.client_id = user_id_input;
END $$
DELIMITER ;


# 6. 고객 승인 프로시저
DROP PROCEDURE IF EXISTS DB_USER_APPROVE;
DELIMITER $$
CREATE PROCEDURE DB_USER_APPROVE(IN client_id_input VARCHAR (15))
BEGIN
    UPDATE user
    SET user_status = 1
    WHERE client_id = client_id_input;
END $$
DELIMITER ;


# 7. 고객 정보 업데이트(연락처)
DROP PROCEDURE IF EXISTS DB_USER_UPDATE_PHONE;
DELIMITER $$
CREATE PROCEDURE DB_USER_UPDATE_PHONE(IN new_phone INTEGER, IN client_id_input VARCHAR (15))
BEGIN
    UPDATE user
    SET user_phone = new_phone
    WHERE client_id = client_id_input;
END $$
DELIMITER ;


# 8. 고객 정보 업데이트(이메일)
DROP PROCEDURE IF EXISTS DB_USER_UPDATE_EMAIL;
DELIMITER $$
CREATE PROCEDURE DB_USER_UPDATE_EMAIL(IN new_email VARCHAR (30), IN client_id_input VARCHAR (15))
BEGIN
    UPDATE user
    SET user_email = new_email
    WHERE client_id = client_id_input;
END $$
DELIMITER ;


# 9. 고객 정보 업데이트(주소)
DROP PROCEDURE IF EXISTS DB_USER_UPDATE_ADDRESS;
DELIMITER $$
CREATE PROCEDURE DB_USER_UPDATE_ADDRESS(IN new_adress VARCHAR (30), IN client_id_input VARCHAR (15))
BEGIN
    UPDATE user
    SET user_adress = new_adress
    WHERE client_id = client_id_input;
END $$
DELIMITER ;


# 10. 관리자 정보 업데이트(연락처)
DROP PROCEDURE IF EXISTS DB_ADMIN_UPDATE_PHONE;
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_UPDATE_PHONE(IN new_phone INTEGER, IN admin_number_input VARCHAR (15))
BEGIN
    UPDATE admin
    SET admin_phone = new_phone
    WHERE admin_number = admin_number_input;
END $$
DELIMITER ;

# 관리자 정보 업데이트(이메일)
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_UPDATE_EMAIL(IN new_email VARCHAR(30),IN admin_number_input varchar(15))
BEGIN
    UPDATE admin SET admin_email = new_email WHERE admin_number = admin_number_input;

END $$
DELIMITER ;


# 11. 관리자 정보 업데이트(주소)
DROP PROCEDURE IF EXISTS DB_ADMIN_UPDATE_ADDRESS;
DELIMITER $$
CREATE PROCEDURE DB_ADMIN_UPDATE_ADDRESS(IN new_adress VARCHAR (30), IN admin_number_input VARCHAR (15))
BEGIN
    UPDATE admin
    SET admin_adress = new_adress
    WHERE admin_number = admin_number_input;
END $$
DELIMITER ;


#inbound

DROP PROCEDURE IF EXISTS db_inbound_allcheck_update;

DELIMITER $$
create definer = root@`%` procedure db_inbound_allcheck_update()
BEGIN
    -- 기존에 존재하는 상품이라면 수량만 업데이트
    UPDATE inventory v
        JOIN inbound i
        ON i.prod_id = v.prod_id
            AND i.client_id = v.client_id

    SET v.quantity = v.quantity + i.quantity
    WHERE i.inbound_status = 0;

-- 존재하지 않는 상품이라면 새로 추가
    INSERT INTO inventory (prod_id, client_id, quantity, ware_id, last_inbound_day)
    SELECT i.prod_id, i.client_id, i.quantity, 'ware1', now()
    FROM inbound i
    WHERE i.inbound_status = 0
      AND NOT EXISTS (
        SELECT 1 FROM inventory v
        WHERE v.prod_id = i.prod_id
          AND v.client_id = i.client_id

    );

-- 입고 상태 변경
    UPDATE inbound
    SET inbound_status = 1, ware_id = 'ware1'
    WHERE inbound_status = 0;
END;
DELIMITER ;

# 1. 상품 목록 조회 프로시저
DROP PROCEDURE IF EXISTS DB_INBOUND_INSERTLIST;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_INSERTLIST()
BEGIN
    SELECT *
    FROM product;
END $$
DELIMITER ;

# 2. 입고 요청 삽입 프로시저
DROP PROCEDURE IF EXISTS DB_INBOUND_INSERT;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_INSERT(
    IN inbound_num VARCHAR (100),
    IN prod_id VARCHAR (10),
    IN inclient_id VARCHAR (255),
    IN quantity INT,
    IN inbound_status INT,
    IN req_inbound_day DATETIME,
    IN ware_id VARCHAR (10)
)
BEGIN
    SET
        @strsql = CONCAT('INSERT INTO inbound VALUES(?,?,?,?,?,?,?)');

    SET
        @inbound_number = inbound_num;
    SET
        @prod_id = prod_id;
    SET
        @inclient_id = inclient_id;
    SET
        @quantity = quantity;
    SET
        @inbound_status = inbound_status;
    SET
        @req_inbound_day = req_inbound_day;
    SET
        @ware_id = ware_id;

    PREPARE stmt FROM @strsql;
    EXECUTE stmt USING @inbound_number, @prod_id, @inclient_id, @quantity, @inbound_status, @req_inbound_day, @ware_id;
    DEALLOCATE PREPARE stmt;

    COMMIT;
END $$
DELIMITER ;


# 3. 특정 고객의 입고 요청 검색
DROP PROCEDURE IF EXISTS DB_INBOUND_SEARCHONE;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_SEARCHONE(IN a VARCHAR (255))
BEGIN
    SET
        @strsql = CONCAT('SELECT * FROM inbound WHERE client_id = ?');
    SET
        @client_id = a;

    PREPARE stmt FROM @strsql;
    EXECUTE stmt USING @client_id;
    DEALLOCATE PREPARE stmt;

    COMMIT;
END $$
DELIMITER ;

# 4. 삭제 가능한 입고 요청 검색 (승인 대기 상태)
DROP PROCEDURE IF EXISTS DB_INBOUND_DELETESEARCH;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_DELETESEARCH(IN a VARCHAR (255))
BEGIN
    SELECT *
    FROM inbound
    WHERE inbound.Inbound_status = 0
      AND inbound.client_id = a;
END $$
DELIMITER ;

# 5. 승인 대기 상태의 고객 목록 조회
DROP PROCEDURE IF EXISTS DB_INBOUND_SEARCH_CLIENTUP;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_SEARCH_CLIENTUP()
BEGIN
    SELECT DISTINCT inbound.client_id, user.user_name
    FROM inbound
             JOIN user ON inbound.client_id = user.client_id
    WHERE inbound.Inbound_status = 0;
END $$
DELIMITER ;

# 6. 입고 요청 삭제 프로시저
DROP PROCEDURE IF EXISTS DB_INBOUND_DELETE;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_DELETE(IN inbound_number VARCHAR (30))
BEGIN
    SET
        @strsql = CONCAT('DELETE FROM inbound WHERE inbound_id = ?');
    SET
        @inbound_number = inbound_number;

    PREPARE stmt FROM @strsql;
    EXECUTE stmt USING @inbound_number;
    DEALLOCATE PREPARE stmt;

    COMMIT;
END $$
DELIMITER ;

# 7. 모든 입고 요청 조회 (승인 대기 상태)
DROP PROCEDURE IF EXISTS DB_INBOUND_ALLCHECK_READ;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_ALLCHECK_READ()
BEGIN
    SELECT *
    FROM inbound
    WHERE Inbound_status = 0;
END $$
DELIMITER ;

#8. 관리자 입고 요청 전체 승인
DROP PROCEDURE IF EXISTS DB_INBOUND_CHECK_CLIENT_READ;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_CHECK_CLIENT_READ()
BEGIN

    # 기존에 존재하는 상품이면 수량 업데이트
    UPDATE inventory v
        JOIN inbound i
        ON i.prod_id = v.prod_id
            AND i.client_id = v.client_id
    SET v.quantity = v.quantity + i.quantity
    WHERE i.inbound_status = 0;

    # 존재하지 않는 상품은 새로 추가
    INSERT INTO inventory (prod_id, client_id, quantity, ware_id, last_inbound_day)
    SELECT i.prod_id, i.client_id, i.quantity, 'ware1', NOW()
    FROM inbound i
    WHERE i.inbound_status = 0
      AND NOT EXISTS (SELECT 1
                      FROM inventory v
                      WHERE v.prod_id = i.prod_id
                        AND v.client_id = i.client_id);

    # 입고 상태 변경
    UPDATE inbound
    SET inbound_status = 1,
        ware_id        = 'ware1'
    WHERE inbound_status = 0;
END $$
DELIMITER ;

#9. 특정 고객의 승인 대기 중인 입고 요청 조회
DROP PROCEDURE IF EXISTS DB_INBOUND_CHECK_CLIENT_READ;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_CHECK_CLIENT_READ(IN a VARCHAR (255))
BEGIN
    SELECT *
    FROM inbound
    WHERE client_id = a
      AND Inbound_status = 0;
END $$
DELIMITER ;

#10. 관리자 입고 요청 업체별 승인
DROP PROCEDURE IF EXISTS DB_INBOUND_CHECK_CLIENT_UPDATE;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_CHECK_CLIENT_UPDATE(IN client_id_param VARCHAR (255))
BEGIN

    # inventory 테이블에 있는 경우 수량 업데이트
    UPDATE inventory v
        JOIN inbound i
        ON i.prod_id = v.prod_id
            AND i.client_id = v.client_id
    SET v.quantity = v.quantity + i.quantity
    WHERE i.inbound_status = 0 AND i.client_id = client_id_param;

    # inventory 테이블에 없는 경우 새로운 데이터 삽입
    INSERT INTO inventory (prod_id, client_id, quantity, ware_id, last_inbound_day)
    SELECT i.prod_id, i.client_id, i.quantity, 'ware1', NOW()
    FROM inbound i
    WHERE i.inbound_status = 0
      AND i.client_id = client_id_param
      AND NOT EXISTS (SELECT 1
                      FROM inventory v
                      WHERE v.prod_id = i.prod_id
                        AND v.client_id = i.client_id);

# inbound 상태 업데이트 (승인 완료: 0 → 1)
    UPDATE inbound
    SET Inbound_status = 1,
        ware_id        = 'ware1'
    WHERE Inbound_status = 0
      AND client_id = client_id_param;
END $$
DELIMITER ;

# 11. 모든 입고 요청 반려 (승인 대기 → 반려 상태)
DROP PROCEDURE IF EXISTS DB_INBOUND_ALLCHECK_RETURN;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_ALLCHECK_RETURN()
BEGIN
    UPDATE inbound
    SET Inbound_status = 2
    WHERE Inbound_status = 0;
END $$
DELIMITER ;

# 12. 특정 고객의 입고 요청 반려
DROP PROCEDURE IF EXISTS DB_INBOUND_CHECK_CLIENT_RETURN;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_CHECK_CLIENT_RETURN(IN a VARCHAR (255))
BEGIN
    UPDATE inbound
    SET Inbound_status = 2
    WHERE client_id = a
      AND Inbound_status = 0;
END $$
DELIMITER ;

# 13. 관리자 입고 요청 개별 승인
DROP PROCEDURE IF EXISTS DB_INBOUND_CHECK_INBOUND_NUMBER_UPDATE;
DELIMITER $$
CREATE PROCEDURE DB_INBOUND_CHECK_INBOUND_NUMBER_UPDATE(IN inbound_num_param VARCHAR (255))
BEGIN
    #기존에 inventory 테이블에 존재하는 경우 수량 업데이트
    UPDATE inventory v
        JOIN inbound i
        ON i.prod_id = v.prod_id
            AND i.client_id = v.client_id
    SET v.quantity = v.quantity + i.quantity
    WHERE i.inbound_id = inbound_num_param
      AND i.inbound_status = 0;

    #존재하지 않으면 새로 삽입
    INSERT INTO inventory (prod_id, client_id, quantity, ware_id, last_inbound_day)
    SELECT prod_id, client_id, quantity, 'ware1', NOW()
    FROM inbound i
    WHERE inbound_id = inbound_num_param
      AND inbound_status = 0
      AND NOT EXISTS (SELECT 1
                      FROM inventory v
                      WHERE v.prod_id = i.prod_id
                        AND v.client_id = i.client_id);

    #inbound_status를 1로 변경 (처리 완료)
    UPDATE inbound
    SET inbound_status = 1,
        ware_id        = 'ware1'
    WHERE inbound_id = inbound_num_param
      AND inbound_status = 0;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS db_inbound_check_inbound_number_return;

delimiter $$
create procedure db_inbound_check_inbound_number_return(in a varchar(30))
begin
    update inbound set Inbound_status = 2 where inbound_id = a and Inbound_status = 0;
end $$
delimiter ;

DROP PROCEDURE IF EXISTS db_inbound_userSearch;

delimiter $$
create procedure db_inbound_userSearch(in a varchar(255))
begin
    select * from inbound where client_id = a order by Inbound_status;
end $$
delimiter ;


#inventory

# 1. 재고 삭제 프로시저
DROP PROCEDURE IF EXISTS inventory_delete;
DELIMITER $$
CREATE PROCEDURE inventory_delete(
    IN input_prod_id VARCHAR (30),
    IN input_client_id VARCHAR (30),
    IN input_ware_id VARCHAR (30)
)
BEGIN
    DELETE
    FROM inventory
    WHERE prod_id = input_prod_id
      AND client_id = input_client_id
      AND ware_id = input_ware_id;
END $$
DELIMITER ;

#2. 카테고리별 재고 조회
DROP PROCEDURE IF EXISTS inventory_read_by_category;
DELIMITER $$
CREATE PROCEDURE inventory_read_by_category(
    IN input_category VARCHAR (50)
)
BEGIN
    SELECT i.prod_id,
           p.prod_name,
           p.prod_category,
           i.client_id,
           i.ware_id,
           i.quantity,
           i.last_inbound_day,
           i.last_outbound_day
    FROM inventory i
             JOIN product p ON i.prod_id = p.prod_id
    WHERE p.prod_category = input_category
    ORDER BY i.prod_id, i.ware_id;
END $$
DELIMITER ;

#3. 특정 고객의 재고 조회
DROP PROCEDURE IF EXISTS inventory_read_by_clientID;
DELIMITER $$
CREATE PROCEDURE inventory_read_by_clientID(
    IN input_client_id VARCHAR (100)
)
BEGIN
    SELECT i.prod_id,
           p.prod_name,
           i.client_id,
           i.ware_id,
           i.quantity,
           i.last_inbound_day,
           i.last_outbound_day
    FROM inventory i
             JOIN product p ON i.prod_id = p.prod_id
    WHERE i.client_id = input_client_id
    ORDER BY i.prod_id, i.ware_id;
END $$
DELIMITER ;

#4. 전체 재고 조회
DROP PROCEDURE IF EXISTS inventory_readAll;
DELIMITER $$
CREATE PROCEDURE inventory_readAll()
BEGIN
    SELECT i.prod_id,
           p.prod_name,
           i.client_id,
           i.ware_id,
           i.quantity,
           i.last_inbound_day,
           i.last_outbound_day
    FROM inventory i
             JOIN product p ON i.prod_id = p.prod_id
    ORDER BY i.prod_id, i.ware_id;
END $$
DELIMITER ;

#5. 특정 상품명으로 재고 조회
DROP PROCEDURE IF EXISTS inventory_readOne_productName;
DELIMITER $$
CREATE PROCEDURE inventory_readOne_productName(
    IN input_prod_name VARCHAR (100)
)
BEGIN
    SELECT i.prod_id,
           p.prod_name,
           i.client_id,
           i.ware_id,
           i.quantity,
           i.last_inbound_day,
           i.last_outbound_day
    FROM inventory i
             JOIN product p ON i.prod_id = p.prod_id
    WHERE p.prod_name = input_prod_name
    ORDER BY i.prod_id, i.ware_id;
END $$
DELIMITER ;

#6. 재고 수량 업데이트
DROP PROCEDURE IF EXISTS inventory_update_quantity;
DELIMITER $$
CREATE PROCEDURE inventory_update_quantity(
    IN input_prod_id VARCHAR (20),
    IN input_client_id VARCHAR (50),
    IN input_ware_id VARCHAR (30),
    IN newQuantity INT
)
BEGIN
    UPDATE inventory
    SET quantity = newQuantity
    WHERE prod_id = input_prod_id
      AND client_id = input_client_id
      AND ware_id = input_ware_id;

    #변경된 재고 조회
    SELECT i.prod_id,
           p.prod_name,
           i.client_id,
           i.ware_id,
           i.quantity,
           i.last_inbound_day,
           i.last_outbound_day
    FROM inventory i
             JOIN product p ON i.prod_id = p.prod_id
    ORDER BY i.prod_id, i.ware_id;
END $$
DELIMITER ;


-- inventory_backup

#1. 기존 백업 테이블 삭제 후 재생성
DROP TABLE IF EXISTS inventory_backup;
CREATE TABLE inventory_backup
(
    prod_id           VARCHAR(50),
    client_id         VARCHAR(50),
    quantity          INT,
    ware_id           VARCHAR(50),
    last_inbound_day  DATE,
    last_outbound_day DATE
);

#2. 기존 트리거 삭제 후 재생성 (삭제 전에 재고 백업)
DROP TRIGGER IF EXISTS backup_before_delete;
DELIMITER $$
CREATE TRIGGER backup_before_delete
    BEFORE DELETE
    ON inventory
    FOR EACH ROW
BEGIN
    INSERT INTO inventory_backup(prod_id,
                                 client_id,
                                 quantity,
                                 ware_id,
                                 last_inbound_day,
                                 last_outbound_day)
    VALUES (OLD.prod_id,
            OLD.client_id,
            OLD.quantity,
            OLD.ware_id,
            OLD.last_inbound_day,
            OLD.last_outbound_day);
END $$
DELIMITER ;


-- outbound

# outbound

# 1. 출고 요청 전체 승인 프로시저
DROP PROCEDURE IF EXISTS OUTBOUND_ALL_APPROVE;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ALL_APPROVE()
BEGIN
    CREATE TEMPORARY TABLE ApprovedOutbound (outbound_id VARCHAR(30));

    INSERT INTO ApprovedOutbound (outbound_id)
    SELECT outbound_id FROM outbound WHERE status = 0;

    UPDATE inventory i
        JOIN outbound o ON i.prod_id = o.prod_id
            AND i.client_id = o.client_id
            AND (i.ware_id = o.ware_id OR o.ware_id IS NULL)
    SET i.quantity = i.quantity - o.quantity
    WHERE o.outbound_id IN (SELECT outbound_id FROM ApprovedOutbound);

    UPDATE outbound
    SET status = 1
    WHERE outbound_id IN (SELECT outbound_id FROM ApprovedOutbound);

    UPDATE outbound
    SET ware_id = 'ware1'
    WHERE outbound_id IN (SELECT outbound_id FROM ApprovedOutbound)
      AND ware_id IS NULL;

    UPDATE inventory i2
        JOIN outbound o2 ON i2.client_id = o2.client_id
            AND o2.prod_id = i2.prod_id
    SET i2.last_outbound_day = NOW()
    WHERE o2.outbound_id IN (SELECT outbound_id FROM ApprovedOutbound);

    DROP TEMPORARY TABLE ApprovedOutbound;
END $$
DELIMITER ;

# 2. 출고 요청 1개 승인 프로시저 (출고 ID 기준)
DROP PROCEDURE IF EXISTS OUTBOUND_ONE_APPROVE;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_APPROVE(IN outbound_input VARCHAR(30))
BEGIN
    CREATE TEMPORARY TABLE ApprovedOutbound (outbound_id VARCHAR(30));

    INSERT INTO ApprovedOutbound (outbound_id)
    SELECT outbound_id FROM outbound WHERE status = 0 AND outbound_id = outbound_input;

    UPDATE inventory i
        JOIN outbound o ON i.prod_id = o.prod_id
            AND i.client_id = o.client_id
            AND (i.ware_id = o.ware_id OR o.ware_id IS NULL)
    SET i.quantity = i.quantity - o.quantity
    WHERE o.outbound_id IN (SELECT outbound_id FROM ApprovedOutbound);

    UPDATE outbound
    SET status = 1
    WHERE outbound_id IN (SELECT outbound_id FROM ApprovedOutbound);

    UPDATE outbound
    SET ware_id = 'ware1'
    WHERE outbound_id IN (SELECT outbound_id FROM ApprovedOutbound)
      AND ware_id IS NULL;

    UPDATE inventory i2
        JOIN outbound o2 ON i2.client_id = o2.client_id
            AND o2.prod_id = i2.prod_id
    SET i2.last_outbound_day = NOW()
    WHERE o2.outbound_id IN (SELECT outbound_id FROM ApprovedOutbound);

    DROP TEMPORARY TABLE ApprovedOutbound;
END $$
DELIMITER ;

# 3. 출고 요청 전체 승인 (클라이언트 ID 기준)
DROP PROCEDURE IF EXISTS OUTBOUND_APPROVE_BY_CLIENT;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_APPROVE_BY_CLIENT(IN client_input VARCHAR(10))
BEGIN
    CREATE TEMPORARY TABLE ApprovedOutbound (client_id VARCHAR(10));

    INSERT INTO ApprovedOutbound (client_id)
    SELECT client_id FROM outbound WHERE status = 0 AND client_id = client_input;

    UPDATE inventory i
        JOIN outbound o ON i.prod_id = o.prod_id
            AND i.client_id = o.client_id
            AND (i.ware_id = o.ware_id OR o.ware_id IS NULL)
    SET i.quantity = i.quantity - o.quantity
    WHERE o.client_id IN (SELECT client_id FROM ApprovedOutbound);

    UPDATE outbound
    SET status = 1
    WHERE client_id IN (SELECT client_id FROM ApprovedOutbound);

    UPDATE outbound
    SET ware_id = 'ware1'
    WHERE client_id IN (SELECT client_id FROM ApprovedOutbound)
      AND ware_id IS NULL;

    UPDATE inventory i2
        JOIN outbound o2 ON i2.client_id = o2.client_id
            AND o2.prod_id = i2.prod_id
    SET i2.last_outbound_day = NOW()
    WHERE o2.client_id IN (SELECT client_id FROM ApprovedOutbound);

    DROP TEMPORARY TABLE ApprovedOutbound;
END $$
DELIMITER ;

# 4. 출고 요청 1개 거절 (출고 ID 기준)
DROP PROCEDURE IF EXISTS OUTBOUND_ONE_RETURN;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_RETURN(IN outbound_input VARCHAR(30))
BEGIN
    UPDATE outbound
    SET status = 2
    WHERE status = 0
      AND outbound_id = outbound_input;
END $$
DELIMITER ;

# 5. 출고 요청 전체 거절 (클라이언트 ID 기준)
DROP PROCEDURE IF EXISTS OUTBOUND_ONE_ID_RETURN;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_ID_RETURN(IN outbound_input VARCHAR(10))
BEGIN
    UPDATE outbound
    SET status = 2
    WHERE status = 0
      AND client_id = outbound_input;
END $$
DELIMITER ;

# 6. 출고 요청 전체 조회
DROP PROCEDURE IF EXISTS OUTBOUND_ALL_SEARCH;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ALL_SEARCH()
BEGIN
    SELECT * FROM outbound;
END $$
DELIMITER ;

# 7. 출고 요청 고객별 조회 (클라이언트 ID 기준)
DROP PROCEDURE IF EXISTS OUTBOUND_ONE_SEARCH;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_ONE_SEARCH(IN client_id_input VARCHAR(10))
BEGIN
    SELECT * FROM outbound WHERE client_id = client_id_input;
END $$
DELIMITER ;

# 8. 출고 요청 취소 (고객 ID 및 출고 ID 기준)
DROP PROCEDURE IF EXISTS OUTBOUND_CANCEL;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_CANCEL(IN id VARCHAR(10), IN input VARCHAR(30))
BEGIN
    DELETE FROM outbound
    WHERE outbound_id = input
      AND client_id = id;
END $$
DELIMITER ;

# 9. 회원 출고 승인 리스트 조회
DROP PROCEDURE IF EXISTS OUTBOUND_SEARCH_APPROVE;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_SEARCH_APPROVE(IN input VARCHAR(10))
BEGIN
    SELECT * FROM outbound WHERE client_id = input AND status = 1;
END $$
DELIMITER ;

# 10. 회원 출고 거절 리스트 조회
DROP PROCEDURE IF EXISTS OUTBOUND_SEARCH_RETURN;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_SEARCH_RETURN(IN input VARCHAR(10))
BEGIN
    SELECT * FROM outbound WHERE client_id = input AND status = 2;
END $$
DELIMITER ;

# 11. 회원 출고 대기 리스트 조회
DROP PROCEDURE IF EXISTS OUTBOUND_SEARCH_PENDING;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_SEARCH_PENDING(IN input VARCHAR(10))
BEGIN
    SELECT * FROM outbound WHERE client_id = input AND status = 0;
END $$
DELIMITER ;

# 12. 클라이언트 ID 출고 요청 존재 여부 확인
DROP PROCEDURE IF EXISTS CLIENT_ID_VALIDATION;
DELIMITER $$
CREATE PROCEDURE CLIENT_ID_VALIDATION(IN input VARCHAR(10))
BEGIN
    DECLARE client_exists INT;

    SELECT COUNT(*) INTO client_exists FROM outbound WHERE client_id = input;

    IF client_exists > 0 THEN
        SELECT 'TRUE' AS result;
    ELSE
        SELECT 'FALSE' AS result;
    END IF;
END $$
DELIMITER ;

# 13. 출고 요청 번호 존재 여부 확인
DROP PROCEDURE IF EXISTS OUTBOUND_NUMBER_VALIDATION;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_NUMBER_VALIDATION(IN input VARCHAR(30))
BEGIN
    DECLARE number_exists INT;

    SELECT COUNT(*) INTO number_exists FROM outbound WHERE outbound_id = input;

    IF number_exists > 0 THEN
        SELECT 'TRUE' AS result;
    ELSE
        SELECT 'FALSE' AS result;
    END IF;
END $$
DELIMITER ;

# 14. 출고 요청 삭제
DROP PROCEDURE IF EXISTS DELETE_OUTBOUND_REQUEST;
DELIMITER $$
CREATE PROCEDURE DELETE_OUTBOUND_REQUEST(
    IN p_outboundKey VARCHAR(100),
    IN p_clientid VARCHAR(100),
    OUT p_result INT
)
BEGIN
    DELETE FROM outbound WHERE outbound_id = p_outboundKey AND client_id = p_clientid AND status = 0;

    IF ROW_COUNT() > 0 THEN
        SET p_result = 1;
    ELSE
        SET p_result = 0;
    END IF;
END $$
DELIMITER ;

# 15. 특정 고객의 출고 요청 (승인 대기) 조회
DROP PROCEDURE IF EXISTS OUTBOUND_USER_PENDING_READ;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_USER_PENDING_READ(IN p_clientId VARCHAR(100))
BEGIN
    SELECT * FROM outbound WHERE client_id = p_clientId AND status = 0;
END $$
DELIMITER ;

# 16. 특정 고객의 출고 요청 전체 조회
DROP PROCEDURE IF EXISTS OUTBOUND_USER_READ;
DELIMITER $$
CREATE PROCEDURE OUTBOUND_USER_READ(IN input_client_id VARCHAR(100))
BEGIN
    SELECT * FROM outbound WHERE client_id = input_client_id;
END $$
DELIMITER ;



