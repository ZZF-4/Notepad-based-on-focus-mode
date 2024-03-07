package com.raodjava.notepad.handler;

import com.raodjava.notepad.view.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuHandler implements ActionListener {

    private MainPage mainPage;
            public MenuHandler(MainPage mainPage){
                this.mainPage = mainPage;
            }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("actionCommand="+actionCommand);
        if("newMenuItem".equals(actionCommand)){
           processNewMenuItem();
        }else if("newDialogMenuItem".equals(actionCommand)){
            new MainPage(false);
        }else if("openMenuItem".equals(actionCommand)){
            processOpenMenuItem();

        }else if("saveMenuItem".equals(actionCommand)){
            doSave();
        }else if("pageMenuItem".equals(actionCommand)){
            processPageMenuItem();
        }else if("printMenuItem".equals(actionCommand)){
            processPrintMenuItem();
        }else if("exitMenuItem".equals(actionCommand)){
            System.exit(0);
        }else if("undoMenuItem".equals(actionCommand)){
            if(mainPage.getUndoManager().canUndo()){
                mainPage.getUndoManager().undo();
            }
        }else if("cutMenuItem".equals(actionCommand)){
            mainPage.getContArea().cut();
        }else if("copyMenuItem".equals(actionCommand)){
            mainPage.getContArea().copy();
        }else if("pasteMenuItem".equals(actionCommand)){
            mainPage.getContArea().paste();
        }else if("deleteMenuItem".equals(actionCommand)){
            processDeleteMenuItem();
        }else if("searchMenuItem".equals(actionCommand)){
            processSearchMenuItem();
        }else if("replaceMenuItem".equals(actionCommand)){
            processReplaceMenuItem();
        }else if("jumpMenuItem".equals(actionCommand)){
            processJumpMenuItem();
        }else if("selectAllMenuItem".equals(actionCommand)){
            mainPage.getContArea().selectAll();
        }else if("datetimeMenuItem".equals(actionCommand)){
            processDatetimeMenuItem();
        }else if("autoBreakLineMenuItem".equals(actionCommand)){
            boolean selected = mainPage.getAutoBreakLineMenuItem().isSelected();
            mainPage.getContArea().setLineWrap(selected);
        }else if("fontMenuItem".equals(actionCommand)){
            new FontDialog(mainPage);
        }else if("fontColorMenuItem".equals(actionCommand)){
            processFontColorMenuItem();
        }else if("backgroundMenuItem".equals(actionCommand)){
            processBackgroundMenuItem();
        }else if("zoomInMenuItem".equals(actionCommand)){
            processZoomInMenuItem();
        }else if("zoomOutMenuItem".equals(actionCommand)){
            processZoomOutMenuItem();
        }else if("defaultFontSizeMenuItem".equals(actionCommand)){
            processDefaultFontSizeMenuItem();
        }else if("statusMenuItem".equals(actionCommand)){
            mainPage.getStatusPanel().setVisible(mainPage.getStatusMenuItem().isSelected());
        }else if("localTimeMenuItem".equals(actionCommand)){
            processLocalTimeMenuItem();
        }else if("timeTipMenuItem".equals(actionCommand)){
            processTimeTipMenuItem();
        }else if("focusMenuItem".equals(actionCommand)){
            processfocusMenuItem();
        }else if("questMenuItem".equals(actionCommand)){
            processQuestMenuItem();
        }else if("aboutMenuItem".equals(actionCommand)){
            processAboutMenuItem();
        }
    }


