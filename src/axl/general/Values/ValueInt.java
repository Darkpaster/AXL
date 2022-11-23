package axl.general.Values;

public class ValueInt extends Value {
    public ValueInt(int n) {
        value = n;
    }

    @Override
    public boolean is_int() {
        return true;
    }
}
