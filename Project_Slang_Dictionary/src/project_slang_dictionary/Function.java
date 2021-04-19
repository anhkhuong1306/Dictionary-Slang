/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import static project_slang_dictionary.File_IO.ReadFile;

/**
 *
 * @author anhkh
 */
public class Function {
    
    
    static final String DEFAULT_FILE_NAME = "slang.txt";
    static final int DEFAULT_RESET_FILE = 1;

    
    public HashMap ImportData(int capacity, Map data){
        HashMap<String, TreeSet<String>> Data = new HashMap<String, TreeSet<String>>(data);
        return Data;
    }
    

    
    public HashMap AddNewWord(HashMap Data){
        Slang sl = new Slang();
        sl.InputWord();
        TreeSet<String> definition = new TreeSet<>();;
        if(Data.containsKey(sl.getWord())){
            String YES_NO;
            Scanner sc = new Scanner(System.in);
            System.out.print("The Slang Word was existed. Do you want to keep on. (yes/no)");
            YES_NO = sc.nextLine();

            if(YES_NO.equalsIgnoreCase("yes")){
                
                definition = (TreeSet<String>) Data.get(sl.getWord());
                if(definition == null){

                    definition.add(sl.getDefinition());
                    Data.put(sl.getWord(), definition);
                    System.out.println("The slang has added.");
                    System.out.println(Data.get(sl.getWord()));

                }
                else{

                    definition.add(sl.getDefinition());
                    Data.put(sl.getWord(), definition);
                    System.out.println("The slang has added.");
                    System.out.println(Data.get(sl.getWord()));

                }
            }
            else{
                return Data;
            }          
        }
        else
        {
            definition.add(sl.getDefinition());
            Data.put(sl.getWord(), sl.getDefinition());
            System.out.println("The slang has added.");
            System.out.println(Data.get(sl.getWord()));

        }
        return Data;
    }
    
    public HashMap DeleteWord(HashMap Data){
        String Slang;
        System.out.println("Enter the slang word you want to delete: ");
        Scanner sc = new Scanner(System.in);
        Slang = sc.nextLine();
        if(Data.containsKey(Slang)){
            System.out.println("Do you want to delete this slang word?(yes/no)");
            String YES_NO;
            sc = new Scanner(System.in);
            YES_NO = sc.nextLine();

            if(YES_NO.equalsIgnoreCase("yes")){
                Object value = Data.remove(Slang);
                if(value == null){
                    System.out.println("Something went wrong when deleting the slang word.");
                }
                else{
                    System.out.println("The slang word was deleted.");
                }
            }
            else{
                System.out.println("The process is cancelled.");
            }
        }
        else{
            System.out.println("The Slang Word does not exists.");
        }
        return Data;
    } 
    
    public HashMap EditWord(HashMap Data){
        String Slang;
        System.out.println("Enter the slang word you want to edit: ");
        Scanner sc = new Scanner(System.in);
        Slang = sc.nextLine();
        
        if(Data.containsKey(Slang)){
            
            System.out.println("There are all definitions of this slang word.");
            System.out.println("==============================================");
            TreeSet<String> definitions = new TreeSet<>();
            definitions = (TreeSet<String>) Data.get(Slang);
            Iterator<String> itr = definitions.iterator();
            int count = 1;
            
            while (itr.hasNext()) {
                System.out.println(count +  ". " + itr.next());
                count++;
            }
            
            System.out.println("Chose the definition you want to edit.");
            String definition = "";
            sc = new Scanner(System.in);
            definition = sc.nextLine();

            while(definition.isEmpty()){
                
                sc = new Scanner(System.in);
                System.out.println("YOU CANNOT LEFT EMPTY THIS FIELD!");
                System.out.println("Chose the definition you want to edit.");
                definition = sc.nextLine();
                
            }
            
            String newDefinition = "";

            if(definitions.contains(definition)){
                
                sc = new Scanner(System.in);
                System.out.println("Enter the new definition you want.");
                newDefinition = sc.nextLine();
                
                while(newDefinition.isEmpty()){
                    
                    System.out.println("YOU CANNOT LEFT EMPTY THIS FIELD!");
                    System.out.println("Enter the new definition you want.");
                    sc = new Scanner(System.in);
                    newDefinition = sc.nextLine();
                    
                }
                
                definitions.add(newDefinition);
                definitions.remove(definition);
                System.out.println("The definition was edited.");
            }
            else{
                
                System.out.println("The definition is not exists. Do you want to add a new definition? (yes/no)");
                String YES_NO;
                sc = new Scanner(System.in);
                YES_NO = sc.nextLine();
                if(YES_NO.equalsIgnoreCase("yes")){
                    
                    System.out.println("Enter the new definition you want.");
                    sc = new Scanner(System.in);
                    newDefinition = sc.nextLine();
                    definitions.add(newDefinition);
                    System.out.println("The definition was edited.");

                }
                else{
                    return Data;
                }
            }
        }
        return Data;
    }
    
    /**
     *
     * @param <error>
     * @param reset
     * @return
     */
    public static HashMap ReadData(int reset, String filename) throws FileNotFoundException{
        
        HashMap<String, TreeSet<String>> Data;
        Map<String, ArrayList> map = null;
        
        File_IO file = new File_IO();
        Function fc = new Function();
        
        String FILE_NAME = "";
        
        if(reset != 1){
            FILE_NAME = filename.length() != 0 || !filename.equalsIgnoreCase(DEFAULT_FILE_NAME) ? filename : DEFAULT_FILE_NAME;
        }
        else{
            FILE_NAME = DEFAULT_FILE_NAME;
        }
        
        map = file.ReadFile(FILE_NAME);
        int sizeMap = map.size();
        Data = fc.ImportData(sizeMap + 100, map);
        
        return Data;
    }

    
    public static void SearchBySlang(String slang, HashMap Data){
        Slang slang_definition = new Slang();
        TreeSet<String>Definition = new TreeSet<String>();
        Definition = (TreeSet<String>) Data.get(slang);
        if(Definition == null){
            System.out.println("The Slang is not exists.");
            return;
        }
        slang_definition.setLístDefinition(Definition);
        slang_definition.PrintDefinition();
    }
    
    public static HashMap History(String slang, HashMap Data, HashMap history){
        TreeSet<String>Definition = new TreeSet<String>();
        Definition = (TreeSet<String>) Data.get(slang);
        System.out.println(Definition);
        if(Definition != null){
            history.put(slang, Definition);
        }
        else{
            Definition = new TreeSet<String>();
            Definition.add("No definition for this slang word");
            history.put(slang, Definition);
        }
        return history;
    }
}
