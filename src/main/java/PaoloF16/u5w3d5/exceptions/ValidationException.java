package PaoloF16.u5w3d5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<String> errorMessages;

    public ValidationException(List<String> errorMessages) {
        super("Validation failed with multiple errors.");
        this.errorMessages = errorMessages;
    }
}