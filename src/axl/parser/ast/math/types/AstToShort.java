package axl.parser.ast.math.types;

import axl.parser.ast.Ast;
import axl.parser.ast.math.AstMath;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstToShort extends AstMath {

    public AstToShort(Ast left)
    {
        super(left, null);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        if(left_is_long())
            mv.visitInsn(L2I);
        else if(left_is_float())
            mv.visitInsn(F2I);
        else if(left_is_double())
            mv.visitInsn(D2I);
        mv.visitInsn(I2S);
    }
}
