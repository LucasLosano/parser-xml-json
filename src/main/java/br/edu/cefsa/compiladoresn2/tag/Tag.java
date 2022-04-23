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
    private Tag nodePai;
    private String nome;
    private String tipo = "Node";
    private String valor;
    public static List<Tag> organizer = new ArrayList<Tag>();
    private static Stack<Tag> parents = new Stack<Tag>();

    private static Tag ultimaTagFechada;

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
    
    public static void manipulaStack(Stack<Tag> stack, String palavraAtual){
        String status = checaStatus(palavraAtual);
        palavraAtual = limpaPalavra(palavraAtual); 
        if(status.equals("INICIO")){
            adicionaStack(stack, palavraAtual);
            organizer.add(stack.peek());
        }
        if(status.equals("VALOR"))
            alteraTipo(stack, palavraAtual);
        if(status.equals("FIM"))
            removeStack(stack, palavraAtual);
    }
    
    private static void adicionaStack(Stack<Tag> stack, String nome){
        Tag tag = new Tag(nome);
        
        if(!organizer.isEmpty() && ultimaTagFechada != null && ultimaTagFechada.getNome().equals(nome)) {
            if(ultimaTagFechada.getTipo().equals("Node"))
                ultimaTagFechada.setTipo("ArrayInicio");
            tag.setTipo("Array");
        }
        stack.add(tag);
    }
    
    private static void removeStack(Stack<Tag> stack, String nome){
        if(!nome.equals(stack.peek().getNome()))
            System.out.println("Erro"); //TODO throws Exception
        
        ultimaTagFechada = stack.pop();
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

    public boolean contains(Integer integer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Tag get(Integer integer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
