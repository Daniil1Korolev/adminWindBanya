package com.example.marketdota.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "hero")
public class HeroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина названия должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "attrib is required")
    @Size(max = 30, message = "Длина названия должна быть не больше 30 символов")
    @Column(name = "attrib")
    private String attrib;

    @OneToMany (mappedBy = "hero", fetch = FetchType.EAGER)
    private Collection<AnTrainingModel> antraining;

    public HeroModel() {
    }

    public HeroModel(long id, String name, String attrib) {
        this.id = id;
        this.name = name;
        this.attrib = attrib;
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
    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }
}
