import java.io.StringReader;
import java.util.Scanner;
import java.util.Stack;


public class RPN {
	Stack<Integer> stack =new Stack<Integer>();
	
	Stack<Integer> evaluate(String expression) {
		StringReader sr = new StringReader(expression);
		Scanner scanner = new Scanner(sr);
		String token;
		while (scanner.hasNext()) {
			token =scanner.next();
			//check for +,-,/,*
			if (token.equals("+"))
				// do the appropriate thing
				// pop 2 items off stack, and place result onto stack
			Integer.parseInt(token);
			//catch all case
			//put the integer on the stack
			Integer.parseInt(token); 
		}
		
		
		return stack;
	}
}