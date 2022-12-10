package axl.general.Math.Unary;

import axl.LOGGER;
import axl.general.Ast;
import axl.general.LocalVars.AstGetLocalVar;
import axl.general.Math.AstUnary;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AstPostInc extends AstUnary{ // --i ++i
    public byte inc = 0;

    public AstPostInc(Ast current)
    {
        super(current);
    }

    @Override
    public void codegen(MethodVisitor mv) {
        if(!(current instanceof AstGetLocalVar)) LOGGER.log("[CODE-GEN] Инкрементация без переменной", true);

        current.codegen(mv); // кидаем в стек значение переменной

        if(current.get_type_jvm().equals("B"))
        {
            current.codegen(mv); // дублируем значение
            mv.visitInsn(inc == 1 ? ICONST_1 : ICONST_M1); // кидем в стек число -1 или 1
            mv.visitInsn(IADD);           // складываем 2 значения на вершине стека
            mv.visitInsn(I2B);            // преобразовываем число на вершине стека в байт
            mv.visitVarInsn(ISTORE, ((AstGetLocalVar)current).var.id);
        }
        else if(current.get_type_jvm().equals("C"))
        {
            current.codegen(mv); // дублируем значение
            mv.visitInsn(inc == 1 ? ICONST_1 : ICONST_M1); // кидем в стек число -1 или 1
            mv.visitInsn(IADD);           // складываем 2 значения на вершине стека
            mv.visitInsn(I2C);            // преобразовываем число на вершине стека в символ
            mv.visitVarInsn(ISTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else if(current.get_type_jvm().equals("S"))
        {
            current.codegen(mv); // дублируем значение
            mv.visitInsn(inc == 1 ? ICONST_1 : ICONST_M1); // кидем в стек число -1 или 1
            mv.visitInsn(IADD);                            // складываем 2 значения на вершине стека
            mv.visitInsn(I2S);                             // преобразовываем число на вершине стека в short
            mv.visitVarInsn(ISTORE, ((AstGetLocalVar)current).var.id);
        }
        else if(current.get_type_jvm().equals("I"))
            mv.visitIincInsn(((AstGetLocalVar)current).var.id, inc); // самый обычный инкремент :)
        else if(current.get_type_jvm().equals("J"))
        {
            mv.visitInsn(DUP2); // дублируем значение (DUP2 для 64 битных значений)
            if(inc == 1) // кидем в стек число -1 или 1
                mv.visitInsn(LCONST_1);
            else
                mv.visitLdcInsn((long)inc);
            mv.visitInsn(LADD);                                        // складываем 2 значения на вершине стека
            mv.visitVarInsn(LSTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else if(current.get_type_jvm().equals("F"))
        {
            mv.visitInsn(DUP); // дублируем значение (DUP для 32 битных значений)
            if(inc == 1) // кидем в стек число -1 или 1
                mv.visitInsn(FCONST_1);
            else
                mv.visitLdcInsn((float)inc);
            mv.visitInsn(FADD);                                        // складываем 2 значения на вершине стека
            mv.visitVarInsn(FSTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else if(current.get_type_jvm().equals("D"))
        {
            mv.visitInsn(DUP2); // дублируем значение (DUP2 для 64 битных значений)
            if(inc == 1) // кидем в стек число -1 или 1
                mv.visitInsn(DCONST_1);
            else
                mv.visitLdcInsn((double)inc);
            mv.visitInsn(DADD);                                        // складываем 2 значения на вершине стека
            mv.visitVarInsn(DSTORE, ((AstGetLocalVar)current).var.id); // сохраняем значение на вершине стека в переменную
        }
        else LOGGER.log("[CODE-GEN] Инкрементация объекта", true);
    }

    @Override
    public String get_type_jvm() {
        return current.get_type_jvm();
    }
}
