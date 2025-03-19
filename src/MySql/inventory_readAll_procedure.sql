
use wmsdb;

drop procedure if exists inventory_readAll;

DELIMITER //

CREATE PROCEDURE inventory_readAll()
BEGIN
    select i.prod_id, prod_name, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_day, i.last_outbound_day
    from inventory i
    join product p on i.prod_id = p.prod_id

    order by i.prod_id, i.ware_id;
end;

DELIMITER ;

call inventory_readAll();



