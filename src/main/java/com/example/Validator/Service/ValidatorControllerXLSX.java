package com.example.Validator.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidatorControllerXLSX {

    @PostMapping("/validate-line")
    public ResponseEntity<String> validateLine(@RequestBody List<String> rowData) {
        int lineaCorrecta = 0;
        int lineaIncorrecta = 0;

        for (int i = 1; i < rowData.size(); i++) {

            boolean isValidLine = ("N/A".equals(getInjuryLocationColumnIndex()) &&
                    !("Near Miss".equals(getReportTypeColumnIndex()) || "Lost Time".equals(getReportTypeColumnIndex())
                            || "First Aid".equals(getReportTypeColumnIndex())));

            if (isValidLine) {
                lineaIncorrecta++;
            } else {
                lineaCorrecta++;
            }
        }

        String mensaje = "Total líneas correctas: " + lineaCorrecta + "\n";

        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    private int getInjuryLocationColumnIndex() {
        return 1; // Suponiendo que es la tercera columna (índice basado en 0)
    }

    private int getReportTypeColumnIndex() {
        return 7; // Suponiendo que es la cuarta columna (índice basado en 0)
    }



}






