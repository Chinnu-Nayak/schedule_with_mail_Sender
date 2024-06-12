package com.chinu.Service;

import com.chinu.Entity.Order;
import com.chinu.Repo.OrderRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private OrderRepo orderRepo;
    public List<Order> fetchAll(){
        return orderRepo.findAll();
    }


    public byte[] generateExcelFile() throws IOException {
        List<Order> orders = orderRepo.findAll();
        SXSSFWorkbook workbook=new SXSSFWorkbook();
//        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");


        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Quantity");
        headerRow.createCell(3).setCellValue("Price");

        // Populate data rows
        int rowNum = 1;
        for (Order order : orders) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getName());
            row.createCell(2).setCellValue(order.getQty());
            row.createCell(3).setCellValue(order.getPrice());
        }

        // Write to file
        byte[] excelBytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            excelBytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return excelBytes;
    }

}
