package Pr.Cars;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Backup {

    public void CreateBackupService() {
        try {
            Sql db = new Sql();
            ArrayList<String> Cars = new ArrayList<>();
            ResultSet rs = db.Query_All_Lisc();
            while (rs.next()) {
                Cars.add(rs.getString("LiscPlate"));
            }

            Workbook workbook = new XSSFWorkbook();
            Font hFont=workbook.createFont();
            hFont.setBold(true);
            hFont.setFontHeightInPoints((short)14);
            CellStyle hStyle=workbook.createCellStyle();
            hStyle.setFont(hFont);
            hStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            hStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            for (int i = 0; i < Cars.size(); i++) {
                Sheet sh = workbook.createSheet(Cars.get(i));
                String[] ColumnHeaders = {"Ημερομηνία", "Τύπος","Αλλαγές","Χιλιόμετρα","Συνεργείο","Επόμενο(ΧΛΜ)","Επόμενο(ΗΜ)","Τιμή","id"};
                Row Header = sh.createRow(0);
                for (int j=0;j<ColumnHeaders.length;j++) {
                    Cell cell = Header.createCell(j);
                    cell.setCellValue(ColumnHeaders[j]);
                    cell.setCellStyle(hStyle);
                }
                ResultSet rs1=db.Query_Specific_With_Lisc(Cars.get(i),"Service");
                int k=1;
                while (rs1.next()){
                    Row row=sh.createRow(k);
                    Cell Date=row.createCell(0);
                    Date.setCellValue(rs1.getString("Date"));
                    Cell Type=row.createCell(1);
                    Type.setCellValue(rs1.getString("Type"));
                    Cell Changes=row.createCell(2);
                    Changes.setCellValue(rs1.getString("Changes"));
                    Cell Km=row.createCell(3);
                    Km.setCellValue(rs1.getInt("Kilometers"));
                    Cell Shop=row.createCell(4);
                    Shop.setCellValue(rs1.getString("Workshop"));
                    Cell NextKm=row.createCell(5);
                    NextKm.setCellValue(rs1.getInt("Next_Kilometers"));
                    Cell NextD=row.createCell(6);
                    NextD.setCellValue(rs1.getString("Next_Date"));
                    Cell Price=row.createCell(7);
                    Price.setCellValue(rs1.getInt("Price"));
                    Cell id=row.createCell(8);
                    id.setCellValue(rs1.getString("Service_Id"));
                    k++;
                }
                for(int l=0;l<ColumnHeaders.length;l++){
                    sh.autoSizeColumn(l);
                }
            }
            db.Disconnect();
            FileOutputStream fileout = new FileOutputStream("Backup/Service.xlsx");
            workbook.write(fileout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void CreateBackupRepair() {
        try {
            Sql db = new Sql();
            ArrayList<String> Cars = new ArrayList<>();
            ResultSet rs = db.Query_All_Lisc();
            while (rs.next()) {
                Cars.add(rs.getString("LiscPlate"));
            }
            Workbook workbook = new XSSFWorkbook();
            Font hFont=workbook.createFont();
            hFont.setBold(true);
            hFont.setFontHeightInPoints((short)14);
            CellStyle hStyle=workbook.createCellStyle();
            hStyle.setFont(hFont);
            hStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            hStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            for (int i = 0; i < Cars.size(); i++) {
                Sheet sh = workbook.createSheet(Cars.get(i));
                String[] ColumnHeaders = {"Ημερομηνία", "Περιγραφή","Αλλαγές","Χιλιόμετρα","Συνεργείο","Τιμή","id"};
                Row Header = sh.createRow(0);
                for (int j=0;j<ColumnHeaders.length;j++) {
                    Cell cell = Header.createCell(j);
                    cell.setCellValue(ColumnHeaders[j]);
                    cell.setCellStyle(hStyle);
                }
                ResultSet rs1=db.Query_Specific_With_Lisc(Cars.get(i),"Repairs");
                int k=1;
                while (rs1.next()){
                    Row row=sh.createRow(k);
                    Cell Date=row.createCell(0);
                    Date.setCellValue(rs1.getString("Date"));
                    Cell Type=row.createCell(1);
                    Type.setCellValue(rs1.getString("Discreption"));
                    Cell Changes=row.createCell(2);
                    Changes.setCellValue(rs1.getString("Changes"));
                    Cell Km=row.createCell(3);
                    Km.setCellValue(rs1.getInt("Kilometers"));
                    Cell Shop=row.createCell(4);
                    Shop.setCellValue(rs1.getString("Workshop"));
                    Cell Price=row.createCell(5);
                    Price.setCellValue(rs1.getInt("Price"));
                    Cell id=row.createCell(6);
                    id.setCellValue(rs1.getString("Repair_Id"));
                    k++;
                }
                for(int l=0;l<ColumnHeaders.length;l++){
                    sh.autoSizeColumn(l);
                }
            }
            db.Disconnect();
            FileOutputStream fileout = new FileOutputStream("Backup/Επισκευές.xlsx");
            workbook.write(fileout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void CreateBackupRefill() {
        try {
            Sql db = new Sql();
            ArrayList<String> Cars = new ArrayList<>();
            ResultSet rs = db.Query_All_Lisc();
            while (rs.next()) {
                Cars.add(rs.getString("LiscPlate"));
            }

            Workbook workbook = new XSSFWorkbook();
            Font hFont=workbook.createFont();
            hFont.setBold(true);
            hFont.setFontHeightInPoints((short)14);
            CellStyle hStyle=workbook.createCellStyle();
            hStyle.setFont(hFont);
            hStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            hStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            for (int i = 0; i < Cars.size(); i++) {
                Sheet sh = workbook.createSheet(Cars.get(i));
                String[] ColumnHeaders = {"Ημερομηνία","Ποσότητα(Lt)", "Χιλίομετρα","Οδηγός","Δρομολόγιο","id"};
                Row Header = sh.createRow(0);
                for (int j=0;j<ColumnHeaders.length;j++) {
                    Cell cell = Header.createCell(j);
                    cell.setCellValue(ColumnHeaders[j]);
                    cell.setCellStyle(hStyle);
                }
                ResultSet rs1=db.Query_Specific_Refill(Cars.get(i));
                int k=1;
                while (rs1.next()){
                    Row row=sh.createRow(k);
                    Cell Date=row.createCell(0);
                    Date.setCellValue(rs1.getString("Date"));
                    Cell Amount=row.createCell(1);
                    Amount.setCellValue(rs1.getString("Amount"));
                    Cell Km=row.createCell(2);
                    Km.setCellValue(rs1.getInt("Kilometers"));
                    Cell Driver=row.createCell(3);
                    Driver.setCellValue(rs1.getString("Driver"));
                    Cell Location=row.createCell(4);
                    Location.setCellValue(rs1.getString("Location"));
                    Cell id=row.createCell(5);
                    id.setCellValue(rs1.getString("Id"));
                    k++;
                }
                for(int l=0;l<ColumnHeaders.length;l++){
                    sh.autoSizeColumn(l);
                }
            }
            db.Disconnect();
            FileOutputStream fileout = new FileOutputStream("Backup/Κάυσιμα.xlsx");
            workbook.write(fileout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void CreateBackupKteo() {
        try {
            Sql db = new Sql();
            ArrayList<String> Cars = new ArrayList<>();
            ResultSet rs = db.Query_All_Lisc();
            while (rs.next()) {
                Cars.add(rs.getString("LiscPlate"));
            }

            Workbook workbook = new XSSFWorkbook();
            Font hFont=workbook.createFont();
            hFont.setBold(true);
            hFont.setFontHeightInPoints((short)14);
            CellStyle hStyle=workbook.createCellStyle();
            hStyle.setFont(hFont);
            hStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            hStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            for (int i = 0; i < Cars.size(); i++) {
                Sheet sh = workbook.createSheet(Cars.get(i));
                String[] ColumnHeaders = {"Ημερομηνία","Χιλιόμετρα","Επόμενο","Συνεργείο","Παρατηρήσεις","Τιμή"};
                Row Header = sh.createRow(0);
                for (int j=0;j<ColumnHeaders.length;j++) {
                    Cell cell = Header.createCell(j);
                    cell.setCellValue(ColumnHeaders[j]);
                    cell.setCellStyle(hStyle);
                }
                ResultSet rs1=db.Query_Specific_With_Lisc(Cars.get(i),"KTEO");
                int k=1;
                while (rs1.next()){
                    Row row=sh.createRow(k);
                    Cell Date=row.createCell(0);
                    Date.setCellValue(rs1.getString("Date"));
                    Cell Km=row.createCell(1);
                    Km.setCellValue(rs1.getInt("Kilometers"));
                    Cell NextD=row.createCell(2);
                    NextD.setCellValue(rs1.getString("DateNext"));
                    Cell Shop=row.createCell(3);
                    Shop.setCellValue(rs1.getString("Company"));
                    Cell Warnings=row.createCell(4);
                    Warnings.setCellValue(rs1.getString("Warnings"));
                    Cell Price=row.createCell(5);
                    Price.setCellValue(rs1.getInt("Price"));

                    k++;
                }
                for(int l=0;l<ColumnHeaders.length;l++){
                    sh.autoSizeColumn(l);
                }
            }
            db.Disconnect();
            FileOutputStream fileout = new FileOutputStream("Backup/ΚΤΕΟ.xlsx");
            workbook.write(fileout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void CreateBackupEmmision() {
        try {
            Sql db = new Sql();
            ArrayList<String> Cars = new ArrayList<>();
            ResultSet rs = db.Query_All_Lisc();
            while (rs.next()) {
                Cars.add(rs.getString("LiscPlate"));
            }

            Workbook workbook = new XSSFWorkbook();
            Font hFont=workbook.createFont();
            hFont.setBold(true);
            hFont.setFontHeightInPoints((short)14);
            CellStyle hStyle=workbook.createCellStyle();
            hStyle.setFont(hFont);
            hStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            hStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            for (int i = 0; i < Cars.size(); i++) {
                Sheet sh = workbook.createSheet(Cars.get(i));
                String[] ColumnHeaders = {"Ημερομηνία","Χιλιόμετρα","Επόμενο","Ενσωμάτοση στο ΚΤΕΟ ?","Τιμή"};
                Row Header = sh.createRow(0);
                for (int j=0;j<ColumnHeaders.length;j++) {
                    Cell cell = Header.createCell(j);
                    cell.setCellValue(ColumnHeaders[j]);
                    cell.setCellStyle(hStyle);
                }
                ResultSet rs1=db.Query_Specific_With_Lisc(Cars.get(i),"EmmisionCard");
                int k=1;
                while (rs1.next()){
                    Row row=sh.createRow(k);
                    Cell Date=row.createCell(0);
                    Date.setCellValue(rs1.getString("Date"));
                    Cell Km=row.createCell(1);
                    Km.setCellValue(rs1.getInt("Kilometers"));
                    Cell NextD=row.createCell(2);
                    NextD.setCellValue(rs1.getString("NextDate"));
                    Cell Warnings=row.createCell(3);
                    Warnings.setCellValue(rs1.getString("WithKTEO"));
                    k++;
                }
                for(int l=0;l<ColumnHeaders.length;l++){
                    sh.autoSizeColumn(l);
                }
            }
            db.Disconnect();
            FileOutputStream fileout = new FileOutputStream("Backup/Κάρτα_Καυσαερίων.xlsx");
            workbook.write(fileout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
