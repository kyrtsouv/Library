import Api.DataStorer;
import MVC.DataHandler;
import Api.Admin;

public class MakeAdmin {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler(DataStorer.load());
        dataHandler.addAdmin(new Admin("me", "me"));
        dataHandler.save();
    }
}
