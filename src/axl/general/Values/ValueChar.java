package axl.general.Values;

public class ValueChar extends Value {
    public ValueChar(char n) {
        value = n;
    }

    @Override
    public boolean is_char() {
        return true;
    }
}
