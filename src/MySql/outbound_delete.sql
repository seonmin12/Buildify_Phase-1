use wmsdb;
select * from outbound;

drop procedure delete_outbound_request;
DELIMITER //
CREATE procedure delete_outbound_request(
    in p_outboundKey varchar(100),
    in p_clientid varchar(100),
    out p_result int
)

BEGIN
    DELETE FROM outbound
        where outbound_id = p_outboundKey
    and client_id = p_clientid
        and status = 0;

    IF ROW_COUNT() >0 Then
        set p_result =1;
        else
        set p_result = 0; -- 삭제 실패
    end if;


end //

DELIMITER ;