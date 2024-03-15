package com.yasemin.entity;

import com.yasemin.utility.CodeGenerator;
import com.yasemin.utility.enums.EStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth")
public class Auth {
    @Id
    private String id;
    @Size(min=3, max=16)
    private String name;
    @Size(min=3, max=20)
    private String surname;
    @Email
    private String email;
    @Size(min=6, max=16)
    private String password;
    @Size(min=11, max=11)
    private String phone;
    @Builder.Default
    private String activationCode= CodeGenerator.generateCode();
    @Builder.Default
    private EStatus status= EStatus.PENDING;
}
