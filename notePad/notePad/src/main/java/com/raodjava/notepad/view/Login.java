package com.raodjava.notepad.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login extends JFrame {
    private MainPage mainPage;
    public Login(boolean needExit) {
        super("记事本登录");
        setSize(300,290);//设计窗体的大小
        JLabel username=new JLabel("用户名"); //实例化JLabel对象
        JLabel password=new JLabel("密    码");
        JTextField text=new JTextField(15);//实例化用户名文本框
        JPasswordField passwordfiled=new JPasswordField(15);//实例化密码框
        passwordfiled.setEchoChar('*');//将输入密码框中的密码以*显示出来
        //建立确定、重置、注册三个按钮
        JButton confilm=new JButton("确定");
        JButton reset=new JButton("重置");
        //JButton signin=new JButton("注册");
        setVisible(true);//使窗体可视化
        Container container=getContentPane();//获取一个容器
        //将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、重置JButton、注册JButton添加到container容器里面
        container.add(username);
        container.add(password);
        container.add(text);
        container.add(passwordfiled);
        container.add(confilm);
        container.add(reset);
        setBounds(0,0,300,290);//设置窗体的长宽各为300、300  让其显示在左上方的300、300处
        setLocationRelativeTo(null);
        container.setLayout(null);
        //username,password,text,passwordfiled,confilm,reset显示在container容器中的位置坐标
        username.setBounds(10,40,50,18);
        password.setBounds(10,80,50,18);
        text.setBounds(60,40,200,18);
        passwordfiled.setBounds(60,80,200,18);
        confilm.setBounds(170,180,60,30);
        reset.setBounds(60,180,60,30);

        if(needExit){
            //点击关闭按钮直接退出程序
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }else{
            //仅销毁当前窗口，但不退出程序
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

        //对重置按钮添加监听事件
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                text.setText("");//对用户名文本框进行重置
                passwordfiled.setText("");//对密码文本框进行重置
            }
        });
        //对确定按钮添加监听事件
        confilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String usernameValue = text.getText();
                String passwordValue = new String(passwordfiled.getPassword());

                // 这里写上用户名和密码，用于测试登陆验证
                String username = "test";
                String password = "123456";
                // 跳转到成功界面的代码
                if (usernameValue.equals(username) && passwordValue.equals(password)) {
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    //登录成功后跳转到主界面
                    dispose(); // 销毁当前窗口
                    new MainPage(true).setVisible(true); // 打开 MainPage 窗口

                    // 跳转到失败界面的代码
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败！");
                }
            }
        });
    }
}

