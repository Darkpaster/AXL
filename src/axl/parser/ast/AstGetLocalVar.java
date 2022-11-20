package axl.parser.ast;

import axl.LOGGER;
import axl.lexer.Token;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstGetLocalVar implements Ast {
    public final String name;
    public final Token.Type type; // int, float...

    public AstGetLocalVar(String name)
    {
        this.name = name;
        this.type = AstLocalVarDefinition.get_var(name).type.getType();
    }

    @Override
    public void codegen(MethodVisitor mv) {
        int id  = AstLocalVarDefinition.get_var(name).id;
        if(is_byte() || is_short() || is_int() || is_bool() || is_char()) mv.visitVarInsn(ILOAD, id);
        else if (is_long()) mv.visitVarInsn(LLOAD, id);
        else if (is_float()) mv.visitVarInsn(FLOAD, id);
        else if (is_double()) mv.visitVarInsn(DLOAD, id);
        else if (is_object()) mv.visitVarInsn(ALOAD, id);
        else LOGGER.log("[CODE-GEN] неизвестный тип объекта");
    }

    public String get_type_jvm()
    {
        if(is_double()) return "D";
        if(is_float())  return "F";
        if(is_long())   return "J";
        if(is_bool())   return "Z";
        if(is_char())   return "C";
        if(is_object()) return "L"+AstLocalVarDefinition.get_var(name).type.getValue().getString()+';';
        return "I";
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
