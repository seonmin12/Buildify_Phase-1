-- warehouse랑 inventory 동기화 트리거
-- insert trigger: 창고에 입고되면 재고 추가 또는 업데이트
use wmsdb;
DROP TRIGGER IF EXISTS sync_inventory_after_warehouse_insert;

DELIMITER //
CREATE TRIGGER sync_inventory_after_warehouse_insert
    AFTER INSERT ON warehouse
    FOR EACH ROW
BEGIN
    -- inventory 테이블에 동일한 제품 있는지 확인
    IF EXISTS(SELECT 1 FROM inventory WHERE inventory.prod_id = NEW.prod_id
                                        AND inventory.ware_id = NEW.ware_id AND inventory.client_id = NEW.client_id) THEN
        -- 기존 데이터 있으면 수량 증가
        UPDATE inventory
        SET quantity = quantity + NEW.quantity
        WHERE inventory.prod_id = NEW.prod_id
          AND inventory.ware_id = NEW.ware_id
          AND inventory.client_id = NEW.client_id;
    ELSE
        -- 존재하지 않으면 새로운 재고 데이터 삽입
        INSERT INTO inventory(prod_id, client_id, ware_id, quantity)
        VALUES(NEW.prod_id, NEW.client_id, NEW.ware_id, NEW.quantity);

    END IF;

END //

DELIMITER ;

-- insert Test
INSERT Into warehouse
values ('2340', '672', 'key_6323', 200, now(), now());

select * from inventory;
select * from warehouse;

-- update trigger



DELIMITER //
CREATE TRIGGER sync_inventory_after_warehouse_update
    AFTER UPDATE ON warehouse
    FOR EACH ROW
BEGIN

    UPDATE inventory
    SET quantity = quantity - OLD.quantity + NEW.quantity
    WHERE inventory.prod_id = NEW.prod_id
      AND inventory.ware_id = NEW.ware_id
      AND inventory.client_id = NEW.client_id;


end //

DELIMITER ;

-- update test
update warehouse set quantity = 500 where prod_id = 'cpu_3478';
select * from warehouse;
select * from inventory;

-- delete trigger
drop trigger if exists sync_inventory_after_warehouse_delete;

DELIMITER //

CREATE TRIGGER sync_inventory_after_warehouse_delete
    AFTER DELETE ON warehouse
    FOR EACH ROW
BEGIN
    DELETE FROM inventory
    WHERE inventory.prod_id = OLD.prod_id
      AND inventory.ware_id = OLD.ware_id
      AND inventory.client_id = OLD.client_id;
END //

DELIMITER ;

-- DELETE test

select * from warehouse;
delete from warehouse where ware_id = '2345';
select * from inventory;