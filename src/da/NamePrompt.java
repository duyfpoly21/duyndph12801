/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

/**
 *
 * @author dell
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NamePrompt extends JFrame{


    private static final long serialVersionUID = 1L;

    String name;

    public NamePrompt(){

        setLayout(new BorderLayout());

        JLabel enterYourName = new JLabel("Enter Your Name Here:");
        JTextField textBoxToEnterName = new JTextField(21);
        JPanel panelTop = new JPanel();
        panelTop.add(enterYourName);
        panelTop.add(textBoxToEnterName);

        JButton submit = new JButton("Submit");
        submit.addActionListener((ActionListener) new SubmitButton(textBoxToEnterName));
        submit.addKeyListener(new SubmitButton(textBoxToEnterName));
        JPanel panelBottom = new JPanel();
        panelBottom.add(submit);

        //Add panelTop to JFrame
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        //JFrame set-up
        setTitle("Name Prompt Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);


    }



    public static void main(String[] args) {
        NamePrompt promptForName = new NamePrompt();
        promptForName.setVisible(true); 
    }


}
