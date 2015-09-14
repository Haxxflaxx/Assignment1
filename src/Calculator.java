import java.util.Scanner;
public class Calculator {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String strForCalc;
		
		strForCalc = input.nextLine();
		input.close();
		
		System.out.println(strForCalc);
		
		strForCalc = RPN.format(strForCalc);
		System.out.println(strForCalc);		
		System.out.println(RPN.calc(strForCalc));
		

		
	}
}

