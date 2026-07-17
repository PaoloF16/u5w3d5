package PaoloF16.u5w3d5.payloads;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        LocalDateTime timestamp
) {
}
