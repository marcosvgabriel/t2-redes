/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcosVinicius
 */
public class RunningNode extends Thread {
    private Socket nodoConex;
    private int idresponsavel;
    private boolean saida;
    
    public RunningNode(Socket newSocket){
        nodoConex = newSocket;
        idresponsavel = 0;
        saida = false;
    }
    
    public RunningNode(Socket connection, int id){
        nodoConex = connection;
        idresponsavel = id;
    }
    
    public String getIP(){
        return this.nodoConex.getInetAddress().getHostAddress();
    }
    
     @Override
    public void run(){
        try {
            while(!saida){
            
            
            
            }
            
            

        } catch (IOException ex) {
            Logger.getLogger(RunningNode.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}
