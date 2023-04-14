import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorMessageFrame  extends JFrame {
    private JTextArea errorMsg = new JTextArea();
    private JButton OKButton = new JButton("OK");

    public ErrorMessageFrame(String s) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints lManager = new GridBagConstraints();
        lManager.insets = new Insets(0, 10, 10, 10);
        errorMsg.setText(s);
        errorMsg.setEditable(false);

        lManager.gridwidth = 3;
        lManager.gridx = 0;
        lManager.gridy = 0;
        this.add(errorMsg, lManager);
        lManager.gridwidth = 1;
        lManager.gridx = 1;
        lManager.gridy = 1;
        this.add(OKButton, lManager);

        OKButton.addActionListener(new InnerActionListener());
    }
    private void closeFrame() {
        this.dispose();
    }

    class InnerActionListener implements ActionListener {
        /**
         * Closes frame on click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // determine source of button click
            if (e.getSource() == OKButton) closeFrame();
        }
    }
}
