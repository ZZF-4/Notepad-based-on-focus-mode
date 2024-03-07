package com.raodjava.notepad.handler;

import com.raodjava.notepad.view.MainPage;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class ContAreaHandler implements DocumentListener , CaretListener {
    private MainPage mainPage;
    public ContAreaHandler(MainPage mainPage){
        this.mainPage = mainPage;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        processContChange();
    }
    private void processContChange(){
       String title =  mainPage.getTitle();
       if(title.startsWith("*")){
           return;
       }
       mainPage.setTitle("*"+title);
       mainPage.setContSaved(false);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        processContChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    @Override
    public void caretUpdate(CaretEvent e) {
        JTextArea contArea = mainPage.getContArea();
        int caretPosition = contArea.getCaretPosition();
        int line = 1;
        int column = 1;
        try {
            //通过偏移量（下标）获取行号，返回值从0开始
            line = contArea.getLineOfOffset(caretPosition)+1;
            int lineStartOffset = contArea.getLineStartOffset(line - 1);
            column = caretPosition-lineStartOffset+1;
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
        mainPage.getStatusLabel().setText("第"+line+"行,第"+column+"列");

    }
}
