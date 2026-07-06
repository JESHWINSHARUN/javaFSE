SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    -- Get the balance of the source account
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account;

    -- Check if sufficient balance is available
    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds.');
    END IF;

    -- Deduct amount from source account
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account;

    -- Add amount to destination account
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Fund transfer completed successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/

-- Execute the procedure
BEGIN
    SafeTransferFunds(1, 2, 200);
END;
/