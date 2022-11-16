package axl.parser.ast.math.types;

import axl.parser.ast.Ast;
import axl.parser.ast.math.AstMath;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstToDouble extends AstMath {

    public AstToDouble(Ast left)
    {
        super(left, null);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        left.codegen(mv);

        if(left_is_float())
            mv.visitInsn(F2D);
        else if(left_is_long())
            mv.visitInsn(L2D);
        else if (!left_is_double())
            mv.visitInsn(I2D);
    }
}
