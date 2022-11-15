package axl.general;

import axl.LOGGER;
import axl.parser.ast.Ast;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class Value implements Ast {
    String value_string;
    short value_short;
    int value_int;
    long value_long;
    float value_float;
    double value_double;
    char value_char;
    boolean value_boolean;
    byte value_byte;

    @Override
    public void codegen(MethodVisitor mv) {
        if(this.is_string())
            mv.visitLdcInsn(value_string);
        else if(is_char())
            mv.visitIntInsn(CALOAD, value_char);
        else if(is_long())
            mv.visitLdcInsn(value_long);
        else if(is_int())
            mv.visitLdcInsn(value_int);
        else if(is_float())
            mv.visitLdcInsn(value_float);
        else if(is_double())
            mv.visitLdcInsn(value_double);
        else if(is_byte())
            mv.visitIntInsn(BIPUSH, value_byte);
        else if(is_short())
            mv.visitIntInsn(SIPUSH, value_short);
        else if(is_boolean())
            if(value_boolean)
                mv.visitInsn(ICONST_1);
            else
                mv.visitInsn(ICONST_0);
        else
            LOGGER.log("[CODE-GEN] неизвестный тип", true);
    }

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
}