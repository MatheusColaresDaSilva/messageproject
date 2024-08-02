package com.smsender.utils;

import com.smsender.exception.InvalidCPFException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidationsUtils {

    public static Boolean isCpfValido(String cpf) {

        validator(cpf);

        int soma = 0;
        int resto;

        if (cpf == "00000000000") {
            throw new InvalidCPFException();
        }

        for (int i=1; i<=9; i++) {
            soma = soma + Integer.parseInt(cpf.substring(i-1, i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if ((resto == 10) || (resto == 11)) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(9, 10)) ) {
            throw new InvalidCPFException();
        }

        soma = 0;

        for (int i = 1; i <= 10; i++) {
            soma = soma + Integer.parseInt(cpf.substring(i-1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if ((resto == 10) || (resto == 11)) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(10, 11) ) ) {
            throw new InvalidCPFException();
        }

        return true;
    }

    private static boolean validator(String field) {
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(field);
        if(!matcher.find()) {
            throw new InvalidCPFException();
        }

        return true;
    }
}
