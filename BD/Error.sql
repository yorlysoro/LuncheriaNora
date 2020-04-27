DELIMITER //
CREATE PROCEDURE `Error` (IN `mensaje` TEXT)
BEGIN
	signal sqlstate '23000' set
    mysql_errno = 1452,
	message_text = mensaje;
END
