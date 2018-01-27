/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.workbomcompfilechecker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apu
 */
public class ExcelTableRow implements ITableRow {
    
    private final List<String> row;
    private final int length;

    public ExcelTableRow(int length) {
        this.row = new ArrayList<>();
        for(int i=0; i<length; i++) this.row.add(null);
        this.length = length;
    }
    
    @Override
    public void setCellValue(int cellId, String value) {
        row.set(cellId, value.trim());
    }

    @Override
    public int getLength() {
        return row.size();
    }

    @Override
    public String getCellValue(int cellId) {
        return row.get(cellId);
    }
    
}
