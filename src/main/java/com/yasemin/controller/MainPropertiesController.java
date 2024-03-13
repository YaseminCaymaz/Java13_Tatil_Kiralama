package com.yasemin.controller;


import com.yasemin.service.MainPropertiesService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yasemin.constants.RestApiUrls.MAIN_PROPERTIES;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAIN_PROPERTIES)
public class MainPropertiesController {
    private final MainPropertiesService mainPropertiesService;
}
