package com.yasemin.repository;

import com.yasemin.entity.MainProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MainPropertiesRepository extends MongoRepository<MainProperties, String> {
}
