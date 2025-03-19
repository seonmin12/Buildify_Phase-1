use wmsdb;

DELIMITER &&
create procedure DB_INBOUND_DELETE(in inbound_number varchar(30))
begin
    set @strsql = concat(' DELETE FROM INBOUND WHERE inbound_number = ? ');
    SET @inbound_number = inbound_number;

prepare stmt from @strsql;
EXECUTE stmt using @inbound_number;
deallocate prepare stmt;

commit;
end &&
    DELIMITER ;