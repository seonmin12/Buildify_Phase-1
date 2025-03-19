use wmsdb;
DELIMITER //

drop procedure if exists inventory_read_by_category;

CREATE PROCEDURE inventory_read_by_category(
    IN input_category varchar(50)
)
BEGIN
    select i.prod_id, p.prod_name, p.prod_category, i.client_id, i.ware_id, i.quantity,
           i.last_inbound_day, i.last_outbound_day
    from inventory i
             join product p on i.prod_id = p.prod_id

    where p.prod_category = input_category
    order by i.prod_id, i.ware_id;
end;

DELIMITER ;

-- test

select * from product;
select * from inventory;
select* from warehouse;
INSERT INTO product values('cpu_1248','apple','기깔난씨피유',600000,'343','cpu',20);
INSERT Into warehouse values('4324','743','cpu_1248',900,now(),now());
call inventory_read_by_category('cpu');

select * from product where prod_category = 'cpu';


