/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcosVinicius
 */

//Main para Conexao do Cliente
public class Cliente {
    public static void main(String args[]) {
        String numeroIP = null;
        int numeroPorta = 6601;
        String opcao = "9";
        String caminho = "";
        String sinal;
        String nome_arq = "";
        boolean conectou = false;
        boolean saida = false;
        Socket superNodoConex = null;
        
        while(!conectou && !saida){
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
                    conectou = true;
                }
                else
                {
                
                    System.out.println("Nao foi possivel conectar, por favor digitar um numero IP valido\n");
                    conectou = false;
                }
            }
            catch(IOException ioex)
            {
                System.out.println("Nodo Inacancavel ou Desconhecido\n");
            }

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
        
        sinal = opcao;
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
                
                //Lembrar de Cortar String para Pegar nome do arquivo
                //Calcular qual no sera responsavel pelo arquivo
                
                int hash = nome_arq.hashCode()%51;
                System.out.println("O nome do Arquivo e: " + nome_arq + "\n");
                System.out.println("Seu codigo Hash e: " + hash + "\n");
                
                
                
                try {
                File arq_digitado = new File(caminho);
                FileInputStream arq_envia = new FileInputStream(arq_digitado);
                OutputStream infoSaida = superNodoConex.getOutputStream();
                for(int i = 0; i < arq_digitado.length(); i++){
                    infoSaida.write(arq_envia.read());
                }
                infoSaida.flush();
                arq_envia.close();
                
               
                        
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }                            
            break;
            case 2:
                //Dowload
                
            break;    
            case 0:
                //Sair e Desconectar
                saida = true;
            break;    
       
        }
             
        
        }
    
    }
}
