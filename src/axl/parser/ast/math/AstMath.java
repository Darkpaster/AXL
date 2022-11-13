package axl.parser.ast.math;

import axl.LOGGER;
import axl.general.Value;
import axl.parser.ast.Ast;
import axl.parser.ast.AstGetLocalVar;
import reloc.org.objectweb.asm.MethodVisitor;

import static reloc.org.objectweb.asm.Opcodes.*;
import static reloc.org.objectweb.asm.Opcodes.I2L;

public class AstMath extends Ast {
    public Ast right;
    public Ast left;

    @Override
    public boolean is_math() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        boolean left_is_long = false;
        if(left instanceof AstGetLocalVar)
            left_is_long = ((AstGetLocalVar) left).is_long();
        else if (left instanceof Value)
            left_is_long = ((Value) left).is_long();
        else if (left instanceof AstMath)
            left_is_long = ((AstMath) left).is_long();

        boolean right_is_long = false;
        if(right instanceof AstGetLocalVar)
            right_is_long = ((AstGetLocalVar) right).is_long();
        else if (right instanceof Value)
            right_is_long = ((Value) right).is_long();
        else if (right instanceof AstMath)
            right_is_long = ((AstMath) right).is_long();

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
            else LOGGER.log("[CODE-GEN] недопустимый (left)");
        } else if (left instanceof AstGetLocalVar) {
            left.codegen(mv);
        }

        if(right_is_long && !left_is_long)
            mv.visitInsn(I2L);

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
            else if(((Value) right).is_long()) {
                mv.visitLdcInsn(((Value) right).getLong());
                mv.visitInsn(I2L);
            }
            else LOGGER.log("[CODE-GEN] недопустимый тип (right)");
        } else if (right instanceof AstGetLocalVar) {
            right.codegen(mv);
        }

        if(left_is_long && !right_is_long)
            mv.visitInsn(I2L);
    }

    public boolean is_long()
    {
        boolean left_is_long = false;
        if(left instanceof AstGetLocalVar)
            left_is_long = ((AstGetLocalVar) left).is_long();
        else if (left instanceof Value)
            left_is_long = ((Value) left).is_long();
        else if (left instanceof AstMath)
            left_is_long = ((AstMath) left).is_long();

        boolean right_is_long = false;
        if(right instanceof AstGetLocalVar)
            right_is_long = ((AstGetLocalVar) right).is_long();
        else if (right instanceof Value)
            right_is_long = ((Value) right).is_long();
        else if (right instanceof AstMath)
            right_is_long = ((AstMath) right).is_long();

        return left_is_long || right_is_long;
    }
}
