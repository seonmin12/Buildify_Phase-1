use wmsdb;
select * from inventory;

drop table if exists inventory_backup;

-- 백업테이블 생성
CREATE TABLE inventory_backup (
                                  prod_id VARCHAR(50),
                                  client_id VARCHAR(50),
                                  quantity INT,
                                  ware_id VARCHAR(50),
                                  last_inbound_date DATE,
                                  last_outbound_date DATE
);


-- 재고 삭제시 정보를 백업테이블에 기록하는 트리거 생성
DELIMITER $$

CREATE TRIGGER backup_before_delete
BEFORE DELETE ON inventory
FOR EACH ROW
BEGIN

INSERT INTO inventory_backup(
                             prod_id ,
                             client_id ,
                             quantity ,
                             ware_id ,
                             last_inbound_date ,
                             last_outbound_date
                            )

    values (
            OLD.prod_id,
            OLD.client_id,
            OLD.quantity,
            OLD.ware_id,
            OLD.last_inbound_date,
            OLD.last_outbound_date

              );


end $$


DELIMITER ;


-- test
INSERT INTO inventory
VALUES (
           'P001',
           'C001',
           100,
           'W001',
           '2025-03-01',
           '2025-03-10'
       );

-- 삭제
DELETE FROM inventory WHERE prod_id = 'P001';

-- 백업 테이블 확인
SELECT * FROM inventory_backup;


