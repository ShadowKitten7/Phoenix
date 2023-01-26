import javax.swing.*;
import java.awt.event.*;

public class NewClass extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton buttonCreate;
    private JTextField textField1;
    private JRadioButton classRadioButton;
    private JRadioButton interfaceRadioButton;
    private JRadioButton enumRadioButton;
    private JRadioButton annotationRadioButton;
    private JRadioButton abstractClassRadioButton;
    private JRadioButton recordRadioButton;

    public NewClass() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCreate);

        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        NewClass dialog = new NewClass();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
