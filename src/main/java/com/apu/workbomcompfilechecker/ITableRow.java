/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.workbomcompfilechecker;

/**
 *
 * @author apu
 */
public interface ITableRow {
    
    public int getLength();    
    public String getCellValue(int cellId);
    public void setCellValue(int cellId, String value);
    
}
