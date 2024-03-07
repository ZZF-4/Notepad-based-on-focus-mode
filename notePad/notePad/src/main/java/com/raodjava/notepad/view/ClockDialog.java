package com.raodjava.notepad.view;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClockDialog extends JDialog implements ActionListener
{
    MyPanel clockPanel;
    private MainPage mainPage;

    Ellipse2D.Double e;

    int x;
    int y;

    Line2D.Double hourLine;
    Line2D.Double minLine;
    Line2D.Double secondLine;

    GregorianCalendar calendar;

    int hour;
    int minute;
    int second;

    String timestr = "";

    static int sethour;
    static int setminute;
    static int setsecond;

    public static final int X = 60;
    public static final int Y = 60;
    public static final int X_BEGIN = 10;
    public static final int Y_BEGIN = 10;
    public static final int RADIAN = 50;

    public ClockDialog(MainPage mainPage)
    {
        super(mainPage,"动态时钟",true);
        this.mainPage = mainPage;

        clockPanel = new MyPanel();
        add(clockPanel);

        Timer t = new Timer();
        Task task = new Task();
        t.schedule(task, 0, 1000);

        sethour = Integer.parseInt(JOptionPane.showInputDialog("请输入小时："));
        setminute = Integer.parseInt(JOptionPane.showInputDialog("请输入分钟："));
        setsecond = Integer.parseInt(JOptionPane.showInputDialog("请输入秒："));

        this.getContentPane().add(clockPanel);
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }



    class MyPanel extends JPanel {
        public MyPanel() {
            e = new Ellipse2D.Double(X_BEGIN, Y_BEGIN, 100, 100);
            hourLine = new Line2D.Double(X, Y, X, Y);
            minLine = new Line2D.Double(X, Y, X, Y);
            secondLine = new Line2D.Double(X, Y, X, Y);


        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawString("12", 55, 25);//整点时间
            g2.drawString("6", 55, 105);
            g2.drawString("9", 15, 65);
            g2.drawString("3", 100, 65);
            g2.drawString(timestr, 0, 130);
            g2.draw(e);
            g2.draw(hourLine);//时针
            g2.draw(minLine);//分针
            g2.draw(secondLine);//秒针
        }
    }

    class Task extends TimerTask {
        public void run() {
            calendar = new GregorianCalendar();
            hour = calendar.get(Calendar.HOUR);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);

           if(sethour == hour && setminute == minute && setsecond == second)
            {
                JOptionPane.showMessageDialog(mainPage,"时间到！！！","提醒",JOptionPane.INFORMATION_MESSAGE );
            }


            timestr = "当前时间：" + hour + " : " + minute + " : " + second;
            hourLine.x2 = X + 40 * Math.cos(hour * (Math.PI / 6) - Math.PI / 2);
            hourLine.y2 = Y + 40 * Math.sin(hour * (Math.PI / 6) - Math.PI / 2);
            minLine.x2 = X + 45 * Math.cos(minute * (Math.PI / 30) - Math.PI / 2);
            minLine.y2 = Y + 45 * Math.sin(minute * (Math.PI / 30) - Math.PI / 2);
            secondLine.x2 = X + 50 * Math.cos(second * (Math.PI / 30) - Math.PI / 2);
            secondLine.y2 = Y + 50 * Math.sin(second * (Math.PI / 30) - Math.PI / 2);

            repaint();
        }
    }

}
