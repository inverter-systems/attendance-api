package com.inverter.attendance.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.inverter.attendance.annotation.impl.CpfValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CpfValidator.class) // Classe que implementa a validação
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Onde pode ser aplicada
@Retention(RetentionPolicy.RUNTIME) // Disponível em runtime
public @interface Cpf {
    String message() default "CPF inválido"; // Mensagem padrão

    Class<?>[] groups() default {}; // Para agrupamento de validações

    Class<? extends Payload>[] payload() default {}; // Payload customizado
}