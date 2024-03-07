package com.raodjava.notepad.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CountDownTimer extends JFrame implements ActionListener {
    private MainPage mainPage;
    private JTextField hourField, minuteField, secondField;
    private JButton startButton;
    private Timer countdownTimer;

    private int hours, minutes, seconds;

    public CountDownTimer(MainPage mainPage) {
        // 设置窗口尺寸
        setSize(new Dimension(400, 200));
        setTitle("专注模式");

        // 创建倒计时输入栏
        JPanel inputPanel = new JPanel();
        JLabel hourLabel = new JLabel("小时:");
        JLabel minuteLabel = new JLabel("分钟:");
        JLabel secondLabel = new JLabel("秒钟:");

        hourField = new JTextField("0", 5);
        minuteField = new JTextField("0", 5);
        secondField = new JTextField("0", 5);

        inputPanel.add(hourLabel);
        inputPanel.add(hourField);
        inputPanel.add(minuteLabel);
        inputPanel.add(minuteField);
        inputPanel.add(secondLabel);
        inputPanel.add(secondField);

        // 创建开始按钮
        startButton = new JButton("开始");
        startButton.addActionListener(this);

        // 将组件添加到窗口中
        JPanel panel = new JPanel();
        panel.add(inputPanel);
        panel.add(startButton);

        add(panel, BorderLayout.CENTER);
    }

    // 按下开始按钮时调用此方法
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startButton) {
            // 将输入转换为秒数
            hours = Integer.parseInt(hourField.getText());
            minutes = Integer.parseInt(minuteField.getText());
            seconds = Integer.parseInt(secondField.getText());

            int totalSeconds = hours*60*60 + minutes*60 + seconds;

            // 创建定时器并启动倒计时
            countdownTimer = new Timer(1000, new CountDownListener(totalSeconds));
            countdownTimer.start();
        }
    }

    // 定时器的动作监听器
    class CountDownListener implements ActionListener {
        private int totalSeconds;

        public CountDownListener(int totalSeconds) {
            this.totalSeconds = totalSeconds;
        }

        //每秒钟倒计时
        public void actionPerformed(ActionEvent ae) {
            if (totalSeconds > 0) {
                totalSeconds--;
                int hours_left = totalSeconds / 3600;
                int minutes_left = (totalSeconds % 3600) / 60;
                int seconds_left = (totalSeconds % 3600) % 60;

                // 更新窗口文本
                hourField.setText(Integer.toString(hours_left));
                minuteField.setText(Integer.toString(minutes_left));
                secondField.setText(Integer.toString(seconds_left));
            } else {
                // 否则，停止计时器并弹出对话框
                countdownTimer.stop();
                JOptionPane.showMessageDialog(null, "专注模式结束!");
            }
        }
    }
}

