use wmsdb;


##
DROP PROCEDURE IF EXISTS DB_INBOUND_SEARCHONE;
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


##
DROP PROCEDURE IF EXISTS db_inbound_deletesearch;
delimiter &&
create procedure db_inbound_deletesearch(in a varchar(255))
begin
    select * from inbound where inbound.Inbound_status = 0 and inbound.client_id = a;
end &&
delimiter ;



##
DROP PROCEDURE IF EXISTS db_inbound_search_clientup;
delimiter &&
create procedure db_inbound_search_clientup()
begin
    select distinct inbound.client_id, user.user_name from inbound join user on  inbound.client_id=user.client_id where  inbound.Inbound_status = 0;
end &&
delimiter ;