//闹钟
    private void processTimeTipMenuItem() {
        ClockDialog t = new ClockDialog(mainPage);

    }

    //专注模式
    private void processfocusMenuItem() {
        CountDownTimer frame = new CountDownTimer(mainPage);
        frame.pack();
        frame.setVisible(true);
    }

    private void processLocalTimeMenuItem() {
        new TimeDialog(mainPage);
    }

    private void processAboutMenuItem() {
        JOptionPane.showMessageDialog(mainPage,
                 "本记事本使用maven构建，使用java swing技术编写。\r\n"
                         +"用户输入用户名与密码进行登录，若用户名或密码错误，则可重置，然后重新登录。\r\n"
                         +"主要功能有新建记事本、打开记事本、保存记事本、查找指定内容、替换指定内容、缩放字体、设置字体、设置提醒等",
                "关于记事本",-1 );
    }

    private void processQuestMenuItem() {
        JOptionPane.showMessageDialog(mainPage,
                "1.怎么进行查找操作？\r\n"
                        + "点击编辑按钮，在下拉框中选择查找，输入想要查找的字符，点击查找下一个。\r\n"
                        + "2.不小心放大了字体，怎么恢复？\r\n"
                        + "点击查看按钮，在下拉框中选择恢复默认缩放。\r\n"
                        + "3.怎样设置文件保存的新路径？\r\n"
                        + "点击新建按钮，在下拉框中选择另存为。\r\n",
                    "问题及使用",JOptionPane.INFORMATION_MESSAGE );
    }

    private void processDefaultFontSizeMenuItem() {
        Font font = mainPage.getContArea().getFont();
        Font newFont = new Font(font.getFamily(), font.getStyle(), mainPage.getDefaultFontSize());
        mainPage.getContArea().setFont(newFont);
    }

    private void processZoomOutMenuItem() {
        Font font = mainPage.getContArea().getFont();
        Font newFont = new Font(font.getFamily(), font.getStyle(), font.getSize()-1);
        mainPage.getContArea().setFont(newFont);
    }

    private void processZoomInMenuItem() {
        Font font = mainPage.getContArea().getFont();
        Font newFont = new Font(font.getFamily(), font.getStyle(), font.getSize()+1);
        mainPage.getContArea().setFont(newFont);

    }

    private void processBackgroundMenuItem() {
        Color color = JColorChooser.showDialog(mainPage, "选择背景颜色", Color.WHITE);
        //设置背景颜色
        mainPage.getContArea().setBackground(color);
    }

    private void processFontColorMenuItem() {
        Color color = JColorChooser.showDialog(mainPage, "选择字体颜色", Color.BLACK);
        //设置字体颜色
        mainPage.getContArea().setForeground(color);

    }

    private void processDatetimeMenuItem() {
        LocalDateTime now = LocalDateTime.now();
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int caretPosition = mainPage.getContArea().getCaretPosition();
        mainPage.getContArea().insert(nowStr,caretPosition
        );
    }

    private void processJumpMenuItem() {
                new JumpDialog(mainPage);
    }

    private void processReplaceMenuItem() {
        ReplaceDialog replaceDialog= new ReplaceDialog(mainPage);
    }

    private void processSearchMenuItem() {
        SearchDialog searchDialog= new SearchDialog(mainPage);
    }

    private void processDeleteMenuItem() {
         //获取光标位置
        int caretPosition= mainPage.getContArea().getCaretPosition();
        if (caretPosition==0){
            return;
        }
        mainPage.getContArea().replaceRange("",caretPosition-1,caretPosition);
    }

    private void processPrintMenuItem() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        if(printerJob.printDialog()){
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    private void processPageMenuItem() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.pageDialog(printerJob.defaultPage());
    }

    private void processNewMenuItem(){
        if(mainPage.isContSaved()){
            //已保存
            reset();
        }else{
            int i = JOptionPane.showConfirmDialog(mainPage,"是否保存","记事本-提示",JOptionPane.YES_NO_CANCEL_OPTION);
            if(i==JOptionPane.YES_OPTION){
                doSave();
                reset();
            }else if(i==JOptionPane.NO_OPTION){
                reset();
            }
        }
    }
    private void processOpenMenuItem(){
        JFileChooser jFileChooser = new JFileChooser();
        //设置默认打开的目录
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        //文件选择对话框
        int clickBtn = jFileChooser.showOpenDialog(mainPage);
        if(clickBtn == JFileChooser.APPROVE_OPTION){
            //选中了某个文件
            File selectedFile =  jFileChooser.getSelectedFile();
            try {
               String fileContent = FileUtils.readFileToString(selectedFile,StandardCharsets.UTF_8);
               mainPage.getContArea().setText(fileContent);
               mainPage.setTitle(selectedFile.getName());
               mainPage.setContSaved(true);
               mainPage.setFilePath(selectedFile.getAbsolutePath());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    private void doSave(){
                String filePath = mainPage.getFilePath();
               String title = mainPage.getTitle();
               if(StringUtils.isBlank(filePath)){
                   //说明文件还没有被保存到磁盘
                   JFileChooser jFileChooser = new JFileChooser();
                   //文件保存对话框
                   int saveOption = jFileChooser.showSaveDialog(mainPage);
                   if(saveOption == jFileChooser.APPROVE_OPTION){
                       //点击的是保存按钮
                       //返回保存的文件对象
                       File selectedFile = jFileChooser.getSelectedFile();
                       filePath = selectedFile.getAbsolutePath();
                       title = selectedFile.getName();
                       mainPage.setFilePath(filePath);
                       mainPage.setTitle(title);
                   }else{
                       //不是保存按钮
                       return;
                   }
               }else{
                   //已经保存过
                   mainPage.setTitle(StringUtils.substringAfter(title,"*"));
               }
               mainPage.setContSaved(true);
               try{
                   FileUtils.writeStringToFile(new File(filePath),mainPage.getContArea().getText(), StandardCharsets.UTF_8);
               }catch (IOException e){
                   e.printStackTrace();
               }
    }
    private void reset(){
                mainPage.getContArea().setText("");
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                mainPage.setContSaved(true);
                mainPage.setFilePath(null);
                mainPage.setTitle("未命名文档.txt");
    }
}
