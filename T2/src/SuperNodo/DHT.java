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
    
    
    private HashMap<String, File> localValues;
    private int numMax; //Range Máximo da Tabela Hash
    private int numMin; //Range Mínimo da Tabela Hash
    private ArrayList<RunningNode> NodosAtivos;
    
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
            for(int i = 0; i < nodeList.size(); i++)
            {
                if(hash> nodeList.get(i).getRemoteLowerBound() && hash < nodeList.get(i).getRemoteUpperBound())
                    return nodeList.get(i).getIP();
            }
        }
        return null;
    }
    
    
    
    
}
