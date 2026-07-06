SET SERVEROUTPUT ON;

DECLARE
BEGIN
    FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP

        IF cust.Balance > 10000 THEN

            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ' || cust.CustomerID ||
                ' promoted to VIP.'
            );

        ELSE

            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = cust.CustomerID;

        END IF;

    END LOOP;

    COMMIT;
END;
/