use wmsdb;
DELIMITER //
drop procedure inventory_read_by_clientID;
CREATE PROCEDURE inventory_read_by_clientID(
    IN input_client_id varchar(100)
)
BEGIN
    select i.prod_id, prod_name, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_date, i.last_outbound_date
    from inventory i
             join product p on i.prod_id = p.prod_id

    where i.client_id = input_client_id
    order by i.prod_id, i.ware_id;
end;

DELIMITER ;

-- test
select * from warehouse;
call inventory_read_by_clientID('552');