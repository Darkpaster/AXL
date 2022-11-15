package axl.parser.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface Ast {
    default void codegen(MethodVisitor mv){}

    default void codegen(ClassWriter cw){}
}
