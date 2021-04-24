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
    /*tham khảo từ nguồn internet và từ nguồn tài liệu thuộc trường ĐH KHTN*/
    public static Map ReadFile(String filename) throws FileNotFoundException{
        
        BufferedReader br = null;
        FileReader fr = null;
        try{
            
            File f = new File(filename);
            
            if (f.exists()) {
                fr = new FileReader(filename);
            } else {
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
                    
                    if(map.containsKey(arrayWord[0]) == false){

                        map.put(arrayWord[0], (TreeSet<String>) Definition);
                    }
                    else{
                        TreeSet<String> tmpArr = map.get(arrayWord[0]);
                        tmpArr.addAll(Definition);
                    }
                }
                else{
                    Definition.add("NO DEFINITION FOR THIS SLANG.");
                    if(map.containsKey(arrayWord[0]) == false){
                        map.put(arrayWord[0].toString(), (TreeSet<String>) Definition);
                    }
                    else{
                        TreeSet<String> tmpArr = map.get(arrayWord[0]);
                        tmpArr.addAll(Definition);
                    }
                }
                
//                System.out.println(arrayWord instanceof String[]);
                
//                if(arrayWord instanceof String[]){
//                    if(map.containsKey(arrayWord[0]) == false){
//
//                        map.put(arrayWord[0], (TreeSet<String>) Definition);
//                    }
//                    else{
//                        TreeSet<String> tmpArr = map.get(arrayWord[0]);
//                        tmpArr.addAll(Definition);
//                    }
//                }
//                else{
//                    if(map.containsKey(arrayWord) == false){
//
//                        map.put(arrayWord.toString(), (TreeSet<String>) Definition);
//                    }
//                    else{
//                        TreeSet<String> tmpArr = map.get(arrayWord);
//                        tmpArr.addAll(Definition);
//                    }
//                }
                    
                
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
    /*tham khảo từ nguồn internet và từ nguồn tài liệu thuộc trường ĐH KHTN*/
    public static void WriteFile(HashMap<String, TreeSet<String>> data, String filename)throws IOException{
        
        
        FileWriter fw = null;
        BufferedWriter bw = null;
        File file = new File(filename);
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
                
                TreeSet<String> list = new TreeSet<String>();
                list =  (TreeSet<String>) mapElement.getValue();
                String Definition = "";
                
                if(!list.isEmpty()){
                    if(list.size() == 1){
                        Definition += list.pollFirst();
                    }
                    else {
                        
                        for (String definition : list) {
                            Definition += definition + "| ";
                        }
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
                bw.flush();
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
