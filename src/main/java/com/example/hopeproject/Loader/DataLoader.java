package com.example.hopeproject.Loader;

import com.example.hopeproject.Service.ExcelLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ExcelLoader excelLoader;

    @Override
    public void run(String... args) throws Exception {
        InputStream excelFile = new ClassPathResource("HOPE_Excel.xlsx").getInputStream();
        excelLoader.chargerDonneesDepuisExcel(excelFile);
        System.out.println("Les données Excel ont été chargées avec succès !");
    }
}
