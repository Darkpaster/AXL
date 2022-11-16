package axl.parser.ast.math.bool;

import axl.LOGGER;
import axl.parser.ast.Ast;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstMore extends AstBool{

    public AstMore() {
        super();
    }

    public AstMore(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public void codegen(MethodVisitor mv) {
//        if(!(left_is_bool() && right_is_bool())) LOGGER.log("[CODE-GEN] логические операторы используются только с bool", true);
//
//
//        Label trueLabel = new Label();
//        Label endLabel = new Label();
//
//        left.codegen(mv);
//        mv.visitJumpInsn(IFEQ, trueLabel);
//        right.codegen(mv);
//        mv.visitJumpInsn(IFEQ, trueLabel);
//        mv.visitInsn(ICONST_1);
//        mv.visitJumpInsn(GOTO, endLabel);
//        mv.visitLabel(trueLabel);
//        mv.visitInsn(ICONST_0);
//        mv.visitLabel(endLabel);
    }
}
