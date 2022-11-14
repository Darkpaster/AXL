package axl.parser.ast.math;

import axl.LOGGER;
import axl.general.Value;
import axl.parser.ast.Ast;
import axl.parser.ast.AstGetLocalVar;
import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;
import static reloc.org.objectweb.asm.Opcodes.I2L;

public class AstPow extends AstMath{

    public AstPow() {
        super();
    }

    public AstPow(Ast left, Ast right) {
        super(left, right);
    }

    @Override
    public boolean is_sub() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        boolean left_is_double = left_is_double();
        boolean right_is_double = right_is_double();

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
            else if(((Value) left).is_long())
                mv.visitLdcInsn(((Value) left).getLong());
            else if(((Value) left).is_float())
                mv.visitLdcInsn(((Value) left).getFloat());
            else if(((Value) left).is_double())
                mv.visitLdcInsn(((Value) left).getDouble());
            else LOGGER.log("[CODE-GEN] недопустимый (left)");
        } else if (left instanceof AstGetLocalVar) {
            left.codegen(mv);
        }

        if(!left_is_double)
        {
            if(left_is_float())
                mv.visitInsn(F2D);
            else if(left_is_long())
                mv.visitInsn(L2D);
            else
                mv.visitInsn(I2D);
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
            else if(((Value) right).is_long())
                mv.visitLdcInsn(((Value) right).getLong());
            else if(((Value) right).is_float())
                mv.visitLdcInsn(((Value) right).getFloat());
            else if(((Value) right).is_double())
                mv.visitLdcInsn(((Value) right).getDouble());
            else LOGGER.log("[CODE-GEN] недопустимый тип (right)");
        } else if (right instanceof AstGetLocalVar) {
            right.codegen(mv);
        }

        if(!right_is_double)
        {
            if(right_is_float())
                mv.visitInsn(F2D);
            else if(right_is_long())
                mv.visitInsn(L2D);
            else
                mv.visitInsn(I2D);
        }

        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "pow", "(DD)D");
    }

    @Override
    public boolean is_double() {
        return true;
    }
}
