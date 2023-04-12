import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*TO DO:
 * 1. Change Procedure Duration TextField to a formatted time/date situation
 * 2. Change add Interaction  Date and Time TextFields to formatted time/date situations
 * 3. Figure out what needs to go in Add Patient Procedure
 */

public class ClinicFrame extends JFrame{

    // Layout, Constraints, Janel;
    GridBagLayout layout;
    GridBagConstraints lManager;

    // standard buttons
    private JButton submitButton = new JButton("Submit");
    private JButton homeButton = new JButton("Home");
    private JButton clearButton = new JButton("Clear");
    private JButton addButton = new JButton("Add");

    // Current Page Enum
    enum CurrPage {
        HOME_PAGE,
        PATIENT,
        DEPARTMENT,
        PROCEDURE,
        DOCTOR,
        MEDICATION,
        INTERACTION,
        PROCEDURE_,
        PATIENT_MEDICATION,
        HEALTH_RECORD,
        DEPARTMENT_SERVICES,
        DOCTOR_PROCEDURES
    }
    CurrPage currentPage;

    // Current Page list of textfields
    ArrayList<JTextField> currentPageTextFields;

    // Home Page fields HP___________
    private JLabel HPLabel = new JLabel("Welcome to Dr. Kanewala's Clinic");
    private JLabel HPUserSelection= new JLabel("What would you like to do?");
    private String[] HPoptions = {"","Add New Patient", "Add New Department", "Add New Procedure", "Add New Doctor",
            "Add New Medication", "Add New Interaction", "Add Patient Procedure", "Add Patient Medications",
            "View Patient Health Record", "View Department Services", "View Procedures Completed by Doctors"};
    private  JComboBox<String> HPUserSelectionOptions = new JComboBox<String>(HPoptions);

    // Person Labels and Text fields
    private JLabel SSNLabel = new JLabel("Social Security Number");
    private JLabel firstNameLabel = new JLabel("First Name");
    private JLabel mInitialLabel = new JLabel("Middle Initial");
    private JLabel lastNameLabel = new JLabel("Last Name");
    private JLabel currAddressLabel = new JLabel("Current Address");
    private JLabel currPhoneLabel = new JLabel("Current Phone Number");
    private JLabel permPhoneLabel = new JLabel("Permanent Phone Number");
    private JLabel DOBLabel = new JLabel("Date of Birth");
    private JLabel sexLabel = new JLabel("Sex");
    private JLabel permAddressLabel = new JLabel("Permanent Address:");
    private JLabel streetLabel = new JLabel("Street");
    private JLabel cityLabel = new JLabel("City");
    private JLabel stateLabel = new JLabel("State");
    private JLabel zipLabel = new JLabel("ZipCode");
    private JTextField SSNTextField = new JTextField();
    private JTextField firstNameTextField = new JTextField();
    private JTextField mInitialTextField = new JTextField();
    private JTextField lastNameTextField = new JTextField();
    private JTextField currAddressTextField = new JTextField();
    private JTextField currPhoneTextField = new JTextField();
    private JTextField permPhoneTextField = new JTextField();
    private JTextField DOBTextField = new JTextField();
    private JTextField sexTextField = new JTextField();
    private JTextField streetTextField = new JTextField();
    private JTextField cityTextField = new JTextField();
    private JTextField stateTextField = new JTextField();
    private JTextField zipTextField = new JTextField();

    // Patient Labels and Text fields
    private JLabel patientIDLabel = new JLabel("Patient ID");
    private JLabel patientConditionLabel = new JLabel("Patient Condition");
    private JLabel primaryCareLabel = new JLabel("Primary Care Doctor");
    private JLabel secondaryCareLabel = new JLabel("Secondary Care Doctor");
    private JTextField patientIDTextField = new JTextField();
    private JTextField patientConditionTextField = new JTextField();
    private JTextField primaryCareTextField = new JTextField();
    private JTextField secondaryCareTextField = new JTextField();

    // Department Labels and Text fields
    private JLabel deptNameLabel = new JLabel("Department Name");
    private JLabel deptCodeLabel = new JLabel("Department Code");
    private JLabel deptPhoneLabel = new JLabel("Department Office Phone");
    private JLabel deptOfficeLabel = new JLabel("Department Office Number");
    private JTextField deptNameTextField = new JTextField();
    private JTextField deptCodeTextField = new JTextField();
    private JTextField deptPhoneTextField = new JTextField();
    private JTextField deptOfficeTextField = new JTextField();
    
