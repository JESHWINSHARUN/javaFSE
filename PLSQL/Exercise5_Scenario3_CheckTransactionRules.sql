SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT
ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(
                -20001,
                'Withdrawal amount exceeds account balance.'
            );
        END IF;
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(
                -20002,
                'Deposit amount must be positive.'
            );
        END IF;
    END IF;
END;
/

-- Test 1: Valid Deposit
INSERT INTO Transactions
VALUES
(4, 1, SYSDATE, 100, 'Deposit');

COMMIT;

-- Test 2: Invalid Withdrawal
INSERT INTO Transactions
VALUES
(5, 1, SYSDATE, 50000, 'Withdrawal');