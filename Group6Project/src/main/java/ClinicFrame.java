import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
            "Add New Medication", "Add New Interaction", "Add Procedure Information", "Edit Patient Medications",
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
    private JLabel permAddressLabel = new JLabel("Permanent Address");
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
        person();

//*********** build out textfield array lists
    }   // end of constructor

    private void resetLayout() {
        this.setLayout(new GridBagLayout());
        lManager = new GridBagConstraints();
        lManager.insets = new Insets(0,10,10,10);
    }

    private void buildTextFieldList() {
        currentPageTextFields = new ArrayList<>();
        switch (currentPage) {
            case HOME_PAGE:
                break;
            case PATIENT:
                personArrayList();
                break;
            case DEPARTMENT:
                personArrayList();
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
    }

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

    private void homePageLayout() {
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
    }

    private void person() {
        resetLayout();
        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(homeButton, lManager);

        lManager.gridx = 0;
        lManager.gridy = 1;
        this.add(SSNLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 1;
        this.add(SSNTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 2;
        this.add(firstNameLabel, lManager);
        lManager.gridx = 2;
        lManager.gridy = 2;
        this.add(firstNameTextField, lManager);

        lManager.gridx = 0;
        lManager.gridy = 3;
        this.add(mInitialLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 4;
        this.add(lastNameLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 5;
        this.add(currAddressLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 6;
        this.add(currPhoneLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 7;
        this.add(permPhoneLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 8;
        this.add(DOBLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 9;
        this.add(sexLabel, lManager);
        lManager.gridwidth = 4;
        lManager.gridx = 0;
        lManager.gridy = 10;
        this.add(permAddressLabel, lManager);
        lManager.gridwidth = 2;
        lManager.gridx = 0;
        lManager.gridy = 11;
        this.add(streetLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 12;
        this.add(cityLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 13;
        this.add(stateLabel, lManager);
        lManager.gridx = 0;
        lManager.gridy = 14;
        this.add(zipLabel, lManager);

    }
    private void addPatient() {
        currentPage = CurrPage.PATIENT;
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
                // clear form

            } else if (e.getSource() == submitButton) {
                switch (currentPage) {
                    case HOME_PAGE:
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
            }
        }
    }
}
