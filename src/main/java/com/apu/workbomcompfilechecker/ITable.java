/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.workbomcompfilechecker;

import com.apu.workbomcompfilechecker.ITableRow;
import java.util.List;

/**
 *
 * @author apu
 */
public interface ITable {
    
    public int getSize();
    public ITableRow getRowById(int id);
    public void addRow(ITableRow row);
    public Integer getColumnIdByName(String name);
    public List<String> getColumnById(int id);
}
