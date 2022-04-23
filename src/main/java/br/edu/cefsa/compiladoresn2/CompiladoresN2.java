package br.edu.cefsa.compiladoresn2;

import br.edu.cefsa.compiladoresn2.arquivo.ArquivoDAO;
import br.edu.cefsa.compiladoresn2.tag.Tag;
import java.util.Stack;

public class CompiladoresN2 {
    
public static StringBuilder stringConverted;
public static Tag tagOrganizer;

    public static void main(String[] args) {
        try{
           String xml = ArquivoDAO.readTextFile("N1.txt");
           checaParenteses(xml);
           StringBuilder x = fazConversaoParaJSON();
           System.out.println(x);
        }
        
        catch(Exception e){
            String a = "";
         }
    }
    
    public static void escreveNode(String valor, StringBuilder stringFinal) {
        stringFinal.append(("\"" + valor + "\"" + ": {"));
    }
    public static void escreveArrayInicio(String valor, StringBuilder stringFinal) {
        stringFinal.append(("\"" + valor + "\"" + ": ["));
    }
    
    public static void escreveDado(Tag tagObj, StringBuilder stringFinal) {
        stringFinal.append("\"" + tagObj.getNome() + "\": \""+ tagObj.getValor()+ "\"");
    }
    
    public static StringBuilder fazConversaoParaJSON(){
        Stack charPilha = new Stack();
        var allTags = Tag.organizer;
        StringBuilder stringToReturn = new StringBuilder();
        stringToReturn.append('{');
        if(allTags != null) {
            for(Integer i = 0; i < allTags.size() ; i++) {
                Tag tagObj = allTags.get(i);
                charPilha.add('{');
                Integer next = i + 1;
                Tag proxTag = new Tag();
                if(allTags.contains(next)) {
                    proxTag = allTags.get(next);
                }
                
                if("Node".equals(tagObj.getTipo())) {
                    stringToReturn.append("\n");
                    System.out.println("Node");
                    escreveNode(tagObj.getNome(), stringToReturn);
                }
                
                if("ArrayInicio".equals(tagObj.getTipo())) {
                    stringToReturn.append("\n");
                    charPilha.add('{');
                    System.out.println("ArrayInicio");
                    escreveArrayInicio(tagObj.getNome(), stringToReturn);
                    stringToReturn.append("\n" + "{");
                }
                
                if("Dado".equals(tagObj.getTipo())){
                    System.out.println("Dado");
                    stringToReturn.append("\n");
                    escreveDado(tagObj, stringToReturn);
                }
                
                if("Array".equals(tagObj.getTipo())) {
                   System.out.println("Array");
                   stringToReturn.append("\n" + "{" );
               }
            }
        }
        return stringToReturn;
     }
    public static boolean checaParenteses(String xml){
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
            if (!pilhaMaiorMenor.empty() && aux == '>'){
                pilhaMaiorMenor.pop();
                Tag.manipulaStack(pilhaTag, palavraAtual);
                palavraAtual = "";
            }
            
        }
        return !pilhaMaiorMenor.empty() && !pilhaTag.empty();
 
    }
}
