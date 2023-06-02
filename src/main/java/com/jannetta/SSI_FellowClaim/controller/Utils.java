package com.jannetta.SSI_FellowClaim.controller;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    static Logger logger = Logger.getLogger(Utils.class.getName());

    public static void checkFile(String filename) {
        try {
            logger.log(Level.INFO,"Check if file " + filename + " exists.");
            File f1 = new File(filename);
            boolean created = f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Properties readProperties() {
        try {
            Properties properties = new Properties();
            FileInputStream in = new FileInputStream("system.properties");
            properties.load(in);
            in.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void storeProperties(Properties p) {
        try {
            FileOutputStream out = new FileOutputStream("system.properties");
            p.store(out, "--- No Comment ---");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
