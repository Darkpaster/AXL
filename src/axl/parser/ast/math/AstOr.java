package axl.parser.ast.math;

import axl.LOGGER;
import axl.parser.ast.Ast;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstOr extends AstMath{

    public AstOr() {
        super();
    }

    public AstOr(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        super.codegen(mv);
        if (is_double() || is_float())
            LOGGER.log("[CODE-GEN] оператор используется только для целых типов", true);
        else if(is_long())
            mv.visitInsn(LOR);
        else
            mv.visitInsn(IOR);
    }
}
