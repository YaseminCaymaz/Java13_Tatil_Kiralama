package com.yasemin.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequestDto {
    private String name;
    private String surname;
    @Email
    private String email;
    private String phone;
    private String password;
    private String rePassword;

}
