package com.yasemin.entity;

import com.yasemin.utility.enums.EStatus;
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
@Document(collection = "users")
public class UserProfile extends BaseEntity {
    @Id
    private String id;
    private String authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Builder.Default
    private EStatus status= EStatus.PENDING;
}
