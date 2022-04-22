/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.compiladoresn2.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author losan
 */
public abstract class ArquivoDAO {
    
    public static String readTextFile(String filePath) throws Exception{
        StringBuilder inputText;
        try ( BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            inputText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                inputText.append(line.trim());
            }
        }
        return inputText.toString();
    }
    
    public static void writeToExitFile(String fileName,StringBuilder outputText) throws Exception {
        File file = new File(fileName);
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(outputText.toString());
        }
    }
}
