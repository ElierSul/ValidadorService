package com.example.Validator.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@RestController
public class ValidatorControllerCSV {

    @PostMapping("/validarlinea")
    public ResponseEntity<Boolean> validarLinea(@RequestBody String[] linea) {
        String email = linea[0];
        String dobStr = linea[1];
        String jobTitle = linea[2];

        boolean isEmailValid = validarEmail(email);
        boolean isDobValid = validarFechaNacimiento(dobStr);
        boolean isJobTitleValid = validarJobTitle(jobTitle);

        boolean isValid = isEmailValid && isDobValid && isJobTitleValid;

        return new ResponseEntity<>(isValid, HttpStatus.OK);

    }

    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validarFechaNacimiento(String dobStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = dateFormat.parse(dobStr);
            Date referenceDate = dateFormat.parse("1980-01-01");
            return dob.after(referenceDate);
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validarJobTitle(String jobTitle) {
        return jobTitle.equals("Haematologist") ||
                jobTitle.equals("Phytotherapist") ||
                jobTitle.equals("Building surveyor") ||
                jobTitle.equals("Insurance account manager") ||
                jobTitle.equals("Educational psychologist");
    }
}
