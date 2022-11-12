package axl.general;

public class ValueFloat extends Value {
    public ValueFloat(float n) {
        value_float = n;
    }

    @Override
    public boolean is_float() {
        return true;
    }
}
