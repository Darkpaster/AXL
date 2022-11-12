package axl.general;

public class ValueChar extends Value {
    public ValueChar(char n) {
        value_char = n;
    }

    @Override
    public boolean is_char() {
        return true;
    }
}
