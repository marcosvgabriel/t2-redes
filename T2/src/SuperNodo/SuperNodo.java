/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
      

/**
 *
 * @author MarcosVinicius
 */
public class SuperNodo {

    private String nIP;
    private int nPorta = 6600;
    private int cPorta = 6601;
    private int id;
    private NodoSocketListener nodoConex;
    private NClienteSocketListener clienteConex;
    private static ArrayList<RunningNode> nodosAtivos;
    private static DHT tabela;
    
    public SuperNodo(int idSent){
        id = idSent;
    }
    
    public static ArrayList<RunningNode> getnodosAtivos(){
        return nodosAtivos;
    }
    
    public static DHT getDHT()
    {
        return tabela;
    }
    
    public int getId(){
        return this.id;
}
   
    
    public void start() throws IOException{
    
    try{
        nodoConex = new NodoSocketListener(nPorta);
        nodoConex.start();
        clienteConex = new NClienteSocketListener(cPorta);
        clienteConex.start();
        nodosAtivos = new ArrayList<>();
            
        tabela = new DHT();
        tabela.setNodosAtivos(nodosAtivos);
        
        nIP = nodoConex.getNodo().getIP();
        Socket SNConex = new Socket(nIP, nPorta);
        OutputStream SNConexOutStream = SNConex.getOutputStream();
        BufferedReader SNCOnexInStream = new BufferedReader(new InputStreamReader(SNConex.getInputStream()));
        nodosAtivos.add(new RunningNode(SNConex,id));
        System.out.println("Node is Online. Waiting for files\n");
               
    }catch(IOException ioex){
            System.out.println("IO failure at Node startup\n");
            System.out.println(ioex.getMessage());
    }

    
    
    
    }
    
    
    
    

    
}
