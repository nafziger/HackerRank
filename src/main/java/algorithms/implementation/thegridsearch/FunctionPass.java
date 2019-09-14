package algorithms.implementation.thegridsearch;

class FunctionPass{


        public static String function1(){
            return "Output1";
        }

        public static String function2(){
            return "Output2";
        }

        /**
         * Executes the function provided that impliments the MyInterface interface
         * @return
         */
        public void functionCaller( MyInterface mI ){
            System.out.println( mI.doSomething() );
        }

       
        public interface MyInterface {
                public String doSomething();
            }
        

        public static void main(String [] args){
            FunctionPass fp = new FunctionPass();
            fp.functionCaller( FunctionPass::function1 );

            // functionCaller( FunctionPass::function1 );
            // functionCaller( FunctionPass::function2 );
        }
    
}