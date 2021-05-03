package com.activeedgetech.employeemanager.util;

import com.activeedgetech.employeemanager.exception.BadRequestException;
import com.activeedgetech.employeemanager.exception.ErrorMessage;
import com.activeedgetech.employeemanager.model.LastId;
import com.activeedgetech.employeemanager.repository.LastIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Utility for generating unique identifiers for employees
 *
 */
@Component
@Slf4j
public class UniqueIdGenerator {


    @Autowired
    private LastIdRepository lastIdRepository;

    /**
     * Generate a unique Id for employees
     * @return the generated employee ID
     */
    @Transactional
    public String generateId() {
        Optional<LastId> optionalLastId = lastIdRepository.findById(1L);

        LastId lastIdEntity = optionalLastId.orElseThrow(
                () -> new BadRequestException("No record of last-stored-employee-Id tracker found")
        );

        Long lastId = lastIdEntity.getLastId();
        String employeeId;
        String empIdNextSequence = String.valueOf(lastId);

        if (empIdNextSequence.length() <= 5) {
            int uuidNextSequenceLength = empIdNextSequence.length();
            for (int i = 0; i < 5 - uuidNextSequenceLength; i++) {
                empIdNextSequence = "0".concat(empIdNextSequence);
            }
            employeeId = "E" + empIdNextSequence;
        } else {
            throw new BadRequestException(ErrorMessage.INVALID_EMPLOYEE_ID.getValue());
        }

        LastId lastIdForUpdate = new LastId();
        lastIdForUpdate.setId(1L);
        lastIdForUpdate.setLastId(++lastId);
        lastIdRepository.save(lastIdForUpdate);

        return employeeId;
    }

}
