package axl.parser.ast.math;

import axl.LOGGER;
import axl.general.Value;
import axl.parser.ast.AstGetLocalVar;
import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;

public class AstSub extends AstMath{

    @Override
    public boolean is_sub() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        if(left instanceof AstMath)
        {
            left.codegen(mv);
        } else if (left instanceof Value) {
            if(((Value) left).is_byte())
                mv.visitIntInsn(BIPUSH, ((Value) left).getByte());
            else if(((Value) left).is_short())
                mv.visitIntInsn(SIPUSH, ((Value) left).getShort());
            else if(((Value) left).is_int())
                mv.visitLdcInsn(((Value) left).getInt());
            else LOGGER.log("[CODE-GEN] недопустимый тип при вычитании (left)");
        } else if (left instanceof AstGetLocalVar) {
            left.codegen(mv);
        }

        if(right instanceof AstMath)
        {
            right.codegen(mv);
        } else if (right instanceof Value) {
            if(((Value) right).is_byte())
                mv.visitIntInsn(BIPUSH, ((Value) right).getByte());
            else if(((Value) right).is_short())
                mv.visitIntInsn(SIPUSH, ((Value) right).getShort());
            else if(((Value) right).is_int())
                mv.visitLdcInsn(((Value) right).getInt());
            else LOGGER.log("[CODE-GEN] недопустимый тип при вычитании (right)");
        } else if (right instanceof AstGetLocalVar) {
            right.codegen(mv);
        }

        mv.visitInsn(ISUB);
    }
}
