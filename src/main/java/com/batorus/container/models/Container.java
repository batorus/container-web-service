package com.batorus.container.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "container")
public class Container extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "courseName may not be null!")
    @Size(min = 2, max = 100, message = "containerName must be between {min} and {max} characters long")
    private String containerName;


    @NotNull(message = "courseCode code may not be null!")
    @Size(min = 2, max = 60, message = "containerCode must be between {min} and {max} characters long")
    @Column(unique = true)
    private String containerCode;

    @Column(columnDefinition = "integer default 1")
    private int enabled = 1;



    public Container() {
    }

    public Container(String containerName, String containerCode, int enabled) {
        this.containerName = containerName;
        this.containerCode = containerCode;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Container{" +
                "containerName='" + containerName + '\'' +
                ", containerCode='" + containerCode + '\'' +
                '}';
    }
}
