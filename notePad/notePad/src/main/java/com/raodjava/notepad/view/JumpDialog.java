package com.raodjava.notepad.view;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JumpDialog extends JDialog implements ActionListener {
    private MainPage mainPage;
    private JTextField lineNumField = new JTextField();
    private JButton jumpBtn = new JButton("跳转");

    public JumpDialog(MainPage mainPage){
        super(mainPage,"跳转",true);
        this.mainPage = mainPage;
        JPanel jPanel = new JPanel(null);
        JLabel tipLabel= new JLabel("输入行号：");
        jPanel.add(tipLabel);
        jPanel.add(lineNumField);
        jPanel.add(jumpBtn);
        tipLabel.setBounds(30,40,100,40);
        lineNumField.setBounds(150,40,150,40);

        jumpBtn.addActionListener(this);
        jumpBtn.setBounds(320,40,100,40);

        this.getContentPane().add(jPanel);
        setSize(500,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jumpBtn){
            String lineNumStr = lineNumField.getText();
            int lineNum;
            try{
                lineNum = Integer.parseInt(lineNumStr);

            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"请输入数字");

                return;
            }

            int lineCount = mainPage.getContArea().getLineCount();
            if(lineNum > lineCount){
                JOptionPane.showMessageDialog(this,"请输入不大于"+lineCount+"的行号");
                return;
            }
            try {
                int lineStartOffset = mainPage.getContArea().getLineStartOffset(lineNum-1);
                mainPage.getContArea().setCaretPosition(lineStartOffset);

            } catch (BadLocationException badLocationException) {
                badLocationException.printStackTrace();
            }
            this.dispose();

        }
    }
}
