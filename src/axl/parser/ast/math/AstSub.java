package axl.parser.ast.math;

import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;

public class AstSub extends AstMath{

    @Override
    public boolean is_sub() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if(is_long())
            mv.visitInsn(LSUB);
        else
            mv.visitInsn(ISUB);
    }
}
