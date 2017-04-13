package whsct.test;

/**
 * Created by Peter on 12/04/2017.
 */

public class Doctor {
    //Variables
    private String doctorNumber;
    private String doctorEmail;


    // Default constructor
    public Doctor() {
    }

    // Overloaded Constructor
    public Doctor(String docNumber, String docEmail){
        this.doctorNumber = docNumber;
        this.doctorEmail = docEmail;
    }

    // Getters and Setters

    public String getDoctorNumber() {
        return doctorNumber;
    }


    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getDoctorName() {
        return doctorEmail;
    }

    public void setDoctorName(String doctorName) {
        this.doctorEmail = doctorName;
    }
}
