/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author anhkh
 */
public class Project_Slang_Dictionary {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
//        String slang = "";
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter slang you want to search: ");
//        slang = sc.nextLine();
        long startTime = System.currentTimeMillis();

        File_IO Read_file = new File_IO();
        Function fc = new Function();
//        Map<String, ArrayList> map = null;
        HashMap<String, TreeSet<String>> hashmap;
//        
//        String filename = "slang.txt";
//        
//        map = Read_file.ReadFile(filename);
//        int sizeMap = map.size();
//
//
        hashmap = fc.ReadData(0, "output.txt");
        
        


//        hashmap = fc.AddNewWord(hashmap);
//        hashmap = fc.AddNewWord(hashmap);
//        System.out.println(hashmap);
//        hashmap = fc.DeleteWord(hashmap);
//          hashmap = fc.EditWord(hashmap);
//        System.out.println(map);
            
        System.out.println(hashmap);
        

        System.out.println();
//        Read_file.WriteFile(hashmap);
//        fc.SearchBySlang(slang, hashmap);
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;   
//        fc.SearchByDefinition(hashmap, "Cool");
//        fc.ThisDaySlang(hashmap);
          fc.QuizDefinition(hashmap);
//        fc.QuizSlang(hashmap);
        System.out.println("Execution time in milliseconds: " + timeElapsed);
//        HashMap<String, TreeSet<String>> history = new HashMap<>();
//        history = fc.History(slang, hashmap, history);
//        Read_file.WriteFile(history, "history.txt");
    }
}
