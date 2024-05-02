package com.example.banyaadmin.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dota")
public class ZakazModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;

    @Min(0)
    @Column(name = "cost")
    private int cost;

    @NotBlank(message = "Password is required")
    @Size(max = 30, message = "Длина должна быть не больше 30 символов")
    @Column(name = "time")
    private String time;
    private String datazasel;
    private String datavisel;

    @ManyToOne
    @JoinColumn(name = "coach_id") // Указывает на столбец, который связывает сущности
    private BanyaModel banya;

    public ZakazModel() {
    }

    public ZakazModel(long id, String datazasel, String datavisel, String name, int cost, String time, BanyaModel banya) {
        this.datazasel = datazasel;
        this.datavisel = datavisel;
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.banya = banya;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatazasel() {
        return datazasel;
    }

    public void setDatazasel(String datazasel) {
        this.datazasel = datazasel;
    }

    public String getDatavisel() {
        return datavisel;
    }

    public void setDatavisel(String datavisel) {
        this.datavisel = datavisel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public BanyaModel getBanya() {
        return banya;
    }

    public void setBanya(BanyaModel banya) {
        this.banya = banya;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
