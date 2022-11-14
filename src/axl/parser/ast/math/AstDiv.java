package axl.parser.ast.math;

import axl.parser.ast.Ast;
import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;

public class AstDiv extends AstMath{

    public AstDiv() {
        super();
    }

    public AstDiv(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public boolean is_sub() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if (is_double())
            mv.visitInsn(DDIV);
        else if (is_float())
            mv.visitInsn(FDIV);
        else if(is_long())
            mv.visitInsn(LDIV);
        else
            mv.visitInsn(IDIV);
    }
}
