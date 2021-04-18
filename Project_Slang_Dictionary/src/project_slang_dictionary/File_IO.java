/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 *
 * @author anhkh
 */
public class File_IO {

    private static String[] Array(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Function function = new Function();
    
    public static Map ReadFile(String filename) throws FileNotFoundException{
        
        BufferedReader br = null;
        FileReader fr = null;
        try{
            
            File f = new File(filename);
            
            if (f.exists()) {
                System.out.println("File existed");
                fr = new FileReader(filename);
            } else {
                System.out.println("File not found!");
                fr = new FileReader("slang.txt");
            }
            
            br = new BufferedReader(fr);
            String sCurrentLine;
            int count = 0;
            Map<String, TreeSet<String>> map = new HashMap<>();
            while((sCurrentLine = br.readLine()) != null){
                String[] strArray = new String[2];
                String arrayWord[] = sCurrentLine.split("`");
                Collection<String> Definition = new TreeSet<>();
                
                if (arrayWord.length >= 2) {
                    if (arrayWord[1].contains("|")) {
                        Definition.addAll(Arrays.asList(arrayWord[1].replaceAll("(\\| ?)", ",").split(",")));
                    } else {
                        Definition.add(arrayWord[1]);
                    }
                }
                
                if(map.containsKey(arrayWord[0]) == false){
                   
                    map.put(arrayWord[0], (TreeSet<String>) Definition);
                }
                else{
                    TreeSet<String> tmpArr = map.get(arrayWord[0]);
                    tmpArr.addAll(Definition);
                }
                
            }
            return map;
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
        return null;
    }
    
    public static void WriteFile(HashMap data)throws IOException{
        
        
        FileWriter fw = null;
        BufferedWriter bw = null;
        File file = new File("output.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();                
            }
            
            fw = new FileWriter(file, false);    
            bw = new BufferedWriter(fw);
            Iterator hmIterator = data.entrySet().iterator();       
            while (hmIterator.hasNext()) {
                
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                String Content = "";
                String Word = (String) mapElement.getKey();
                
                TreeSet<String> list = (TreeSet<String>) mapElement.getValue();
                String Definition = "";
                
                while(!list.isEmpty()){
                    if(list.size() == 1){
                        Definition += list.pollFirst();
                    }
                    else {
                        Definition += list.pollFirst() + "| ";
                    }
                }
            
                Content = Word + '`' + Definition;
                bw.write(Content);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    

}
