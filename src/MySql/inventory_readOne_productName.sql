use wmsdb;
DELIMITER //

CREATE PROCEDURE inventory_readOne_productName(
    IN input_prod_name varchar(100)
)
BEGIN
    select i.prod_id, prod_name, i.client_id, i.ware_id, i.quantity,
           w.last_inbound_day, w.last_outbount_day
    from inventory i
             join product p on i.prod_id = p.prod_id
             join warehouse w on i.prod_id = w.prod_id
        AND i.ware_id = w.ware_id
        AND i.client_id = w.client_id
    where p.prod_name = input_prod_name
    order by i.prod_id, i.ware_id;
end;

DELIMITER ;

-- test
select * from product;
call inventory_readOne_productName('블루투스키보드');