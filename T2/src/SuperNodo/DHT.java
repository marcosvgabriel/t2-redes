/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperNodo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MarcosVinicius
 */
public class DHT {
    
    
    private HashMap<String, File> valoresHash;
    private int idresponsavel; 
    private ArrayList<RunningNode> NodosAtivos;
    
    public DHT(){
        valoresHash = new HashMap<>();
        idresponsavel = 0;
        NodosAtivos = null;
        
    }
    
    public DHT(int id){
        idresponsavel = id;             
    }
    
    public int getId(){
        return idresponsavel;
    }
    
       
    public void setNodosAtivos(ArrayList<RunningNode> nListaNodos){
        NodosAtivos = nListaNodos;    
    }
    
    public String Localizar(String filename)
    {
        int hash = filename.hashCode()%1024;
        if(hash < 0)
            hash = hash*-1;
        
        if(hash > numMax && hash < numMin)
        {
            return "127.0.0.1";
        }
        else
        {
            for(int i = 0; i < NodosAtivos.size(); i++)
            {
                if(hash> NodosAtivos.get(i).getRemoteLowerBound() && hash < NodosAtivos.get(i).getRemoteUpperBound())
                    return NodosAtivos.get(i).getIP();
            }
        }
        return null;
    }
    
     public File getArquivo(String filename)
    {
        return valoresHash.get(filename);
    }
    public boolean adicionaArq(File nArquivo)
    {
        if(valoresHash.put(nArquivo.getName(), nArquivo) == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<String> getNomeArq()
    {
        return new ArrayList<>(valoresHash.keySet());
    }
    public ArrayList<File> getFiles()
    {
        return new ArrayList<>(valoresHash.values());
    }
    public void removeFile(String filename)
    {
        valoresHash.remove(filename);
    }
    
    
    
}
