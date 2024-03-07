package com.raodjava.notepad.view;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchDialog extends JDialog implements ActionListener {
    private MainPage mainPage;

    private JTextField searchField = new JTextField();
    private JCheckBox caseSensitiveBox = new JCheckBox("是否区分大小写");
    private JCheckBox loopBox = new JCheckBox("循环查找");
    private JButton searchNextBtn = new JButton("查找下一个");
    private JButton cancelBtn = new JButton("取消");
    public SearchDialog(MainPage mainPage){
        super(mainPage,"查找",true);
        this.mainPage = mainPage;
        JPanel jPanel = new JPanel(null);
        JLabel searchLabel= new JLabel("查找内容");
        jPanel.add(searchLabel);
        jPanel.add(searchField);
        jPanel.add(searchNextBtn);
        jPanel.add(caseSensitiveBox);
        jPanel.add(loopBox);
        jPanel.add(cancelBtn);

        //对组件进行布局
        searchLabel.setBounds(30,30,100,40);
        searchField.setBounds(150,30,150,40);

        searchNextBtn.addActionListener(this);
        searchNextBtn.setBounds(320,30,150,40);

        cancelBtn.addActionListener(this);
        cancelBtn.setBounds(320,90,150,40);

        caseSensitiveBox.addActionListener(this);
        caseSensitiveBox.setBounds(30,90,150,40);

        loopBox.addActionListener(this);
        loopBox.setBounds(30,150,150,40);

        this.getContentPane().add(jPanel);
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelBtn){
            this.dispose();
        }else if(e.getSource() == searchNextBtn){
            String searchText = searchField.getText();
            if(StringUtils.isBlank(searchText)){
                return;
            }
            int caretPosition= mainPage.getContArea().getCaretPosition();

            doSearch(searchText,caretPosition,false);
        }
    }

    private void doSearch(String searchText, int fromIndex,boolean isFromLoop) {
        String content = mainPage.getContArea().getText();
        if (StringUtils.isBlank(content)){
            return;
        }
        //从fromIndex所在的位置往后查
        int index;
        if(caseSensitiveBox.isSelected()){
            index = content.indexOf(searchText,fromIndex);
        }else{
            index = StringUtils.indexOfIgnoreCase(content,searchText,fromIndex);
        }
        if(index>-1){
            //搜到选中
            mainPage.getContArea().select(index,index+searchText.length());
        }else{
            //没有搜索到
            if(loopBox.isSelected()&& !isFromLoop){//选中了循环搜索
                doSearch(searchText,0,true);
            }else{
                JOptionPane.showMessageDialog(this,"找不到\""+searchText+"\"");
            }
        }
    }
}
