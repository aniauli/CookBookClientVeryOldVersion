import java.io.*;
import java.net.Socket;

abstract class Provider {

    private Socket socket;
    protected DataOutputStream dataOutputStream;
    protected DataInputStream dataInputStream;

    public Provider() {
        connectToServerSocket("localhost", 8080);
        createDataInputAndOutputStream();
    }

    private void connectToServerSocket(String host, Integer port) {
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

    abstract String findItem(String itemToFind);
    abstract void sendFindToServer(String toSend);
    abstract String receiveFromServer();
    abstract String addItem(String itemToAdd);
    abstract void sendAddToServer(String toSend);
}
