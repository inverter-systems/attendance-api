package com.inverter.attendance.annotation.impl;

import com.inverter.attendance.annotation.Cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String ssn, ConstraintValidatorContext context) {
        if (ssn == null || ssn.isEmpty()) {
            return false; // Ou true, dependendo do requisito
        }

        // Remove caracteres não numéricos
        ssn = ssn.replaceAll("[^0-9]", "");
        
        if (ssn.length() == 9) {
        	return isValidSsn(ssn);
        } else if (ssn.length() == 11) {
        	return isValidCpf(ssn);
        } 

        return false;
    }
    
    private boolean isValidCpf(String ssn) {
    	// Calcula os dígitos verificadores
        try {
            int[] digits = ssn.chars().map(Character::getNumericValue).toArray();

            // Primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += digits[i] * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) firstDigit = 0;

            // Segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += digits[i] * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) secondDigit = 0;

            // Verifica se os dígitos calculados batem com os informados
            return digits[9] == firstDigit && digits[10] == secondDigit;
        } catch (Exception e) {
            return false;
        }
	}

	private boolean isValidSsn(String ssn) {
    	return ssn.length() == 9;
    }
}