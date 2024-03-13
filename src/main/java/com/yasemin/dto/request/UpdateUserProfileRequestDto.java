package com.yasemin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequestDto {
    private String token;
    private String name;
    private String surname;
    private String email;
    private String password;
}
