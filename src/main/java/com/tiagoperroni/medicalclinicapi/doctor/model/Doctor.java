package com.tiagoperroni.medicalclinicapi.doctor.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_DOCTOR")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C_NAME")
    @NotNull(message = "O campo NAME, deve ser preenchido.")
    private String name;

    @Column(name = "C_CRM")
    @NotEmpty(message = "O campo CRM, deve ser preenchido.")
    private String crm;

    @Column(name = "C_REGISTER_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;


    public Doctor() {
    }

    public Doctor(Long id, String name, String crm, LocalDate registerDate) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.registerDate = registerDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
