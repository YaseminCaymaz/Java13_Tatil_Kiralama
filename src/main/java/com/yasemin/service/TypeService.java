package com.yasemin.service;

import com.yasemin.entity.Type;
import com.yasemin.exception.ErrorType;
import com.yasemin.exception.TatilKiralamaException;
import com.yasemin.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Boolean addType(String name) {
      if (typeRepository.existsByName(name)) {
          throw new TatilKiralamaException(ErrorType.EXIST_TYPE);
      }
        typeRepository.save(Type.builder()
                .name(name)
                .build());
        return true;
    }

    public Boolean updateType(String name) {
        if (!typeRepository.existsByName(name)) {
            throw new TatilKiralamaException(ErrorType.NOT_EXIST_TYPE);
        }
        typeRepository.save(Type.builder()
                .name(name)
                .build());
        return true;
    }

    public Boolean deleteType(String name) {
        if (!typeRepository.existsByName(name)) {
            throw new TatilKiralamaException(ErrorType.NOT_EXIST_TYPE);
        }
        typeRepository.deleteByName(name);
        return true;
    }
}
