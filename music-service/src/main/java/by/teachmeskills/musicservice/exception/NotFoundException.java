package by.teachmeskills.musicservice.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private Long id;

    public NotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
