import java.io.IOException;

public class RecipeProvider extends ProductProvider {

    protected String showAllMeals(String mealName){
        sendShowAllMealsToServer(mealName);
        return receiveFromServer();
    }

    protected void sendShowAllMealsToServer(String mealName) {
        try {
            dataOutputStream.writeUTF("Show " + mealName);
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