    // Procedure Labels and Text fields
    private JLabel procNumberLabel = new JLabel("Procedure Number");
    private JLabel procNameLabel = new JLabel("Procedure Name");
    private JLabel procDescLabel = new JLabel("Procedure Description");
    private JLabel procDurationLabel = new JLabel("Procedure Duration");
    private JTextField procNumberTextField = new JTextField();
    private JTextField procNameTextField = new JTextField();
    private JTextField procDescTextField = new JTextField();
    private JTextField procDurationTextField = new JTextField();

    // Doctor Labels and Text fields
    private JLabel doctorIDLabel = new JLabel("Doctor ID");
    private JLabel doctorDepartmentLabel = new JLabel("Department");
    private JTextField doctorIDTextField = new JTextField();
    private JTextField doctorDepartmentTextField = new JTextField();

    // Medication Labels and Text fields
    private JLabel medNameLabel = new JLabel("Medication Name");
    private JLabel medDescLabel = new JLabel("Medication Description");
    private JLabel medManufacturerLabel = new JLabel("Medication Manufacturer");
    private JTextField medNameTextField = new JTextField();
    private JTextField medDescTextField = new JTextField();
    private JTextField medManufacturerTextField = new JTextField();
    
    // Interaction Labels and Text fields
    private JLabel interIDLabel = new JLabel("Interaction ID");
    private JLabel interDescLabel = new JLabel("Interaction Description");
    private JLabel interDateLabel = new JLabel("Interaction Date");
    private JLabel interTimeLabel = new JLabel("Interaction Time");
    private JTextField interIDTextField = new JTextField();
    private JTextField interDescTextField = new JTextField();
    private JTextField interDateField = new JTextField();
    private JTextField interTimeTextField = new JTextField();
    
    // Add Procedure Labels and Text fields
    
 
    // Prompt for editing Patient Medications
    private JLabel editPatientMedsLabel1 = new JLabel("Enter Patient ID below");
    private JLabel editPatientMedsLabel2 = new JLabel("Make corrections as necessary and select 'Submit' to save changes.");
    private JTextField patientIDforMedsTextField = new JTextField(20);

    // Prompt for viewing Patient HeathRecord
    private JLabel viewPatientLabel = new JLabel("Enter Patient ID below to view Health Record.");
    private JTextField patientIDforRecordTextField = new JTextField(20);

    // Prompt for viewing Department Services
    private JLabel viewDeptSvcLabel = new JLabel("Enter Department number below to view " +
            "services provided by that department.");
    private JTextField deptNumforServicesTextField = new JTextField(20);

    // Prompt for viewing Doctor Procedures
    private JLabel viewDrProcLabel = new JLabel("Enter Doctor ID below to see Procedures" +
            " performed by that Doctor.");
    private JTextField docIDforProceduresTextField = new JTextField(20);



