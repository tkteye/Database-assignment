CREATE TABLE UserLog (
    logID NUMBER PRIMARY KEY,
    logMessage VARCHAR2(1000),
    logDate DATE
);

CREATE OR REPLACE TRIGGER log_user_activity
AFTER INSERT OR UPDATE OR DELETE ON table
FOR EACH ROW
BEGIN
    INSERT INTO UserLog (logID, logMessage, logDate)
    VALUES (log_sequence.NEXTVAL, 'User activity logged.', SYSDATE);
END;
