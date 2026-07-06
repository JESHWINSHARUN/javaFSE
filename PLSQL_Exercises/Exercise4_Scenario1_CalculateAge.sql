SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
)
RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

-- Execute the function
DECLARE
    v_age NUMBER;
BEGIN
    SELECT CalculateAge(DOB)
    INTO v_age
    FROM Customers
    WHERE CustomerID = 1;

    DBMS_OUTPUT.PUT_LINE('Customer Age: ' || v_age);
END;
/