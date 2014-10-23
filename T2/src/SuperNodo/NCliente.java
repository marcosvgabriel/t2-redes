/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

/**
 *
 * @author MarcosVinicius
 */
public class NCliente extends Thread{
    
    private Socket NCConex;
    private boolean saida;
    
    public NCliente(Socket NCSocket){
        NCConex =  NCSocket;
        saida = false;   
    }
    
    @Override
    public void run()
    {
        try {
            while(!saida){
                
            }
                
                
                
        }catch(IOException ioex)
        {
            
        }
    }
    
    
}
