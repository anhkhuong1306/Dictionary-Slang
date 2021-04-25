/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    static final String DEFAULT_FILE_NAME = "slang.txt";
    
    private static String[] Array(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Function function = new Function();
    /*tham khảo từ nguồn internet và từ nguồn tài liệu thuộc trường ĐH KHTN*/
    public Map ReadFile(String filename) throws FileNotFoundException{
        
        BufferedReader br = null;
//        InputStreamReader fr = null;
        FileReader fr = null;
        File dir = null;
        File f = null;
        try{

                String className = this.getClass().getName().replace('.', '/');
                String classJar = this.getClass().getResource("/" + className + ".class").toString(); /* tham khảo từ https://www.rgagnon.com/javadetails/java-0391.html */
                
                if (classJar.startsWith("jar:")) {
                    System.out.println("*** running from jar!");
                    String jarPath = URLDecoder.decode(getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8"); /* tham khảo từ https://coderanch.com/t/669663/java/load-write-file-getClass-getResource */
                    String completePath = jarPath.substring(0, jarPath.lastIndexOf("/")) + File.separator + "data" + File.separator + filename;

                    f = new File(completePath);
                    try {
                        if (!f.exists() && !f.createNewFile()) {
                            System.out.println("File doesn't exist, and creating file with path: " + completePath + " failed. ");

                        }else{
                            System.out.println("Input data exists, or file with path " + completePath + " created successfully. ");
                        }
                        fr = new FileReader(completePath);
                     
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    dir = new File("src/data");
                    f = new File(dir, filename);                   
                    if (!f.exists()) {
                       f = new File(dir, DEFAULT_FILE_NAME);                  
                    }
                    fr = new FileReader(f.getAbsolutePath());
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
    public void WriteFile(HashMap<String, TreeSet<String>> data, String filename)throws IOException{
        
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        File dir = null;
        File file = null;        
        
        
        String className = this.getClass().getName().replace('.', '/');
        String classJar = this.getClass().getResource("/" + className + ".class").toString(); /* tham khảo từ https://www.rgagnon.com/javadetails/java-0391.html */

        if (classJar.startsWith("jar:")) {
            System.out.println("*** running from jar!");
            String jarPath = URLDecoder.decode(getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8"); /* tham khảo từ https://coderanch.com/t/669663/java/load-write-file-getClass-getResource */
            String completePath = jarPath.substring(0, jarPath.lastIndexOf("/")) + File.separator + "data" + File.separator + filename;

            file = new File(completePath);
            try {
                if (!file.exists() && !file.createNewFile()) {
                    System.out.println("File doesn't exist, and creating file with path: " + completePath + " failed. ");

                } else {
                    System.out.println("Input data exists, or file with path " + completePath + " created successfully. ");
                }
                fw = new FileWriter(completePath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dir = new File("src/data");
            file = new File(dir, filename);
            if (!file.exists()) {
                file.createNewFile();                
            }
            fw = new FileWriter(file.getAbsolutePath());
        }
        
        try {

            fw = new FileWriter(file.getCanonicalPath(), false);    
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
