package br.edu.cefsa.compiladoresn2;

import br.edu.cefsa.compiladoresn2.arquivo.ArquivoDAO;
import br.edu.cefsa.compiladoresn2.conversor.ConversorXML;
import br.edu.cefsa.compiladoresn2.tag.Tag;

public class CompiladoresN2 {
    public static void main(String[] args) {
        try {
            String xml = ArquivoDAO.readTextFile("N1.txt");
            if(!Tag.validaXML(xml))
                throw new Exception("XML inválido");
            
            StringBuilder result = ConversorXML.fazConversaoParaJSON();
            ArquivoDAO.writeToExitFile("ResultJson.txt", result);
        } catch (Exception e) {
            System.out.print("Desculpe, algo deu errado!\n" + e.getMessage());
        }
    }
}
