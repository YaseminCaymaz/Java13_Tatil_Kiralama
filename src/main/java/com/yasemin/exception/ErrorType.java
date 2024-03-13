package com.yasemin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000, "Sunucuda beklenmeye hata oluştu, lütfen terar deneyiniz",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN_ERROR(1002, "Geçersiz Token bilgisi",HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_USERNAME(2000,"Bu Kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_EMAIL(2000,"Bu Email adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ERROR_INVALID_LOGIN_PARAMETER(2001,"Kullanıcı adı ya da Şifre hatalıdır. Lütfen tekrar deneyiniz.t",HttpStatus.BAD_REQUEST),
    ERROR_INVALID_USER_ID(2003,"Kullanıcı id geçersiz. Böyle bir id li kullanıcı olmadığı için güncelleme yaplılamamıştır.",HttpStatus.BAD_REQUEST),

    BAD_REQUEST_ERROR(1001, "Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    ERROR_PASSWORD_NOT_MATCH(2004, "Girilen sifreler uyusmuyor. Lütfen tekrar deneyiniz",HttpStatus.BAD_REQUEST ),
    TOKEN_OLUSTURULAMADI(2005, "Token oluşturulamadı", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(2006, "Kullanıcı bulunamadı",HttpStatus.BAD_REQUEST ),
    ROLE_NOT_FOUND(2007,"Rol bulunamadı" , HttpStatus.BAD_REQUEST),
    HOTEL_NOT_FOUND(2008,"Hotel bulunamadı" ,HttpStatus.BAD_REQUEST ),
    BOOKING_NOT_FOUND(2009,"Rezervasyon bulunamadı" , HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND_ERROR(2010,"" ,HttpStatus.BAD_REQUEST ),
    FAVORI_HOTEL_EXIST(2011,"Bu favoriotel zaten mevcut." ,HttpStatus.BAD_REQUEST ),
    FAVORI_HOTEL_NOT_FOUND(2012,"Favori otel bulunamadı" ,HttpStatus.BAD_REQUEST );

    int code;
    String message;
    HttpStatus httpStatus;


}
