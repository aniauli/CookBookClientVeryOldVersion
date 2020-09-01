import java.io.IOException;

public class ProductProvider extends Provider {

    @Override
    String findItem(String itemToFind) {
        sendFindToServer(itemToFind);
        return receiveFromServer();
    }

    @Override
    void sendFindToServer(String toSend) {
        try {
            dataOutputStream.writeUTF("Find product");
            dataOutputStream.writeUTF(toSend);
        } catch (IOException e) {
            System.out.println("Can't send product to server");
        }
    }

    @Override
    String receiveFromServer() {
        String receivedMessage;
        try {
            receivedMessage = dataInputStream.readUTF();
            return receivedMessage;
        } catch (IOException e) {
            System.out.println("Can't receive from server");
            return "Server Error";
        }
    }

    @Override
    String addItem(String itemToAdd) {
        sendAddToServer(itemToAdd);
        return receiveFromServer();
    }

    @Override
    void sendAddToServer(String toSend) {
        try {
            dataOutputStream.writeUTF("Add product");
            dataOutputStream.writeUTF(toSend);
        } catch (IOException e) {
            System.out.println("Can't send product to server");
        }
    }

}
