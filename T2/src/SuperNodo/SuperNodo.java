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
    
    private String mensagem;
    private String nIP;
    private int nPorta = 6600;
    private int cPorta;
    private NodoSocketListener nodoConex;
    private NClienteSocketListener clienteConex;
    private static ArrayList<RunningNode> nodosAtivos;
    private static DHT tabela;
    
    public SuperNodo(String nmensagem, String nnIP, int nnPorta, int ncPorta){
        mensagem = nmensagem;
        nIP = nnIP;
        nPorta = nnPorta;
        cPorta = ncPorta;
    }
    
    public static ArrayList<RunningNode> getnodosAtivos(){
        return nodosAtivos;
    }
    
    public static DHT getDHT()
    {
        return tabela;
    }
    public static void enviaNodos(String menssagem)
    {
        for(int i = 0; i < nodosAtivos.size(); i++)
        {
            nodosAtivos.get(i).send(menssagem);
        }
    }
    
    
    public void start(){
    
    try{
        nodoConex = new NodoSocketListener(nPorta);
        nodoConex.start();
        clienteConex = new NClienteSocketListener(cPorta);
        clienteConex.start();
        nodosAtivos = new ArrayList<>();
            
        tabela = new DHT();
        tabela.setNodosAtivos(nodosAtivos);
            
        if(!nIP.isEmpty())
        {
            //Conecta ao SuperNode indicado, requisitando as informações pertinentes
            Socket SNConex = new Socket(nIP, nPort);
                OutputStream SNConexOutStream = existingSuperNode.getOutputStream();
                BufferedReader existingSuperNodeInStream = new BufferedReader(new InputStreamReader(existingSuperNode.getInputStream()));
                //Requisitando registro deste node na rede.
                SNConexOutStream.write("001 - New Node Registration\n".getBytes());
                SNConexOutStream.flush();
                //Preenchendo o primeiro nó com a conexão atual
                
                String lastMessage = existingSuperNodeInStream.readLine();
                String[] splitString = lastMessage.split(";");
                knownNodes.add(new RemoteNode(existingSuperNode,Integer.parseInt(splitString[0]),
                        Integer.parseInt(splitString[1]),
                        Integer.parseInt(splitString[2])));
                
                //Loop para preencher a lista de SuperNodes que foram recebidos na lista acima.
                lastMessage = existingSuperNodeInStream.readLine();
                int size = Integer.parseInt(lastMessage);
                int bigNode = 0;
                int bigCriteria = knownNodes.get(0).getCriteria();
                for(int i = 0; i < size; i++)
                {
                    lastMessage = existingSuperNodeInStream.readLine();
                    splitString = lastMessage.split(";");
                    int criteria = Integer.parseInt(splitString[3]);
                    knownNodes.add(new RemoteNode(new Socket(splitString[0], nodePort),Integer.parseInt(splitString[1]),Integer.parseInt(splitString[2]),criteria));
                    //2- Determinando qual Supernode vai ter a carga dividida com esse node novo baseado na lista recebida
                    if(bigCriteria < criteria)
                    {
                        bigCriteria = criteria;
                        bigNode = i;
                    }
                }
                
                RemoteNode sharingNode = knownNodes.get(bigNode);
                sharingNode.send("006 - Split Node Load\n");
                status.setText("Node is Online. Waiting for files\n");
            }
            else
            {
                //Configura esse Supernode para aceitar 
                local.setLocalLowerBound(0);
                local.setLocalUpperBound(1024);
                status.setText("Node is Online\n");
         }
    }
    
    catch(IOException ioex)
        {
            status.setText(status.getText().concat("IO failure at Node startup\n"));
            status.setText(status.getText().concat(ioex.getMessage()));
        }
    
    
    
    }
    
    
    
    

    
}
