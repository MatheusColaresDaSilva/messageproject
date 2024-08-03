package com.smbackoffice.unit;

import static org.junit.jupiter.api.Assertions.*;

import com.smbackoffice.entity.AccountPos;
import com.smbackoffice.entity.AccountPre;
import com.smbackoffice.exception.InsufficientFundsException;
import com.smbackoffice.service.PreDeductStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PreDeductStrategyTest {

    private PreDeductStrategy preDeductStrategy;
    private AccountPre accountPre;

    @BeforeEach
    public void setUp() {
        preDeductStrategy = new PreDeductStrategy();
        accountPre = new AccountPre();
        accountPre.setCreditValue(100.0);
    }

    @Test
    public void testDeductValueSuccess() throws InsufficientFundsException {
        Double result = preDeductStrategy.deductValue(50.0, accountPre);
        assertEquals(50.0, result);
        assertEquals(50.0, accountPre.getCreditValue());
    }

    @Test
    public void testDeductValueInsufficientCredit() {
        assertThrows(InsufficientFundsException.class, () -> {
            preDeductStrategy.deductValue(150.0, accountPre);
        });
    }

    @Test
    public void testDeductValueInvalidAccountType() {
        AccountPos accountPos = new AccountPos();
        assertThrows(IllegalArgumentException.class, () -> {
            preDeductStrategy.deductValue(50.0, accountPos);
        });
    }

    @Test
    public void testDeductValueNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            preDeductStrategy.deductValue(null, accountPre);
        });
    }
}
