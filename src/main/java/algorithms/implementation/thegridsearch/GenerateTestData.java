package algorithms.implementation.thegridsearch;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class GenerateTestData{
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String [] args){
        

        int R,C,r,c;
        boolean forcePattern;
        String fileName;

        Random rand = new Random();       
        String[] RCrcF = scanner.nextLine().split(" ");            
        R = Integer.parseInt(RCrcF[0]);
        C = Integer.parseInt(RCrcF[1]);
        r = Integer.parseInt(RCrcF[2]);
        c = Integer.parseInt(RCrcF[3]);

        try{
            forcePattern = Boolean.parseBoolean(RCrcF[4]);
        } catch (IndexOutOfBoundsException e){
            forcePattern = true;
        }

        try{
            fileName = RCrcF[6];
        } catch (IndexOutOfBoundsException e){
            fileName = "testCase.txt";
        }

        //System.out.println( R +" "+ C +" "+ r +" "+ c +" "+ forcePattern);

        String[]G = new String[R];
        String[]P = new String[r];

        for(int ri=0;ri<R;ri++){
            StringBuilder sb = new StringBuilder();
            for(int ci=0;ci<C;ci++){     
                sb.append( new Integer( rand.nextInt(10) ).toString().charAt(0) );               
            }
            G[ri]= sb.toString();
        }

        if (forcePattern){
            // pick the starting point
            int rKey = rand.nextInt(R-r);
            int cKey = rand.nextInt(C-c);          
            //System.out.println( (R-r) +" "+ (C-c) +" "+ (R) +" "+ (C)  );
            //System.out.println("rKey: "+rKey+" cKey: "+cKey);
            for(int ri=rKey;ri<rKey + r;ri++){                
                StringBuilder sb = new StringBuilder();
                for(int ci=cKey;ci<c+cKey;ci++){     
                    sb.append( G[ri].charAt(ci) );               
                }
                P[ri-rKey]= sb.toString();
            }   
        } else{
            for(int ri=0;ri<r;ri++){
                StringBuilder sb = new StringBuilder();
                for(int ci=0;ci<c;ci++){     
                    sb.append( new Integer( rand.nextInt(10) ).toString().charAt(0) );               
                }
                P[ri]= sb.toString();
            }   
        }
         
        
        System.out.println("1");
        System.out.println(R +" "+ C);
        for(String row : G){
            System.out.println( row.toString() );
        }
        System.out.println(r +" "+ c);
        for(String row : P){
        System.out.println( row.toString() );
        }

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            //writer.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            writer.write(Integer.toString(1)); 
            writer.newLine();
            writer.write(R +" "+ C);
            writer.newLine();
            for(String row : G){
                writer.write( row.toString() );
                writer.newLine();
            }
            writer.write(r +" "+ c);
            writer.newLine();
            for(String row : P){
                writer.write( row.toString() );
                writer.newLine();
            }
            writer.close();

        } catch (IOException e){

        }
        


    }    
}