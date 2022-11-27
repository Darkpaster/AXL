package axl.general.LocalVars;

import axl.LOGGER;
import axl.general.Ast;
import axl.general.Values.Value;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstGetLocalVar extends Ast {
    public VarsCounter.Var var;

    public AstGetLocalVar(VarsCounter.Var var)
    {
        this.var = var;
    }

    @Override
    public String get_type_jvm() {
        return var.type;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        int priority = Value.priority(var.type);
        if     (priority == 1) mv.visitVarInsn(ILOAD, var.id);
        else if(priority == 2) mv.visitVarInsn(LLOAD, var.id);
        else if(priority == 3) mv.visitVarInsn(FLOAD, var.id);
        else if(priority == 4) mv.visitVarInsn(DLOAD, var.id);
        else if(priority == 5) mv.visitVarInsn(ALOAD, var.id);
        else LOGGER.log("[CODE-GEN] неизвестный тип переменной", true);
    }
}
