import javax.swing.JFrame;
public class Main
{
	public static void main(String[] args)
	{	
		//Create CustomJFrame Object
		ClinicFrame frame = new ClinicFrame();
		
		//Make GUI visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
