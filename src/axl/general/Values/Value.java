package axl.general.Values;

import axl.LOGGER;
import axl.general.Ast;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class Value extends Ast {
    public Object value;

    @Override
    public void codegen(MethodVisitor mv) {
        if (is_float() || is_double() || is_int() || is_long() || is_string())
            mv.visitLdcInsn(value);
        else if (is_char())
            mv.visitIntInsn(BIPUSH, (char) value);
        else if (is_byte())
            mv.visitIntInsn(BIPUSH, (byte) value);
        else if (is_short())
            mv.visitIntInsn(SIPUSH, (short) value);
        else if (is_bool())
            if ((boolean) value)
                mv.visitInsn(ICONST_1);
            else
                mv.visitInsn(ICONST_0);
        else
            LOGGER.log("[CODE-GEN] неизвестный тип", true);
    }

    @Override
    public String get_type_jvm() {
        if (is_char()) return "C";
        if (is_byte()) return "B";
        if (is_short()) return "S";
        if (is_int()) return "I";
        if (is_float()) return "F";
        if (is_double()) return "D";
        if (is_long()) return "J";
        if (is_bool()) return "Z";
        if (is_object()) return (String) value;
        LOGGER.log("[CODE-GEN] неизвестный тип", true);
        return "ERROR";
    }

    @Override
    public void codegen(ClassWriter cw) {
        LOGGER.log("[CODE-GEN] вызов 'codegen()' у неверного класса.", true);
    }

    public Object getValue() {
        return value;
    }

    public boolean is_string() {
        return false;
    }

    public boolean is_short() {
        return false;
    }

    public boolean is_int() {
        return false;
    }

    public boolean is_long() {
        return false;
    }

    public boolean is_float() {
        return false;
    }

    public boolean is_double() {
        return false;
    }

    public boolean is_object() {
        return false;
    }

    public boolean is_char() {
        return false;
    }

    public boolean is_bool() {
        return false;
    }

    public boolean is_byte() {
        return false;
    }
}