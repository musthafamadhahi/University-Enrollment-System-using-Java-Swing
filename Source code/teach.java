/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Musthafa
 */
class teach {
    private String lecturer_no, module_code, day, venue, start_time, end_time;

    public teach(String lecturer_no, String module_code, String day, String venue, String start_time, String end_time) {
        this.lecturer_no = lecturer_no;
        this.module_code = module_code;
        this.day = day;
        this.venue = venue;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getLecturer_no() {
        return lecturer_no;
    }

    public void setLecturer_no(String lecturer_no) {
        this.lecturer_no = lecturer_no;
    }

    public String getModule_code() {
        return module_code;
    }

    public void setModule_code(String module_code) {
        this.module_code = module_code;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
