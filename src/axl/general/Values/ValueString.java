package axl.general.Values;

public class ValueString extends Value {
    public ValueString(String str) {
        value = str;
    }

    @Override
    public boolean is_string() {
        return true;
    }
}
