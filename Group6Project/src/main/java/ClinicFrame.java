import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/*TO DO:
 * x. Change Procedure Duration TextField to a formatted time/date situation
 * x. Change add Interaction  Date and Time TextFields to formatted time/date situations
 * x. Figure out what needs to go in Add Patient Procedure
 * x. Find out how to add procedures to doctors
 * x. Check all fields that have specific formatting requirements
 *      -> per StringChecker
 * 6. update character limit for SSN and other req fields for Health Record
 * 7. Call add_____SQL methods for respective pages when submit button pressed
 * 8. Generate Queries for pages that request information
 * 9. Call query_______ methods for respective pages when submit button pressed
 * 10. fix submit button switch cases
 * 11. TEST TEST TEST
 */

public class ClinicFrame extends JFrame{

    // Connection Variable
    Connection conn;

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
            "View Patient Health Record", "View Department Services", "View Procedures completed by Doctors"};
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
    private JLabel patientHeadingLabel = new JLabel("Enter new Patient Information below");
    private JLabel patientIDLabel = new JLabel("Patient ID");
    private JLabel patientConditionLabel = new JLabel("Patient Condition");
    private JLabel primaryCareLabel = new JLabel("Primary Care Doctor");
    private JLabel secondaryCareLabel = new JLabel("Secondary Care Doctor");
    private JTextField patientIDTextField = new JTextField();
    private JTextField patientConditionTextField = new JTextField();
    private JTextField primaryCareTextField = new JTextField();
    private JTextField secondaryCareTextField = new JTextField();

    // Department Labels and Text fields
    private JLabel deptCodeLabel = new JLabel("Department Code");
    private JLabel deptNameLabel = new JLabel("Department Name");
    private JLabel deptOfficeLabel = new JLabel("Department Office Number");
    private JLabel deptPhoneLabel = new JLabel("Department Office Phone");
    private JLabel deptHeadLabel = new JLabel("Department Head");
    private JTextField deptCodeTextField = new JTextField();
    private JTextField deptNameTextField = new JTextField();
    private JTextField deptOfficeTextField = new JTextField();
    private JTextField deptPhoneTextField = new JTextField();
    private JTextField deptHeadTextField = new JTextField();
    
    // Procedure Labels and Text fields
    private JLabel procNumberLabel = new JLabel("Procedure Number");
    private JLabel procNameLabel = new JLabel("Procedure Name");
    private JLabel procDescLabel = new JLabel("Procedure Description");
    private JLabel procDurationLabel = new JLabel("Procedure Duration");
    private JLabel procDepartmentLabel = new JLabel("Procedure Department");
    private JLabel procDrLabel = new JLabel("Procedure Doctor");
    private JTextField procNumberTextField = new JTextField();
    private JTextField procNameTextField = new JTextField();
    private JTextField procDescTextField = new JTextField();
    private JTextField procDurationTextField = new JTextField();
    private JTextField procDepartmentTextField = new JTextField();
    private JTextField procDrTextField = new JTextField();

    // Doctor Labels and Text fields
    private JLabel doctorIDLabel = new JLabel("Doctor ID");
    private JLabel doctorDepartmentLabel = new JLabel("Department");
    private JLabel doctorProceduresLabel = new JLabel("Procedures performed by doctor" +
            "(separated by a comma)");
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
    private JLabel interPatientLabel = new JLabel("Interaction Patient");
    private JLabel interDateLabel = new JLabel("Interaction Date");
    private JLabel interTimeLabel = new JLabel("Interaction Time");
    private JLabel interDescLabel = new JLabel("Interaction Description");
    private JTextField interIDTextField = new JTextField();
    private JTextField interPatientTextField = new JTextField();
    private JTextField interDateField = new JTextField();
    private JTextField interTimeTextField = new JTextField();
    private JTextField interDescTextField = new JTextField();

    // Add Patient Procedure Labels and Text fields
    private JLabel procPatientLabel = new JLabel("Patient ID");
    private JLabel procPatientNumberLabel = new JLabel("Procedure Number");
    private JLabel procNotesLabel = new JLabel("Procedure Notes");
    private JLabel procDateLabel = new JLabel("Procedure Date");
    private JLabel procTimeLabel = new JLabel("Procedure Time");
    private JTextField procPatientTextField = new JTextField();
    private JTextField procPatientNumberTextField = new JTextField();
    private JTextField procNotesTextField = new JTextField();
    private JTextField procDateTextField = new JTextField();
    private JTextField procTimeTextField = new JTextField();

    // Prompt for adding Patient Medications
    private JLabel prescDrLabel = new JLabel("Prescribing Doctor ID");
    private JLabel prescMedLabel = new JLabel("Prescription Medication Name");
    private JLabel prescPatientLabel = new JLabel("Patient ID");
    private JLabel prescDateLabel = new JLabel("Prescription Date");
    private JTextField prescDrTextField = new JTextField();
    private JTextField prescMedTextField = new JTextField();
    private JTextField prescPatientTextField = new JTextField();
    private JTextField prescDateTextField = new JTextField();

    // Prompt for viewing Patient HeathRecord
    private JLabel viewPatientLabel = new JLabel("Enter Patient ID below to view Health Record and click 'Submit'");
    private JLabel patientProceduresLabel = new JLabel("Procedures undergone by Patient");
    private JLabel patientInteractionsLabel = new JLabel("Patient Interactions");
    private JLabel patientMedicaitonsLabel = new JLabel("Patient Medications");

    private JTextArea patientProceduresTextArea = new JTextArea();
    private JTextArea patientInteractionsTextArea = new JTextArea();
    private JTextArea patientMedicationsTextArea = new JTextArea();

    // Prompt for viewing Department Services
    private JLabel viewDeptSvcLabel = new JLabel("Enter Department code to view " +
            "services provided and click 'Submit'");
    private JLabel deptSvcOfferedLabel = new JLabel("This department offers the below services:");
    private JTextField deptSvcCode = new JTextField(20);
    private JTextArea deptSvcOfferedTextArea = new JTextArea();

    // Prompt for viewing Doctor Procedures
    private JLabel viewDrProcLabel = new JLabel("Enter Doctor ID below to see Procedures" +
            " performed and click 'Submit'");
    private JLabel drProcLabel = new JLabel("Procedures performed by this Doctor:");
    private JTextField drIDProcTextField = new JTextField(20);
    private JTextArea drProcTextArea = new JTextArea();

    public ClinicFrame() {
        // Connect to server

        setTitle("Dr. Kanewala's Clinic System");
        InnerActionListener listener = new InnerActionListener();
        clearButton.addActionListener(listener);
        submitButton.addActionListener(listener);
        homeButton.addActionListener(listener);
        setCharLimits();
        homePage();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                disconnectFromDB();
                System.exit(0);
            }
        });

