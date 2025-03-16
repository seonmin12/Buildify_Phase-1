use wmsdb;
DELIMITER //

CREATE PROCEDURE inventory_read_by_category(
    IN input_category varchar(50)
)
BEGIN
    select i.prod_id, p.prod_name, p.prod_category, i.client_id, i.ware_id, i.quantity,
           w.last_inbound_day, w.last_outbount_day
    from inventory i
             join product p on i.prod_id = p.prod_id
             join warehouse w on i.prod_id = w.prod_id
        AND i.ware_id = w.ware_id
        AND i.client_id = w.client_id
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

