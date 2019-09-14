package algorithms.implementation.thegridsearch;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    TokenTracker tokenTracker;
    String[] G, P;    
    int R, C, r, c;
    static final int TOKENSIZE=6;    
    public Solution(String[] G, String[] P, int R, int C, int r, int c){
        this.G = G;
        this.P = P;
        // G Size
        this.R = R;
        this.C = C;
        // P Size
        this.r = r;
        this.c = c;
    }  

   
    /**
     * Search for a rectangular pattern of digits in a grid.
     * 
     * @param G String array of digits forming the Grid to be searched
     * @param P String array of digits forming the Pattern to be searched for.
     * @return "Yes" if P is in G. "No" otherwise.
     */
    public String gridSearch(String[] G, String[] P) {   
        int tokenSetSize = (int) Math.pow(10, Solution.TOKENSIZE);
        System.out.println("Size: " + tokenSetSize );
        tokenTracker = new TokenTracker(tokenSetSize);
        tokenizeArrays();    
        //printArrays();
        //printTokens();

        // Check that the Grid has at least as many instances of each token as the Pattern 
        if (!validatePatternInstanceCount()){
            return "No";
        }

        // Get the list of tokens from the pattern ordered by Grid/Pattern uniqueness 
        ArrayList<Token> fpTokens = tokenTracker.getFingerprintOrderedTokens();

        // Print token details
        for( Token token : fpTokens){
            System.out.println( String.format("Token: %s  GridCount: %s PatternCount: %s FingerprintValue: %s", 
                token.id, token.gridInstanceCount, token.patternInstanceCount,
                token.gridInstanceCount * token.patternInstanceCount) );
        }

        // Get the first fingerprint token
        Token token = fpTokens.get(0);
        // Pick the first instance of this token in the Pattern to be the anchor
        TokenInstance patternAnchorToken = token.patternInstance.get(0);
        // Calculate all the delta's
        for( TokenInstance tokenInstance : token.patternInstance){
            System.out.println( patternAnchorToken.tokenDelta(tokenInstance) );
        }

        // Get an occurence of this token in from the grid
        TokenInstance gridToken = token.patternInstance.get(0);

        return "Yes";
    }

    /**
     * Each token in the Solution's TokenTracker should have a grid instance count
     * greater than or equal to the pattern instance count. If this is not the case 
     * then the pattern can not be found in the grid.
     */
    private boolean validatePatternInstanceCount(){
        for( Token token : this.tokenTracker.tokens){
            if( token.gridInstanceCount < token.patternInstanceCount){
                return false;
            }
        }
        return true;
    }
    /**
     * Print the Grid and Pattern arrays
     */
    private  void printArrays(){
        System.out.println("ARRAY");
        for(String line : this.G){
            System.out.println(line);
        }
        System.out.println("PATTERN");
        for(String line : this.P){
            System.out.println(line);
        }
        
        // Pretty print array G
        for(int i=0; i < this.G.length; i++){
            String row = this.G[i];
            for(int j=0; j<row.length(); j++){
                System.out.print( row.charAt(j) + " ");
            }
            System.out.println();
        }

    }

    /**
     *  Print the tokens and token summaries
     */
    private void printTokens(){        
        System.out.println( String.format("TokenTracker has %s tokens", this.tokenTracker.tokens.length));
        int gridInstancesCount = 0;
        int patternInstancesCount = 0;
        int tokenCount = 0;       
        for (Token token : this.tokenTracker.tokens){
            try{                
                System.out.println( String.format("Token: %s  GridCount: %s PatternCount: %s", 
                token.id, token.gridInstanceCount, token.patternInstanceCount) );
                gridInstancesCount += token.gridInstanceCount;
                patternInstancesCount += token.patternInstanceCount;
                tokenCount ++;
            } catch(NullPointerException e){

            }
        }
        System.out.println( String.format("TokenTracker has %s grid tokens and %s pattern tokens", 
        gridInstancesCount, patternInstancesCount));
        System.out.println( String.format("TokenTracker has %s tokens", 
        tokenCount));
    }

    /** 
     * Interface for the passing of a token adder method
     */
    private interface AddTokenInterface{public void addToken(int token, short x, short y);}

    /** 
     *  Tracks all the tokens in a given array in a Solution's TokenTracker object
     * 
     * @param array         An Grid of pattern array. An array of Strings
     * @param rows          The number of rows in the given Array
     * @param columns       The number of columns in the given array / The number of char in the row String.
     * @param tokenAdder    Lambda function that adds a token instance to a tracker object.
     */
    private void tokenizeArray(String[] array, int rows, int columns, AddTokenInterface tokenAdder){
        for(short i=0; i< rows; i++){
            //for each row            
            int k;
            for(short j=0; j<columns-Solution.TOKENSIZE+1; j++){
                //for each column 
                k = j + Solution.TOKENSIZE;  
                int token = Integer.parseInt( array[i].subSequence(j,k).toString() );    
                tokenAdder.addToken(token,  j, i);                         
            }
        }
    }
    /**
     * Tokenize the Grid and Pattern arrays.
     * 
     * Call tokenizeArray function for Grid and Patern arrays.
     * Use a lambda to pass the adder method that corresponds to each grid type
     */
    private void tokenizeArrays(){
        tokenizeArray(this.G, this.R, this.C, (a,b,c) -> this.tokenTracker.addGridToken(a, b,c ) );
        tokenizeArray(this.P, this.r, this.c, (a,b,c) -> this.tokenTracker.addPatternToken(a, b,c ) );
    }


    // Create the scanner to be used to read input 
    private static final Scanner scanner;
    static {   
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) throws IOException {
        // For local run vs HackerRank compatibility 
        BufferedWriter bufferedWriter ;
        try{
            // HackerRank env bufferWriter
            bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        } catch (NullPointerException e){
            // Local env bufferWriter
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        // Get number of test cases
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            // Read RC line into array
            String[] RC = scanner.nextLine().split(" ");
            // Get R
            int R = Integer.parseInt(RC[0]);
            // Get C
            int C = Integer.parseInt(RC[1]);
            // Make Array/Grid G with R rows
            String[] G = new String[R];
            // Read R rows into G
            for (int i = 0; i < R; i++) {
                String GItem = scanner.nextLine();
                G[i] = GItem;
            }

            // Read rc line into array
            String[] rc = scanner.nextLine().split(" ");
            // Get r
            int r = Integer.parseInt(rc[0]);
            // Get c
            int c = Integer.parseInt(rc[1]);
            // Make Array/Pattern with r rows
            String[] P = new String[r];
            // Read r rows into P
            for (int i = 0; i < r; i++) {
                String PItem = scanner.nextLine();
                P[i] = PItem;
            }
            // Get the search result
            Solution solution = new Solution(G, P, R, C ,r, c);
            String result = solution.gridSearch(G, P);
            // Write search result to the buffer
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

   

    /**
     * TokenTracker is used to maintain a list of tokens. Each token maintains its own lists
     * of occurences in the grid and pattern. For each occurence the x,y coords are stored.
     */
    private class TokenTracker {
        private int size;
        private Token[] tokens;   
        
        /**
         * Only returns tokens found in both Pattern and Grid.
         * We want a list of tokens in ascending fingerprint order.
         * Fingerprint order being a measure of how unique this token is in both the
         * patern and in the grid. It is the product of the instance count of this token
         * from the Grid and Pattern.
         */
        public ArrayList<Token> getFingerprintOrderedTokens(){
            // Get the count of non-null tokens
            // Make a Token array of that size
            // Add all non-null tokens to that array
            // sort that array
            ArrayList<Token> orderedTokens = new ArrayList<Token>();
            for(Token token : this.tokens){
                if(token!=null 
                && token.gridInstanceCount != 0 
                && token.patternInstanceCount !=0){
                    orderedTokens.add(token);
                }                
            }
            orderedTokens.sort(new TokenComparator());           
            return orderedTokens;
        }
        /**
         * Implement a Comparator for tokens. We want to rank them by the product of 
         * their Grid and Pattern instance count. A low count indicates that the token
         * is "rare" and is a better fingerprint represention.
         */
        private class TokenComparator implements Comparator<Token>{
            @Override
            public int compare(Token t1, Token t2){
                long t1c = (long) t1.gridInstanceCount * (long) t1.patternInstanceCount;
                long t2c = (long) t2.gridInstanceCount * (long) t2.patternInstanceCount;    
                return (int) t1c - (int) t2c;
            }
        }

        public TokenTracker(int size){
            this.size = size;
            tokens = new Token[size];
            
        }
        
        public void addGridToken(int tokenid, short xIndex, short yIndex){
            addToken(tokenid, xIndex, yIndex, true);
        }
        public void addPatternToken(int tokenid, short xIndex, short yIndex){
            addToken(tokenid, xIndex, yIndex, false);
        }
        /**
         * Adds a token to the token list if it's not already present.
         * If the token is present then it updates the grid or pattern 
         * instances list and count for that token.
         * 
         * @param tokenid The int value uniquely identifying a token
         * @param xIndex  x index of its instance
         * @param yIndex  y index of its instance
         * @param type True for Grid, False for Pattern
         */
        private void addToken(int tokenid, short xIndex, short yIndex, boolean type){
            try{
                if (type){                    
                    tokens[tokenid].addGridInstance(xIndex, yIndex);
                } else{
                    tokens[tokenid].addPatternInstance(xIndex, yIndex);
                }
                
            } catch (NullPointerException e){
                Token token = new Token(tokenid);
                tokens[tokenid] = token;
                addToken(tokenid, xIndex, yIndex, type);
            }
        }
        private boolean validateToken(int token){
            if(token <= size){
                return true;
            } else {
                return false;
            }      
        }
    }
    private class Token{
        ArrayList<TokenInstance> gridInstance = new ArrayList<TokenInstance>();
        ArrayList<TokenInstance> patternInstance= new ArrayList<TokenInstance>();
        int gridInstanceCount = 0;
        int patternInstanceCount = 0;
        String id;
        public Token(int token){
            
            this.id = String.format("%" + Solution.TOKENSIZE + "s", token).replace(' ', '0');
        }
        /**
         * For this token add a grid instance.
         * 
         * Add to the ArrayList that contains the x,y coords of every occurance of this 
         * pattern appearing in the Grid.
         * @param x
         * @param y
         */
        public void addGridInstance(short x, short y){
            TokenInstance tok = new TokenInstance(x, y);
            this.gridInstance.add(tok);
            this.gridInstanceCount++;
        }
        /**
         * For this token add a pattern instance
         * 
         *  Add to the ArrayList that contains the x,y coords of every occurance of this 
         * pattern appearing in the Pattern.
         * @param x
         * @param y
         */
        public void addPatternInstance(short x, short y){
            TokenInstance tok = new TokenInstance(x, y);
            this.patternInstance.add(tok);
            this.patternInstanceCount++;
        }        
    }
    /**
     * Contains index of a token instance
     */
    private class TokenInstance{
        short x;
        short y;
        public TokenInstance(short x, short y){
            this.x = x;
            this.y = y;
        }
        /**
         * Get the delta coords for this TokenInstance to the provided TokenInstance
         * 
         * @param ti
         * @return
         */
        public int[] tokenDelta(TokenInstance ti){
            int[] delta = new int[2];
            delta[0] = ti.x - this.x;
            delta[1] = ti.y -this.y; 
            return delta;
        }
    }
    

}

