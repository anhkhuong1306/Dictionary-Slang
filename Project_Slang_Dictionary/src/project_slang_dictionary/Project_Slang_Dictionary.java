/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import sun.applet.Main;

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

        File_IO Read_file = new File_IO();
        Function fc = new Function();
 
        HashMap<String, TreeSet<String>> hashmap = fc.ReadData(0, "output.txt");
        HashMap<String, TreeSet<String>> history = fc.ReadData(0, "history.txt");
        
        boolean Continue = true;
        while(Continue == true)
        {
            String option;
            System.out.println("==========Menu==========");
            System.out.println("1. Looking up Slang Word.");
            System.out.println("2. Looking up Definition.");
            System.out.println("3. Display the slang word was finded.");
            System.out.println("4. Adding a slang word.");
            System.out.println("5. Edit a slang word.");
            System.out.println("6. Delete a slang word.");
            System.out.println("7. Reset the data of dictionary.");
            System.out.println("8. This day slang word.");
            System.out.println("9. Quiz with slang word.");
            System.out.println("10. Quiz with definition.");
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your option: ");
            option = sc.nextLine();
            System.out.println();
            
            switch(option){
                case "1": 
                    System.out.println("========== 1. Looking up Slang Word. ==========");
                    System.out.println();
                    String slang = "";
                    sc = new Scanner(System.in);
                    System.out.println("Enter slang you want to search: ");
                    slang = sc.nextLine();
                    
                    long startTime_1 = System.currentTimeMillis();
                    fc.SearchBySlang(slang, hashmap);
                    long endTime_1 = System.currentTimeMillis();
                    long timeElapsed =  endTime_1 - startTime_1;   
                    System.out.println("Execution time in milliseconds: " + timeElapsed);
                    
                    history = fc.History(slang, hashmap, history);
                    break;
                    
                case "2":
                    System.out.println("========== 2. Looking up Definition. ==========");
                    System.out.println();
                    String definition = "";
                    sc = new Scanner(System.in);
                    System.out.println("Enter slang you want to search: ");
                    definition = sc.nextLine();
                    
                    long startTime_2 = System.currentTimeMillis();                    
                    fc.SearchByDefinition(hashmap, definition);
                    long endTime_2 = System.currentTimeMillis();
                    timeElapsed = endTime_2 - startTime_2;   
                    System.out.println("Execution time in milliseconds: " + timeElapsed);                    
                    break;
                    
                case "3":
                    System.out.println("========== 3. Display the slang word was finded. ==========");
                    System.out.println();
                    long startTime_3 = System.currentTimeMillis(); 
                    fc.PrintData(history);
                    long endTime_3 = System.currentTimeMillis();
                    timeElapsed = endTime_3 - startTime_3;   
                    System.out.println("Execution time in milliseconds: " + timeElapsed);
                    break;
                    
                case "4":
                    System.out.println("========== 4. Adding a slang word. ==========");
                    System.out.println();
                    hashmap = fc.AddNewWord(hashmap);
                    break;
                    
                case "5":
                    System.out.println("========== 5. Edit a slang word. ==========");
                    System.out.println();
                    hashmap = fc.EditWord(hashmap);
                    break;
                    
                case "6":
                    System.out.println("========== 6. Delete a slang word. ==========");
                    System.out.println();
                    hashmap = fc.DeleteWord(hashmap);
                    break;
                    
                case "7":
                    System.out.println("========== 7. Reset the data of dictionary. ==========");
                    System.out.println();
                    hashmap = fc.ReadData(1, "slang.txt");
                    break;
                    
                case "8":
                    System.out.println("========== 8. This day slang word. ==========");
                    System.out.println();
                    long startTime_4 = System.currentTimeMillis(); 
                    fc.ThisDaySlang(hashmap);
                    long endTime_4 = System.currentTimeMillis();
                    timeElapsed = endTime_4 - startTime_4;   
                    System.out.println("Execution time in milliseconds: " + timeElapsed);
                    break;
                    
                case "9":
                    System.out.println("========== 9. Quiz with slang word. ==========");
                    System.out.println();
                    fc.QuizSlang(hashmap);
                    break;
                    
                case "10":
                    System.out.println("========== 10. Quiz with definition. ==========");
                    System.out.println();
                    fc.QuizDefinition(hashmap);
                    break;
                    
                default:
                    System.out.println("The option is invalid");
                    break;
            }
            
            System.out.println();
            System.out.println("========== Do you want to continue ==========");
            System.out.print("Yes or No: ");
            String moretime = "";
            sc = new Scanner(System.in);
            moretime = sc.nextLine();
            
            if(moretime.equalsIgnoreCase("y") || moretime.equalsIgnoreCase("yes")){
                Continue = true;  
                continue;   
            }
            else{
                Continue = false;
                break;
            }
        }
        
        Read_file.WriteFile(hashmap, "output.txt");
        Read_file.WriteFile(history, "history.txt");
        
    }
}
