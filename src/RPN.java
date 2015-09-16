import java.util.Arrays;
public class RPN {

	/**
	 * Takes a string input and returns the string in reverse Polish notation.
	 */
	static public String format(String input){
		String output = "";
		String tmp = "";
		
		input = removeWS(input);
		input = addSeparator(input, 1);
		
		for(int i = 0; i < input.length();i++){
			
			switch(classify(input.charAt(i))){
			//If operand
			case 1:
				output += input.charAt(i);
				break;
				
			//If left parentheses
			case 2:
				tmp = input.charAt(i) + tmp;
				break;
			
			//If right parentheses
			case 3:
				while(!tmp.isEmpty()){
					if(classify(tmp.charAt(0)) == 2){
						tmp = removeFirst(tmp);
						break;
					}
					else{
						output += tmp.charAt(0);
						tmp = removeFirst(tmp);
					}
				}
				break;
			
			//If operator
			case 4:
				while(!tmp.isEmpty() && p(tmp.charAt(0)) >= p(input.charAt(i)) && classify(tmp.charAt(0)) != 2){
					output += tmp.charAt(0);
					tmp = removeFirst(tmp);
				}
				tmp = input.charAt(i) + tmp;
				break;
			}//End of switch
			
			
		}//End of for loop
		
		while(!tmp.isEmpty()){
			switch(classify(tmp.charAt(0))){
			case 2:
				tmp = removeFirst(tmp);
			default:
				output += tmp.charAt(0);
				tmp = removeFirst(tmp);
			}
		}
		output = addSeparator(output, 2);
		return output;
	}
	/**
	 * A function that returns a value based on what entered char is
	 * 1 = operand, 2 = left parentheses, 3 = right parentheses,
	 * 4 = operator
	 */
	static private int classify(char c){
		int cls;
		
		if(p(c) >= 0) cls = 4;
		
		else if(c == ')') cls = 3;
		
		else if(c == '(') cls = 2;
		
		else cls = 1;
		
		return cls;
	}
	
	/**
	 * This subroutine/method/function takes as input a character c
	 * and returns an integer (0 or 1) corresponding to the priority of operator c
	 * -1  it returned whenever c is not an operator.
	 */
	
	static private int  p(char c){
		if ((c=='+') || (c=='-')) {
			return 0;
		}
		else if ((c=='/') || (c=='*')) {
			return 1;
		}
		else {
			return -1;
		} 
	}
	
	/**
	 * Removes the first element of a string.
	 * If the string is empty, it returns an empty string
	 * 
	 */
	static private String removeFirst(String input){
		String output = "";
		
		if(input.isEmpty());
		
		else if(input.length() < 2);
		
		else output = input.substring(1);
		
		return output;
	}
	
	/**
	 * Adds ',' as a separator.
	 * Key - 1: adds after numbers 2: makes sure operators are separated.
	 * Returns a string.
	 */
	static private String addSeparator(String input, int key){
		String output = "";
		switch(key){
		case 1:
			for(int i = 0; i < input.length(); i++){
				if(Character.getNumericValue(input.charAt(i)) == -1 && input.charAt(i) != '('){
					output += ',';
					output += input.charAt(i);
				}
				else output += input.charAt(i);
			}//End of for loop
			output += ',';
			break;
			
		case 2:
			for(int i = 0; i < input.length() - 1; i++){
				if(classify(input.charAt(i)) == 4 && input.charAt(i + 1) != ','){
					output += input.charAt(i);
					output += ',';
				}
				else output += input.charAt(i);
			}
			output += input.charAt(input.length() - 1);
			break;
		}
		return output;
	}//End of addSeparator()
	
	/**
	 * Removes white spaces from a string.
	 * Returns the modified string.
	 */
	static private String removeWS(String input){
		String output = "";
		
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) != ' ') output += input.charAt(i);
		}
		
		return output;
	}
	
	/**
	 * Takes a RPN formatted string with ',' used as separator
	 * and returns the result as an string
	 */
	static public String calc(String input){
		String[] calcString = input.split(",");
		String[] tmp = new String[5];
		Arrays.fill(tmp, "");
		
		for(int i = 0; i < calcString.length; i++){
			if(stringIsNumeric(calcString[i])){
				tmp = strAdd(tmp, calcString[i]);
			}
			else{
				tmp[1] = "" + operate(tmp[1], tmp[0], calcString[i]);
				tmp = strSub(tmp);
			}
		}
		
		return tmp[0];
	}
	
	/**
	 * Takes two values and an operator and performs
	 * the operation on the values.
	 * Returns String of x o y.
	 */
	static private int operate(String x, String y, String o){
		switch (o){
		case "+":
			return Integer.parseInt(x) + Integer.parseInt(y);
		
		case "-":
			return Integer.parseInt(x) - Integer.parseInt(y);
			
		case "*":
			return Integer.parseInt(x) * Integer.parseInt(y);
			
		case "/":
			return Integer.parseInt(x) / Integer.parseInt(y);
		
		default:
			return 0;
		}
	}
	
	/**
	 * Returns true if the first char in the string passed is a number.
	 */
	static private boolean stringIsNumeric(String input){
		if(classify(input.charAt(0)) == 1) return true;
		else return false;
	}
	
	/**
	 * Add string element to first position of a string array.
	 * Previous elements will be moved up one index and the last will fall off.
	 * Returns the updated string.
	 */
	static private String[] strAdd(String[] list, String input){
		String tmp = "";
		
		for(int i = 0; i < list.length; i++){
			tmp = list[i];
			list[i] = input;
			input = tmp;
		}
		return list;
	}
	
	/**
	 * Remove string element from first position of a string array.
	 * Previous elements will be moved down one index and the last will fall off.
	 * Returns the updated string.
	 */
	static private String[] strSub(String[] list){
		String tmp = "";
		String tmp2 = "";
		
		for(int i = list.length - 1; i >= 0; i--){
			tmp = list[i];
			list[i] = tmp2;
			tmp2 = tmp;
		}
		return list;
	}
}