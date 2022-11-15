package axl.parser.ast.math;

import axl.general.Value;
import axl.parser.ast.Ast;
import axl.parser.ast.AstGetLocalVar;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstMath implements Ast {
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
    public void codegen(MethodVisitor mv) {
        boolean left_is_long = left_is_long();
        boolean right_is_long = right_is_long();
        boolean left_is_double = left_is_double();
        boolean right_is_double = right_is_double();

        left.codegen(mv);

        if (right_is_double && (!left_is_double)) {
            if(left_is_long)
                mv.visitInsn(L2D);
            else if (left_is_float())
                mv.visitInsn(F2D);
            else if(!left_is_float())
                mv.visitInsn(I2D);
        }
        else if(right_is_float() && (!left_is_float())) {
            if(left_is_double)
                mv.visitInsn(D2F);
            else if (left_is_long)
                mv.visitInsn(L2F);
            else if(!left_is_long())
                mv.visitInsn(I2F);
        }
        else if(right_is_long && (!left_is_long) && (!left_is_float())) {
            if (left_is_float())
                mv.visitInsn(F2L);
            else if(!left_is_float() && !left_is_double)
                mv.visitInsn(I2L);
        }

        right.codegen(mv);


        if (left_is_double && (!right_is_double)) {
            if(right_is_long)
                mv.visitInsn(L2D);
            else if (right_is_float())
                mv.visitInsn(F2D);
            else if(!right_is_float())
                mv.visitInsn(I2D);
        }
        else if(left_is_float() && (!right_is_float())) {
            if(right_is_double)
                mv.visitInsn(D2F);
            else if (right_is_long)
                mv.visitInsn(L2F);
            else if(!right_is_long())
                mv.visitInsn(I2F);
        }
        else if(left_is_long && (!right_is_long) && (!right_is_float())) {
            if (right_is_float())
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

    public boolean is_bool()
    {
        return left_is_bool() || right_is_bool();
    }

    public boolean left_is_bool()
    {
        boolean left_is_bool = false;
        if(left instanceof AstGetLocalVar)
            left_is_bool = ((AstGetLocalVar) left).is_bool();
        else if (left instanceof Value)
            left_is_bool = ((Value) left).is_boolean();
        else if (left instanceof AstMath)
            left_is_bool = ((AstMath) left).is_bool();
        return left_is_bool;
    }

    public boolean right_is_bool()
    {
        boolean right_is_bool = false;
        if(right instanceof AstGetLocalVar)
            right_is_bool = ((AstGetLocalVar) right).is_bool();
        else if (right instanceof Value)
            right_is_bool = ((Value) right).is_boolean();
        else if (right instanceof AstMath)
            right_is_bool = ((AstMath) right).is_bool();
        return right_is_bool;
    }

    public char get_type_jvm()
    {
        if(is_double()) return 'D';
        if(is_float())  return 'F';
        if(is_long())   return 'J';
        if(is_bool())   return 'Z';
        return 'I';
    }
}
