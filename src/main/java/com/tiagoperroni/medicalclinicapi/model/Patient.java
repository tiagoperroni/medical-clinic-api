package com.tiagoperroni.medicalclinicapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_PATIENT")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C_NAME", length = 100)
    @NotEmpty(message = "O campo NOME, deve ser preenchido.")
    private String name;

    @Column(name = "C_CPF", unique = true)
    @CPF(message = "O CPF é inválido, verifique e tente novamente.")
    @NotEmpty(message = "O campo CPF deve ser preenchido.")
    private String cpf;

    @Column(name = "C_AGE")
    @NotNull(message = "O campo IDADE, deve ser preenchido. ")
    private Integer age;

    @Column(name = "C_REGISTER_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;

    public Patient() {}

    public Patient(Long id, String name, String cpf, Integer age, LocalDate registerDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        Patient patient = (Patient) o;
        return id.equals(patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
