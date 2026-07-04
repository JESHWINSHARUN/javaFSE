SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE
ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Test the trigger
UPDATE Customers
SET Balance = Balance + 500
WHERE CustomerID = 1;

COMMIT;

SELECT CustomerID, Name, Balance, LastModified
FROM Customers
WHERE CustomerID = 1;