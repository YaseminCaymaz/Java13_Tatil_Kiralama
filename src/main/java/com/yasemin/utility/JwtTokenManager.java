package com.yasemin.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yasemin.exception.ErrorType;
import com.yasemin.exception.TatilKiralamaException;
import com.yasemin.utility.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1. Kullanıcılara Token oluşturmak
     * 2. Gelen Token bilgisini doğrulamak
     */
    //-> 255^6 -> 3^6*100^6 81.000.000.000.000 / 3.400.000.000 -> 27.000 sn
    private final String SECRETKEY = "Ha6Ua15ALxb$dn7nL'HEJuKeG5YN4m,s5&b!NYaG_]9VlbA#3i";
    private final String ISSUER = "Java13Auth";
    private final Long EXDATE = 1000L * 60*5; // 5 dakika

    public Optional<String> createToken(String authId){
        String token;
        try{
            token = JWT.create()
                    .withAudience()
                    .withClaim("authid", authId) // key->value şeklinde açık değerler tutumak için kullanılır.
                    .withClaim("ahmet_amca","selam nasılsın ?")
                    .withClaim("liste", List.of("ali","veli","deniz"))
                    .withIssuer(ISSUER) // oluşturan kişi
                    .withIssuedAt(new Date()) // oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXDATE)) // token ın ne zaman süresini dolacağı
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }


    public Optional<String> validateToken(String token){
        try{
            /**
             * Token doğrularken ve içinden bilgileri çekerken ilk olarak
             * 1- şifreleme algoritmasını kullanarak token verify edilmeli.
             * 2- bu doğrulama işleminde süresinin dolup dolmadığıda kontrol edilmeli
             * 3- sahipliği bizim mi
             * Bunlar okey ise token decode edilmiş oluyor. Sonrasında ise claim nesnelerinin içinden
             * bilgiler alınarak dönüş yapılır.
             */
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            String userId = decodedJWT.getClaim("id").toString();
            return Optional.of(userId);
        }catch (Exception e){
            return Optional.empty();
        }
    }
        public Optional<String> createToken(String id, ERole role){
        String token = null;
        Date date = new Date(System.currentTimeMillis()+(EXDATE));
        try {
            token = JWT.create()
                    .withAudience()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id",id)
                    .withClaim("role",role.toString())
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
    public Optional<String> getRoleFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).withAudience().build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
            }
            String role = decodedJWT.getClaim("role").asString();
            return Optional.of(role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
    }
}
