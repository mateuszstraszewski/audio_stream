package MicTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {

    JFrame frame = new JFrame("Test GUI");
    JButton button = new JButton("Press");
    boolean stopped = false;

    public Gui() {
        prepareGUI();
        buttonProperties();
    }

    public void prepareGUI() {
        frame.setTitle("GUI Window");
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(200, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void buttonProperties() {
        button.setBounds(130, 200, 100, 40);
        frame.add(button);
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.stopped = true;
        System.out.println("You clicked the button");
    }


}

