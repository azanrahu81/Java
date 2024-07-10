import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem 
{

    // Nested class for Patient
    static class Patient 
    {
        private String name;
        private int patientId;
        private String disease;

        public Patient(String name, int patientId, String disease) 
        {
            this.name = name;
            this.patientId = patientId;
            this.disease = disease;
        }

        public String getName() 
        {
            return name;
        }

        public int getPatientId() {
            return patientId;
        }

        public String getDisease() {
            return disease;
        }
    }

    // Nested class for Doctor
    static class Doctor {
        private String name;
        private int doctorId;
        private String specialization;

        public Doctor(String name, int doctorId, String specialization)
         {
            this.name = name;
            this.doctorId = doctorId;
            this.specialization = specialization;
        }

        public String getName() 
        {
            return name;
        }

        public int getDoctorId() 
        {
            return doctorId;
        }

        public String getSpecialization()
         {
            return specialization;
        }
    }

    // Main class containing patient and doctor lists and main methods
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;

    public HospitalManagementSystem()
     {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
    }

    public void addPatient(String name, int patientId, String disease) 
    {
        Patient patient = new Patient(name, patientId, disease);
        patients.add(patient);
        System.out.println("Patient added successfully!");
    }

    public void addDoctor(String name, int doctorId, String specialization) 
    {
        Doctor doctor = new Doctor(name, doctorId, specialization);
        doctors.add(doctor);
        System.out.println("Doctor added successfully!");
    }

    public void viewPatientDetails(int patientId)
     {
        for (Patient patient : patients) 
        {
            if (patient.getPatientId() == patientId) 
            {
                System.out.println("Patient Name: " + patient.getName());
                System.out.println("Patient ID: " + patient.getPatientId());
                System.out.println("Disease: " + patient.getDisease());
                return;
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
    }

    public void viewDoctorDetails(int doctorId) 
    {
        for (Doctor doctor : doctors) 
        {
            if (doctor.getDoctorId() == doctorId) 
            {
                System.out.println("Doctor Name: " + doctor.getName());
                System.out.println("Doctor ID: " + doctor.getDoctorId());
                System.out.println("Specialization: " + doctor.getSpecialization());
                return;
            }
        }
        System.out.println("Doctor with ID " + doctorId + " not found.");
    }

    public static void main(String[] args)
     {
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\n1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. View Patient Details");
            System.out.println("4. View Doctor Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter disease: ");
                    String disease = scanner.nextLine();
                    hms.addPatient(patientName, patientId, disease);
                    break;
                case 2:
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter doctor ID: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine();
                    hms.addDoctor(doctorName, doctorId, specialization);
                    break;
                case 3:
                    System.out.print("Enter patient ID to view details: ");
                    patientId = scanner.nextInt();
                    hms.viewPatientDetails(patientId);
                    break;
                case 4:
                    System.out.print("Enter doctor ID to view details: ");
                    doctorId = scanner.nextInt();
                    hms.viewDoctorDetails(doctorId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
