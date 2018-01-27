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
public class ExcelTable implements ITable {
    
    private final List<List<String>> table;
    private final List<String> tableColumnNames;
    private boolean tableIsCreated;
    private int lastRowId;

    public ExcelTable() {
        this.table = new ArrayList<>();
        this.tableColumnNames = new ArrayList<>();
        this.tableIsCreated = false;
        this.lastRowId = 0;
    }
    
    public void addColumn(List<String> column, String name) {
        if(tableIsCreated)  return;
        table.add(column);
        tableColumnNames.add(name);
    }
    
    public void finishEditTableStructure() {
        this.tableIsCreated = true;
    }
    
    public void addRow(ITableRow row) {
        for(List list:table) { //fill row by nulls at first
            list.add(null);
        }
        int rowId = this.lastRowId;
        this.lastRowId++;
        for(int columnId=0; columnId<row.getLength(); columnId++)
            table.get(columnId).set(rowId, row.getCellValue(columnId));
    }

    @Override
    public ITableRow getRowById(int id) {
        ExcelTableRow tableRow = new ExcelTableRow(table.size());
        for(int i=0; i<table.size(); i++) {
            tableRow.setCellValue(i, table.get(i).get(id));
        }
        return tableRow;
    }
    
}
