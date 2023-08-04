package com.jannetta.SSI_FellowClaim.view;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class MenuBar extends JMenuBar {
    Logger logger = Logger.getLogger(getClass().getName());

    MenuBar(MainFrame mainFrame) {
        JMenu file;
        JMenuItem openFile;
        JMenuItem saveFile;
        JMenuItem printFile;
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        openFile = new JMenuItem("Open", KeyEvent.VK_O);
        openFile.addActionListener(mainFrame);
        saveFile = new JMenuItem("Save", KeyEvent.VK_S);
        saveFile.addActionListener(mainFrame);
        printFile = new JMenuItem("Print", KeyEvent.VK_P);
        printFile.addActionListener(mainFrame);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        this.add(file);
    }

}
