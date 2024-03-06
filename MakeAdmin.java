import Api.Data;
import MVC.DataHandler;
import Api.Admin;

public class MakeAdmin {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler(Data.load());
        dataHandler.addAdmin(new Admin("me", "me"));
        dataHandler.save();
    }
}
