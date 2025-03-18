use wmsdb;
select * from outbound;

DELIMITER //
CREATE procedure delete_outbound_request(
    in p_outboundKey varchar(30),
    in p_clientid varchar(30),
    out p_result int
)

BEGIN
    DELETE FROM outbound
        where `Key` = p_outboundKey
    and client_id = p_clientid
        and status = 0;

    IF ROW_COUNT() >0 Then
        set p_result =1;
        else
        set p_result = 0; -- 삭제 실패
    end if;


end //

DELIMITER ;