import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PropertyTest {
    private String strA;
    private transient StringProperty strP;

    private int intA;
    private transient IntegerProperty intP;

    public PropertyTest(String attribute, int attribute2) {
        this.strA = attribute;
        strP = new SimpleStringProperty(attribute);

        this.intA = attribute2;
        intP = new SimpleIntegerProperty(attribute2);
    }

    public void setAttributes(String attribute, int attribute2) {
        this.strA = attribute;

        this.intA = attribute2;
    }

    public String getStrP() {
        return strP.get();
    }

    public int getIntP() {
        return intP.get();
    }

    public static void main(String[] args) {
        PropertyTest pt = new PropertyTest("Hello", 5);
        System.out.println(pt.getStrP());
        System.out.println(pt.getIntP());

        pt.setAttributes("World", 10);

        System.out.println(pt.getStrP());
        System.out.println(pt.getIntP());
    }
}
