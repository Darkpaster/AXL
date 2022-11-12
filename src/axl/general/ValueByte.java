package axl.general;

public class ValueByte extends Value {
    public ValueByte(byte n) {
        value_byte = n;
    }

    @Override
    public boolean is_byte() {
        return true;
    }
}
