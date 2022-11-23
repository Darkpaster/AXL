package axl.general.Values;

public class ValueFloat extends Value {
    public ValueFloat(float n) {
        value = n;
    }

    @Override
    public boolean is_float() {
        return true;
    }
}
