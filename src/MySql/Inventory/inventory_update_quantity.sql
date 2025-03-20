use wmsdb;


##
drop procedure if exists inventory_update_quantity;
DELIMITER &&
CREATE PROCEDURE inventory_update_quantity(
    IN input_prod_id varchar(20), IN input_client_id varchar(50)
    ,IN input_ware_id varchar(30), IN newQuantity int)
BEGIN
    update inventory
        set quantity = newQuantity
    where prod_id = input_prod_id
    and client_id = input_client_id
    and ware_id = input_ware_id;

    select i.prod_id, prod_name, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_day, i.last_outbound_day
    from inventory i
             join product p on i.prod_id = p.prod_id
    order by i.prod_id, i.ware_id;
end &&
DELIMITER ;









