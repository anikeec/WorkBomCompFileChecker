/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.workbomcompfilechecker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apu
 */
public class Main {
    
    public static String fileTestPcb = "D:/Temp/testPcb.xls";
    public static String fileComp = "D:/Temp/comp.xls";
    
    public static void main(String[] args) {
        try {
            ExcelTable compTable = Excel.loadTableFromExcel(fileComp);
            ExcelTable fileTable = Excel.loadTableFromExcel(fileTestPcb);
            System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
