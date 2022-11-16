package axl.parser.ast.math.types;

import axl.parser.ast.Ast;
import axl.parser.ast.math.AstMath;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstToInt extends AstMath {

    public AstToInt(Ast left)
    {
        super(left, null);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        left.codegen(mv);

        if(left_is_long())
            mv.visitInsn(L2I);
        else if(left_is_float())
            mv.visitInsn(F2I);
        else if(left_is_double())
            mv.visitInsn(D2I);
    }
}
