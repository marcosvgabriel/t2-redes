/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.BufferedReader;
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
            //Trata as mensagens chegando do cliente em questão.
            BufferedReader messages = new BufferedReader(new InputStreamReader(NCConex.getInputStream()));
            OutputStream sending = NCConex.getOutputStream();
            String lastMessage;
            
            while(!saida)
            {
                lastMessage = messages.readLine();
                switch(lastMessage)
                {
                    case "1":
                    {
                        lastMessage = messages.readLine();
                        sending.write((SuperNode.getDHTReference().getLocation(lastMessage)+"\n").getBytes());
                        break;
                    }
                    case "102 - Sending File":
                    {
                        lastMessage = messages.readLine();
                        if(SuperNode.getDHTReference().getLocation(lastMessage).matches("127.0.0.1"))
                        {
                            File receivingFile = new File("./files/"+lastMessage);
                            try
                            {
                                if(receivingFile.createNewFile())
                                {
                                    lastMessage = messages.readLine();
                                    int size = Integer.parseInt(lastMessage);
                                    sending.write("104 - File Accepted\n".getBytes());
                                    FileOutputStream diskOutput;
                                    diskOutput = new FileOutputStream(receivingFile);
                                    InputStream receivingStream = clientConnection.getInputStream();
                                    for(int i = 0; i < size; i++)
                                        diskOutput.write(receivingStream.read());
                                    diskOutput.flush();
                                    SuperNode.getDHTReference().addFile(receivingFile);
                                }
                                else
                                {
                                    sending.write("203 - File Already Exists\n".getBytes());
                                }
                            }
                            catch(IOException | NumberFormatException ex)
                            {
                                System.err.append(ex.getMessage());
                            }
                        }
                        else
                        {
                            sending.write("201 - Wrong File Location\n".getBytes());
                        }
                        break;
                    }
                    case "103 - Retrieving File":
                    {
                        lastMessage = messages.readLine();
                        if(SuperNode.getDHTReference().getLocation(lastMessage).matches("127.0.0.1"))
                        {
                            File sendingFile = SuperNode.getDHTReference().getFile(lastMessage);
                            if(sendingFile != null)
                            {
                                FileInputStream diskInput = new FileInputStream(sendingFile);
                                
                                sending.write((sendingFile.length()+"\n").getBytes());

                                for(int i = 0; i < sendingFile.length(); i++)
                                {
                                    sending.write(diskInput.read());
                                }
                                sending.flush();
                                diskInput.close();
                            }
                            else
                            {
                                sending.write("202 - File Does Not Exist\n".getBytes());
                            }
                        }
                        else
                        {
                            sending.write("201 - Wrong File Location\n".getBytes());
                        }
                        break;
                    }
                }
            }
        }
        catch(IOException ioex)
        {
            
        }
    }
    
    
}
