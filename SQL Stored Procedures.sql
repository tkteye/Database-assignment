a.	Insert Record

CREATE OR REPLACE PROCEDURE insert_record(p_data VARCHAR2) AS
BEGIN
    -- Insert logic here
    -- INSERT INTO table (column) VALUES (p_data);
EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;

b.	Retrieve Record

CREATE OR REPLACE PROCEDURE retrieve_record(p_data VARCHAR2) IS
    CURSOR c1 IS
        SELECT * FROM table WHERE column = p_data;
BEGIN
    FOR rec IN c1 LOOP
        DBMS_OUTPUT.PUT_LINE(rec.column);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;

c.	Update Record

CREATE OR REPLACE PROCEDURE update_record(p_data VARCHAR2) AS
BEGIN
    -- Update logic here
    -- UPDATE table SET column = new_value WHERE column = p_data;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;

d.	Delete Record

CREATE OR REPLACE PROCEDURE delete_record(p_data VARCHAR2) AS
BEGIN
    -- Delete logic here
    -- DELETE FROM table WHERE column = p_data;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
