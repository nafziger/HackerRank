package algorithms.implementation.queensattackii;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    public int n, k, r_q, c_q;
    public int [][] obstacles;
    ObstacleHash obsHash;
    // Complete the queensAttack function below.
    Solution(int n, int k, int r_q, int c_q, int[][] obstacles){
        this.n =n;
        this.k =k;
        this.r_q=r_q;
        this.c_q=c_q;
        this.obstacles=obstacles;
    }

    public int queensAttack() {        
        // Build a quick lookup data structure for the obstacles
        obsHash = new ObstacleHash(this.obstacles);
        System.out.println(obsHash.contains(1,1));
        System.out.println(obsHash.contains(2,2));
        System.out.println(obsHash.contains(3,2));
        
        // 8 directions
        // N NW W SW S SE E NE
        return 1;
    }
    public boolean checkDirectionRadius(int r, DirectionInterface di){
        int[] directionCoords = di.direction(this.r_q, this.c_q, r);        
        return validCoords(directionCoords) && !obsHash.contains(directionCoords);
    }
    public interface DirectionInterface{
        public int[] direction(int x, int y, int r);
    }
    public boolean validCoords(int[] coords){
        return coords[0]<=this.n && coords[0]>=1 && coords[1]<=this.n && coords[1]>=0;        
    }
    static int[] N(int x, int y, int r){
        return new int[]{x, y + r};
    }
    static int[] NW(int x, int y, int r){
        return new int[]{x - r, y + r};
    }
    static int[] W(int x, int y, int r){
        return new int[]{x - r, y};
    }
    static int[] SW(int x, int y, int r){
        return new int[]{x - r, y - r};
    }
    static int[] S(int x, int y, int r){
        return new int[]{x, y - r};
    }
    static int[] SE(int x, int y, int r){
        return new int[]{x + r, y - r};
    }
    static int[] E(int x, int y, int r){
        return new int[]{x + r, y};
    }
    static int[] NE(int x, int y, int r){
        return new int[]{x + r, y + r};
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
        Solution solution = new Solution(n, k, r_q, c_q, obstacles);
        int result = solution.queensAttack();

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
    public boolean contains(int[] coords){
        return contains(coords[0] ,coords[1]);
    }
        
}