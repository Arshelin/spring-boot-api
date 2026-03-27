package com.example.demo.mapper;

import com.example.demo.model.TestEntity;
import com.example.demo.model.dto.TestDTO;

public class TestMapper {

    public static TestDTO toDto(TestEntity test) {
        return TestDTO.builder()
                .id(test.getId())
                .test(test.getTest())
                .build();
    }
}
