/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
            System.out.print("The Slang Word was existed. Do you want to keep on. (yes/no): ");
            YES_NO = sc.nextLine();

            if(YES_NO.equalsIgnoreCase("yes")){
                
                definition = (TreeSet<String>) Data.get(sl.getWord());
                if(definition == null){

                    definition.add(sl.getDefinition());
                    Data.put(sl.getWord(), definition);
                    System.out.print("The slang has added: " + sl.getWord());
                }
                else{
                    definition.add(sl.getDefinition());
                    Data.put(sl.getWord(), definition);
                    System.out.print("The slang has added: " + sl.getWord());
                }
            }
            else{
                return Data;
            }          
        }
        else
        {
            definition.add(sl.getDefinition());
            Data.put(sl.getWord(), definition);
            System.out.println("The slang has added. " + sl.getWord());
        }
        return Data;
    }
    
    public HashMap DeleteWord(HashMap Data){
        String Slang;
        System.out.print("Enter the slang word you want to delete: ");
        Scanner sc = new Scanner(System.in);
        Slang = sc.nextLine();
        if(Data.containsKey(Slang)){
            System.out.print("Do you want to delete this slang word?(yes/no): ");
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
        System.out.print("Enter the slang word you want to edit: ");
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
                System.out.print("Enter the new definition you want: ");
                newDefinition = sc.nextLine();
                
                while(newDefinition.isEmpty()){
                    
                    System.out.println("YOU CANNOT LEFT EMPTY THIS FIELD!");
                    System.out.print("Enter the new definition you want: ");
                    sc = new Scanner(System.in);
                    newDefinition = sc.nextLine();
                    
                }
                
                definitions.add(newDefinition);
                definitions.remove(definition);
                System.out.println("The definition was edited.");
            }
            else{
                
                System.out.print("The definition is not exists. Do you want to add a new definition? (yes/no): ");
                String YES_NO;
                sc = new Scanner(System.in);
                YES_NO = sc.nextLine();
                if(YES_NO.equalsIgnoreCase("yes")){
                    
                    System.out.print("Enter the new definition you want: ");
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
     * @param reset
     * @param filename
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
        TreeSet<String>Definition = new TreeSet<>();
        Definition = (TreeSet<String>) Data.get(slang);
        
        if(!Data.containsKey(slang)){
            System.out.println("The Slang is not exists.");
            return;
        }
        if(Definition == null){
            System.out.println("Slang: " + slang);
            System.out.println("No definition for this slang.");
            return;
        }
        slang_definition.setLístDefinition(Definition);
        slang_definition.PrintDefinition();
    }
    
    public static HashMap History(String slang, HashMap<String, TreeSet<String>> Data, HashMap<String, TreeSet<String>> history){
        TreeSet<String>Definition = new TreeSet<>();
        Definition = Data.get(slang);
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
    
    public static void SearchByDefinition(HashMap<String, TreeSet<String>> data, String value) {
        TreeSet<String> key_finded = new TreeSet<>();
        Slang slang_definition = new Slang();

        for (String key: data.keySet())
        {
            if (data.get(key).contains(value)) {
               key_finded.add((String)key);
            }
        }
        slang_definition.setLístDefinition(key_finded);
        slang_definition.PrintDefinition();
    }
    
    public static void ThisDaySlang(HashMap<String, TreeSet<String>> data) throws IOException{
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        
        int index = day + month + year;
        if(index > data.size()){
            index = index - data.size();
        }
        
        
        File_IO file = new File_IO();
        Map loadFromFile = file.ReadFile("thisdayslangword.txt");
        Slang slang = new Slang();
        
        if(loadFromFile.containsKey(currentDate)){
            
            String slangWord = loadFromFile.keySet().toArray()[0].toString();  
            TreeSet<String> definitions = new TreeSet<>();
            
            definitions = data.get(slangWord);

            System.out.println("Slang: " + slangWord);
            System.out.println("Definition: ");
            
            slang.setLístDefinition(definitions);
            slang.PrintDefinition();
            
        }
        else
        {
            /*Tham khảo từ stackoverflow https://stackoverflow.com/questions/16108734/convert-setmap-entryk-v-to-hashmapk-v*/
            Map.Entry<String, TreeSet<String>> entry = (Map.Entry<String, TreeSet<String>>) data.entrySet().toArray()[index];
            
            HashMap<String, TreeSet<String>> thisDaySlangWord = new HashMap<>();
            TreeSet<String> definitions = entry.getValue();
            
            System.out.println("Slang: " + entry.getKey());
            System.out.println("Definition: ");
            
            slang.setLístDefinition(definitions);
            slang.PrintDefinition();
            
            definitions = new TreeSet<>();
            definitions.add(currentDate.toString());
            
            thisDaySlangWord.put(entry.getKey(), definitions);
            
            file.WriteFile(thisDaySlangWord, "thisdayslangword.txt");
        
        }
        
    }
    
    public static void QuizSlang(HashMap<String, TreeSet<String>> data){
        int size_data = data.size();
        Random rand = new Random();
        ArrayList<Integer> set = new ArrayList<>(4);
        ArrayList<String> answerList = new ArrayList<>();
        String Question = "";
        String Result = "";
        
        while(set.size() < 4){
            set.add(rand.nextInt(size_data) + 1);
        }
        
        for(int i = 0; i < set.size(); i++){
            Map.Entry<String, TreeSet<String>> entry = (Map.Entry<String, TreeSet<String>>) data.entrySet().toArray()[set.get(i)];
            TreeSet<String> answer;
            answer = (TreeSet<String>) entry.getValue();
            boolean isExists = set.contains(size_data);
            int index = set.indexOf(set.get(i));
            answerList.add(index, answer.first().toString());
            
            if(i == set.size() - 1){
                answer = (TreeSet<String>) entry.getValue();
                Question = entry.getKey();
                Result = answer.first();
            }
        }
        
//        Iterator it = data.entrySet().iterator();
//        while (it.hasNext() && set.isEmpty() == false && size_data != 0) {
//            Map.Entry pair = (Map.Entry)it.next();
//            boolean isExists = set.contains(size_data);
//            TreeSet<String> answer;
//            
//            if(isExists == true){
//                int index = set.indexOf(size_data);
//                int index_answer = 0;
//                answer = (TreeSet<String>) pair.getValue();
//                answerList.add(index_answer, answer.first().toString());
//                
//                if(set.size() == 1){
//                    answer = (TreeSet<String>) pair.getValue();
//                    Question = pair.getKey().toString();
//                    Result = answer.first().toString();
//                }
//                set.remove((Object)size_data);
//            }
//            
//            size_data -= 1;            
//        }
        
        System.out.println("Choose the A, B, C, D to select the answer.");
        System.out.println("Question: " + Question);
        Collections.shuffle(answerList);
   
        System.out.println("A. " + answerList.get(0));
        System.out.println("B. " + answerList.get(1));
        System.out.println("C. " + answerList.get(2));
        System.out.println("D. " + answerList.get(3));

        String user_answer = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        user_answer = sc.nextLine();
        
        switch(user_answer.toUpperCase()){
            case "A":
                user_answer = answerList.get(0);
                break;
            case "B":
                user_answer = answerList.get(1);
                break;
            case "C":
                user_answer = answerList.get(2);
                break;
            case "D":
                user_answer = answerList.get(3);
                break;
            default:
                user_answer = "";
        }
        
        if(user_answer.equalsIgnoreCase(Result)){
            System.out.println("Your answer is correct.");
        }
        else{
            System.out.println("Your answer is incorrect.");
            System.out.println("-> Answer: " + Result + " <-");
        }
    }
    
    public static void QuizDefinition(HashMap<String, TreeSet<String>> data){
        int size_data = data.size();
        Random rand = new Random();
        ArrayList<Integer> set = new ArrayList<>(4);
        ArrayList<String> answerList = new ArrayList<>();
        String Question = "";
        String Result = "";
        
        while(set.size() < 4){
            set.add(rand.nextInt(size_data) + 1);
        }
        
        for (int i = 0; i < set.size(); i++) {
            Map.Entry<String, TreeSet<String>> entry = (Map.Entry<String, TreeSet<String>>) data.entrySet().toArray()[set.get(i)];
            String answer;
            answer = entry.getKey();
            boolean isExists = set.contains(size_data);
            int index = set.indexOf(set.get(i));
            answerList.add(index, answer);

            if (i == set.size() - 1) {
                TreeSet<String> TSQuestion = entry.getValue();
                Question = TSQuestion.first();
                Result = answer;
            }
        }
        
//        Iterator it = data.entrySet().iterator();
//        while (it.hasNext() && set.isEmpty() == false && size_data != 0) {
//            Map.Entry pair = (Map.Entry)it.next();
//            boolean isExists = set.contains(size_data);
//            String answer;
//            
//            if(isExists == true){
//                int index = set.indexOf(size_data);
//                int index_answer = 0;
//                answer = pair.getKey().toString();
//                answerList.add(index_answer, answer);
//                
//                if(set.size() == 1){
//                    answer =  pair.getKey().toString();
//                    TreeSet<String> TSQuestion = (TreeSet<String>) pair.getValue();
//                    Question = TSQuestion.first().toString();
//                    Result = answer;
//                }
//                set.remove((Object)size_data);
//            }
//            
//            size_data -= 1;            
//        }
        
        System.out.println("Choose the A, B, C, D to select the answer.");
        System.out.println("Question: " + Question);
        Collections.shuffle(answerList);
   
        System.out.println("A. " + answerList.get(0));
        System.out.println("B. " + answerList.get(1));
        System.out.println("C. " + answerList.get(2));
        System.out.println("D. " + answerList.get(3));

        String user_answer = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        user_answer = sc.nextLine();
        
        switch(user_answer.toUpperCase()){
            case "A":
                user_answer = answerList.get(0);
                break;
            case "B":
                user_answer = answerList.get(1);
                break;
            case "C":
                user_answer = answerList.get(2);
                break;
            case "D":
                user_answer = answerList.get(3);
                break;
            default:
                user_answer = "";
        }
        
        if(user_answer.equalsIgnoreCase(Result)){
            System.out.println("Your answer is correct.");
        }
        else{
            System.out.println("Your answer is incorrect.");
            System.out.println("-> Answer: " + Result + " <-");

        }
    }
    
    public static void PrintData(HashMap data){
        Iterator it = data.entrySet().iterator();
        int count = 1;
        while (it.hasNext() ){
            Map.Entry pair = (Map.Entry)it.next();
            Slang slang = new Slang();
            TreeSet<String> definitions = new TreeSet<String>();
            definitions = (TreeSet<String>)pair.getValue();
            slang.setLístDefinition(definitions);
            System.out.println("----- " + count + " -----");
            System.out.println("Slang: " + pair.getKey());
            System.out.println("Definitions:");
            slang.PrintDefinition();
            System.out.println("------------");
            System.out.println();
            
            count++;
        }
    }
}
