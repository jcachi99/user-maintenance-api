package com.maintenance.user.util.validator;

import com.maintenance.user.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class PasswordConstraintsValidator implements ConstraintValidator<Password, String> {

    private static final String PASSAY_MESSAGE_FILE_PATH = "src/main/resources/passay_es.properties";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator passwordValidator = new PasswordValidator(generateMessageResolver(),
                Arrays.asList(
                        //minimo 10, maximo 128 caracteres
                        new LengthRule(10, 128),

                        //al menos una mayúscula
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),

                        //al menos una minúscula
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),

                        //Al menos un número
                        new CharacterRule(EnglishCharacterData.Digit, 1),

                        //Al menos un caracter especial
                        new CharacterRule(EnglishCharacterData.Special, 1),

                        new WhitespaceRule(MatchBehavior.Contains)
                )
        );

        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(passwordValidator.getMessages(result).stream().findFirst()
                        .orElse(constraintValidatorContext.getDefaultConstraintMessageTemplate()))
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

    private MessageResolver generateMessageResolver() {

        Properties props = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PASSAY_MESSAGE_FILE_PATH)) {
            props.load(new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1));
            return new PropertiesMessageResolver(props);
        } catch (IOException exception) {
            log.error("Parámetro inválido de lenguaje!");
            throw new ResourceNotFoundException("Parámetro inválido de lenguaje!");
        }
    }
}
