package com.example.hopeproject.Service;

import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Repository.OutilRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExcelLoader {

    @Autowired
    private OutilRepository outilRepository;

    public void chargerDonneesDepuisExcel(InputStream excelFile) throws Exception {
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            Outil outil = new Outil(
                    row.getCell(0).getStringCellValue(), // Titre (Colonne A)
                    row.getCell(1).getStringCellValue(), // Domaine (Colonne B)
                    row.getCell(2).getStringCellValue(), // Description simple (Colonne C)
                    row.getCell(3).getStringCellValue(), // Description détaillée (Colonne D)
                    row.getCell(4).getStringCellValue(), // Accès (Colonne E)
                    row.getCell(5).getStringCellValue()  // Lien (Colonne F)
            );

            outilRepository.save(outil);
        }

        workbook.close();
    }
}
