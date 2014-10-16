/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.*;
import java.net.*;
      

/**
 *
 * @author MarcosVinicius
 */
public class SuperNodo {
    
    private String mensagem;
    private NodoSocketListener incomingNode;
    
    public static void run(){
    
    try{
        incomingNode = new NodoSocketListener(6600);
        incomingNode.start();
    
    
    
    
    
    }
    catch(IOException ioex)
        {
            status.setText(status.getText().concat("IO failure at Node startup\n"));
            status.setText(status.getText().concat(ioex.getMessage()));
        }
    
    
    
    }
    
    
    
    

    
}
