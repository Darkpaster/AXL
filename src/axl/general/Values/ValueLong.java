package axl.general.Values;

public class ValueLong extends Value {
    public ValueLong(long n) {
        value = n;
    }

    @Override
    public boolean is_long() {
        return true;
    }
}
