/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcosVinicius
 */
public class Cliente {
    public static void main(String args[]) {
        String numeroIP = null;
        int numeroPorta = 6601;
        String opcao = "9";
        String caminho;
        boolean saida = true;
        Socket superNodoConex;
        System.out.println("Digite o número IP do SuperNodo que deseja Conectar");
        BufferedReader nIP = new BufferedReader(new InputStreamReader(System.in));   
        
        //Leitura do Endereço IP
        try {   
          numeroIP = nIP.readLine();   
        } catch (IOException ioe) {   
          System.out.println("IO erro tentando ler o ip");   
          System.exit(1);   
        }
        
        //Conexao com o Nodo
        try
        {
            if(numeroIP != null && numeroIP.length() > 0)
            {
                superNodoConex = new Socket(numeroIP, numeroPorta);
                System.out.println("Conectado ao Nodo\n");
            }
            else
            {
                System.out.println("Nao foi possivel conectar, por favor digitar um numero IP valido\n");
            }
        }
        catch(IOException ioex)
        {
            System.out.println("Nodo Inacancavel ou Desconhecido\n");
        }
        
        while(saida){
        System.out.println("Oque deseja fazer?\n");
        System.out.println("1 - Upload\n");
        System.out.println("2 - Download\n");
        System.out.println("0 - Sair\n");
        System.out.println("Digite a opção que deseja: ");
        
        
        BufferedReader nOp = new BufferedReader(new InputStreamReader(System.in));   
        
        //Leitura da Opcao Desejada
        try {   
          opcao = nOp.readLine();   
        } catch (IOException ioe) {   
          System.out.println("IO erro tentando ler a opcao");   
          System.exit(1);   
        }
        
        switch(Integer.getInteger(opcao)){
            case 1:
                //Upload
                System.out.println("Digite o caminho do arquivo a ser carregado: ");
                BufferedReader cam = new BufferedReader(new InputStreamReader(System.in));   
        
                //Leitura do Caminho
                try {   
                caminho = cam.readLine();   
                } catch (IOException ioe) {   
                System.out.println("IO erro tentando ler o caminho");   
                System.exit(1);   
                }
                
                try {
                File arq_digitado = new File(caminho);
                FileInputStream arq_envia = new FileInputStream(arq_digitado);
                OutputStream infoSaida = superNodoConex.getOutputStream();
                
                //infoSaida.write("101 - Retrive Possible File Location\n".getBytes());
                infoSaida.write((arq_digitado.getName()+"\n").getBytes());
                
                BufferedReader messageBuffer = new BufferedReader(new InputStreamReader(superNodoConex.getInputStream()));
                String lastMessage = messageBuffer.readLine();
                
                Socket targetNode = null;
                
                if(!lastMessage.matches("127.0.0.1"))
                {
                    targetNode = new Socket(lastMessage, Integer.parseInt(textPort.getText()));
                    nodeOutput = targetNode.getOutputStream();
                    messageBuffer = new BufferedReader(new InputStreamReader(targetNode.getInputStream()));
                }
                
                nodeOutput.write("102 - Sending File\n".getBytes());
                nodeOutput.write((chosenFile.getName()+"\n").getBytes());
                nodeOutput.write((chosenFile.length()+"\n").getBytes());
                
                lastMessage = messageBuffer.readLine();
                
                switch (lastMessage)
                {
                    case "104 - File Accepted":
                    {
                        areaStatus.setText(areaStatus.getText().concat("Starting upload\n"));
                        for(int i = 0; i < chosenFile.length(); i++)
                        {
                            nodeOutput.write(sendingFile.read());
                        }
                        nodeOutput.flush();
                        sendingFile.close();
                        areaStatus.setText(areaStatus.getText().concat("Upload Complete\n"));
                        break;
                    }
                    case "203 - File Already Exists":
                    {
                        areaStatus.setText(areaStatus.getText().concat("File already exists on the system\n"));
                        break;
                    }
                    case "201 - Wrong File Location":
                    {
                        areaStatus.setText(areaStatus.getText().concat("Trying to save file on the wrong server\n"));
                        break;
                    }
                }
                
                if(targetNode != null)
                {
                    targetNode.close();
                }
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                
            break;
            case 2:
                //Dowload
                
            break;    
            case 0:
                //Sair e Desconectar
            break;    
       
        }
             
        
        }
    }
    
}
