
use wmsdb;
# 현재 유저에게 할당된 창고 용적률 구하는 프로시저
DELIMITER $$
CREATE PROCEDURE useware()
BEGIN
    SELECT sum(user.user_ware_size) as '현재 창고 할당량'
    FROM user
    WHERE user_status = 1;
end $$
DELIMITER ;

DROP PROCEDURE useware;