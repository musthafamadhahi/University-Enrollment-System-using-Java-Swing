/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
class degree {
    private String degree_code, degree_title;

    public String getDegree_code() {
        return degree_code;
    }

    public void setDegree_code(String degree_code) {
        this.degree_code = degree_code;
    }

    public String getDegree_title() {
        return degree_title;
    }

    public void setDegree_title(String degree_title) {
        this.degree_title = degree_title;
    }

    public degree(String degree_code, String degree_title) {
        this.degree_code = degree_code;
        this.degree_title = degree_title;
    }
}
