package axl.general.Values;

public class ValueBoolean extends Value {
    public ValueBoolean(boolean n) {
        value = n;
    }

    @Override
    public boolean is_bool() {
        return true;
    }
}
