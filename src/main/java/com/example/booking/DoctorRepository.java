package com.example.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    // ***************** method to get all doctors whose speciality and city matches with the parameter passed ******************s
    List<Doctor> findBySpecialityAndCity(String a, String b);
}
