package com.jannetta.SSI_FellowClaim;

import com.jannetta.SSI_FellowClaim.model.AllSections;
import com.jannetta.SSI_FellowClaim.view.MainFrame;

import java.util.logging.Logger;

public class Root {
    Logger logger = Logger.getLogger(getClass().getName());
    MainFrame mainFrame;
    AllSections allSections;

    Root() {
        allSections = new AllSections();
        mainFrame = new MainFrame(this);
    }


    public AllSections getAllSections() {
        return allSections;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
