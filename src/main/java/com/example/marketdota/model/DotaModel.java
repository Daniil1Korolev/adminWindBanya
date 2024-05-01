package com.example.marketdota.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "dota")
public class DotaModel {
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
    private CoachesModel coaches;

    public DotaModel() {
    }

    public DotaModel(long id, String datazasel,String datavisel, String name, int cost, String time, CoachesModel coaches) {
        this.datazasel = datazasel;
        this.datavisel = datavisel;
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.coaches = coaches;

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

    public CoachesModel getCoaches() {
        return coaches;
    }

    public void setCoaches(CoachesModel coaches) {
        this.coaches = coaches;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
