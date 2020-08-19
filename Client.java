import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Socket serverSocket = new Socket("localhost", 8080);
        Scanner scanner = new Scanner(System.in);

        WindowApp windowApp = new WindowApp();
        windowApp.start();

        windowApp.submitSearchProductButton.addActionListener(actionEvent -> {
            String productToFind = windowApp.searchProductField.getText();
            if(productToFind != null){
                try {
                    System.out.println(productToFind);
                    send(serverSocket, productToFind);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        send(serverSocket, scanner.nextLine());
        Runnable runnable = () -> {
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))) {
                String line;
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread fromServerThread = new Thread(runnable);
        fromServerThread.setDaemon(true);
        fromServerThread.start();

        String line;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if("/quit".equals(line)){
                break;
            }
            send(serverSocket, line);
        }

        windowApp.close();
    }

    private static void send(Socket serverSocket, String line) throws IOException {
        OutputStream outputStream = serverSocket.getOutputStream();
        outputStream.write(line.getBytes());
        outputStream.write(System.lineSeparator().getBytes());
        outputStream.flush();
    }
}
