package axl.general;

import axl.LOGGER;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public abstract class Ast {
    public abstract void codegen(MethodVisitor mv);

    public abstract void codegen(ClassWriter mv);

    public String get_type_jvm()
    {
        LOGGER.log("[CODE-GEN] получение типа у неверного класса.", true);
        return null;
    }
}
