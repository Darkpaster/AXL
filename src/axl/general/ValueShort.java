package axl.general;

public class ValueShort extends Value {
    public ValueShort(short n) {
        value_short = n;
    }

    @Override
    public boolean is_short() {
        return true;
    }
}
