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
    
    @Override
    public void run()
    {
        RemoteNode newConnection;
        while(!exit)
        {
            try {
                newConnection = new RemoteNode(SocketComm.accept());
                newConnection.start();
            } catch (IOException ex) {
                Logger.getLogger(NodeSocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeSocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
