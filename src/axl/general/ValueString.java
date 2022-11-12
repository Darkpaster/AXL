package axl.general;

public class ValueString extends Value {
    public ValueString(String str) {
        value_string = str;
    }

    @Override
    public boolean is_string() {
        return true;
    }
}
