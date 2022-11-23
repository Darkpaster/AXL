package axl.general.Values;

public class ValueByte extends Value {
    public ValueByte(byte n) {
        value = n;
    }

    @Override
    public boolean is_byte() {
        return true;
    }
}
