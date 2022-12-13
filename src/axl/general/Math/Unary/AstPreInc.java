package axl.general.Math.Unary;

import axl.LOGGER;
import axl.general.Ast;
import axl.general.LocalVars.AstGetLocalVar;
import axl.general.Math.AstUnary;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstPreInc extends AstUnary{ // --i ++i
    public byte inc = 0;

    public AstPreInc(Ast current)
    {
        super(current);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        if(!(current instanceof AstGetLocalVar)) LOGGER.log("[CODE-GEN] Инкрементация без переменной", true);

        if(!current.get_type_jvm().equals("I"))
            current.codegen(mv); // кидаем в стек значение переменной если она не integer. (для int нормальный инкремент есть)

        if(current.get_type_jvm().equals("B"))
        {
            mv.visitInsn(inc == 1 ? ICONST_1 : ICONST_M1); // кидем в стек число -1 или 1
            mv.visitInsn(IADD);           // складываем 2 значения на вершине стека
            mv.visitInsn(I2B);            // преобразовываем число на вершине стека в байт
            mv.visitVarInsn(ISTORE, ((AstGetLocalVar)current).var.id);
        }
        else if(current.get_type_jvm().equals("C"))
        {
            mv.visitInsn(inc == 1 ? ICONST_1 : ICONST_M1); // кидем в стек число -1 или 1
            mv.visitInsn(IADD);           // складываем 2 значения на вершине стека
            mv.visitInsn(I2C);            // преобразовываем число на вершине стека в символ
            mv.visitVarInsn(ISTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else if(current.get_type_jvm().equals("S"))
        {
            mv.visitInsn(inc == 1 ? ICONST_1 : ICONST_M1); // кидем в стек число -1 или 1
            mv.visitInsn(IADD);                            // складываем 2 значения на вершине стека
            mv.visitInsn(I2S);                             // преобразовываем число на вершине стека в short
            mv.visitVarInsn(ISTORE, ((AstGetLocalVar)current).var.id);
        }
        else if(current.get_type_jvm().equals("I"))
            mv.visitIincInsn(((AstGetLocalVar)current).var.id, inc); // самый обычный инкремент :)
        else if(current.get_type_jvm().equals("J"))
        {
            if(inc == 1) // кидем в стек число -1 или 1
                mv.visitInsn(LCONST_1);
            else
                mv.visitLdcInsn((long)inc);
            mv.visitInsn(LADD);                                        // складываем 2 значения на вершине стека
            mv.visitVarInsn(LSTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else if(current.get_type_jvm().equals("F"))
        {
            if(inc == 1) // кидем в стек число -1 или 1
                mv.visitInsn(FCONST_1);
            else
                mv.visitLdcInsn((float)inc);
            mv.visitInsn(FADD);                                        // складываем 2 значения на вершине стека
            mv.visitVarInsn(FSTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else if(current.get_type_jvm().equals("D"))
        {
            if(inc == 1) // кидем в стек число -1 или 1
                mv.visitInsn(DCONST_1);
            else
                mv.visitLdcInsn((double)inc);
            mv.visitInsn(DADD);                                        // складываем 2 значения на вершине стека
            mv.visitVarInsn(DSTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else LOGGER.log("[CODE-GEN] Инкрементация объекта", true);

        current.codegen(mv); // кидаем в стек значение переменной (префикс же)
    }

    @Override
    public String get_type_jvm() { // Обязательно (нужно в будущем)
        return current.get_type_jvm();
    }
}
