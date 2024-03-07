package com.raodjava.notepad.view;

import com.raodjava.notepad.handler.ContAreaHandler;
import com.raodjava.notepad.handler.MenuHandler;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainPage extends JFrame{

    //swing 里面的菜单
    private JMenuBar jMenuBar = new JMenuBar();
    private JTextArea contArea = new JTextArea();
    //是否保存
    private boolean contSaved = true;
    //文件的保存路径
    private String filePath = null;

    private int defaultFontSize = 23;
    private JPanel statusPanel = new JPanel();
    private JLabel statusLabel = new JLabel("第1行，第1列");
    private UndoManager undoManager = new UndoManager();
    private MenuHandler menuHandler = new MenuHandler(this);
    private JCheckBoxMenuItem autoBreakLineMenuItem = new JCheckBoxMenuItem("自动换行");
    private JCheckBoxMenuItem statusMenuItem = new JCheckBoxMenuItem("状态栏（S）");
    public MainPage(boolean needExit){
        super("未命名文档.txt");
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createViewMenu();
        createTimeMenu();
        createHelpMenu();

        this.setJMenuBar(jMenuBar);

        Font defaultFont = contArea.getFont();

        contArea.setFont(new Font(defaultFont.getFamily(),defaultFont.getStyle(),defaultFontSize));
        contArea.setTabSize(2);
        contArea.getDocument().addUndoableEditListener(undoManager);
        ContAreaHandler contAreaHandler = new ContAreaHandler(this);
        //变更监听
        contArea.getDocument().addDocumentListener(contAreaHandler);
        contArea.addCaretListener(contAreaHandler);

        //滚动面板
        JScrollPane contScrollPane = new JScrollPane(contArea);
        this.getContentPane().add(contScrollPane);
        //设置状态栏
        statusPanel.add(statusLabel);
        this.getContentPane().add(statusPanel, BorderLayout.SOUTH);

        setBounds(0,0,700,800);
        //居中显示
        setLocationRelativeTo(null);
        if(needExit){
            //点击关闭按钮直接退出程序
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }else{
            //仅销毁当前窗口，但不退出程序
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

        setResizable(true);
        setVisible(true);
    }


    private void createHelpMenu() {
        JMenu helpmatMenu = new JMenu("帮助");

        JMenuItem questMenuItem = new JMenuItem("问题及使用");
        questMenuItem.setActionCommand("questMenuItem");
        questMenuItem.addActionListener(menuHandler);
       // questMenuItem.setIconTextGap(20);
        JMenuItem aboutMenuItem = new JMenuItem("关于记事本");
        aboutMenuItem.setActionCommand("aboutMenuItem");
        aboutMenuItem.addActionListener(menuHandler);
       // aboutMenuItem.setIconTextGap(20);

        helpmatMenu.add(questMenuItem);
        helpmatMenu.add(aboutMenuItem);

        jMenuBar.add(helpmatMenu);
    }

    private void createTimeMenu() {
        JMenu timematMenu = new JMenu("时间管理");

        JMenuItem localTimeMenuItem = new JMenuItem("本地时间显示");
        localTimeMenuItem.setActionCommand("localTimeMenuItem");
        localTimeMenuItem.addActionListener(menuHandler);
      //  localTimeMenuItem.setIconTextGap(20);

        JMenuItem timeTipMenuItem = new JMenuItem("闹钟提醒");
        timeTipMenuItem.setActionCommand("timeTipMenuItem");
        timeTipMenuItem.addActionListener(menuHandler);
      //  timeTipMenuItem.setIconTextGap(20);

        JMenuItem focusMenuItem = new JMenuItem("专注模式");
        focusMenuItem.setActionCommand("focusMenuItem");
        focusMenuItem.addActionListener(menuHandler);
       // focusMenuItem.setIconTextGap(20);

        timematMenu.add(localTimeMenuItem);
        timematMenu.add(timeTipMenuItem);
        timematMenu.add(focusMenuItem);
        jMenuBar.add(timematMenu);
    }

    private void createViewMenu() {
        JMenu viewMenu = new JMenu("查看（V）");
        viewMenu.setMnemonic(KeyEvent.VK_V);

        JMenu zoomMenu = new JMenu("缩放（Z）");
        zoomMenu.setActionCommand("zoomMenu");
        zoomMenu.addActionListener(menuHandler);
       // zoomMenu.setIconTextGap(20);
        zoomMenu.setMnemonic(KeyEvent.VK_Z);

        JMenuItem zoomInMenuItem = new JMenuItem("放大（I）");
        zoomInMenuItem.setActionCommand("zoomInMenuItem");
        zoomInMenuItem.addActionListener(menuHandler);
       // zoomInMenuItem.setIconTextGap(20);
        zoomInMenuItem.setMnemonic(KeyEvent.VK_I);
        zoomInMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem zoomOutMenuItem = new JMenuItem("缩小（O）");
        zoomOutMenuItem.setActionCommand("zoomOutMenuItem");
        zoomOutMenuItem.addActionListener(menuHandler);
       // zoomOutMenuItem.setIconTextGap(20);
        zoomOutMenuItem.setMnemonic(KeyEvent.VK_O);
        zoomOutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem defaultFontSizeMenuItem = new JMenuItem("恢复默认缩放");
        defaultFontSizeMenuItem.setActionCommand("defaultFontSizeMenuItem");
        defaultFontSizeMenuItem.addActionListener(menuHandler);
       // defaultFontSizeMenuItem.setIconTextGap(20);
        defaultFontSizeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,KeyEvent.CTRL_DOWN_MASK));

        //设置默认选中
        statusMenuItem.setSelected(true);
        statusMenuItem.setActionCommand("statusMenuItem");
        statusMenuItem.addActionListener(menuHandler);
       // statusMenuItem.setIconTextGap(20);
        statusMenuItem.setMnemonic(KeyEvent.VK_S);

        zoomMenu.add(zoomInMenuItem);
        zoomMenu.add(zoomOutMenuItem);
        zoomMenu.add(defaultFontSizeMenuItem);


        viewMenu.add(zoomMenu);
        viewMenu.add(statusMenuItem);


        jMenuBar.add(viewMenu);
    }

    private void createFormatMenu() {
        JMenu formatMenu = new JMenu("格式（O）");
        formatMenu.setMnemonic(KeyEvent.VK_O);


        autoBreakLineMenuItem.setActionCommand("autoBreakLineMenuItem");
        autoBreakLineMenuItem.addActionListener(menuHandler);
       // autoBreakLineMenuItem.setIconTextGap(20);
        autoBreakLineMenuItem.setMnemonic(KeyEvent.VK_W);

        JMenuItem fontMenuItem = new JMenuItem("字体（F）");
        fontMenuItem.setActionCommand("fontMenuItem");
        fontMenuItem.addActionListener(menuHandler);
      //  fontMenuItem.setIconTextGap(20);
        fontMenuItem.setMnemonic(KeyEvent.VK_F);

        JMenuItem fontColorMenuItem = new JMenuItem("字体颜色");
        fontColorMenuItem.setActionCommand("fontColorMenuItem");
        fontColorMenuItem.addActionListener(menuHandler);
      //  fontColorMenuItem.setIconTextGap(20);

        JMenuItem backgroundMenuItem = new JMenuItem("背景颜色");
        backgroundMenuItem.setActionCommand("backgroundMenuItem");
        backgroundMenuItem.addActionListener(menuHandler);
       // backgroundMenuItem.setIconTextGap(20);

        formatMenu.add(autoBreakLineMenuItem);
        formatMenu.add(fontMenuItem);
        formatMenu.add(fontColorMenuItem);
        formatMenu.add(backgroundMenuItem);


        jMenuBar.add(formatMenu);
    }

    private  void createEditMenu(){
        JMenu editMenu = new JMenu("编辑（E）");
        editMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem undoMenuItem = new JMenuItem("撤销（Z）");
        undoMenuItem.setActionCommand("undoMenuItem");
        undoMenuItem.addActionListener(menuHandler);
       // undoMenuItem.setIconTextGap(20);
        undoMenuItem.setMnemonic(KeyEvent.VK_Z);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem cutMenuItem = new JMenuItem("剪切（X）");
        cutMenuItem.setActionCommand("cutMenuItem");
        cutMenuItem.addActionListener(menuHandler);
       // cutMenuItem.setIconTextGap(20);
        cutMenuItem.setMnemonic(KeyEvent.VK_X);
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem copyMenuItem = new JMenuItem("复制（C）");
        copyMenuItem.setActionCommand("copyMenuItem");
        copyMenuItem.addActionListener(menuHandler);
       // copyMenuItem.setIconTextGap(20);
        copyMenuItem.setMnemonic(KeyEvent.VK_C);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem pasteMenuItem = new JMenuItem("粘贴（V）");
        pasteMenuItem.setActionCommand("pasteMenuItem");
        pasteMenuItem.addActionListener(menuHandler);
       // pasteMenuItem.setIconTextGap(20);
        pasteMenuItem.setMnemonic(KeyEvent.VK_V);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem deleteMenuItem = new JMenuItem("删除（L）");
        deleteMenuItem.setActionCommand("deleteMenuItem");
        deleteMenuItem.addActionListener(menuHandler);
       // deleteMenuItem.setIconTextGap(20);
        deleteMenuItem.setMnemonic(KeyEvent.VK_L);
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke("DELETE"));

        JMenuItem searchMenuItem = new JMenuItem("查找（F）");
        searchMenuItem.setActionCommand("searchMenuItem");
        searchMenuItem.addActionListener(menuHandler);
        //searchMenuItem.setIconTextGap(20);
        searchMenuItem.setMnemonic(KeyEvent.VK_F);
        searchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem replaceMenuItem = new JMenuItem("替换（R）");
        replaceMenuItem.setActionCommand("replaceMenuItem");
        replaceMenuItem.addActionListener(menuHandler);
       // replaceMenuItem.setIconTextGap(20);
        replaceMenuItem.setMnemonic(KeyEvent.VK_R);
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem jumpMenuItem = new JMenuItem("跳转（G）");
        jumpMenuItem.setActionCommand("jumpMenuItem");
        jumpMenuItem.addActionListener(menuHandler);
      //  jumpMenuItem.setIconTextGap(20);
        jumpMenuItem.setMnemonic(KeyEvent.VK_G);
        jumpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem selectAllMenuItem = new JMenuItem("全选（A）");
        selectAllMenuItem.setActionCommand("selectAllMenuItem");
        selectAllMenuItem.addActionListener(menuHandler);
       // selectAllMenuItem.setIconTextGap(20);
        selectAllMenuItem.setMnemonic(KeyEvent.VK_A);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem datetimeMenuItem = new JMenuItem("日期和时间（D）");
        datetimeMenuItem.setActionCommand("datetimeMenuItem");
        datetimeMenuItem.addActionListener(menuHandler);
        //datetimeMenuItem.setIconTextGap(20);
        datetimeMenuItem.setMnemonic(KeyEvent.VK_D);
        datetimeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));

        editMenu.add(undoMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(deleteMenuItem);
        editMenu.addSeparator();
        editMenu.add(searchMenuItem);
        editMenu.add(replaceMenuItem);
        editMenu.add(jumpMenuItem);
        editMenu.addSeparator();
        editMenu.add(selectAllMenuItem);
        editMenu.add(datetimeMenuItem);



        jMenuBar.add(editMenu);

    }
    private void createFileMenu(){
        //菜单
        JMenu fileMenu = new JMenu("文件（F）");
        //设置助记符
        fileMenu.setMnemonic(KeyEvent.VK_F);
        //设置菜单项
        JMenuItem newMenuItem = new JMenuItem("新建（N）");
        newMenuItem.setActionCommand("newMenuItem");
        //设置监听
        newMenuItem.addActionListener(menuHandler);
        //设置距离边界的大小
       // newMenuItem.setIconTextGap(20);
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        //设置快捷键
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));


        JMenuItem newDialogMenuItem = new JMenuItem("新窗口（W）");
        newDialogMenuItem.setActionCommand("newDialogMenuItem");
        newDialogMenuItem.addActionListener(menuHandler);
        //newDialogMenuItem.setIconTextGap(20);
        newDialogMenuItem.setMnemonic(KeyEvent.VK_W);
        newDialogMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));

        JMenuItem openMenuItem = new JMenuItem("打开（O）");
        openMenuItem.setActionCommand("openMenuItem");
        openMenuItem.addActionListener(menuHandler);
       // openMenuItem.setIconTextGap(20);
        openMenuItem.setMnemonic(KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK ));

        JMenuItem saveMenuItem = new JMenuItem("保存（S）");
        saveMenuItem.setActionCommand("saveMenuItem");
        saveMenuItem.addActionListener(menuHandler);
        //saveMenuItem.setIconTextGap(20);
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK ));

        JMenuItem pageMenuItem = new JMenuItem("页面设置");
        pageMenuItem.setActionCommand("pageMenuItem");
        pageMenuItem.addActionListener(menuHandler);
       // pageMenuItem.setIconTextGap(20);

        JMenuItem printMenuItem = new JMenuItem("打印");
        printMenuItem.setActionCommand("printMenuItem");
        printMenuItem.addActionListener(menuHandler);
       // printMenuItem.setIconTextGap(20);
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK ));

        JMenuItem exitMenuItem = new JMenuItem("退出（X）");
        exitMenuItem.setActionCommand("exitMenuItem");
        exitMenuItem.addActionListener(menuHandler);
       // exitMenuItem.setIconTextGap(20);
        exitMenuItem.setMnemonic(KeyEvent.VK_X);

        //把菜单项加到菜单中
        fileMenu.add(newMenuItem);
        fileMenu.add(newDialogMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(pageMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);





        //把菜单加入菜单条中
        jMenuBar.add(fileMenu);
    }
    public boolean isContSaved(){
        return contSaved;
    }
    public void setContSaved(boolean saved) {
        this.contSaved = saved;
    }

    public JTextArea getContArea() {
        return contArea;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public JCheckBoxMenuItem getAutoBreakLineMenuItem() {
        return autoBreakLineMenuItem;
    }

    public int getDefaultFontSize() {
        return defaultFontSize;
    }

    public JPanel getStatusPanel() {
        return statusPanel;
    }

    public JCheckBoxMenuItem getStatusMenuItem() {
        return statusMenuItem;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
