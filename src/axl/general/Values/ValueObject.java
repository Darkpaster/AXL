package axl.general.Values;

public class ValueObject extends Value {
    ValueObject(String obj) {
        value = obj;
    }

    @Override
    public boolean is_object() {
        return true;
    }
}
