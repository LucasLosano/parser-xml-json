/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.compiladoresn2.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author losan
 */
public class Tag {

    private String nome;
    private String tipo = "Node";
    private String valor;
    private List<Tag> tags = new ArrayList<Tag>();
    public static List<Tag> organizer = new ArrayList<Tag>();

    public Tag() {
    }

    public Tag(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(Tag tag) {
        this.tags.add(tag);
    }
    
    public static void manipulaStack(Stack<Tag> stack, String palavraAtual){
        String status = checaStatus(palavraAtual);
        palavraAtual = limpaPalavra(palavraAtual); 
        if(status.equals("INICIO")) 
            adicionaStack(stack, palavraAtual);
        if(status.equals("VALOR"))
            alteraTipo(stack, palavraAtual);
        if(status.equals("FIM"))
            removeStack(stack, palavraAtual);
        if(stack.size() == 1 && status.equals("INICIO"))
            organizer.add(stack.peek());
    }
    
    private static void adicionaStack(Stack<Tag> stack, String nome){
        Tag tag = new Tag(nome);
        
        if(organizer.size() != 0 && !getUltimaTag().getTags().isEmpty() && getUltimaTag().getTags().get(getUltimaTag().getTags().size()-1).getNome().equals(nome)) {
            //organizer.get(organizer.size()-1).setTipo("Array"); //ERRADO!!!
            tag.setTipo("Array");
        }
        if(!stack.empty() && stack.peek().getTipo().equals("Node"))
            stack.peek().setTags(tag);

        stack.add(tag);
    }
    
    private static void removeStack(Stack<Tag> stack, String nome){
        if(!nome.equals(stack.peek().getNome()))
            System.out.println("Erro"); //TODO throws Exception
        
        stack.pop();
    }
    
    private static void alteraTipo(Stack<Tag> stack, String valor){
        stack.peek().setValor(valor);
        stack.peek().setTipo("Dado");
    }
    
    private static String checaStatus(String palavra){
        if(palavra.startsWith("</") && palavra.endsWith(">"))
            return "FIM";
        if(palavra.startsWith("<") && palavra.endsWith(">"))
            return "INICIO";

        return "VALOR";
    }
    
    private static String limpaPalavra(String palavra){
        return palavra.replace("</", "").replace(">", "").replace("<", "");
    }
    
    private static Tag getUltimaTag() {
        return organizer.get(organizer.size()-1);
    }
}
