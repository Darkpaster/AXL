package axl.general;

public class ValueObject extends Value {
    ValueObject(String obj) {
        value_string = obj;
    }

    @Override
    public boolean is_object() {
        return true;
    }
}
