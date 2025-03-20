use wmsdb;


##
DROP PROCEDURE IF EXISTS inventory_read_by_clientID;
DELIMITER &&
CREATE PROCEDURE inventory_read_by_clientID(
    IN input_client_id varchar(100))
BEGIN
    select i.prod_id, prod_name, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_day, i.last_outbound_day
    from inventory i
             join product p on i.prod_id = p.prod_id

    where i.client_id = input_client_id
    order by i.prod_id, i.ware_id;
end &&
DELIMITER ;
