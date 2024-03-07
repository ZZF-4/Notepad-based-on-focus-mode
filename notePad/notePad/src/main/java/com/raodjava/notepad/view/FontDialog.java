package com.raodjava.notepad.view;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontDialog extends JDialog implements ActionListener {
    private MainPage mainPage;
    private JComboBox<String> fontFamilyComboBox;
    private JComboBox<String> fontStyleComboBox;
    private JComboBox<Integer> fontSizeComboBox;

    private JButton confirmBtn = new JButton("确认");

    public FontDialog(MainPage mainPage){
        super(mainPage,"字体设置",true);
        this.mainPage = mainPage;
        JPanel jPanel = new JPanel(null);

        //字体设置
        // 获取系统字体名称
        GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] availableFontFamilyNames = localGraphicsEnvironment.getAvailableFontFamilyNames();
        fontFamilyComboBox = new JComboBox<>(availableFontFamilyNames);
        jPanel.add(fontFamilyComboBox);
        //字形设置
        String[] fontStyleArr = {"Plain","Bold","Italic"};
        fontStyleComboBox = new JComboBox<>(fontStyleArr);
        jPanel.add(fontStyleComboBox);
        //字号设置
        Integer[] fontSizeArr = {30,40,50};
        fontSizeComboBox = new JComboBox<>(fontSizeArr);
        jPanel.add(fontSizeComboBox);

        confirmBtn.addActionListener(this);
        jPanel.add(confirmBtn);

        //放入下拉框并给元素定位
        fontFamilyComboBox.setBounds(30,50,200,40);
        fontStyleComboBox.setBounds(300,50,100,40);
        fontSizeComboBox.setBounds(430,50,80,40);
        confirmBtn.setBounds(200,150,100,40);

        this.getContentPane().add(jPanel);
        //窗口大小
        setSize(550,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }


    //设置接口，监控确认按钮
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn){
            String fontFamily = (String) fontFamilyComboBox.getSelectedItem();
            String fontStyleStr = (String) fontStyleComboBox.getSelectedItem();
            Integer fontSize = (Integer) fontSizeComboBox.getSelectedItem();
            int fontStyle=0;
            if("Plain".equals(fontStyleStr)){
                fontStyle = Font.PLAIN;
            }else if("Bold".equals(fontStyleStr)){
                fontStyle = Font.BOLD;
            }else if("Italic".equals(fontStyleStr)){
                fontStyle = Font.ITALIC;
            }
            //确认后创建一个新的字体对象
            Font font = new Font(fontFamily, fontStyle, fontSize);
            //设置新的字体
            mainPage.getContArea().setFont(font);
            this.dispose();

        }
    }

}
