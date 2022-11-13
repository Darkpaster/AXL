package axl.parser.ast;

import reloc.org.objectweb.asm.ClassWriter;
import reloc.org.objectweb.asm.MethodVisitor;

public class Ast {
    public boolean is_class_definition()
    {
        return false;
    }

    public boolean is_var_definition()
    {
        return false;
    }

    public boolean is_get_local_var()
    {
        return false;
    }

    public boolean is_function_definition()
    {
        return false;
    }

    public boolean is_function_call()
    {
        return false;
    }

    public boolean is_sum()
    {
        return false;
    }

    public boolean is_sub()
    {
        return false;
    }

    public boolean is_math()
    {
        return false;
    }

    public boolean is_value()
    {
        return false;
    }

    public void codegen(MethodVisitor mv){}

    public void codegen(ClassWriter cw){}
}
