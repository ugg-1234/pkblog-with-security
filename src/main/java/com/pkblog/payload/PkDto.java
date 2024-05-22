package com.pkblog.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PkDto {
    private long id;

    @NotEmpty
    @Size(min = 2,message = "pkName should be 2 character")
    private String pkName;
    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 10, message = "Surname must be 2 to 10 characters long")
  //  @Pattern(regexp = "[A-Za-z]{2}[A-Za-z]{3}", message = "Surname must start with two alphabetic characters and be followed by three alphabetic characters")

    private String pkSurname;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^[6-9]\\d{9}$",message = "exact 10 numbers & numbers starts bet 6 to 9 ")
    private String mobile;
}
