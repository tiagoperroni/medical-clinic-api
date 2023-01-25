package com.tiagoperroni.medicalclinicapi.doctor.service;

import com.tiagoperroni.medicalclinicapi.doctor.model.Doctor;
import com.tiagoperroni.medicalclinicapi.doctor.repository.DoctorRepository;
import com.tiagoperroni.medicalclinicapi.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorRepository{

    private Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Doctor> getAll() {
        Query query = this.entityManager.createNativeQuery(
                "SELECT doc.id, doc.C_NAME, doc.C_CRM, doc.C_REGISTER_DATE FROM TB_DOCTOR as doc"
        );

        List<Object[]> objs = query.getResultList();

        if (objs.isEmpty()) {
            return null;
        }

        return convertObjectToDoctorList(objs);
    }

    @Override
    public Doctor findById(Long id) {
        Query query = this.entityManager.createNativeQuery(
                "SELECT id, C_NAME, C_CRM, C_REGISTER_DATE FROM TB_DOCTOR WHERE id = :id"
        )
                .setParameter("id", id);

        List<Object[]> objects = query.getResultList();

        if (objects.isEmpty()) {
            throw new EntityNotFoundException(String.format("O médico de id, %s não foi encontrado.", id));
        }

        return convertObjectsToDoctor(objects);
    }

    @Transactional
    @Override
    public void save(Doctor doctor) {
        int query = this.entityManager.createNativeQuery(
                "INSERT INTO TB_DOCTOR(id, C_NAME, C_CRM, C_REGISTER_DATE) VALUES (:id, :name, :crm, :date)"
        )
                .setParameter("id", null)
                .setParameter("name", doctor.getName())
                .setParameter("crm",doctor.getCrm())
                .setParameter("date", LocalDate.now())
                .executeUpdate();

        if (query > 0) {
            this.logger.info(String.format("O Doutor %s, foi salvo com sucesso.", doctor.getName()));
        }
    }

    @Transactional
    @Override
    public void update(Long id, Doctor doctor) {

        this.findById(id); // verify if the doctor exists

        int query = this.entityManager.createNativeQuery(
                "UPDATE TB_DOCTOR SET C_NAME = :name, C_CRM = :crm WHERE id = :id"
        )
                .setParameter("name", doctor.getName())
                .setParameter("crm", doctor.getCrm())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void delete(Long id) {

        this.findById(id); // verify if the doctor exists

        var query = this.entityManager.createNativeQuery(
                "DELETE FROM TB_DOCTOR WHERE id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
    }

    /**
     * Convert a list of objects to Doctor and save into a list
     * */
    private List<Doctor> convertObjectToDoctorList(List<Object[]> objects) {
        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            var doctor = new Doctor();
            BigInteger id = ((BigInteger) objects.get(i)[0]);
            doctor.setId(id.longValue());
            doctor.setName((String) objects.get(i)[1]);
            doctor.setCrm((String) objects.get(i)[2]);
            Date data = ((Date) objects.get(i)[3]);
            doctor.setRegisterDate(data.toLocalDate());
            doctors.add(doctor);
        }

        return doctors;
    }

    /**
     * Convert a list of objects to a Doctor and return.
     * */
    private Doctor convertObjectsToDoctor(List<Object[]> objects) {
        var doctor = new Doctor();
        BigInteger idDoctor = ((BigInteger) objects.get(0)[0]);
        doctor.setId(idDoctor.longValue());
        doctor.setName((String) objects.get(0)[1]);
        doctor.setCrm((String) objects.get(0)[2]);
        Date data = ((Date) objects.get(0)[3]);
        doctor.setRegisterDate(data.toLocalDate());

        return doctor;
    }
}
