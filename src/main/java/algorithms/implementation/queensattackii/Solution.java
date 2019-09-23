package algorithms.implementation.queensattackii;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        
        // Build a quick lookup data structure for the obstacles
        ObstacleHash obsHash = new ObstacleHash(obstacles);
        System.out.println(obsHash.contains(1,1));
        System.out.println(obsHash.contains(2,2));
        System.out.println(obsHash.contains(3,2));
        return 1;
    }
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        } catch(NullPointerException e){
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
final class ObstacleHash{
    private HashMap<Integer, HashSet<Integer>> obsHash;
    public ObstacleHash(int[][] obstacles){
        // Build a quick lookup data structure for the obstacles
        obsHash = new HashMap<Integer, HashSet<Integer>>();        
        for(int[]obs : obstacles){
            if(obsHash.containsKey(obs[0])){
                HashSet<Integer> intHash = obsHash.get(obs[0]);
                intHash.add(obs[1]);
            } else{
                HashSet<Integer> intHash = new HashSet<Integer>();
                intHash.add(obs[1]);
                obsHash.put(obs[0], intHash);
            }            
        }
    }
    public boolean contains(int x, int y){
        HashSet<Integer> intHash = obsHash.get(x);
        if(intHash != null){
            return intHash.contains(y);
        } else{
            return false;
        }
    }
}