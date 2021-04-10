/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author anhkh
 */
public class File_IO {
    
    public static void ReadFile(String filename) throws FileNotFoundException{
        
        BufferedReader br = null;
        FileReader fr = null;
        try{
            
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filename));
            while((sCurrentLine = br.readLine()) != null){
                System.out.println(sCurrentLine);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(br != null){
                    br.close();
                }
                if(fr != null){
                    fr.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    
}