    public ClinicFrame() {
        // Connect to server
//        try {
//            //Load JDBC driver
//            Class.forname("Oracle.jdbc.driver.oracleDriver");
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }
//
//        String serverName = "cisvm-oracle.unfcsd.unf.edu";
//        String portNumber = "1521";
//        String sid = "orcl";
//        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
//        String username ="G6"
//        String password ="G6Spring2023"
//        try {
//            //Create a connection using given url,username and password
//            Connection conn = DriverManager.getConnection(url, username, password);
//
//            boolean reachable = conn.isValid(10);/
//
//            if(reachable) {
//
//                System.out.println("Successfully connected");
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        setTitle("Dr. Kanewala's Clinic System");
        InnerActionListener listener = new InnerActionListener();
        clearButton.addActionListener(listener);
        submitButton.addActionListener(listener);
        homeButton.addActionListener(listener);
        homePage();

    }   // end of constructor

    /**
     * Called anytime a user selects Home or Submit Buttons.  Resets the frame and creates new Layout Manager
     */
    private void resetLayout() {
        getContentPane().removeAll();
        getContentPane().repaint();
        this.setLayout(new GridBagLayout());
        lManager = new GridBagConstraints();

        lManager.insets = new Insets(0,10,10,10);
        buildTextFieldList();
    }

    /**
     * Builds an iterable arraylist of all text fields in the current view, in the order they appear
     */
    private void buildTextFieldList() {
        currentPageTextFields = new ArrayList<>();
        switch (currentPage) {
            case HOME_PAGE:
                break;
            case PATIENT:
                currentPageTextFields.add(patientIDTextField);
                personArrayList();
                currentPageTextFields.add(patientConditionTextField);
                currentPageTextFields.add(primaryCareTextField);
                currentPageTextFields.add(secondaryCareTextField);
                break;
            case DEPARTMENT:
            	currentPageTextFields.add(deptNameTextField);
                currentPageTextFields.add(deptCodeTextField);
                currentPageTextFields.add(deptPhoneTextField);
                currentPageTextFields.add(deptOfficeTextField);
                break;
            case PROCEDURE:
            	currentPageTextFields.add(procNumberTextField);
                currentPageTextFields.add(procNameTextField);
                currentPageTextFields.add(procDescTextField);
                currentPageTextFields.add(procDurationTextField);
                break;
            case DOCTOR:
                currentPageTextFields.add(doctorIDTextField);
                personArrayList();
                currentPageTextFields.add(doctorDepartmentTextField);
                break;
            case MEDICATION:
            	currentPageTextFields.add(medNameTextField);
                currentPageTextFields.add(medDescTextField);
                currentPageTextFields.add(medManufacturerTextField);
                break;
            case INTERACTION:
            	currentPageTextFields.add(interIDTextField);
                currentPageTextFields.add(interDescTextField);
                currentPageTextFields.add(interDateField);
                currentPageTextFields.add(interTimeTextField);
                break;
            case PROCEDURE_:
                break;
            case PATIENT_MEDICATION:
                break;
            case HEALTH_RECORD:
                break;
            case DEPARTMENT_SERVICES:
                break;
            case DOCTOR_PROCEDURES:
                break;
        }
        for (var TF : currentPageTextFields) {
            TF.setColumns(20);
        }
    }
    /**
     * adds the person text fields to the current textfields arraylist
     */
    private void personArrayList() {
        currentPageTextFields.add(SSNTextField);
        currentPageTextFields.add(firstNameTextField);
        currentPageTextFields.add(mInitialTextField);
        currentPageTextFields.add(lastNameTextField);
        currentPageTextFields.add(currAddressTextField);
        currentPageTextFields.add(currPhoneTextField);
        currentPageTextFields.add(permPhoneTextField);
        currentPageTextFields.add(DOBTextField);
        currentPageTextFields.add(sexTextField);
        currentPageTextFields.add(streetTextField);
        currentPageTextFields.add(cityTextField);
        currentPageTextFields.add(stateTextField);
        currentPageTextFields.add(zipTextField);
    }
    /**
     * Displays the home page
     */
    private void homePage() {
        currentPage = CurrPage.HOME_PAGE;
        resetLayout();

        lManager.gridx = 0;
        lManager.gridy = 0;
        lManager.gridwidth = 2;
        this.add(HPLabel, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(HPUserSelection, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(HPUserSelectionOptions, lManager);

        lManager.gridx = 1;
        lManager.gridy = 4;
        this.add(submitButton, lManager);
        submitButton.setName("Submit");

        setSize(450,450);
    }

    /**
     * Displays the Person data (standard to Patients and Doctors)
     * @param i integer offset from the top of the screen.  Increase i whenever space is needed for extra textfields,
     *          labels or other information.  Example, Patient ID.
     */
    private void person(int i) {
        // i provides a buffer from the top to add text fields, ID's, information etc.
        resetLayout();
        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);

        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = i + 1;
        this.add(SSNLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 1;
        this.add(SSNTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 2;
        this.add(firstNameLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 2;
        this.add(firstNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 3;
        this.add(mInitialLabel, lManager);

        lManager.gridx = 2;
        lManager.gridy = i + 3;
        this.add(mInitialTextField, lManager);
        mInitialTextField.setColumns(5);

        lManager.gridx = 0;
        lManager.gridy = i + 4;
        this.add(lastNameLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 4;
        this.add(lastNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 5;
        this.add(currAddressLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 5;
        this.add(currAddressTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 6;
        this.add(currPhoneLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 6;
        this.add(currPhoneTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 7;
        this.add(permPhoneLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 7;
        this.add(permPhoneTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 8;
        this.add(DOBLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 8;
        this.add(DOBTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 9;
        this.add(sexLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 9;
        this.add(sexTextField, lManager);

        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = i + 10;
        this.add(permAddressLabel, lManager);

        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = i + 11;
        this.add(streetLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 11;
        this.add(streetTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 12;
        this.add(cityLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 12;
        this.add(cityTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = i + 13;
        this.add(stateLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 13;
        this.add(stateTextField, lManager);
        stateTextField.setColumns(5);

        lManager.gridx = 0;
        lManager.gridy = i + 14;
        this.add(zipLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i + 14;
        this.add(zipTextField, lManager);

    }

    /**
     * Displays Patient Information
     * @param i integer offset from the top of the screen.  Increase i whenever space is needed for extra textfields,
     *      *          labels or other information.  Example, Prompt for user to enter Patient ID (viewPatient method).
     */
    private void addPatient(int i) {
        currentPage = CurrPage.PATIENT;
        person(2);

        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(patientIDLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 1;
        this.add(patientIDTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 16 + i;
        this.add(patientConditionLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 16 + i;
        this.add(patientConditionTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 17 + i;
        this.add(primaryCareLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 17 + i;
        this.add(primaryCareTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 18 + i;
        this.add(secondaryCareLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 18 + i;
        this.add(secondaryCareTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 19 + i;
        this.add(clearButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 19 + i;
        this.add(submitButton, lManager);
        
        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 20;
        this.add(addButton, lManager);

        setSize(550,800);
    }
    /**
     * Adding a Department
     */
    private void addDepartment(){
    	currentPage = CurrPage.DEPARTMENT;
    	resetLayout();
    	
    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);   

        lManager.gridwidth = 1;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(deptNameLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 1;
        this.add(deptNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(deptCodeLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 2;
        this.add(deptCodeTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(deptPhoneLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 3;
        this.add(deptPhoneTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(deptOfficeLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 4;
        this.add(deptOfficeTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 5;
        this.add(submitButton, lManager);
        
        setSize(550,800);
        
    }
    /**
     * Adding a Procedure
     */
    private void addProcedure(){
    	currentPage = CurrPage.PROCEDURE;
    	resetLayout();
    
    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);
    	
        lManager.gridx = 0;
        lManager.gridy = 1;
        lManager.gridwidth = 1;
        this.add(procNumberLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 1;
        this.add(procNumberTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(procNameLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 3;
        this.add(procNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(procDescLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 4;
        this.add(procDescTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(procDurationLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 5;
        this.add(procDurationTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 6;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 6;
        this.add(submitButton, lManager);

        setSize(550,800);
    	

    }
    /**
     * Displays Doctor Information
     * @param i integer offset from the top of the screen.  Increase i whenever space is needed for extra textfields,
     *      *          labels or other information.  Example, Prompt for user to enter
     *                  Doctor ID (viewDoctorProcedures method).
     */
    private void addDoctor(int i){
        currentPage = CurrPage.DOCTOR;

        person(2);

        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(doctorIDLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 1;
        this.add(doctorIDTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 16 + i;
        this.add(doctorDepartmentLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 16 + i;
        this.add(doctorDepartmentTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 17 + i;
        this.add(clearButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 17 + i;
        this.add(submitButton, lManager);

        setSize(550,800);
    }
    /**
     * Adding a Medication
     */
    private void addMedication(){
    	currentPage = CurrPage.MEDICATION;
    	resetLayout();

    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);

        lManager.gridwidth = 1;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(medNameLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 1;
        this.add(medNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(medDescLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 2;
        this.add(medDescTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(medManufacturerLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 3;
        this.add(medManufacturerTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 4;
        this.add(submitButton, lManager);

        setSize(550,800);
    }
    /**
     * Adding an Interaction
     */
    private void addInteraction(){
    	currentPage = CurrPage.INTERACTION;
    	resetLayout();

    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);
    	
        lManager.gridwidth = 1;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(interIDLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 1;
        this.add(interIDTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(interDescLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 2;
        this.add(interDescTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(interDateLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 3;
        this.add(interDateField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(interTimeLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 4;
        this.add(interTimeTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 5;
        this.add(submitButton, lManager);

        setSize(550,800);
    	
    }
    /**
     * Adding a Procedure to a Specific Patient's Health Records
     */
    private void addProceduretoPatient(){
    	currentPage = CurrPage.PROCEDURE_;
    	resetLayout();
   
    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(clearButton, lManager);
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(submitButton, lManager);
        
        setSize(550,800);
    }
    /**
     * Edit Patient Medications
     */
    private void editPatientMedication(){
    	currentPage = CurrPage.PATIENT_MEDICATION;
    	resetLayout();

    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);
       
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(editPatientMedsLabel1, lManager);
        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(editPatientMedsLabel2, lManager);
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(patientIDforMedsTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(clearButton, lManager);
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(submitButton, lManager);
        
        setSize(550,800);
    }
    /**
     *View Patient Health Record
     */
    private void viewPatient(){
    	currentPage = CurrPage.HEALTH_RECORD;
    	resetLayout();

    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);	
        
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(viewPatientLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(patientIDforRecordTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(clearButton, lManager);
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(submitButton, lManager);
        
        
        setSize(550,800);
    }
    /**
     * Displays the Services that the Department offers
     */
    private void viewDepartmentServices(){
    	currentPage = CurrPage.DEPARTMENT_SERVICES;
    	resetLayout();
    
    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(viewDeptSvcLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(deptNumforServicesTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(clearButton, lManager);
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(submitButton, lManager);
        
        
        setSize(550,800);
    }
    /**
     * View the Procedures any Doctor has done by Doctor ID
     */
    private void viewDoctorProcedures(){
    	currentPage = CurrPage.DOCTOR_PROCEDURES;
    	resetLayout();

    	lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);
        
       
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(viewDrProcLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(docIDforProceduresTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(clearButton, lManager);
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(submitButton, lManager);
        
        
        setSize(550,800);
    }

    class InnerActionListener implements ActionListener {
        /**
         * Overrides actionPerformed method of the ActionListener interface
         * Listens for user click and stores user data and clears the form if applicable
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // determine source of button click
            if (e.getSource() == clearButton) {
                for (var TF : currentPageTextFields) {
                    TF.setText("");
                }

            } else if (e.getSource() == submitButton) {
                switch (currentPage) {
                    case HOME_PAGE:
                        this.homePageSelection(HPUserSelectionOptions.getSelectedIndex());
                        break;
                    case PATIENT:
                        break;
                    case DEPARTMENT:
                        break;
                    case PROCEDURE:
                        break;
                    case DOCTOR:
                        break;
                    case MEDICATION:
                        break;
                    case INTERACTION:
                        break;
                    case PROCEDURE_:
                        break;
                    case PATIENT_MEDICATION:
                        break;
                    case HEALTH_RECORD:
                        break;
                    case DEPARTMENT_SERVICES:
                        break;
                    case DOCTOR_PROCEDURES:
                        break;
                }
            } else if (e.getSource() == homeButton) {
                ClinicFrame.this.homePage();
            }
        }

        private void homePageSelection(int index) {
            switch (index){
                case 1:
                    ClinicFrame.this.addPatient(2);
                    break;
                case 2:
                    ClinicFrame.this.addDepartment();
                    break;
                case 3:
                    ClinicFrame.this.addProcedure();
                    break;
                case 4:
                    ClinicFrame.this.addDoctor(2);
                    break;
                case 5:
                    ClinicFrame.this.addMedication();
                    break;
                case 6:
                    ClinicFrame.this.addInteraction();
                    break;
                case 7:
                    ClinicFrame.this.addProceduretoPatient();
                    break;
                case 8:
                    ClinicFrame.this.editPatientMedication();
                    break;
                case 9:
                    ClinicFrame.this.viewPatient();
                    break;
                case 10:
                    ClinicFrame.this.viewDepartmentServices();
                    break;
                case 11:
                    ClinicFrame.this.viewDoctorProcedures();
                default:
                    break;
            }

        }
    }
}