///////////////////////////////// PRIOR TO SUBMISSION //////////////////
        // uncomment the disconnetFromDB method
        // uncomment the below line
        connectToDB();


    }   // end of constructor


    /**
     * Connects to Oracle DB
     */
    private void connectToDB(){
        try {
            //Load JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e){
            System.out.println("Error on line 248");
        }

        String serverName = "cisvm-oracle.unfcsd.unf.edu";
        String portNumber = "1521";
        String sid = "orcl";
        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
        String username ="G6";
        String password ="G6Spring2023";
        try {
            //Create a connection using given url,username and password
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from Oracle DB
     */
    public void disconnectFromDB(){
///////////////////////// uncomment prior to submission //////////////////////////

        try {
            conn.close();
            System.out.println("Disconnected from Database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
            case HEALTH_RECORD:
            case PATIENT:
                currentPageTextFields.add(patientIDTextField);
                personArrayList();
                currentPageTextFields.add(patientConditionTextField);
                currentPageTextFields.add(primaryCareTextField);
                currentPageTextFields.add(secondaryCareTextField);
                break;
            case DEPARTMENT:
                currentPageTextFields.add(deptCodeTextField);
                currentPageTextFields.add(deptNameTextField);
                currentPageTextFields.add(deptOfficeTextField);
                currentPageTextFields.add(deptPhoneTextField);
                currentPageTextFields.add(deptHeadTextField);
                break;
            case PROCEDURE:
            	currentPageTextFields.add(procNumberTextField);
                currentPageTextFields.add(procNameTextField);
                currentPageTextFields.add(procDescTextField);
                currentPageTextFields.add(procDurationTextField);
                currentPageTextFields.add(procDepartmentTextField);
                break;
            case DOCTOR:
                currentPageTextFields.add(doctorIDTextField);
                personArrayList();
                currentPageTextFields.add(doctorDepartmentTextField);
                break;
            case MEDICATION:
            	currentPageTextFields.add(medNameTextField);
                currentPageTextFields.add(medManufacturerTextField);
                currentPageTextFields.add(medDescTextField);
                break;
            case INTERACTION:
            	currentPageTextFields.add(interIDTextField);
                currentPageTextFields.add(interPatientTextField);
                currentPageTextFields.add(interDateField);
                currentPageTextFields.add(interTimeTextField);
                currentPageTextFields.add(interDescTextField);
                break;
            case PROCEDURE_:
                currentPageTextFields.add(procPatientTextField);
                currentPageTextFields.add(procPatientNumberTextField);
                currentPageTextFields.add(procNotesTextField);
                currentPageTextFields.add(procDateTextField);
                currentPageTextFields.add(procTimeTextField);
                currentPageTextFields.add(procDrTextField);
                break;
            case PATIENT_MEDICATION:
                currentPageTextFields.add(prescDrTextField);
                currentPageTextFields.add(prescMedTextField);
                currentPageTextFields.add(prescPatientTextField);
                currentPageTextFields.add(prescDateTextField);
                break;
            case DEPARTMENT_SERVICES:
                currentPageTextFields.add(deptSvcCode);
                break;
            case DOCTOR_PROCEDURES:
                currentPageTextFields.add(drIDProcTextField);
                break;
        }
        for (var TF : currentPageTextFields) {
            TF.setColumns(20);
        }
    }

    /**
     * clears all text fields in current view
     */
    private void clearTextFields() {
        for (var TF : currentPageTextFields) {
            TF.setText("");
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
        person(i);

        lManager.gridx = 0;
        lManager.gridy = i-1;
        this.add(patientIDLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = i-1;
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
        this.add(deptCodeLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 1;
        this.add(deptCodeTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(deptNameLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 2;
        this.add(deptNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(deptOfficeLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 3;
        this.add(deptOfficeTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(deptPhoneLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 4;
        this.add(deptPhoneTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(deptHeadLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 5;
        this.add(deptHeadTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 6;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 6;
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
        this.add(procDepartmentLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 6;
        this.add(procDepartmentTextField, lManager);



        lManager.gridx = 0;
        lManager.gridy = 8;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 8;
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
        lManager.gridy = 17 + i;
        this.add(doctorDepartmentLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 17 + i;
        this.add(doctorDepartmentTextField, lManager);

//        lManager.gridx = 0;
//        lManager.gridy = 18 + i;
//        this.add(doctorProceduresLabel, lManager);
//        lManager.gridx = 2;
//        lManager.gridy = 18 + i;
//        this.add(doctorProceduresTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 19 + i;
        this.add(clearButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 19 + i;
        this.add(submitButton, lManager);

        setSize(700,800);
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
        this.add(medManufacturerLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 2;
        this.add(medManufacturerTextField, lManager);
        
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(medDescLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 3;
        this.add(medDescTextField, lManager);

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
        this.add(interPatientLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 2;
        this.add(interPatientTextField, lManager);

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
        this.add(interDescLabel, lManager);
        lManager.gridx = 1;
        lManager.gridy = 5;
        this.add(interDescTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 6;
        this.add(clearButton, lManager);
        lManager.gridx = 1;
        lManager.gridy = 6;
        this.add(submitButton, lManager);

        setSize(550,800);
    	
    }
    /**
     * Adding a Procedure to a Specific Patient's Health Records
     */
    private void addProceduretoPatient(){
    	currentPage = CurrPage.PROCEDURE_;
    	resetLayout();

    	lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);

        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(procPatientLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 1;
        this.add(procPatientTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(procPatientNumberLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 2;
        this.add(procPatientNumberTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(procNotesLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 3;
        this.add(procNotesTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(procDateLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 4;
        this.add(procDateTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(procTimeLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 5;
        this.add(procTimeTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 6;
        this.add(procDrLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 6;
        this.add(procDrTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 7;
        this.add(submitButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 7;
        this.add(clearButton, lManager);
        
        setSize(550,800);
    }
    /**
     * Edit Patient Medications
     */
    private void addMedicationtoPatient(){
    	currentPage = CurrPage.PATIENT_MEDICATION;
        resetLayout();

        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);

        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(prescDrLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 1;
        this.add(prescDrTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(prescMedLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 2;
        this.add(prescMedTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(prescPatientLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 3;
        this.add(prescPatientTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(prescDateLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 4;
        this.add(prescDateTextField, lManager);


        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(submitButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 5;
        this.add(clearButton, lManager);
        
        setSize(550,800);
    }
    /**
     *View Patient Health Record
     */
    private void viewPatient(){
    	int i = 3;
        addPatient(i);
        currentPage = CurrPage.HEALTH_RECORD;

        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(viewPatientLabel, lManager);

        // need to add TF's for prescribed medicine, interactions and procedures
        patientProceduresTextArea.setColumns(40);
        patientProceduresTextArea.setRows(8);
        patientInteractionsTextArea.setColumns(40);
        patientInteractionsTextArea.setRows(8);
        patientMedicationsTextArea.setColumns(40);
        patientMedicationsTextArea.setRows(8);

        lManager.gridwidth = 4;
        lManager.gridx = 4;
        lManager.gridy = 1;
        this.add(patientProceduresLabel, lManager);
        lManager.gridheight = 5;
        lManager.gridx = 4;
        lManager.gridy = 2;
        this.add(patientProceduresTextArea, lManager);

        lManager.gridheight = 1;
        lManager.gridx = 4;
        lManager.gridy = 7;
        this.add(patientInteractionsLabel, lManager);
        lManager.gridheight = 5;
        lManager.gridx = 4;
        lManager.gridy = 8;
        this.add(patientInteractionsTextArea, lManager);

        lManager.gridheight = 1;
        lManager.gridx = 4;
        lManager.gridy = 13;
        this.add(patientMedicaitonsLabel, lManager);
        lManager.gridheight = 5;
        lManager.gridx = 4;
        lManager.gridy = 14;
        this.add(patientMedicationsTextArea, lManager);
        
        
        setSize(1300,800);
    }
    /**
     * Displays the Services that the Department offers
     */
    private void viewDepartmentServices(){
    	currentPage = CurrPage.DEPARTMENT_SERVICES;
    	resetLayout();
    
    	lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);

        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(viewDeptSvcLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 1;
        this.add(deptSvcCode, lManager);

        deptSvcOfferedTextArea.setColumns(40);
        deptSvcOfferedTextArea.setRows(8);
        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(deptSvcOfferedLabel, lManager);
        lManager.gridheight = 7;
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(deptSvcOfferedTextArea, lManager);


        lManager.gridwidth = 2;
        lManager.gridheight = 1;
        lManager.gridx = 0;
        lManager.gridy = 12;
        this.add(submitButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 12;
        this.add(clearButton, lManager);
        
        
        setSize(1000,800);
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
        this.add(drIDProcTextField, lManager);

        drProcTextArea.setColumns(40);
        drProcTextArea.setRows(8);
        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(drProcLabel, lManager);
        lManager.gridheight = 7;
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(drProcTextArea, lManager);

        lManager.gridwidth = 2;
        lManager.gridheight = 1;
        lManager.gridx = 0;
        lManager.gridy = 12;
        this.add(submitButton, lManager);
        lManager.gridx = 2;
        lManager.gridy = 12;
        this.add(clearButton, lManager);
        
        
        setSize(700,800);
    }

    /**
     * Limits the characters the user can input into each textfield per
     * TABLE limitations
     */
    private void setCharLimits() {
        // Person Labels and Text fields
        SSNTextField.setDocument(new CharLimit(9));
        firstNameTextField.setDocument(new CharLimit(15));
        mInitialTextField.setDocument(new CharLimit(1));
        lastNameTextField.setDocument(new CharLimit(15));
        currAddressTextField.setDocument(new CharLimit(50));
        currPhoneTextField.setDocument(new CharLimit(10));
        permPhoneTextField.setDocument(new CharLimit(10));
        DOBTextField.setDocument(new CharLimit(10));
        sexTextField.setDocument(new CharLimit(10));
        streetTextField.setDocument(new CharLimit(15));
        cityTextField.setDocument(new CharLimit(15));
        stateTextField.setDocument(new CharLimit(2));
        zipTextField.setDocument(new CharLimit(5));

        // Patient Labels and Text fields
        patientIDTextField.setDocument(new CharLimit(9));
        patientConditionTextField.setDocument(new CharLimit(8));
        primaryCareTextField.setDocument(new CharLimit(9));
        secondaryCareTextField.setDocument(new CharLimit(9));

        // Department Labels and Text fields
        deptCodeTextField.setDocument(new CharLimit(4));
        deptNameTextField.setDocument(new CharLimit(15));
        deptOfficeTextField.setDocument(new CharLimit(4));
        deptPhoneTextField.setDocument(new CharLimit(10));
        deptHeadTextField.setDocument(new CharLimit(9));

        // Procedure Labels and Text fields
        procNumberTextField.setDocument(new CharLimit(7));
        procNameTextField.setDocument(new CharLimit(50));
        procDescTextField.setDocument(new CharLimit(50));
//        procDurationTextField.setDocument();
        procDepartmentTextField.setDocument(new CharLimit(4));

        // Doctor Labels and Text fields
        doctorIDTextField.setDocument(new CharLimit(9));
        doctorDepartmentTextField.setDocument(new CharLimit(4));
//        doctorProceduresTextField.setDocument();

        // Medication Labels and Text fields
        medNameTextField.setDocument(new CharLimit(15));
        medDescTextField.setDocument(new CharLimit(50));
        medManufacturerTextField.setDocument(new CharLimit(15));

        // Interaction Labels and Text fields
//        interIDTextField.setDocument();
        interPatientTextField.setDocument(new CharLimit(9));
        interDateField.setDocument(new CharLimit(10));
        interTimeTextField.setDocument(new CharLimit(4));
        interDescTextField.setDocument(new CharLimit(150));

        // Add Procedure Labels and Text fields
        procPatientTextField.setDocument(new CharLimit(9));
        procPatientNumberTextField.setDocument(new CharLimit(7));
        procNotesTextField.setDocument(new CharLimit(200));
        procDateTextField.setDocument(new CharLimit(10));
        procTimeTextField.setDocument(new CharLimit(4));
        procDrTextField.setDocument(new CharLimit(9));

        // Prompt for adding Patient Medications
        prescDrTextField.setDocument(new CharLimit(9));
        prescMedTextField.setDocument(new CharLimit(15));
        prescPatientTextField.setDocument(new CharLimit(9));
        prescDateTextField.setDocument(new CharLimit(10));

        // view department services
        deptSvcCode.setDocument(new CharLimit(4));
    }

    private void displayErrorMsg(String s) {
        ErrorMessageFrame frame = new ErrorMessageFrame(s);

        //Make GUI visible
        frame.setSize(450,450);
        frame.setVisible(true);
    }
    private void displaySuccessMsg() {
        SuccessFrame frame = new SuccessFrame();
        //Make GUI visible
        frame.setSize(100,100);
        frame.setVisible(true);

        clearTextFields();
    }

    private String checkPerson(){
        StringBuilder errorMsg = new StringBuilder();
        if (!StringChecker.SSNCheck(SSNTextField.getText()) )
            errorMsg.append("SSN must be 9 digits\n");
        if (!StringChecker.phoneCheck(currPhoneTextField.getText()))
            errorMsg.append("Current Phone must be 10 digits\n");
        if (!StringChecker.phoneCheck(permPhoneTextField.getText()))
            errorMsg.append("Permanent Phone must be 10 digits\n");
        if (!StringChecker.dateCheck(DOBTextField.getText()) )
            errorMsg.append("DOB must be MM-DD-YYYY Format\n");
        if (!StringChecker.sexCheck(sexTextField.getText()) )
            errorMsg.append("Sex must be Male, Female, or other\n");
        if (!StringChecker.stateCheck(stateTextField.getText()))
            errorMsg.append("State must be 2 letter abbreviation\n");
        if (!StringChecker.zipCheck(zipTextField.getText()))
            errorMsg.append("Zip Code must be 5 digits\n");
        return errorMsg.toString();
    }

    private boolean checkPt(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.patientIDCheck(patientIDTextField.getText()) )
            errorMsg.append("Patient ID must be P followed by 8 digits\n");

        errorMsg.append(checkPerson());

        if (!StringChecker.conditionCheck(patientConditionTextField.getText()))
            errorMsg.append("Patient Condition must be 'Critical', 'Stable', or 'Fair' \n");
        if (!StringChecker.drIDCheck(primaryCareTextField.getText()))
            errorMsg.append("Primary Care Doctor ID must be D followed by 8 digits\n");
        if (!secondaryCareTextField.getText().equals("") &&
                !StringChecker.drIDCheck(secondaryCareTextField.getText()))
            errorMsg.append("Primary Care Doctor ID must be D followed by 8 digits\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }

    private boolean checkDept(){
        StringBuilder errorMsg = new StringBuilder();

        if (deptCodeTextField.getText().equals(""))
            errorMsg.append("Department Code cannot be blank.\n");
        if (deptNameTextField.getText().equals(""))
            errorMsg.append("Department Name cannot be blank.\n");
        if (!StringChecker.officeNumCheck(deptOfficeTextField.getText()))
            errorMsg.append("Department Office must be 4 digits.\n");
        if (!deptPhoneTextField.getText().equals("") &&
                !StringChecker.phoneCheck(deptPhoneTextField.getText()))
            errorMsg.append("Department Office Phone must be 10 digits or blank\n");
        if (!deptHeadTextField.getText().equals("") && !StringChecker.drIDCheck(deptHeadTextField.getText()))
            errorMsg.append("Department Head must be a Doctor ID (D followed by 8 digits)\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkProc(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.procedureNumberCheck(procNumberTextField.getText()))
            errorMsg.append("Procedure Number must be three letters followed by 4 digits.\n");
        if (procNameTextField.getText().equals(""))
            errorMsg.append("Procedure Name cannot be blank.\n");
        if (procDescTextField.getText().equals(""))
            errorMsg.append("Procedure Description cannot be blank.\n");
        if (!StringChecker.procedureDurationCheck(procDurationTextField.getText()))
            errorMsg.append("Procedure duration must be a number.\n");
        if (procDepartmentTextField.getText().equals(""))
            errorMsg.append("Department Code cannot be blank.\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }

        return true;
    }
    private boolean checkDr(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.drIDCheck(doctorIDTextField.getText()) )
            errorMsg.append("Doctor ID must be D followed by 8 digits\n");

        errorMsg.append(checkPerson());

        if (doctorDepartmentTextField.getText().equals(""))
            errorMsg.append("Department Code cannot be blank.\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkMed(){
        StringBuilder errorMsg = new StringBuilder();

        if (medNameTextField.getText().equals(""))
            errorMsg.append("Medication Name cannot be blank.\n");
        if (medManufacturerTextField.getText().equals(""))
            errorMsg.append("Medication Manufacturer cannot be blank.\n");
        if (medDescTextField.getText().equals(""))
            errorMsg.append("Medication Description cannot be blank.\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkInt(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.interactionIDCheck(interIDTextField.getText()))
            errorMsg.append("Interaction ID must be a unique Integer for this patient.\n");
        if (!StringChecker.patientIDCheck(interPatientTextField.getText()) )
            errorMsg.append("Patient ID must be P followed by 8 digits\n");
        if (!StringChecker.dateCheck(interDateField.getText()) )
            errorMsg.append("Interaction Date must be MM-DD-YYYY Format\n");
        if (!StringChecker.timeCheck(interTimeTextField.getText()))
            errorMsg.append("Interaction time must be in the form HHMM, using the 24-Hour Clock\n");
        if (interDescTextField.getText().equals(""))
            errorMsg.append("Interaction Description cannot be blank.\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkPtProc(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.patientIDCheck(procPatientTextField.getText()) )
            errorMsg.append("Patient ID must be P followed by 8 digits\n");
        if (!StringChecker.procedureNumberCheck(procPatientNumberTextField.getText()))
            errorMsg.append("Procedure Number must be three letters followed by 4 digits.\n");
        if (!StringChecker.dateCheck(procDateTextField.getText()) )
            errorMsg.append("Procedure Date must be MM-DD-YYYY Format\n");
        if (!StringChecker.timeCheck(procTimeTextField.getText()))
            errorMsg.append("Procedure time must be in the form HHMM, using the 24-Hour Clock\n");
        if (!StringChecker.drIDCheck(procDrTextField.getText()) )
            errorMsg.append("Doctor ID must be D followed by 8 digits\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkPtMed(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.drIDCheck(prescDrTextField.getText()) )
            errorMsg.append("Doctor ID must be D followed by 8 digits\n");
        if (prescMedTextField.getText().equals(""))
            errorMsg.append("Medication Name cannot be blank.\n");
        if (!StringChecker.patientIDCheck(prescPatientTextField.getText()) )
            errorMsg.append("Patient ID must be P followed by 8 digits\n");
        if (!StringChecker.dateCheck(prescDateTextField.getText()) )
            errorMsg.append("Procedure Date must be MM-DD-YYYY Format\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkHealthRecord(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.patientIDCheck(patientIDTextField.getText()) )
            errorMsg.append("Patient ID must be P followed by 8 digits\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkDeptSvc(){
        StringBuilder errorMsg = new StringBuilder();

        if (deptSvcCode.getText().equals(""))
            errorMsg.append("Department Code cannot be blank.\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }
    private boolean checkDrProc(){
        StringBuilder errorMsg = new StringBuilder();

        if (!StringChecker.drIDCheck(drIDProcTextField.getText()) )
            errorMsg.append("Doctor ID must be D followed by 8 digits\n");

        if (errorMsg.length() != 0) {
            displayErrorMsg(errorMsg.toString());
            return false;
        }
        return true;
    }





    /**
     * Adds tuple to PERSON Table.  Called in addPatientSQL and addDoctorSQL
     * @throws SQLException
     */
    private void addPersonSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PERSON " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, SSNTextField.getText());
        pstmt.setString(2, firstNameTextField.getText());
        if (mInitialTextField.getText().equals(""))
            pstmt.setNull(3,Types.NULL);
        else
            pstmt.setString(3, mInitialTextField.getText());

        pstmt.setString(4, lastNameTextField.getText());

        if (currAddressTextField.getText().equals(""))
            pstmt.setNull(5,Types.NULL);
        else
            pstmt.setString(5, currAddressTextField.getText());

        if (currPhoneTextField.getText().equals(""))
            pstmt.setNull(6,Types.NULL);
        else
            pstmt.setString(6, currPhoneTextField.getText());

        pstmt.setString(7, permPhoneTextField.getText());
        pstmt.setString(8, DOBTextField.getText());
        pstmt.setString(9, sexTextField.getText());
        pstmt.setString(10, streetTextField.getText());
        pstmt.setString(11, cityTextField.getText());
        pstmt.setString(12, stateTextField.getText());
        pstmt.setString(13, zipTextField.getText());
        pstmt.executeUpdate();

        // add null values for MInitial, curr_address, Curr_phone
    }
    /**
     * Adds tuple to PATIENT Table.
     * @throws SQLException
     */
    private void addPatientSQL() throws SQLException {
        addPersonSQL();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PATIENT " +
                "VALUES(?, ?, ?, ?, ?)");
        pstmt.setString(1, SSNTextField.getText());
        pstmt.setString(2, patientIDTextField.getText());
        pstmt.setString(3, patientConditionTextField.getText());
        pstmt.setString(4, primaryCareTextField.getText());
        if (secondaryCareTextField.getText().equals(""))
            pstmt.setNull(5, Types.NULL);
        else
            pstmt.setString(5, secondaryCareTextField.getText());
        pstmt.executeUpdate();

    }
    /**
     * Adds tuple to DOCTOR Table.
     * @throws SQLException
     */
    private void addDoctorSQL() throws SQLException {
        addPersonSQL();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DOCTOR " +
                "VALUES(?, ?, ?)");
        pstmt.setString(1, SSNTextField.getText());
        pstmt.setString(2, doctorIDTextField.getText());
        pstmt.setString(3, doctorDepartmentTextField.getText());
        pstmt.executeUpdate();
    }
    /**
     * Adds tuple to DEPARTMENT Table.
     * @throws SQLException
     */
    private void addDepartmentSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DEPARTMENT " +
                "VALUES(?, ?, ?, ?, ?)");
        pstmt.setString(1, deptCodeTextField.getText());
        pstmt.setString(2, deptNameTextField.getText());
        pstmt.setString(3, deptOfficeTextField.getText());

        if (deptPhoneTextField.getText().equals(""))
            pstmt.setNull(4, Types.NULL);
        else
            pstmt.setString(4, deptPhoneTextField.getText());

        pstmt.setString(5, deptHeadTextField.getText());

        pstmt.executeUpdate();
    }
    /**
     * Adds tuple to INTERACTION Table.
     * @throws SQLException
     */
    private void addInteractionSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO INTERACTION " +
                "VALUES(?, ?, ?, ?, ?)");
        pstmt.setInt(1, Integer.parseInt(interIDTextField.getText()));
        pstmt.setString(2, interPatientTextField.getText());
        pstmt.setString(3, interDateField.getText());
        pstmt.setString(4, interTimeTextField.getText());
        pstmt.setString(5, interDescTextField.getText());

        pstmt.executeUpdate();
    }
    /**
     * Adds tuple to PROCEDURE Table.
     * @throws SQLException
     */
    private void addProcedureSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PROCEDURE " +
                "VALUES(?, ?, ?, ?, ?)");
        pstmt.setString(1, procNumberTextField.getText());
        pstmt.setString(2, procNameTextField.getText());
        pstmt.setString(3, procDescTextField.getText());
        pstmt.setFloat(4, Float.parseFloat(procDurationTextField.getText()));
        pstmt.setString(5, procDepartmentTextField.getText());
        pstmt.executeUpdate();

        addPerformsSQL(procDrTextField.getText(), procNumberTextField.getText());

    }
    /**
     * Adds tuple to PERFORMS Table. Called by addProcedureSQL
     * @throws SQLException
     */
    private void addPerformsSQL(String dr, String proc) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PERFORMS " +
                "VALUES(?, ?)");
        pstmt.setString(1, dr);
        pstmt.setString(2, proc);
        pstmt.executeUpdate();
    }
    /**
     * Adds tuple to PRESCRIBED_MEDICINE Table.
     * @throws SQLException
     */
    private void addPrescribedMedSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PRESCRIBED_MEDICINE " +
                "VALUES(?, ?, ?)");
        pstmt.setString(1, medNameTextField.getText());
        pstmt.setString(2, medManufacturerTextField.getText());
        pstmt.setString(3, medDescTextField.getText());

        pstmt.executeUpdate();
    }
    /**
     * Adds tuple to PRESCRIPTION Table.
     * @throws SQLException
     */
    private void addPrescriptionSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PRESCRIPTION " +
                "VALUES(?, ?, ?, ?)");
        pstmt.setString(1, prescDrTextField.getText());
        pstmt.setString(2, prescMedTextField.getText());
        pstmt.setString(3, prescPatientTextField.getText());
        pstmt.setString(4, prescDateTextField.getText());

        pstmt.executeUpdate();

    }
    /**
     * Adds tuple to UNDERGOES Table.
     * @throws SQLException
     */
    private void addUndergoesSQL() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO UNDERGOES " +
                "VALUES(?, ?, ?, ?, ?)");
        pstmt.setString(1, procPatientTextField.getText());
        pstmt.setString(2, procPatientNumberTextField.getText());
        pstmt.setString(3, procNotesTextField.getText());
        pstmt.setString(4, procDateTextField.getText());
        pstmt.setString(5, procTimeTextField.getText());

        pstmt.executeUpdate();

    }



    class CharLimit extends PlainDocument {
        private int limit;
        public CharLimit(int lim){
            this.limit = lim;
        }
        public void insertString(int offset, String s, AttributeSet set) throws BadLocationException {
            if(s == null) return;
            else if ((getLength() + s.length()) <= limit) {
                super.insertString(offset, s, set);
            }
        }
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
                clearTextFields();
            } else if (e.getSource() == submitButton) {
                switch (currentPage) {
                    case HOME_PAGE:
                        this.homePageSelection(HPUserSelectionOptions.getSelectedIndex());
                        break;
                    case PATIENT:
                        if(checkPt()) {
                            try {
                                addPatientSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case DEPARTMENT:
                        if(checkDept()) {
                            try {
//                                addDepartmentSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case PROCEDURE:
                        if(checkProc()) {
                            try {
//                                addProcedureSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case DOCTOR:
                        if(checkDr()) {
                            try {
//                                addDoctorSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case MEDICATION:
                        if(checkMed()) {
                            try {
//                                addPrescribedMedSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case INTERACTION:
                        if(checkInt()) {
                            try {
//                                addInteractionSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case PROCEDURE_:
                        if(checkPtProc()) {
                            try {
//                                addUndergoesSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case PATIENT_MEDICATION:
                        if(checkPtMed()) {
                            try {
//                                addPrescriptionSQL();
                                displaySuccessMsg();

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case HEALTH_RECORD:
                        if(checkHealthRecord()) {
                            try {

                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case DEPARTMENT_SERVICES:
                        if(checkDeptSvc()) {
                            try {


                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
                        break;
                    case DOCTOR_PROCEDURES:
                        if(checkDrProc()) {
                            try {


                            } catch (Exception e1) {
                                displayErrorMsg(e1.getMessage());
                            }
                        }
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
                    ClinicFrame.this.addMedicationtoPatient();
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
