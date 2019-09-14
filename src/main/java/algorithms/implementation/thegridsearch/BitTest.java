package algorithms.implementation.thegridsearch;
class BitTest {


    public static void main(String [] args){        
        int i = 0b1010;//10
        System.out.println( i + " " + Integer.toBinaryString(i) );
        // bitwise completement
        int c = ~i;// 0101 5 
        System.out.println(c + " " + Integer.toBinaryString(c) );
    }
}