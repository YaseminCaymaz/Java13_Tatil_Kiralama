package com.yasemin.service;

import com.yasemin.repository.MainPropertiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPropertiesService {
    private final MainPropertiesRepository mainPropertiesRepository;
}
