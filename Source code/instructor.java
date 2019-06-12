/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
class instructor {
    private String instructor_no,name,address,nic_no,contact_no,email,starting_date,faculty;
    
    

    public instructor(String instructor_no, String name, String address, String nic_no, String contact_no, String email, String starting_date, String faculty) {
        this.instructor_no = instructor_no;
        this.name = name;
        this.address = address;
        this.nic_no = nic_no;
        this.contact_no = contact_no;
        this.email = email;
        this.starting_date = starting_date;
        this.faculty = faculty;
    }

    public String getInstructor_no() {
        return instructor_no;
    }

    public void setInstructor_no(String lecturer_no) {
        this.instructor_no = lecturer_no;
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

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
}
