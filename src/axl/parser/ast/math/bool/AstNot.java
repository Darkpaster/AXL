package axl.parser.ast.math.bool;

import axl.LOGGER;
import axl.parser.ast.Ast;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstNot extends AstBool{

    public AstNot() {
        super();
    }

    public AstNot(Ast left) {
        super(left, null);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        if(!(left_is_bool())) LOGGER.log("[CODE-GEN] логические операторы используются только с bool", true);


        Label trueLabel = new Label();
        Label endLabel = new Label();

        left.codegen(mv);
        mv.visitJumpInsn(IFNE, trueLabel);
        mv.visitInsn(ICONST_1);
        mv.visitJumpInsn(GOTO, endLabel);
        mv.visitLabel(trueLabel);
        mv.visitInsn(ICONST_0);
        mv.visitLabel(endLabel);
    }
}
