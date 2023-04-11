import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
/**
 * Creates the labels and fields for the GUI and prints them using the girdbaglayout manager
 */
public class CustomJFrame extends JFrame
{
	private JLabel headingLabel = new JLabel("Welcome to Dr. Kanewala's Clinic");
//	private JLabel firstNameLabel = new JLabel("Patient First Name: ");
//	private JLabel lastNameLabel= new JLabel("Patient Last Name: ");
//	private JLabel phoneNumberLabel= new JLabel("Patient Phone Number: ");
//	private JLabel emailLabel= new JLabel("Patient Email: ");
//	private JLabel genderLabel= new JLabel("Patient Sex: ");
//	private JLabel dietaryLabel= new JLabel("Dietary Questions");
//	private JLabel waterLabel= new JLabel("How many cups of water on average do you drink a day?");
//	private JLabel mealsLabel= new JLabel("How many meals on average do you eat a day?");
//	private JLabel checkBoxLabel= new JLabel("Do any of these meals regularly contain:");
	private JLabel userSelection= new JLabel("What would you like to do?");
//	private JLabel weightLabel= new JLabel("How much do you weigh?");
//	public static JTextField firstNameTextField = new JTextField("",20);
//	private static JTextField lastNameTextField= new JTextField("",20);
//	private static JTextField phoneNumberTextField= new JTextField("",20);
//	private static JTextField emailTextField= new JTextField("",20);
//	private static JRadioButton maleRadioButton= new JRadioButton("Male");
//	private static JRadioButton femaleRadioButton= new JRadioButton("Female");
//	private static ButtonGroup radioButtonGroup= new ButtonGroup();
//	static SpinnerNumberModel spinnerModel = new SpinnerNumberModel(15,0,50,1);
//	private static JSpinner waterIntakeSpinner= new JSpinner(spinnerModel);
//	private static JSlider mealSlider= new JSlider(SwingConstants.HORIZONTAL,0,10,3);
//	private static JCheckBox wheatCheckBox= new JCheckBox("Wheat");
//	private static JCheckBox sugarCheckBox= new JCheckBox("Sugar");
//	private static JCheckBox dairyCheckBox= new JCheckBox("Dairy");
//	
	static String[] items = {"","Add New Patient", "Add New Department","Add New Procedure","Add New Doctor", "Add New Medication", "Add New Interaction"
			,"Add Procedure Information","Edit Patient Medications","View Patient Health Record",
			"View Department Services","View Procedures Completed by Doctors"};
	private static JComboBox<String> userSelectionComboBox= new JComboBox<String>(items);
	
	static NumberFormat numberFormatter = NumberFormat.getIntegerInstance();
	private static JFormattedTextField weightFormattedTextField= new JFormattedTextField(numberFormatter);
	

	private JButton clearButton= new JButton("Clear");
	private JButton submitButton= new JButton("Submit");

