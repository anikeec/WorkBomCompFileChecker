/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.workbomcompfilechecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author apu
 */
public class Excel {
    
    public static ExcelTable loadTableFromExcel(String file) throws IOException {
        try (HSSFWorkbook myExcelBook = 
                        new HSSFWorkbook(new FileInputStream(file))) {
            HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
            int numberOfRows = myExcelSheet.getLastRowNum();
            
            ExcelTable table = new ExcelTable();
            
            //create structure
            HSSFRow row = myExcelSheet.getRow(0);
            int rowLength = row.getLastCellNum();
            String columnName;
            for(int i=0; i<rowLength; i++) {
                if((row.getCell(i)!= null) && 
                    (row.getCell(i).getCellTypeEnum() == CellType.STRING))
                    columnName = row.getCell(i).getStringCellValue();
                else
                    columnName = "";
                table.addColumn(new ArrayList<>(), columnName);
            }
            table.finishEditTableStructure();
            
            String strValue;
            ExcelTableRow tableRow;
            for(int i=0; i<numberOfRows; i++) {
                row = myExcelSheet.getRow(i);
                
                tableRow = new ExcelTableRow(rowLength);               
                
                for(int cellId=0; cellId<rowLength; cellId++) {
                    if(row == null) {
                        strValue = "";
                    } else if(row.getCell(cellId) == null) {
                        strValue = "";
                    } else switch (row.getCell(cellId).getCellTypeEnum()) {
                        case STRING:
                            strValue = row.getCell(cellId).getStringCellValue();
                            break;
                        case NUMERIC:
                            strValue = "" + row.getCell(cellId).getNumericCellValue();
                            break;
                        default:
                            strValue = "";
                            break;
                    }
                    tableRow.setCellValue(cellId, strValue);
                }
                table.addRow(tableRow);
            }
            return table;
        }        
    }
    
}
