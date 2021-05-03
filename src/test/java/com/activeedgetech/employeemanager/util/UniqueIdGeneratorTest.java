package com.activeedgetech.employeemanager.util;

import com.activeedgetech.employeemanager.model.LastId;
import com.activeedgetech.employeemanager.repository.LastIdRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class UniqueIdGeneratorTest {

    // Create a mock of Resource to change its behaviour for testing
    @Mock
    private LastIdRepository lastIdRepository;

    @InjectMocks
    UniqueIdGenerator uniqueIdGenerator;

    @Test
    public void generateIdTest() {

        // Initialize mocks created above
        MockitoAnnotations.initMocks(this);
        LastId lastId = new LastId();
        lastId.setLastId(2L);
        lastId.setId(1L);
        when(lastIdRepository.findById(1l)).thenReturn(Optional.of(lastId));

        String expectedEmployeeId = "E00002";
        Assertions.assertEquals(expectedEmployeeId, uniqueIdGenerator.generateId());
    }
}
