package axl.general;

import axl.parser.ast.Ast;

public class Value extends Ast {
    String value_string;
    short value_short;
    int value_int;
    long value_long;
    float value_float;
    double value_double;
    char value_char;
    boolean value_boolean;
    byte value_byte;

    public String   getString()
    {
        return value_string;
    }

    public int      getInt()
    {
        return value_int;
    }

    public long     getLong()
    {
        return value_long;
    }

    public short    getShort()
    {
        return value_short;
    }

    public float    getFloat()
    {
        return value_float;
    }

    public double   getDouble()
    {
        return value_double;
    }

    public char     getChar()
    {
        return value_char;
    }

    public boolean  getBoolean()
    {
        return value_boolean;
    }

    public byte     getByte()
    {
        return value_byte;
    }

    public boolean        is_string()
    {
        return false;
    }

    public boolean        is_short()
    {
        return false;
    }

    public boolean        is_int()
    {
        return false;
    }

    public boolean        is_long()
    {
        return false;
    }

    public boolean        is_float()
    {
        return false;
    }

    public boolean        is_double()
    {
        return false;
    }

    public boolean        is_object()
    {
        return false;
    }

    public boolean        is_char()
    {
        return false;
    }

    public boolean        is_boolean()
    {
        return false;
    }

    public boolean        is_void() {
        return false;
    }

    public boolean        is_byte() {
        return false;
    }

    @Override
    public boolean        is_value()
    {
        return true;
    }
}