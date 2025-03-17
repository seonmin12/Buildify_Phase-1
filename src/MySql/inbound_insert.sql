DELIMITER &&
CREATE PROCEDURE DB_Inbound_INSERT(in inbound_num varchar(30), in prod_id varchar(10), in inclient_id varchar(20), in quantity int,
                                   in inbound_status int, req_inbound_day datetime, in ware_id varchar(10), in DBname varchar(20))
BEGIN
    SET @strsql = concat(' INSERT INTO ', DBname, ' VALUES(?,?,?,?,?,?,?);' );

    SET @inbound_number = inbound_num;
    SET @prod_id = prod_id;
    SET @inclient_id = inclient_id;
    SET @quantity = quantity;
    SET @inbound_status = inbound_status;
    SET @req_inbound_day = req_inbound_day;
    SET @ware_id = ware_id;

PREPARE stmt FROM @strsql;
EXECUTE stmt using @inbound_number, @prod_id, @inclient_id, @quantity, @inbound_status, @req_inbound_day, @ware_id;
deallocate prepare stmt;

commit ;
end;
DELIMITER ;


