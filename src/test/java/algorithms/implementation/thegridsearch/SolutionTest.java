package algorithms.implementation.thegridsearch;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@TestInstance(Lifecycle.PER_CLASS)
public class SolutionTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    /**
     * Sets system.in to the input from a file
     * @param fileName
     */
    private void provideInputFromFile(String fileName) {          
              
        // InputStream inputStream = SolutionTest.class.getResourceAsStream(fileName);
        // Get the file resource as an inputStream from the package.    
        InputStream inputStream = SolutionTest.class.getResourceAsStream(fileName);  
        byte[] buff = new byte[8000];
        int bytesRead = 0;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try{
            while((bytesRead = inputStream.read(buff)) != -1) {
                bao.write(buff, 0, bytesRead);
            }
        } catch (Exception e){

        }
        // Convert the byte array output stream to a byte array
        byte[] data = bao.toByteArray();
        // convert the byte array into an input stream
        ByteArrayInputStream bin = new ByteArrayInputStream(data);
        systemOut.println(fileName +" "+ System.in);
        System.setIn(bin);  
        systemOut.println(fileName +" "+ System.in);
        
    }

    private String getOutput() {
        return testOut.toString();
        //return Systen.out.toString(0;)
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    
    @ParameterizedTest
    @CsvSource({    
        // "50_50_10_10_input.txt, output02.txt",
        // "testCase.txt,     output02.txt",        
        // "inputHard00.txt,     output00.txt",     
          
        //  "input00.txt,     output00.txt",   
        //  "input01.txt,     output01.txt",  
        //  "input02.txt,     output02.txt",  
        //  "input03.txt,     output03.txt",
        //  "input04.txt,     output04.txt",  
        
        "inputTestCase4.txt,     outPutTestCase4.txt", 
        "inputTestCase6.txt,       outputTestCase6.txt", 
        "inputTestCase10.txt,     outputTestCase10.txt",         

    })   
    public void runPrimaryTestCases(String inputFile, String expectedOutputFile) throws IOException {   
        systemOut.println("TESTING: "+inputFile +" "+ expectedOutputFile);

        provideInputFromFile(inputFile);        
        Solution.main(new String[0]);       

        // Get the expected result from file
        InputStream expectedOutputInputStream = SolutionTest.class.getResourceAsStream(expectedOutputFile); 
        ByteArrayOutputStream expectedOutputByteStream = new ByteArrayOutputStream();
        // Read the InputStream into the ByteArrayOutputStream in chuncks of 1024 bytes       
        byte[] buffer = new byte[1024];
        int length;
        while ((length = expectedOutputInputStream.read(buffer)) != -1) {
            expectedOutputByteStream.write(buffer, 0, length);             
        }           
        
        systemOut.println(expectedOutputByteStream.toString());
        systemOut.println(getOutput());
        // Test that the two ByteArrayOutputStreams have the same string representation.
        assertEquals(expectedOutputByteStream.toString(), getOutput() );
        //assertEquals(1,1);
    }

}