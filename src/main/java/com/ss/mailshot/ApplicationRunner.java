package com.ss.mailshot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 21-Mar-2010
 * Time: 22:46:36
 */
public class ApplicationRunner {
    public static void main(String[] args) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mailshot.xml");
    }
}
