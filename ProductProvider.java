import java.io.*;
import java.net.Socket;

public class ProductProvider {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public ProductProvider() {
        connectToServerSocket("localhost", 8080);
        createDataInputAndOutputStream();
    }

    private void connectToServerSocket(String host, Integer port)
    {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDataInputAndOutputStream() {
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String findProduct(String productToFind) {
        if (productToFind != null) {
            return findNotNullProduct(productToFind);
        }
        return "There's no product to find";
    }

    private String findNotNullProduct(String productToFind) {
        try {
            dataOutputStream.writeUTF(productToFind);
        } catch (IOException e){
            System.out.println("Can't send product to server");
        }
        String receivedMessage;
        try {
            receivedMessage = dataInputStream.readUTF();
            return receivedMessage;
        } catch (IOException e) {
            System.out.println("Can't receive from server");
            return "Pudlo!";
        }
    }
}
