package com.raodjava.notepad.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDialog extends JDialog implements ActionListener {
    private MainPage mainPage;

    public TimeDialog(MainPage mainPage){
        super(mainPage,"本地时间显示",true);
        this.mainPage = mainPage;

        JPanel app = new JPanel(null);
        JLabel clock=new JLabel();
        app.add(clock);
        clock.setBounds(30,40,100,40);


        clock.setHorizontalTextPosition(JLabel.CENTER); //设置标题水平对齐方式 水平放在中央
        app.setLayout(new BorderLayout());
        clock.setFont(new Font("微软雅黑", Font.BOLD, 30));
        app.add(clock,BorderLayout.CENTER);

      //  mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread t=new MyThread(clock);
        t.start();
        this.getContentPane().add(app);
        setSize(500,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class MyThread extends Thread{
        private JLabel clock;
        public MyThread(JLabel clock){
            this.clock=clock;
        }
        public void run(){
            while (true){
                clock.setText(this.getTime());
            }
        }
        public String getTime(){
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(day);
        }
    }
}

