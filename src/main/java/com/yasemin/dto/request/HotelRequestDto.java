package com.yasemin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestDto {
    private String token;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;
    private String website;
    private String image;
    private String facilities;
    private String typeId;
    private String propertiesId;

}
