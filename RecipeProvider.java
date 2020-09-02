import java.io.IOException;

public class RecipeProvider extends Provider {

    @Override
    protected void sendShowAllItemsToSerer() {
        try {
            dataOutputStream.writeUTF("Show all recipes");
        } catch (IOException e) {
            System.out.println("Can't send the message to server");
        }
    }

    protected void sendFindToServer(String toSend) {
        try {
            dataOutputStream.writeUTF("Find recipe");
            dataOutputStream.writeUTF(toSend);
        } catch (IOException e) {
            System.out.println("Can't send recipe to server");
        }
    }

    @Override
    protected void sendAddToServer(String toSend) {
        try {
            dataOutputStream.writeUTF("Add recipe");
            dataOutputStream.writeUTF(toSend);
        } catch (IOException e) {
            System.out.println("Can't send recipe to server");
        }
    }
}
