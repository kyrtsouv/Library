import Api.Data;

import Api.Admin;

public class MakeAdmin {

    public static void main(String[] args) {
        Data data = Data.load();
        data.addAdmin(new Admin("medialab", "medialab_2024"));
        data.save();
    }
}
