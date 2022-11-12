package axl.general;

public class ValueLong extends Value {
    public ValueLong(long n) {
        value_long = n;
    }

    @Override
    public boolean is_long() {
        return true;
    }
}
