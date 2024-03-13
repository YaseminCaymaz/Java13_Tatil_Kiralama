package com.yasemin.entity;

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
@Document(collection = "comments")
public class Comments extends BaseEntity {
    @Id
    private String id;
    @Size(max = 500)
    private String comment;
    private String userId;
    private double point;
    private String hotelId;
}
