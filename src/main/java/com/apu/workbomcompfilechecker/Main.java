/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.workbomcompfilechecker;

import java.io.IOException;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author apu
 */
public class Main {
    
    private static final Log log = Log.getInstance();
    private static final Class classname = Main.class;
 
    
    public static String fileTestPcb = "D:/Temp/testPcb.xls";
    public static String fileComp = "D:/Temp/comp.xls";
    
    public static void main(String[] args) {
        try {
            ExcelTable compTable = Excel.loadTableFromExcel(fileComp);
            ExcelTable fileTable = Excel.loadTableFromExcel(fileTestPcb);
            if(Checker.compareComponentNames(fileTable, compTable) == false) {
                for(String str:Checker.getErrors()) {
//                    System.out.println(str);
                    log.debug(classname,str);
                }
            }
        } catch(IOException ex) {
            log.debug(classname,ExceptionUtils.getStackTrace(ex));
        }
    }
    
}
