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
public class Checker {
    
    private static List<String> errors;
    
    public static List<String> getErrors() {
        return errors;
    }
    
    public static boolean compareComponentNames(ITable bomTable, ITable compTable) {
        errors = new ArrayList<>();
        
        int numberOfRow = bomTable.getSize();
        
        Integer referenceBomCid = 
                    bomTable.getColumnIdByName("Reference");
        Integer compNameBomCid = 
                bomTable.getColumnIdByName("Component Name");
        Integer valueBomCid = 
                bomTable.getColumnIdByName("Value");
        Integer referenceCompCid = 
                compTable.getColumnIdByName("Нотификатор компоненты");
        Integer compNameECompCid = 
                compTable.getColumnIdByName("Название компоненты (латинское)");
        Integer compNameRCompCid = 
                compTable.getColumnIdByName("Название компоненты (русское)");
        Integer valueCompCid = 
                compTable.getColumnIdByName("Номинал");

        if((referenceBomCid == null) ||
           (compNameBomCid == null) ||
           (referenceCompCid == null) ||
           (compNameECompCid == null) ||
           (compNameRCompCid == null) ||
           (valueCompCid == null)) {
            errors.add("Error in input file.");
            return false;
        }
        
        ITableRow rowBom, rowComp;        
        String referenceBom;
        String compNameBom;
        String valueBom;
        String referenceComp;
        String compNameEComp;
        String compNameRComp;
        String valueComp; 
        
        for(int rowBomId=1; rowBomId<numberOfRow; rowBomId++) {
            
            rowBom = bomTable.getRowById(rowBomId);            
            if(rowBom == null) continue;
            
            referenceBom = rowBom.getCellValue(referenceBomCid);
            compNameBom = rowBom.getCellValue(compNameBomCid);
            
            rowComp = null;
            List<String> referenceListComp = compTable.getColumnById(referenceCompCid);
            for(int rowCompId=1; rowCompId<compTable.getSize(); rowCompId++) {
                if(referenceListComp.get(rowCompId).equalsIgnoreCase(referenceBom)) {
                    rowComp = compTable.getRowById(rowCompId);
                    break;
                }
            }
            
            if(rowComp == null) {
                errors.add("" + referenceBom + " - Can't find reference in comp.xls");
                continue;
            }
            
            compNameEComp = rowComp.getCellValue(compNameECompCid);
            compNameRComp = rowComp.getCellValue(compNameRCompCid);
            
            if((compNameBom.equalsIgnoreCase(compNameEComp) == false) &&
               (compNameBom.equalsIgnoreCase(compNameRComp) == false)) {
                String error = "Different name for " + referenceBom + ": \r\n" +
                        "bom - " + compNameBom + "\r\n" +
                        "comp.xls - " + compNameEComp + "\r\n" +
                        "comp.xls - " + compNameRComp + "\r\n";
                errors.add(error);                
                continue;
            }
            
            
            if(valueBomCid != null) {
                valueBom = rowBom.getCellValue(valueBomCid);
                valueComp = rowComp.getCellValue(valueCompCid);
                if(!valueBom.equalsIgnoreCase(valueComp)) {
                     String error = "Different value for " + referenceBom + ": \r\n" +
                             "bom - " + valueBom + "\r\n" +
                             "comp.xls - " + valueComp + "\r\n";
                     errors.add(error);                
                     continue;
                 }
            }            
        }
        if(!errors.isEmpty())  return false;
        return true;
    }
}
