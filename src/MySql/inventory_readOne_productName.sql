use wmsdb;
drop procedure if exists inventory_readOne_productName;
DELIMITER //

CREATE PROCEDURE inventory_readOne_productName(
    IN input_prod_name varchar(100)
)
BEGIN
    select i.prod_id, prod_name, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_date, i.last_outbound_date
    from inventory i
             join product p on i.prod_id = p.prod_id

    where p.prod_name = input_prod_name
    order by i.prod_id, i.ware_id;
end;

DELIMITER ;

-- test
select * from product;
call inventory_readOne_productName('블루투스키보드');