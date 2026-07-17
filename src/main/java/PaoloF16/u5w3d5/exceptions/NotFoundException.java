package PaoloF16.u5w3d5.exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("We haven't found an element with id " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }
}