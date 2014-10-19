/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcosVinicius
 */
public class NClienteSocketListener extends Thread {
    
    private ServerSocket SocketCommC;
    private boolean saida;
    
    public NClienteSocketListener(int porta){
        saida = false;
        try {
            SocketCommC = new ServerSocket(porta);
        } catch (IOException ex) {
            Logger.getLogger(NClienteSocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sair(){
        saida = true;
    }    

    
    @Override
    public void run()
    {
        NCliente NConex;
        while(!saida)
        {
            try {
                NConex = new NCliente(SocketCommC.accept());
                NConex.start();
            } catch (IOException ex) {
                Logger.getLogger(NClienteSocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(NClienteSocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    
}
