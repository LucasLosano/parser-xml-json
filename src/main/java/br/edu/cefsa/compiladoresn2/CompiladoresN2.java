/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package br.edu.cefsa.compiladoresn2;

import br.edu.cefsa.compiladoresn2.arquivo.ArquivoDAO;
import br.edu.cefsa.compiladoresn2.tag.Tag;
import java.util.Stack;

/**
 *
 * @author losan
 */
public class CompiladoresN2 {

    public static void main(String[] args) {
        try{
           String xml = ArquivoDAO.readTextFile("C:\\Users\\losan\\Desktop\\Documentos\\N1.txt");
           checaParenteses(xml);
        }
        catch(Exception e){
            String a = "";
        }
    }
    
    public static boolean checaParenteses(String xml){
        Stack pilhaMaiorMenor = new Stack();
        Stack<Tag> pilhaTag = new Stack<Tag>();
        String palavraAtual = "";
        
        for (Character aux : xml.toCharArray()) {
            palavraAtual += aux;
            
            if (aux == '<') {
                if(!palavraAtual.equals(aux.toString()))
                {
                    Tag.manipulaStack(pilhaTag, palavraAtual.replace("<", ""));
                    palavraAtual = "<";
                }
                pilhaMaiorMenor.add(aux);
                continue;
            }
            if (!pilhaMaiorMenor.empty() && aux == '>'){
                pilhaMaiorMenor.pop();
                Tag.manipulaStack(pilhaTag, palavraAtual);
                palavraAtual = "";
            }
            
        }
        
        if (!pilhaMaiorMenor.empty())
            return false;
        return true;
    }
}
