package com.smbackoffice.unit;

import static org.junit.jupiter.api.Assertions.*;

import com.smbackoffice.entity.AccountPos;
import com.smbackoffice.entity.AccountPre;
import com.smbackoffice.exception.InsufficientFundsException;
import com.smbackoffice.service.PosDeductStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PosDeductStrategyTest {

    private PosDeductStrategy posDeductStrategy;
    private AccountPos accountPos;

    @BeforeEach
    public void setUp() {
        posDeductStrategy = new PosDeductStrategy();
        accountPos = new AccountPos();
        accountPos.setCurrentValue(100.0);
        accountPos.setMaximumValue(200.0);
    }

    @Test
    public void testDeductValueSuccess() throws InsufficientFundsException {
        Double result = posDeductStrategy.deductValue(50.0, accountPos);
        assertEquals(150.0, result);
        assertEquals(150.0, accountPos.getCurrentValue());
        assertEquals(200.0, accountPos.getMaximumValue());
    }

    @Test
    public void testDeductValueExceedsMaximum() {
        assertThrows(InsufficientFundsException.class, () -> {
            posDeductStrategy.deductValue(300.0, accountPos);
        });
    }

    @Test
    public void testDeductValueInvalidAccountType() {
        AccountPre accountPre = new AccountPre();
        assertThrows(IllegalArgumentException.class, () -> {
            posDeductStrategy.deductValue(50.0, accountPre);
        });
    }

    @Test
    public void testDeductValueNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            posDeductStrategy.deductValue(null, accountPos);
        });
    }
}
