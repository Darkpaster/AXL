package axl.parser.ast.math;

import axl.parser.ast.Ast;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstSub extends AstMath{

    public AstSub() {
        super();
    }

    public AstSub(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if (is_double())
            mv.visitInsn(DSUB);
        else if (is_float())
            mv.visitInsn(FSUB);
        else if(is_long())
            mv.visitInsn(LSUB);
        else
            mv.visitInsn(ISUB);
    }
}
