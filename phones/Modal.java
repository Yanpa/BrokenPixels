package phones;

import javax.swing.*;

public class Modal extends JDialog {
    /**
     * Creates a modal window
     * @param parent
     * @param title
     * @param message
     */
    public Modal(JFrame parent, String title, String message){
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(parent);

        setVisible(true);
    }
}
