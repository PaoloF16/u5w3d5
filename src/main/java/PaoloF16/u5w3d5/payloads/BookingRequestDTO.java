package PaoloF16.u5w3d5.payloads;

import jakarta.validation.constraints.NotNull;

public record BookingRequestDTO(

        @NotNull(message = "Event ID is required")
        Long eventId
) {
}
