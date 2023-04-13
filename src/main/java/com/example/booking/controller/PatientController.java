package com.example.booking.controller;

import com.example.booking.Patient;
import com.example.booking.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
//  *************************  patientcontroller for adding,deleting and fetching all patients
@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    // ******************************* api to add a new patient ************************
    @PostMapping("/addpatient")
    public @ResponseBody Patient addOne(@RequestParam(name="name")String name,@RequestParam(name="city")String city,@RequestParam(name="email")String email,@RequestParam(name="contactno")String contactno,@RequestParam(name="symption")String symption){
       try {
           Patient patient=new Patient();
           if (name.length() < 3)
               throw new Exception();
           patient.setName(name);
           patient.setCity(city);
           EmailVerifier verifier=new EmailVerifier();
           if(!verifier.isValidEmail(email)){
               System.out.println("email");
               throw new Exception();}
           patient.setEmail(email);
           if(contactno.length()!=10){
               System.out.println("CONT");
               throw new Exception();}
           patient.setContactno(contactno);
           if(!(symption.equals("arthritics") || symption.equals("back pain") || symption.equals("tissue injuries") || symption.equals("dysmenorrhea") || symption.equals("skin infection") || symption.equals("skin burn") || symption.equals("ear pain"))){
               System.out.println("symp");
               throw new Exception();}
           patient.setSympton(symption);
           patientRepository.save(patient);
           return patient;
       }
       catch(Exception ex){
          return new Patient();
       }
    }
//  ******************************** api to delete a patient ************************
    @DeleteMapping("/deletepatient")
    public @ResponseBody String delete(@RequestParam(name="patientid")int id){
        try {
            patientRepository.deleteById(id);
            return "deleted";
        }
        catch(Exception ex){
            return "not deleted";
        }
    }
    //  ****************************** api to fetch all patients ************************8
    @GetMapping("/allpatient")
    public @ResponseBody ArrayList<Patient> allPatient(){
        ArrayList<Patient> patients= (ArrayList<Patient>) patientRepository.findAll();
        return patients;
    }
}
