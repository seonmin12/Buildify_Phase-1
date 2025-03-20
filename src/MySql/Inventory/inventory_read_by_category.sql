use wmsdb;


##
drop procedure if exists inventory_read_by_category;
DELIMITER &&
CREATE PROCEDURE inventory_read_by_category(
    IN input_category varchar(50))
BEGIN
    select i.prod_id, p.prod_name, p.prod_category, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_day, i.last_outbound_day
    from inventory i
             join product p on i.prod_id = p.prod_id

    where p.prod_category = input_category
    order by i.prod_id, i.ware_id;
end &&
DELIMITER ;




