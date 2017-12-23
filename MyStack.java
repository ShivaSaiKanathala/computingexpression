package calculation;

import java.util.*;



public class MyStack {

	/*Step 1: Define three data members for this class
	 * Data member 1: is called "total_stacks" which is used
	 * to record how many MyStack objects have been created;
	 *  
	 * Data member 2: is called "id" represents the id number
	 * the current MyStack object. Each created MyStack object
	 * should have one unique id number, which is assigned at
	 * the time when the object is created. You can use the total_stacks
	 * as a reference. For example, the first created MyStack object has ID = 0;
	 * The second created MyStack Object has ID = 1, and so forth
	 * 
	 * Data member 3: is called "stack", which is the Java supported Stack.
	 * This is the main variable to store the expression.
	 * 
	 * For these three data members, you should determine whether it is a 
	 * "static" or "non-static" according to its role as mentioned above
	 * 
	 * Try to use the "Generate Setters and Getters" tool in the "Source" menu to 
	 * create the three pairs of setters and getters automatically*/

	
	
	
	//Do the step 1 here
	
	private static int total_stacks=0;
	private static int id=0;
	private static String stack=null;
	private static Stack inputTokens = new Stack();
	//private static Stack<Character> inputTokens = new Stack<Character>();
//	private static Stack<Character> outputTokens = new Stack<Character>();
	public static int getTotal_stacks() {
		return total_stacks;
	}


	public static void setTotal_stacks(int total_stacks) {
		MyStack.total_stacks = total_stacks;
	}


	public static int getId() {
		return id;
	}


	public static void setId(int id) {
		MyStack.id = id;
	}


	public static String getStack() {
		return stack;
	}


	public static void setStack(String stack) {
		MyStack.stack = stack;
	}
	
	public static Stack<Character> getInputTokens() {
		return inputTokens;
	}


	public static void setInputTokens(Stack<Character> inputTokens) {
		MyStack.inputTokens = inputTokens;
	}

	
	
	
	
	/*Step 2: Create two constructors of MyStack(). For both of the constructors, you
	 * need to make sure to assign the ID for the created object. Meanwhile, maintain
	 * the number of "total_stacks" globally, which means to increase it by one every time when a
	 * new object is created 
	 * 
	 * Constructor 1: this constructor has no input arguments. You need to update the
	 * related variables as mentioned above. Also print out a message, e.g. "A stack with 
	 * the id #5 is created" (do this at the end of the constructor) 
	 * 
	 * Constructor 2: this constructor has one input argument - "String exp". In addition to finishing the
	 * tasks as constructor 1 does, it also push the expression as string type into the 
	 * stack data member by simply calling addItem() member function. 
	 * */
	
	
	

	
	//Do the step 2 here

	

	public MyStack(){
	/*setTotal_stacks(total_stacks);
	setId(id);*/
	//System.out.println("total no of stacks ::: "+getTotal_stacks());
	System.out.println("A stack with  the id #"+ getId() + " is created ");
	id++;
	total_stacks++;
	setId(id);
	setTotal_stacks(total_stacks);
	}
	
	public MyStack(String exp){
		/*setTotal_stacks(total_stacks);
		setId(id);*/
		System.out.println(exp);
		//setStack(exp);
		System.out.println("total no of stacks ::: "+getTotal_stacks());
		System.out.println("A stack with  the id #"+ getId() + " is created ");
		id++;
		total_stacks++;
		setId(id);
		setTotal_stacks(total_stacks);
		addItem(exp);
	}
	
	
	
	
	
	/*Step 3: complete the functions below*/
	
	


	/*remove the top item (String) from the stack data member*/
	public void removeItem()
	{
		
		if(!inputTokens.isEmpty()){
			inputTokens.clear();
		}
		/*while(!inputTokens.isEmpty()){
			inputTokens.clear();
		}*/
	}

	
	/*Print out all the items of the stack by printing each one in a new line 
	 * For example, for an expression 5 * 18 + 21
	 * 			   [0] 5
	 * 			   [1] *
	 * 			   [2] 18
	 * 			   [3] +
	 * 			   [5] 21
	 * If you use the stack API directly, you probably can only access the items in the
	 * order from the top to the bottom. So to print them out in the order as the items
	 * are inserted, you need to use the Iterator class, which is returned from the stack.
	 * You need to look it up online on how to use Iterator class*/ 
	public void showItems()
	{
		Iterator<Character> stackIterartor=getInputTokens().iterator();
		int index=0;
		System.out.println("Stack Item(s) are ::::");
		while(stackIterartor.hasNext()){
			
			System.out.println("[ "+index+" ] "+stackIterartor.next());
			index++;
		}
	}
	
	
	/* Return the top character of the stack, you can't remove the top item but just read it*/
	public String getTopItem()
	{
		return null;
		
	}
	
	//Return how many items are there in the stack
	public int getSize()
	{
		return getInputTokens().size();
		//Implement here here
	}
	
	
	
	/*Step 4: as described in the instruction. This is the most important function for this class
	 * The role is to process the input String, and store them into the stack as items*/
	public void addItem(String exp)
	{
		setStack(exp);
		char[] chars = exp.toCharArray();
		
		//this loop for processing input values
		/*
		 * here charIndex ::: to retrieve char[] values
		 * prv_flag ::: for strong prv_index 
		 * use of prv_flag ::: some times  expression values have more then one digit at that time for combining those digits this will be useful
		 * bcz char[] stores one char value only  
		 */
		
			for (int charsIndex = 0, prv_flag = 0; charsIndex < chars.length; charsIndex++) {
				// Current token is a number, push it to stack for numbers
				if (chars[charsIndex] == ' ')
					continue;

				// Current token is a number, push it to stack for numbers
				if (chars[charsIndex] >= '0' && chars[charsIndex] <= '9') {
					prv_flag = charsIndex;
					StringBuffer sbuf = new StringBuffer();
					// There may be more than one digits in number
					while (prv_flag < chars.length && chars[prv_flag] >= '0' && chars[prv_flag] <= '9')
						sbuf.append(chars[prv_flag++]);
					inputTokens.push(sbuf.toString());
					charsIndex = prv_flag - 1;
				}
				// Current token is an opening brace, push it to 'outputTokens'
				else 
					inputTokens.push(chars[charsIndex]);
	}
}

	
	

	
}
