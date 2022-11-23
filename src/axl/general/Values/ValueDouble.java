package axl.general.Values;

public class ValueDouble extends Value {
    public ValueDouble(double n) {
        value = n;
    }

    @Override
    public boolean is_double() {
        return true;
    }
}
