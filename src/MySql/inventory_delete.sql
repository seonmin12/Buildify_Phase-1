use wmsdb;

drop procedure if exists inventory_delete;

DELIMITER //
create procedure inventory_delete(
    in input_prod_id varchar(30), in input_client_id varchar(30),
    in input_ware_id varchar(30)
)
BEGIN

    delete from inventory
    where prod_id = input_prod_id
    and client_id = input_client_id
    and ware_id = input_ware_id;



end //

DELIMITER ;

-- test
call inventory_delete('cpu_3478','552','552');

select * from inventory;
select * from inventory_backup;


