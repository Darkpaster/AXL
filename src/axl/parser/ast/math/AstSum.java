package axl.parser.ast.math;

import axl.parser.ast.Ast;
import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;

public class AstSum extends AstMath{

    public AstSum() {
        super();
    }

    public AstSum(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public boolean is_sum() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if (is_double())
            mv.visitInsn(DADD);
        else if (is_float())
            mv.visitInsn(FADD);
        else if(is_long())
            mv.visitInsn(LADD);
        else
            mv.visitInsn(IADD);
    }
}
