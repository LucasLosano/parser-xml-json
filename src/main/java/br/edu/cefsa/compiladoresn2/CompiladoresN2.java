package br.edu.cefsa.compiladoresn2;

import br.edu.cefsa.compiladoresn2.arquivo.ArquivoDAO;
import br.edu.cefsa.compiladoresn2.conversor.ConversorXML;
import br.edu.cefsa.compiladoresn2.tag.Tag;
import java.util.Stack;

public class CompiladoresN2 {

    public static StringBuilder stringConverted;
    public static Tag tagOrganizer;

    public static void main(String[] args) {
        try {
            String xml = ArquivoDAO.readTextFile("N1.txt");
            checaParenteses(xml);
            StringBuilder result = ConversorXML.fazConversaoParaJSON();
            System.out.println(result);
            ArquivoDAO.writeToExitFile("ResultJson.txt", result);
        } catch (Exception e) {
            String a = "";
        }
    }
    public static boolean checaParenteses(String xml) {
        Stack pilhaMaiorMenor = new Stack();
        Stack<Tag> pilhaTag = new Stack<Tag>();
        String palavraAtual = "";

        for (Character aux : xml.toCharArray()) {
            palavraAtual += aux;

            if (aux == '<') {
                if (!palavraAtual.equals(aux.toString())) {
                    Tag.manipulaStack(pilhaTag, palavraAtual.replace("<", ""));
                    palavraAtual = "<";
                }
                pilhaMaiorMenor.add(aux);
                continue;
            }
            if (!pilhaMaiorMenor.empty() && aux == '>') {
                pilhaMaiorMenor.pop();
                Tag.manipulaStack(pilhaTag, palavraAtual);
                palavraAtual = "";
            }

        }
        return !pilhaMaiorMenor.empty() && !pilhaTag.empty();

    }
}
