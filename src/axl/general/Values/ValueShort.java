package axl.general.Values;

public class ValueShort extends Value {
    public ValueShort(short n) {
        value = n;
    }

    @Override
    public boolean is_short() {
        return true;
    }
}
