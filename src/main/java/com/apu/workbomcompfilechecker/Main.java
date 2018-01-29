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
    
    public static void main(String[] args) {
        try {
            String fileTestPcb = args[0];
            String fileComp = args[1];
            ExcelTable compTable = Excel.loadTableFromExcel(fileComp);
            ExcelTable fileTable = Excel.loadTableFromExcel(fileTestPcb);
            if(Checker.compareComponentNames(fileTable, compTable) == false) {
                for(String str:Checker.getErrors()) {
                    log.info(classname,str);
                }
            }
        } catch(IOException ex) {
            log.debug(classname,ExceptionUtils.getStackTrace(ex));
        }
    }
    
}
