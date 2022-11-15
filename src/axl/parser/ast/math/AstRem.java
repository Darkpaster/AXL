package axl.parser.ast.math;

import axl.parser.ast.Ast;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstRem extends AstMath{

    public AstRem() {
        super();
    }

    public AstRem(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if (is_double())
            mv.visitInsn(DREM);
        else if (is_float())
            mv.visitInsn(FREM);
        else if(is_long())
            mv.visitInsn(LREM);
        else
            mv.visitInsn(IREM);
    }
}
