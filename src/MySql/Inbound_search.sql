use wmsdb;

delimiter &&
create procedure DB_INBOUND_SEARCHONE(in a varchar(255))
Begin
    set @strsql = concat(' select * from inbound where client_id = ? ');
    set @client_id= a;

prepare stmt from @strsql;
execute stmt using @client_id;
deallocate  prepare stmt;
commit ;

end &&
delimiter ;




delimiter //
create procedure db_inbound_deletesearch(in a varchar(255))
begin
select * from inbound where inbound.Inbound_status = 0 and inbound.client_id = a;
end //
    delimiter ;




    delimiter &&
create procedure db_inbound_search_clientup()
begin
select distinct client_id from inbound where Inbound_status = 0;
end &&
    delimiter ;