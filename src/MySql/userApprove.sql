
use wmsdb;
# 현재 유저에게 할당된 창고 용적률 구하는 프로시저
DELIMITER $$
CREATE PROCEDURE useware()
BEGIN
    SELECT sum(user.user_ware_size) as '현재 창고 할당량'
    FROM user
    WHERE user_status = 1;
end $$
DELIMITER ;

# 현재 할당 가능한 창고 면적
# 창고사이즈 - 현재 승인된 회원에게 할당중인 창고 평수 계산
DELIMITER $$
CREATE PROCEDURE USE_AVAILABLE_WARE()
BEGIN
    DECLARE ware_size INT DEFAULT (select warehouse_area.ware_total_size
                                   from wmsdb.warehouse join wmsdb.warehouse_area
                                                             on wmsdb.warehouse_area.ware_id = wmsdb.warehouse.ware_id);
    DECLARE user_use INT DEFAULT (SELECT sum(user.user_ware_size)
                                  FROM user
                                  WHERE user_status = 1);
    SELECT (ware_size - user_use);
end $$
DELIMITER ;


call USE_AVAILABLE_WARE();


# USE_AVAILABLE_WARE() 프로시저 테스트용 DATA
INSERT INTO warehouse_area (ware_id, ware_name, ware_location, ware_size)
VALUES ('WH001', '메인창고', '서울A구역', 10000);

INSERT INTO warehouse (ware_id, client_id, prod_id, quantity, last_inbound_day, last_outbount_day)
VALUES ('WH001', 'C001', 'P001', 500, '2025-03-10', NULL);
