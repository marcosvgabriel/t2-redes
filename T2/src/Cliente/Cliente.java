/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;


import SuperNodo.*;
import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author MarcosVinicius
 */

//Main para Conexao do Cliente


public class Cliente {
    DHT Cliente;
    
    //public void criarDHTCliente(){
    //};
    
    public static void main(String args[]) throws IOException, IOException {
        String numeroIP = null;
        int numeroPorta = 6601;
        String opcao = "9";
        String caminho = "";
        String sinal;
        String nome_arq;
        String nome_arquivoLidoUpload;
        String nome_arquivoLidoDownload;
        String nova;
        boolean conectou = false;
        boolean saida = false;
        Socket superNodoConex = null;
        DHT dhtCliente;     
        
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
                BufferedReader camUpload = new BufferedReader(new InputStreamReader(System.in));   
        
                //Leitura do Caminho
                try {   
                caminho = camUpload.readLine();   
                } catch (IOException ioe) {   
                System.out.println("IO erro tentando ler o caminho");   
                System.exit(1);   
                }
                
                //Lembrar de Cortar String para Pegar nome do arquivo
                //Se for sempre o mesmo caminho:
                BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));  
                nome_arquivoLidoUpload = buf.readLine();
                nome_arq = nome_arquivoLidoUpload.substring(19, (nome_arquivoLidoUpload.length()-2)); //supondo que 19 seja onde acaba a string referente ao diretorio
                          
                //Se não for sempre o mesmo caminho:
                
                //?            
                
                
                //Calcular qual no sera responsavel pelo arquivo
                
                int hashU = nome_arq.hashCode()%51;
                System.out.println("O nome do Arquivo para fazer Upload e: " + nome_arq + "\n");
                System.out.println("Seu codigo Hash e: " + hashU + "\n");
                
                
                
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
                System.out.println("Digite o nome arquivo para ser carregado: ");
                BufferedReader nome_arquivoDownload = new BufferedReader(new InputStreamReader(System.in));   
        
                //Leitura do Caminho
                try {   
                nome_arq = nome_arquivoDownload.readLine();   
                } catch (IOException ioe) {   
                System.out.println("IO erro tentando ler o caminho");   
                System.exit(1);   
                }
                
                //Lembrar de Cortar String para Pegar nome do arquivo
                //Se for sempre o mesmo caminho:
                //BufferedReader bufDownload = new BufferedReader(new InputStreamReader(System.in));  
                //nome_arquivoLidoDownload = bufDownload.readLine();
                //nome_arq = nome_arquivoLidoDownload.substring(19, (nome_arquivoLidoDownload.length()-2)); //supondo que 19 seja onde acaba a string referente ao diretorio
                          
                //Se não for sempre o mesmo caminho:
                
                //?            
                
                
                //Calcular qual no sera responsavel pelo arquivo
                
                int hashD = nome_arquivoDownload.hashCode()%51;
                System.out.println("O nome do Arquivo para fazer Download e: " + nome_arquivoDownload + "\n");
                System.out.println("Seu codigo Hash e: " + hashD + "\n");
                             
                try {
                    //funcao para resgatar o caminho de aonde o arquivo esta armazenado
                //caminho = //retorno da funcao hash
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
            case 0:
                //Sair e Desconectar
                saida = true;
            break;    
       
        }
             
        
        }
    
    }
}
