SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus      IN NUMBER
)
IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus / 100)
    WHERE Department = p_department;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Bonus updated successfully.');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Execute the procedure
BEGIN
    UpdateEmployeeBonus('IT', 10);
END;
/