	public CustomJFrame()
	{
		setTitle("Dr. Kanewala's Clinic System");
		
		//creates the gridbaglayout manager
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints layoutManager = new GridBagConstraints();	
		layoutManager.insets = new Insets(0,10,10,10);

		JPanel main = new JPanel(layout);


		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		layoutManager.gridwidth = 2;
		main.add(headingLabel,layoutManager);


//		layoutManager.gridx = 0;
//		layoutManager.gridy = 1;
//		layoutManager.gridwidth = 1;
//		main.add(firstNameLabel,layoutManager);
//
//		//adds the first name text field to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 1;
//		main.add(firstNameTextField,layoutManager);
//
//		//adds the last name label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 2;
//		main.add(lastNameLabel,layoutManager);
//
//		//adds the last name text field to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 2;
//		main.add(lastNameTextField,layoutManager);
//
//		//adds the phone number label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 3;
//		main.add(phoneNumberLabel,layoutManager);
//
//		//adds the phone number text field to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 3;
//		main.add(phoneNumberTextField,layoutManager);
//
//		//adds the email label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 4;
//		main.add(emailLabel,layoutManager);
//
//		//adds the email text field to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 4;
//		main.add(emailTextField,layoutManager);
//
//		//adds the gender label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 5;
//		main.add(genderLabel,layoutManager);
//
//		//adds the three radio buttons for genders to the radio button group
//		radioButtonGroup.add(maleRadioButton);
//		radioButtonGroup.add(femaleRadioButton);
//
//		//adds the male radio button to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 5;
//		main.add(maleRadioButton,layoutManager);
//
//		//adds the female radio button to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 6;
//		main.add(femaleRadioButton,layoutManager);
//
//		//adds the prefer not to say radio button to the JPanel
//		layoutManager.gridx = 1;
//		layoutManager.gridy = 7;
//
//		//adds the dietary label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 10;
//		layoutManager.gridwidth = 2;
//		main.add(dietaryLabel,layoutManager);
//
//		//adds the water label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 11;
//		main.add(waterLabel,layoutManager);
//
//		//adds the water intake spinner to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 12;
//		main.add(waterIntakeSpinner,layoutManager);
//
//		//adds the meals label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 13;
//		main.add(mealsLabel,layoutManager);
//
//		//adds the meal slider to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 14;
//		mealSlider.setMajorTickSpacing(1);
//		mealSlider.setPaintTicks(true);
//		mealSlider.setPaintLabels(true);
//		mealSlider.getValue();
//		main.add(mealSlider,layoutManager);
//
//		//adds the checkbox label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 15;
//		main.add(checkBoxLabel,layoutManager);
//
//		//adds the wheat check box to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 16;
//		main.add(wheatCheckBox,layoutManager);
//
//		//adds the sugar check box to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 17;
//		main.add(sugarCheckBox,layoutManager);
//
//		//adds the dairy check box to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 18;
//		main.add(dairyCheckBox,layoutManager);
//
		//adds the walk Label to the JPanel
		layoutManager.gridx = 0;
		layoutManager.gridy = 2;
		main.add(userSelection,layoutManager);
//
//		//adds the walk combo box ot the JPanel
		layoutManager.gridx = 0;
		layoutManager.gridy = 3;
    	main.add(userSelectionComboBox,layoutManager);
//
//		//adds the wieght label to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 21;
//		main.add(weightLabel,layoutManager);
//
//		//adds the weight text field to the JPanel
//		layoutManager.gridx = 0;
//		layoutManager.gridy = 22;
//		weightFormattedTextField.setColumns(20);
//		main.add(weightFormattedTextField,layoutManager);
// 
    	//adds the submit button to the JPanel
    	layoutManager.gridx = 1;
    	layoutManager.gridy = 4;
    	main.add(submitButton,layoutManager);
    	submitButton.setName("Submit");
    			
//		//adds the clear button to the JPanel
		layoutManager.gridx = 1;
		layoutManager.gridy = 5;
    	main.add(clearButton,layoutManager);
		clearButton.setName("Clear");

		//adds the JPanel to the JFrame using the gridbaglayout manager
		add(main,layoutManager);
		
		InnerActionListener listener = new InnerActionListener();
		submitButton.addActionListener(listener);
		clearButton.addActionListener(listener);
	}	

	class InnerActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton) e.getSource();
			String name = button.getName();
			
			if(name.equals("Submit"))
			{
				//Assigns each variable from the GUI with a string that can then be parsed to the csv
				
				/*String firstName = firstNameTextField.getText().toString();
				if(firstName == null) {
					firstName = "Null";
				}
				else {
					firstName = firstNameTextField.getText().toString();
				}
				
				String lastName = lastNameTextField.getText().toString();
				if(lastName == null) {
					lastName = "Null";
				}
				else {
					lastName = lastNameTextField.getText().toString();
				}
				
				String phoneNumber = phoneNumberTextField.getText().toString();
				if(phoneNumber == null) {
					phoneNumber = "Null";
				}
				else {
					phoneNumber = phoneNumberTextField.getText().toString();
				}
				
				String email = emailTextField.getText().toString();
				if(email == null) {
					email = "Null";
				}
				else {
					email = emailTextField.getText().toString();
				}
				
				String gender = "";
				if (femaleRadioButton.isSelected()) {
					gender = "Female";
				}
				if (maleRadioButton.isSelected()) {
					gender = "Male";
				}
			
				if(!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
					gender = "Null";
				}
				String water = waterIntakeSpinner.getValue().toString();
			
				int meals = mealSlider.getValue();
				
				String wheat = "";
				if (wheatCheckBox.isSelected()) {
					wheat = "TRUE";
				}
				else {
					wheat = "FALSE";
				}
				String sugar = "";
				if (sugarCheckBox.isSelected()) {
					sugar = "TRUE";
				}
				else {
					sugar = "FALSE";
				}
				String dairy = "";
				if (dairyCheckBox.isSelected()) {
					dairy = "TRUE";
				}
				else {
					dairy = "FALSE";
				}
				String miles = userSelectionComboBox.getSelectedItem().toString();
				
				String weight = weightFormattedTextField.getText().toString();
				
				//Appends all of the strings and adds commas to ensure that the file will separate the strings when printing
				fileHandler.writeResults(firstName + "," + lastName + "," + 
						phoneNumber + "," + email + "," + gender + "," +  water + "," + meals + "," + wheat + ","
						+ sugar + "," + dairy + "," + miles + "," + weight);*/
				
				clearForm();
			}
			if(name.equals("Clear"))
			{
				clearForm();
			}
		}
		/**
		 * Clears all of the information from the GUI when the user presses clear
		 */
		private void clearForm()
		{
			userSelectionComboBox.setSelectedItem("");
		}
	}
	}



















