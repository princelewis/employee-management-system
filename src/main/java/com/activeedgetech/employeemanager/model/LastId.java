package com.activeedgetech.employeemanager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "last_id")
public class LastId {
    @Id
    private Long id;

    @Column(name = "last_id")
    private  Long lastId;
}
