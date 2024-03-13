package com.yasemin.controller;

import com.yasemin.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yasemin.constants.RestApiUrls.TYPE;

@RestController
@RequiredArgsConstructor
@RequestMapping(TYPE)
public class TypeController {
    private final TypeService typeService;
}
