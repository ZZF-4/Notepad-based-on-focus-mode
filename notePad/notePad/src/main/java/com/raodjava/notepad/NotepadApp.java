package com.raodjava.notepad;

import com.raodjava.notepad.view.Login;
import com.raodjava.notepad.view.MainPage;

import javax.swing.*;

public class NotepadApp {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
       // new MainPage(true);
        new Login(true);
    }
}
