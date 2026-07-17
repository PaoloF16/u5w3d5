package PaoloF16.u5w3d5.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventDTO(

        @NotBlank(message = "Title is required")
        String title,

        String description,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotBlank(message = "Location is required")
        String location,

        @NotNull(message = "Total seats are required")
        int totalSeats
) {
}