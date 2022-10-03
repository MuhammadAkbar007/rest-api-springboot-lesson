package uz.pdp.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull(message = "FISH bo'sh bo'lmasligi kerak !")
    private String fullName;

    @NotNull(message = "Telefon raqam bo'sh bo'lmasligi kerak !")
    private String phoneNumber;

    @NotNull(message = "Address bo'sh bo'lmasligi kerak !")
    private String address;
}
