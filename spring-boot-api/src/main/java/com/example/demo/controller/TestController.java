package com.example.demo.controller;

import com.example.demo.model.dto.TestDTO;
import com.example.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    public final TestService testService;

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TestDTO createData() {
        return testService.createData();
    }

    @GetMapping("/get")
    public List<TestDTO> getData() {
        return testService.getData();
    }
}
