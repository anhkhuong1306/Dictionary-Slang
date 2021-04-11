/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author anhkh
 */
public class Slang {
    private String word;
    private String definition;
    TreeSet<String> listdefinition = new TreeSet<String>();

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public TreeSet<String> getListDefinition() {
        return listdefinition;
    }
    
    /**
     *
     * @param array
     */
    public void setLístDefinition(Collection definition){
        this.listdefinition = new TreeSet<String>(definition);
    }
    
    public void PrintSlang(){
        System.out.println("Word: " + this.word);
        System.out.println("Definition: " + (this.definition));
    }
    
    public void InputWord(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the slang: ");
        this.word = sc.nextLine();
        System.out.println("Enter the Definition: ");
        sc = new Scanner(System.in);
        this.definition = sc.nextLine();
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

}
