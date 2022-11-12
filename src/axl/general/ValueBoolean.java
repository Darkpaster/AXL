package axl.general;

public class ValueBoolean extends Value {
    public ValueBoolean(boolean n) {
        value_boolean = n;
    }

    @Override
    public boolean is_boolean() {
        return true;
    }
}
