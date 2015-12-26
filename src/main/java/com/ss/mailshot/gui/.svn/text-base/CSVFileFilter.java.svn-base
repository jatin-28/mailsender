package com.ss.mailshot.gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 27-Mar-2010
 * Time: 13:23:44
 */
public class CSVFileFilter extends FileFilter {            
    @Override
    public boolean accept(File file) {
        return file.getName().contains(".csv") || file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Comma Separated Files: *.csv";
    }
}
