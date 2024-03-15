package com.yasemin.repository;

import com.yasemin.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);

    Optional<Auth> findByActivationCode(String activationCode);
}
