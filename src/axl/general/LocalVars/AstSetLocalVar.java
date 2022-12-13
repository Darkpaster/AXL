package axl.general.LocalVars;

import axl.LOGGER;
import axl.general.Ast;
import axl.general.Values.Value;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstSetLocalVar extends Ast {
    public String name;
    public VarCounter.Var var;
    public Ast value;

    public AstSetLocalVar(String name, Ast value)
    {
        this.name  = name;
        this.value = value;
    }

    @Override
    public String get_type_jvm() {
        return var.type;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        value.codegen(mv);

        int priority = Value.priority(var.type);
        if     (priority == 1) mv.visitVarInsn(ISTORE, var.id);
        else if(priority == 2) mv.visitVarInsn(LSTORE, var.id);
        else if(priority == 3) mv.visitVarInsn(FSTORE, var.id);
        else if(priority == 4) mv.visitVarInsn(DSTORE, var.id);
        else if(priority == 5) mv.visitVarInsn(ASTORE, var.id);
        else LOGGER.log("[CODE-GEN] неизвестный тип переменной", true);
    }
}
