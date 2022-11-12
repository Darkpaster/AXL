package axl.general;

public class ValueDouble extends Value {
    public ValueDouble(double n) {
        value_double = n;
    }

    @Override
    public boolean is_double() {
        return true;
    }
}
