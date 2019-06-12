/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
class module {
    private String module_code,module_title,type,faculty,degree;
    private int year,semester,credit_value,fee;

     public module(String module_title, int semester, int credit_value, int fee) {
        this.module_title = module_title;
        this.semester = semester;
        this.credit_value = credit_value;
        this.fee = fee;
    }
     
     public module(String module_code, String module_title, int year, int semester, int credit_value, int fee, String type, String faculty, String degree) {
        this.module_code = module_code;
        this.module_title = module_title;
        this.year = year;
        this.semester = semester;
        this.credit_value = credit_value;
        this.fee = fee;
        this.type = type;
        this.faculty = faculty;
        this.degree = degree;
    }

    public module(String module_code, String module_title) {
        this.module_code = module_code;
        this.module_title = module_title;
    }

    public module(String module_code, String module_title, int year, int semester, int credit_value, int fee, String type, String degree) {
        this.module_code = module_code;
        this.module_title = module_title;
        this.year = year;
        this.semester = semester;
        this.credit_value = credit_value;
        this.fee = fee;
        this.type = type;
        this.degree = degree;
    }

    public String getModule_code() {
        return module_code;
    }

    public void setModule_code(String modue_code) {
        this.module_code = modue_code;
    }

    public String getModule_title() {
        return module_title;
    }

    public void setModule_title(String module_title) {
        this.module_title = module_title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit_value() {
        return credit_value;
    }

    public void setCredit_value(int credit_value) {
        this.credit_value = credit_value;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    
    
    
}
