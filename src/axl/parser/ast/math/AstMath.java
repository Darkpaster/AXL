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

    public AstMath(){
        right = null;
        left = null;
    }

    public AstMath(Ast left, Ast right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean is_math() {
        return true;
    }

    @Override
    public void codegen(MethodVisitor mv) {
        boolean left_is_long = left_is_long();
        boolean right_is_long = right_is_long();
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

        if (right_is_double && (!left_is_double)) {
            if(left_is_long)
                mv.visitInsn(L2D);
            else if (left_is_float() && !(left instanceof Value))
                mv.visitInsn(F2D);
            else if(!left_is_float())
                mv.visitInsn(I2D);
        }
        else if(right_is_long && (!left_is_long)) {
            if(left_is_double && !(left instanceof Value))
                mv.visitInsn(D2L);
            else if (left_is_float() && !(left instanceof Value))
                mv.visitInsn(F2L);
            else if(!left_is_float() && !left_is_double)
                mv.visitInsn(I2L);
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


        if (left_is_double && (!right_is_double)) {
            if(right_is_long)
                mv.visitInsn(L2D);
            else if (right_is_float() && !(right instanceof Value))
                mv.visitInsn(F2D);
            else if(!right_is_float())
                mv.visitInsn(I2D);
        }
        else if(left_is_long && (!right_is_long)) {
            if(right_is_double && !(right instanceof Value))
                mv.visitInsn(D2L);
            else if (right_is_float() && !(right instanceof Value))
                mv.visitInsn(F2L);
            else if(!right_is_float() && !right_is_double)
                mv.visitInsn(I2L);
        }
    }

    public boolean is_long()
    {

        return left_is_long() || right_is_long();
    }

    public boolean left_is_long()
    {
        boolean left_is_long = false;
        if(left instanceof AstGetLocalVar)
            left_is_long = ((AstGetLocalVar) left).is_long();
        else if (left instanceof Value)
            left_is_long = ((Value) left).is_long();
        else if (left instanceof AstMath)
            left_is_long = ((AstMath) left).is_long();
        return left_is_long;
    }

    public boolean right_is_long()
    {
        boolean right_is_long = false;
        if(right instanceof AstGetLocalVar)
            right_is_long = ((AstGetLocalVar) right).is_long();
        else if (right instanceof Value)
            right_is_long = ((Value) right).is_long();
        else if (right instanceof AstMath)
            right_is_long = ((AstMath) right).is_long();

        return right_is_long;
    }

    public boolean is_double()
    {
        return left_is_double() || right_is_double();
    }

    public boolean left_is_double()
    {
        boolean left_is_double = false;
        if(left instanceof AstGetLocalVar)
            left_is_double = ((AstGetLocalVar) left).is_double();
        else if (left instanceof Value)
            left_is_double = ((Value) left).is_double();
        else if (left instanceof AstMath)
            left_is_double = ((AstMath) left).is_double();
        return left_is_double;
    }

    public boolean right_is_double()
    {
        boolean right_is_double = false;
        if(right instanceof AstGetLocalVar)
            right_is_double = ((AstGetLocalVar) right).is_double();
        else if (right instanceof Value)
            right_is_double = ((Value) right).is_double();
        else if (right instanceof AstMath)
            right_is_double = ((AstMath) right).is_double();
        return right_is_double;
    }

    public boolean is_float()
    {
        return left_is_float() || right_is_float();
    }

    public boolean left_is_float()
    {
        boolean left_is_float = false;
        if(left instanceof AstGetLocalVar)
            left_is_float = ((AstGetLocalVar) left).is_float();
        else if (left instanceof Value)
            left_is_float = ((Value) left).is_float();
        else if (left instanceof AstMath)
            left_is_float = ((AstMath) left).is_float();
        return left_is_float;
    }

    public boolean right_is_float()
    {
        boolean right_is_float = false;
        if(right instanceof AstGetLocalVar)
            right_is_float = ((AstGetLocalVar) right).is_float();
        else if (right instanceof Value)
            right_is_float = ((Value) right).is_float();
        else if (right instanceof AstMath)
            right_is_float = ((AstMath) right).is_float();
        return right_is_float;
    }
}
