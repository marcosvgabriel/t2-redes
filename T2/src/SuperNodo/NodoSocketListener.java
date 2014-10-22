/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcosVinicius
 */

public class NodoSocketListener extends Thread {
    
    private ServerSocket SocketComm;
    private RunningNode nodoNovoConex;
    private boolean saida;
    
    public NodoSocketListener(int port)
    {
        saida = false;
        try {
            SocketComm = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(NodoSocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void exit()
    {
        saida = true;
    }
    
    public RunningNode getNodo(){
        return nodoNovoConex;
    }
    
    @Override
    public void run()
    {
        while(!saida)
        {
            try {
                nodoNovoConex = new RunningNode(SocketComm.accept());
                nodoNovoConex.start();
            } catch (IOException ex) {
                Logger.getLogger(NodoSocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(NodoSocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
