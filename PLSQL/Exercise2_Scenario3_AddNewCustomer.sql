SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
)
IS
BEGIN
    INSERT INTO Customers
    (CustomerID, Name, DOB, Balance, LastModified)
    VALUES
    (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer added successfully.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID already exists.');

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Execute the procedure
BEGIN
    AddNewCustomer(
        3,
        'Michael',
        TO_DATE('1998-04-15','YYYY-MM-DD'),
        8000
    );
END;
/