import Api.Data;

import Api.Admin;

public class MakeAdmin {

    public static void main(String[] args) {
        Data data = Data.load();
        data.addAdmin(new Admin("me", "me"));
        data.save();
    }
}
