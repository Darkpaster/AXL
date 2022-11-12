package axl.general;

public class ValueInt extends Value {
    public ValueInt(int n) {
        value_int = n;
    }

    @Override
    public boolean is_int() {
        return true;
    }
}
