/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MarcosVinicius
 */


//Main para Criaçao dos SuperNodos
public class main {
    //private static SuperNodo nSuperNodo;
    private static SuperNodo nSuperNodo;
    private static final List<SuperNodo> SuperNodos = new ArrayList();
 
    
    public static void main(String args[]) throws IOException {
        int decisao;
        boolean saida = false;
        
        while(!saida){
        System.out.println("Oque deseja fazer? Incluir Novo SuperNodo (1) ou Sair (0): ");
        Scanner in = new Scanner(System.in);
        decisao = in.nextInt();
        
        //Cria o SuperNodo
        if(decisao == 1){
            //Gera um ID randomico para identificação do Super Nó
            //Esse ID devera ser verificado posteriormente se existe ou nao
            Random gerador = new Random();
            int id = gerador.nextInt(51);
           // nSuperNodo = new SuperNodo(id);
            nSuperNodo = new SuperNodo(id);
            SuperNodos.add(nSuperNodo); //preciso arrumar isso! nao estou conseguindo adicionar            
            System.out.println("SuperNodo Criado! ID: " + SuperNodos.get(id).getId()); //quero printar o valor do ID do SuperNodo
            //System.out.println("SuperNodo Criado! ID: " + id);
            SuperNodos.get(id).start();
        }
        //Sai da Aplicacao e fecha todas as conexoes
        else{
            saida = true;
        
        }
        
        }
        
        
        
        
        System.exit(0);
            
    }
    
}
