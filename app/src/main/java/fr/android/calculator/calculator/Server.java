package fr.android.calculator.calculator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private Socket sock;

    public Server(Socket s) {
        sock = s;
    }

    @Override
    public void run() {

        try {
            System.out.println("FONCTIONNE!");
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            // read op1, op2 and the operation to make
            Double op1 = dis.readDouble();
            System.out.println(op1);
            char operator1 = dis.readChar();
            System.out.println(operator1);
            Double op2 = dis.readDouble();
            System.out.println(op2);

            Double res = CalculusServer.doOp(op1, op2, operator1);

            // send back res
            dos.writeDouble(res);

            dis.close();
            dos.close();
            sock.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class CalculusServer {


    public static double doOp(double op1, double op2, char operator1) throws Exception
    {
        switch (operator1) {

            case '+':
                return op1 + op2;

            case '-':
                return op1 - op2;

            case'*':
                return op1 * op2;

            case '/':
                if (op2 != 0)
                    return op1 / op2;
                else
                    throw new Exception();

            default:
                throw new Exception();
        }

    }



    public static void main(String[] args) throws Exception {

        // Example of a distant calculator
        ServerSocket ssock = new ServerSocket(9879);

        while (true) { // infinite loop
            Socket comm = ssock.accept();
            System.out.println("connection established");

            new Thread(new Server(comm)).start();

        }

    }

}

