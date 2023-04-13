package com.example.booking.controller;

import com.example.booking.Doctor;
import com.example.booking.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// ************************* doctor controller for adding new doctor,deleting a doctor by id and fetching all doctors
@Controller
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    // **********************  api for adding a new doctor url is localhost:999/adddoc
    @PostMapping("/addoc")
    public @ResponseBody Doctor addOne(@RequestParam(name="name")String name,@RequestParam(name="city")String city,@RequestParam(name="email")String email,@RequestParam(name="contactno")String contactno,@RequestParam(name="speciality")String speciality){
       try {
           Doctor doctor = new Doctor();
           if(name.length()<3)
               throw new Exception();
           doctor.setName(name);
           if (city.equals("noida") && city.equals("delhi") && city.equals("faridabad")){
               System.out.println("city");
               throw new Exception();}
           doctor.setCity(city);
           EmailVerifier ver=new EmailVerifier();
          if(!ver.isValidEmail(email)){
              System.out.println("email");
              throw new Exception();}
          doctor.setEmail(email);
          if(contactno.length() !=10){
              System.out.println("contactno");
              throw new Exception();}
          doctor.setContactno(contactno);
          if(!(speciality.equals("orthopedic") || speciality.equals("gynecology") || speciality.equals("dermatology") || speciality.equals("ent"))){
              System.out.println("spec");
              throw new Exception();}
          doctor.setSpeciality(speciality);
          doctorRepository.save(doctor);
          return doctor;
       }
       catch(Exception ex){
   return new Doctor();
       }
    }
// *********************************** api for deleting a doctor by its id *************************
    @DeleteMapping("/deletedoctor")
    public @ResponseBody String deleteDoctor(int id){
        try {
            Doctor doctor = doctorRepository.findById(id).get();
            doctorRepository.delete(doctor);
            return "doctor deleted";
        }
        catch(Exception ex){
            return "not deleted";
        }
    }

    // *********************************** api for fetching all doctors
    @GetMapping("/alldoc")
    public @ResponseBody ArrayList<Doctor> allDoc(){
        ArrayList<Doctor> doctor= (ArrayList<Doctor>) doctorRepository.findAll();
        return doctor;
    }

}
