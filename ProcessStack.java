package calculation;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

//This Message class is just to used as a return type to show whether the stored items of the stack
//can be executable or valid. There are two data member involved, the first one "success" is
// to indicate whether the expression is valid, 0 - empty expression; 1 - invalid expression; 2 - valid expression 
// Actually only when success == 2, the second parameter "result" is meaningful. It shows the 
// result of the expression. Otherwise for the case success == 0 or success == 1, we can assume
// result = 0.
class Message
{
	private int success;  //valid indicator 0 - empty expression; 1 - invalid expression; 2 - valid expression 
	private double result;
	private String expression;
	
	
	//Constructor 1
	public Message()
	{
		success = 0; //by default, assume the expression is 0
		result = 0.0;
	}
	
	public Message(int s, double r)
	{
		success = s;
		result = r;
	}
	

	//below are basic setter and getter
	public int getSuccess() {
		return success;
	}
	
	public void setSuccess(int success) {
		this.success = success;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

}

/*This is a display class that is used to show message. In other word, for the class ProcessStack below, it use
 * this Display class to print out message information. 
 * 
 * The reason I design it in this way is to let you practice implementing different tasks in 
 * different modules. So this Display class is to take care of the output message. The ProcessStack
 * class is to take care the logic*/
class Display{
	public static void showMessage(Message m)
	{
		if(m.getSuccess() == 0)
		{
			System.out.println("No any input from the stack");
		}
		else if(m.getSuccess() == 1)
		{
			System.out.println("The expression is wrong!");
		}
		else
		{
			System.out.println("The expression result is: " + m.getResult());
		}
	}
}


/*The most important class in this file, which is used to process */
public class ProcessStack {

	public ProcessStack()
	{
		
	}
	
	
	/*This method call the the calculate() and print out the corresponding message*/
	public static void parseMyStack(MyStack s)
	{
		Message m = calculate(s);
		Display.showMessage(m);
	}
	
	
	
	/*Step 5: implement the following function 
	 * This is the most important function of this file. The description for this function
	 * is already defined in the instructions. Basecially, you will check the stored item of 
	 * the MyStack input parameter. There are three possible cases: empty expression, invalid expression,
	 * and valid expression. If it is a valid expression, calculate it. According to the three
	 * cases, return different messages.
	 * */
	public static Message calculate(MyStack ms)
	{  
		System.out.println(ms.getStack());
		Message msg=new Message();
		if(ms.getSize()==0){
			msg.setSuccess(0);
			return msg;
		}
		else if (!Pattern.matches("^[\\d\\s()+-/\\*.]*$", ms.getStack())) {
			msg.setSuccess(1);
			return msg;
		}
		else {
			Stack<Integer> digits=new Stack<Integer>();
			Stack<Character> symbol=new Stack<Character>();
			Iterator it=ms.getInputTokens().iterator();
			
			while(it.hasNext()){
				String value=it.next().toString();
				 if (value.equals(" ") )
		                continue;
				
				if((Pattern.matches("^[0-9]{1,45}$", value))){
					digits.push(Integer.parseInt(value));
					}
				else if (value.equals("("))
					symbol.push(value.charAt(0));

			// Closing brace encountered, solve entire brace
			else if (value.equals(")")) {
				while (symbol.peek() != '('){
				//	try{
					digits.push(applyOp(symbol.pop(), digits.pop(), digits.pop()));
					/*}catch(Exception e){
						break;
					}*/
				}
				symbol.pop();
			}
				else if(value.equals("+")|| value.equals("-") || value.equals("*") || value.equals("/")){
					  while (!symbol.empty() && hasPrecedence(value.charAt(0), symbol.peek())){
						 // try{
					 
		                  digits.push(applyOp(symbol.pop(), digits.pop(), digits.pop()));
						 /* }catch(Exception e){
							  break;
						  }*/
					  }
		                // Push current token to 'ops'.
		                symbol.push(value.charAt(0));
					//digits.push(Integer.parseInt(value));
				}
				  
			}
			while (!symbol.empty())
				try{
	            digits.push(applyOp(symbol.pop(), digits.pop(), digits.pop()));
				}catch (Exception e) {
					System.out.println("not valied");
					msg.setSuccess(1);
					return msg;
				}
			
	        // Top of 'values' contains result, return it
			int result=digits.pop();
	        System.out.println(result);
	        msg.setResult(result);
			msg.setSuccess(2);
			return msg;
		}
		
		
		
		//if ms stores correct expression, return new Message(2, result);
		//if ms has empty expression, return new Message(0, 0.0);
		//if ms has invalid expression, return new Message(1, 0.0)
	}


	private static Integer applyOp(Character pop, Integer a, Integer b) {
		switch (pop)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if(b == 0){
            	throw new
                UnsupportedOperationException("Cannot divide by zero");
            }
            return a / b;
        }
        return 0;
	}

		 public static boolean hasPrecedence(char op1, char op2)
		    {
		        if (op2 == '(' || op2 == ')')
		            return false;
		        if ((op1 == '*' || op1 == '/') && (op2 == '-' || op2 == '+'))
		            return false;
		        else
		            return true;
		    }

	
}
