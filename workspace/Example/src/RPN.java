import java.io.StringReader;
import java.util.Scanner;
import java.util.Stack;


public class RPN {
	
	int operand1;
	int operand2;
	char op;
	
	Stack<Integer> stack =new Stack<Integer>();
	Stack<Integer> evaluate(String expression) {
		StringReader sr = new StringReader(expression);
		Scanner scanner = new Scanner(sr);
		String token;
		//String op = "+-*/"; 
		
		while (scanner.hasNext()) {
			token =scanner.next();
			//stack.push();
			
			switch(token){
			case "+":
				operand2=stack.pop();
				operand1=stack.pop();
				stack.push(operand2 + operand1);
				break;
			case "-": 
				operand2=stack.pop();
				operand1=stack.pop();
				stack.push(operand2 - operand1);
				break;
			case "*":
				operand2=stack.pop();
				operand1=stack.pop();
				stack.push(operand2 * operand1);
				break;
			case "/":
				operand2=stack.pop();
				operand1=stack.pop();
				stack.push(operand2 / operand1);
				break;
			default: 
				int x=Integer.parseInt(token);
				stack.push(x);
			
		}
			
			
				
		}
		
		
		return stack;
	}
}