/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
public class postgraduate_student extends student {
    private String previous_qualification_type,institute,year_of_completion;
    
    public postgraduate_student(String previous_qualification_type, String institute, String year_of_completion) {
        this.previous_qualification_type = previous_qualification_type;
        this.institute = institute;
        this.year_of_completion = year_of_completion;
    }
    
    public postgraduate_student(String register_no, String name, String address, String nic_no, String contact_no, String email, String dob, String gender, String admission_date, String degree,String previous_qualification_type, String institute, String year_of_completion) {
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
        this.previous_qualification_type = previous_qualification_type;
        this.institute = institute;
        this.year_of_completion = year_of_completion;
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

    public String getPrevious_qualification_type() {
        return previous_qualification_type;
    }

    public void setPrevious_qualification_type(String previous_qualification_type) {
        this.previous_qualification_type = previous_qualification_type;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getYear_of_completion() {
        return year_of_completion;
    }

    public void setYear_of_completion(String year_of_completion) {
        this.year_of_completion = year_of_completion;
    }

}
