import java.util.StringTokenizer;
import java.util.ArrayList;

public class operation {
    public static void main(String[] args){
        ArrayList<String> operation = new ArrayList<String>();
        String data = "123 + 35 / 5 / 2 * 5 + 8 - 14 * 8";
        StringTokenizer st = new StringTokenizer (data, " ");
        
        while ( st.hasMoreTokens())
        {
            String tmp = st.nextToken();
            operation.add(tmp);
        }

        // '*' and '/' first
        for (int i = 0; i < operation.size(); i++) {
            switch(operation.get(i)){
                case "/": 
                {
                    float result = Float.valueOf(operation.get(i - 1)) / Float.valueOf(operation.get(i + 1));
                    operation.set(i,Float.toString(result));
                    operation.remove(i - 1);
                    operation.remove(i);
                    i = 0;
                    System.out.println("The result after division: " + operation);
                }
                break;
                case "*":
                {
                    float result = Float.valueOf(operation.get(i - 1)) * Float.valueOf(operation.get(i + 1));
                    operation.set(i,Float.toString(result));
                    operation.remove(i - 1);
                    operation.remove(i);
                    i = 0;
                    System.out.println("The result after multiplication: " + operation);
                }
                break;
                default: // code block
                break;
            }
        }
        
        //   '+' and '-' later
        for (int i = 0; i < operation.size(); i++) {
            switch(operation.get(i)){
                case "+": 
                {
                    float result = Float.valueOf(operation.get(i - 1)) + Float.valueOf(operation.get(i + 1));
                    operation.set(i,Float.toString(result));
                    operation.remove(i - 1);
                    operation.remove(i);
                    i = 0;
                    System.out.println("The result after addition: " + operation);
                }
                break;
                case "-":
                {
                    float result = Float.valueOf(operation.get(i - 1)) - Float.valueOf(operation.get(i + 1));
                    operation.set(i,Float.toString(result));
                    operation.remove(i - 1);
                    operation.remove(i);
                    i = 0;
                    System.out.println("The result after subtraction: " + operation);
                }
                 break;
                default: // code block
                 break;
            }
        }

        System.out.println("The final result is: " + operation.get(0));
    }
}