import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server = null;
    private static Socket socket = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;

    public static boolean isPerfectNumber(int number){
        try{
            int sum = 0;
            for(int i=1; i<=number/2;i++){
                if(number%i == 0) 
                  sum+=i;
              }
              if(sum == number)return true;
              return false;
        } catch(Error){
            return false;
        }
      }

    private int findPerfectNumberNearest(int number)
    {
        try{
            int perfectNumber = number;
            int sum = 0;
            while(true)
            {
                perfectNumber ++;
                for(int i = 1; i <= perfectNumber/2; i++)
                {
                    if(perfectNumber%i == 0) 
                    sum+=i;
                }
                if (perfectNumber == sum) break;
                sum = 0;
            }
            return perfectNumber;
        } catch(Error){
            return 0;
        }
    }

    public static void main(String[] args) {
        try {
            server = new ServerSocket(5000);
            System.out.println("Server started...");
            socket = server.accept();
            System.out.println("Client " + socket.getInetAddress() + " connected...");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true) {
                // Server nhận dữ liệu từ client qua stre
                String line = in.readLine();
                if (line.equals("bye"))
                    break;
                System.out.println("Server received: " + line);
                // Server gửi phản hồi ngược lại cho client (chuỗi đảo ngược)
                StringBuilder newline = new StringBuilder();
                if(this.isPerfectNumber(Integer.valueOf(line)))
                {
                    line = line;
                }
                else{
                    line = this.findPerfectNumberNearest(Integer.valueOf(line)).toString();
                }
                newline.append(line);
                line = newline.reverse().toString();
                out.write(line);
                out.newLine();
                out.flush();
            }
            System.out.println("Server closed connection");
            in.close();
            out.close();
            socket.close();
            server.close();
        } catch (IOException e) { System.err.println(e); }
    }
}
