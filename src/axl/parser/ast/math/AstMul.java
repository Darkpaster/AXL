package axl.parser.ast.math;

import axl.parser.ast.Ast;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstMul extends AstMath{

    public AstMul() {
        super();
    }

    public AstMul(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if (is_double())
            mv.visitInsn(DMUL);
        else if (is_float())
            mv.visitInsn(FMUL);
        else if(is_long())
            mv.visitInsn(LMUL);
        else
            mv.visitInsn(IMUL);
    }
}
