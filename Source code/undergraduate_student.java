/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
public class undergraduate_student extends student {
    private String intake,subject1,subject1_result,subject2,subject2_result,subject3,subject3_result,english_result,district_rank,island_rank;
    
    public undergraduate_student(String intake, String subject1, String subject1_result, String subject2, String subject2_result, String subject3, String subject3_result, String english_result, String district_rank, String island_rank) {
        this.intake = intake;
        this.subject1 = subject1;
        this.subject1_result = subject1_result;
        this.subject2 = subject2;
        this.subject2_result = subject2_result;
        this.subject3 = subject3;
        this.subject3_result = subject3_result;
        this.english_result = english_result;
        this.district_rank = district_rank;
        this.island_rank = island_rank;
    }
    
    public undergraduate_student(String register_no, String name, String address, String nic_no, String contact_no, String email, String dob, String gender, String admission_date,  String degree,String intake, String subject1, String subject1_result, String subject2, String subject2_result, String subject3, String subject3_result, String english_result, String district_rank, String island_rank) {
        this.register_no = register_no;
        this.name = name;
        this.address = address;
        this.nic_no = nic_no;
        this.contact_no = contact_no;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.admission_date = admission_date;
        this.degree = degree;
        this.intake = intake;
        this.subject1 = subject1;
        this.subject1_result = subject1_result;
        this.subject2 = subject2;
        this.subject2_result = subject2_result;
        this.subject3 = subject3;
        this.subject3_result = subject3_result;
        this.english_result = english_result;
        this.district_rank = district_rank;
        this.island_rank = island_rank;
    }

    public String getRegister_no() {
        return register_no;
    }

    public void setRegister_no(String register_no) {
        this.register_no = register_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic_no() {
        return nic_no;
    }

    public void setNic_no(String nic_no) {
        this.nic_no = nic_no;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject1_result() {
        return subject1_result;
    }

    public void setSubject1_result(String subject1_result) {
        this.subject1_result = subject1_result;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject2_result() {
        return subject2_result;
    }

    public void setSubject2_result(String subject2_result) {
        this.subject2_result = subject2_result;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public String getSubject3_result() {
        return subject3_result;
    }

    public void setSubject3_result(String subject3_result) {
        this.subject3_result = subject3_result;
    }

    public String getEnglish_result() {
        return english_result;
    }

    public void setEnglish_result(String english_result) {
        this.english_result = english_result;
    }

    public String getDistrict_rank() {
        return district_rank;
    }

    public void setDistrict_rank(String district_rank) {
        this.district_rank = district_rank;
    }

    public String getIsland_rank() {
        return island_rank;
    }

    public void setIsland_rank(String island_rank) {
        this.island_rank = island_rank;
    }
    
}
