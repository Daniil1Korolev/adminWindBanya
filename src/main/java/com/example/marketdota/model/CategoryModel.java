package com.example.marketdota.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "categ")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина названия должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Time is required")
    @Size(max = 30, message = "Длина должна быть не больше 10 символов")
    @Column(name = "time")
    private String time;

    @NotBlank(message = "Status is required")
    @Size(max = 30, message = "Длина должна быть не больше 30 символов")
    @Column(name = "status")
    private String status;



    public CategoryModel() {
    }

    public CategoryModel(long id, String name, String time, String status) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
