package axl.parser.ast;

import axl.LOGGER;
import axl.lexer.Token;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstSetLocalVar implements Ast {
    public final int id;
    public final Token.Type type; // int, float...
    public final Ast value;

    public AstSetLocalVar(int id, Token.Type type, Ast value)
    {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        value.codegen(mv);

        if(is_byte() || is_short() || is_int() || is_bool() || is_char()) mv.visitIntInsn(ISTORE, id);
        else if (is_long()) mv.visitIntInsn(LSTORE, id);
        else if (is_float()) mv.visitIntInsn(FSTORE, id);
        else if (is_double()) mv.visitIntInsn(DSTORE, id);
        else if (is_object()) mv.visitIntInsn(ASTORE, id);
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

    public boolean is_bool()
    {
        return type == Token.Type.BOOLEAN;
    }
}
