package com.udacity.jdnd.course3.critter.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.udacity.jdnd.course3.critter.dto.pet.PetType;
import com.udacity.jdnd.course3.critter.dto.users.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exception.BadRequestException;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.DayOfWeek;
import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<String> handleInvalidFormatException(Exception e) {
        if (e.getLocalizedMessage().contains("com.udacity.jdnd.course3.critter.dto.pet.PetType")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Value of PetType must be one of these: " + Arrays.toString(PetType.class.getEnumConstants()));
        }
        if (e.getLocalizedMessage().contains("com.udacity.jdnd.course3.critter.dto.users.EmployeeSkill")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Value of EmployeeSkill must be one of these: " + Arrays.toString(EmployeeSkill.class.getEnumConstants()));
        }
        if (e.getLocalizedMessage().contains("java.time.DayOfWeek")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Value of DayOfWeek must be one of these: " + Arrays.toString(DayOfWeek.class.getEnumConstants()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }
}
