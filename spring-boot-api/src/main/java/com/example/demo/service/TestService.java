package com.example.demo.service;

import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestEntity;
import com.example.demo.model.dto.TestDTO;
import com.example.demo.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public TestDTO createData() {
        return TestMapper.toDto(
                testRepository.save(TestEntity.builder().test("test").build())
        );
    }

    public List<TestDTO> getData() {
        return testRepository.findAll().stream()
                .map(TestMapper::toDto)
                .toList();
    }
}
