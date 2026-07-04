SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount   IN NUMBER,
    p_interest_rate IN NUMBER,
    p_years         IN NUMBER
)
RETURN NUMBER
IS
    v_monthly_installment NUMBER;
BEGIN
    -- Simple Interest Formula
    v_monthly_installment :=
        (p_loan_amount +
        (p_loan_amount * p_interest_rate * p_years / 100))
        / (p_years * 12);

    RETURN v_monthly_installment;
END;
/

-- Execute the function
DECLARE
    v_installment NUMBER;
BEGIN
    v_installment := CalculateMonthlyInstallment(5000, 5, 5);

    DBMS_OUTPUT.PUT_LINE(
        'Monthly Installment: ' ||
        ROUND(v_installment, 2)
    );
END;
/