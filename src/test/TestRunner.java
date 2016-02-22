/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Danica
 */
 
 import org.junit.runner.JUnitCore;
 import org.junit.runner.Result;
 import org.junit.runner.notification.Failure;
 
public class TestRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
		Result result = JUnitCore.runClasses(TestObjectStorage.class);
		int errorNum = 0;
		for(Failure failure : result.getFailures()) {
			errorNum++;
			System.out.println("Error no: " + errorNum);
			System.out.println(failure.toString());
		}
		
		if(errorNum == 0) {
			System.out.println("No errors.");
		}
    }
    
}
