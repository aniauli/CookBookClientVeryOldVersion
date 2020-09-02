import java.io.IOException;

public class ProductProvider extends Provider {

    @Override
    protected void sendShowAllItemsToSerer() {
        try {
            dataOutputStream.writeUTF("Show all products");
        } catch (IOException e) {
            System.out.println("Can't send the message to server");
        }
    }

    @Override
    protected void sendFindToServer(String toSend) {
        try {
            dataOutputStream.writeUTF("Find product");
            dataOutputStream.writeUTF(toSend);
        } catch (IOException e) {
            System.out.println("Can't send product to server");
        }
    }

    @Override
    protected void sendAddToServer(String toSend) {
        try {
            dataOutputStream.writeUTF("Add product");
            dataOutputStream.writeUTF(toSend);
        } catch (IOException e) {
            System.out.println("Can't send product to server");
        }
    }

}
