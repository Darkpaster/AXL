package axl.parser.ast.math;

import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;

public class AstSum extends AstMath{

    @Override
    public boolean is_sum() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if(is_long())
            mv.visitInsn(LADD);
        else
            mv.visitInsn(IADD);
    }
}
