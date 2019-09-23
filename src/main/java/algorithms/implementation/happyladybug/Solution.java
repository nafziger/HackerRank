package algorithms.implementation.happyladybug;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private static boolean isHackerRankEnvironment=true;
    
    static String happyLadybugs(String b) {
        // A zero length board is solved
        if(b.length()==0){
            return "YES";
        }        
        if(b.length()==1){
            if (b.charAt(0)!='_'){
                // A board with one non-blank element is unsolvable
                return "NO";
            } else{
                // A board with one blank element is solved
                return "YES";
            }
        }
        boolean isSolved = true;        
        Hashtable<Character, Integer> charTable = new Hashtable<Character, Integer>(26); 
        // Interate through the board  
        char[] charArr= b.toCharArray();

        for(int i=0;i<b.length();i++){
            char c = charArr[i];
            // Check if board is valid
            if(isSolved==true){
                if( i!=0 && i!=b.length()-1){            
                    // if c is blank or if c = c at i+1 or i-1
                    if( c!='_' && !(c==charArr[i+1] || c==charArr[i-1]) ){
                        // Character has no neighbor
                        isSolved = false;
                        print(String.format("Not solved at (Character,Postion): (%s,%s)",c,i));
                    }
                } else if (i==0){
                    // If c is blank or if c = c at i+1
                    if( c!='_' && !(c==charArr[i+1]) ){
                        // Character has no neighbor
                        isSolved = false;
                        print(String.format("Not solved at (Character,Postion): (%s,%s)",c,i));
                    }
                } else if (i==b.length()-1){
                    // if c is blank or if c = c at i-1
                    if( c!='_' && !(c==charArr[i-1]) ){
                        // Character has no neighbor
                        isSolved = false;
                        print(String.format("Not solved at (Character,Postion): (%s,%s)",c,i));
                    }
                }            
            }            
            charTable.put(c, charTable.getOrDefault(c,0)+1);          
        }
        print(String.format("isSolved: %s",isSolved));       
        print(String.format("%s",charTable)); 
        // If the board is in a solved state the we are done
        if(isSolved){
            print("Board is in a solved state");
            return "YES";
        }
         // A blank space is required to solve an unsolved board
         if(!charTable.containsKey('_')){
            print("No blank space found in unsolved board.");
            return "NO";
         }
        // No (non-blank) character should appear only once on the board
        for(char c : charTable.keySet() ){
            if( c!='_' && charTable.get(c)==1){
                print("Non-blank character appeard only once. Character: "+c);
                return "NO";
            }
        }       
        print("Board can be solved");
        return "YES";
    }

    private static void print(String str){
        if (!Solution.isHackerRankEnvironment){
            System.out.println(str);
        }        
    }

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        
        BufferedWriter bufferedWriter;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
            Solution.isHackerRankEnvironment = true;
        } catch(NullPointerException e){
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            Solution.isHackerRankEnvironment = false;
        }
        
        

        int g = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int gItr = 0; gItr < g; gItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String b = scanner.nextLine();

            String result = happyLadybugs(b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
