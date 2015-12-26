package com.ss.mailshot.gui.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 27-Mar-2010
 * Time: 14:20:10
 */
public class MapTableModel extends AbstractTableModel {
    private List<Map<String, String>> rows;
    private String[] headers;

    public MapTableModel(List<Map<String, String>> rows, String[] headers) {
        this.rows = rows;
        this.headers = headers;
    }

    @Override
    public String getColumnName(int i) {
        return headers[i];
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Map<String, String> map = rows.get(i);
        return map.get(headers[i1]);
    }

    public List<Map<String, String>> getRowsMap() {
        return rows;
    }
}
