package axl.parser.ast;

import axl.LOGGER;
import axl.lexer.Token;
import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;

public class AstGetLocalVar extends Ast{
    public final String name;
    public final short id;
    public final Token.Type type; // int, float...

    public AstGetLocalVar(String name, short id, Token.Type type)
    {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean is_get_local_var() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        if(is_byte() || is_short() || is_int()) mv.visitIntInsn(ILOAD, id);
        else if (is_long()) mv.visitIntInsn(LLOAD, id);
        else if (is_char()) mv.visitIntInsn(CALOAD, id);
        else if (is_object()) mv.visitIntInsn(ALOAD, id);
        else LOGGER.log("[CODE-GEN] неизвестный тип объекта");
    }

    public boolean is_byte()
    {
        return type == Token.Type.BYTE;
    }

    public boolean is_short()
    {
        return type == Token.Type.SHORT;
    }

    public boolean is_int()
    {
        return type == Token.Type.INT;
    }

    public boolean is_long()
    {
        return type == Token.Type.LONG;
    }

    public boolean is_double()
    {
        return type == Token.Type.DOUBLE;
    }

    public boolean is_float()
    {
        return type == Token.Type.FLOAT;
    }

    public boolean is_char()
    {
        return type == Token.Type.CHAR;
    }

    public boolean is_object()
    {
        return type == Token.Type.OBJECT;
    }
}
