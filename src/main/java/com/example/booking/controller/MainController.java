package com.example.booking.controller;

import com.example.booking.Doctor;
import com.example.booking.DoctorRepository;
import com.example.booking.Patient;
import com.example.booking.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Optional;


//   ********************************88  maincontroller in which we paas the patient id as parameter and return suggested doctors
@Controller
public class MainController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/suggestdoctor")
    public @ResponseBody ArrayList<String>  suggest(@RequestParam(name="patientid") int patientid) {
        Patient patient = patientRepository.findById(patientid).get();
        String city = patient.getCity();
        String symption = patient.getSympton();
        ArrayList<String> list=new ArrayList<>();
        ArrayList<Doctor> doctor=new ArrayList<>();
        if(!(city.equals("delhi") || city.equals("noida") || city.equals("faridabad"))){
           list.add("we are still watching to exapnd your location");
        return list;}
        else if(!(symption.equals("arthritics") || symption.equals("back pain") || symption.equals("tissue injuries") || symption.equals("dysmenorrhea") || symption.equals("skin infection") || symption.equals("skin burn") || symption.equals("ear pain"))){
            list.add("There isn’t any doctor present at your location for your symptom");
            return list;
        }
        else if(symption.equals("arthritis") ||  symption.equals("back pain") ||  symption.equals("tissue injuries")){
            doctor= (ArrayList<Doctor>) doctorRepository.findBySpecialityAndCity("orthopedic",city);
            for(Doctor doctor1:doctor){
                System.out.println(doctor1);
                list.add(doctor1.toString());}
            System.out.println(list);
            System.out.println("gynec");
            if(list.isEmpty()){
                list.add("no such doctor available for this symption");
                return list;}
            return list;
        } else if (symption.equals("skin infection") || symption.equals("skin burn")) {
            doctor= (ArrayList<Doctor>) doctorRepository.findBySpecialityAndCity("gynecology",city);
            for(Doctor doctor1:doctor)
                list.add(doctor1.toString());
            System.out.println("dermato");
            if(list.isEmpty()){
                list.add("no such doctor available for this symption");
            return list;}
            return list;
        }
        else
        {
            doctor= (ArrayList<Doctor>) doctorRepository.findBySpecialityAndCity("ent",city);
            for(Doctor doctor1:doctor)
                list.add(doctor1.toString());
            System.out.println("other");
            if(list.isEmpty()){
                list.add("no such doctor available for this symption");
                return list;
            }
            return list;
        }
    }

}
