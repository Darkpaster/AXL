package axl.general;

import axl.LOGGER;
import axl.lib.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public abstract class Ast {
    public int line = 0;

    public void codegen(MethodVisitor mv){
        LOGGER.log("[CODE-GEN] вызов 'codegen(mv)' у неверного класса", true);
    }

    public void codegen(ClassWriter cw){
        LOGGER.log("[CODE-GEN] вызов 'codegen(cw)' у неверного класса", true);
    }

    public void codegen(){
        LOGGER.log("[CODE-GEN] вызов 'codegen()' у неверного класса", true);
    }

    public String get_type_jvm()
    {
        LOGGER.log("[CODE-GEN] получение типа у неверного класса.", true);
        return null;
    }
}
