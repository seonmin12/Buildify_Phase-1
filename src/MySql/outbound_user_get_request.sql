
use wmsdb;

DELIMITER //

CREATE PROCEDURE outbound_user_pending_read(
    IN p_clientId VARCHAR(50)
)
BEGIN
    SELECT * FROM outbound
    WHERE client_id = p_clientId AND status = 0;
END //

DELIMITER ;

select * from outbound;


