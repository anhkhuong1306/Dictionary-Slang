/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author anhkh
 */
public class Function {
    
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
    
}
