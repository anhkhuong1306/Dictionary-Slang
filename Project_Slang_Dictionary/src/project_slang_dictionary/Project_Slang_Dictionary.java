/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_slang_dictionary;

import java.io.FileNotFoundException;

/**
 *
 * @author anhkh
 */
public class Project_Slang_Dictionary {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File_IO Read_file = new File_IO();
        String filename = "slang.txt";
        Read_file.ReadFile(filename);
    }
}
