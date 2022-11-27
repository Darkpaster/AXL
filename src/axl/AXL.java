/*
Kesha: 
Empty dream: by шиза
+[------->++<]>--.[-->+<]>--.>-[--->+<]>-.>-[----->+<]>+.+++[->++<]>+.-[-->+<]>---.[--->++<]>.[++>---<]>.[----->+
+<]>--.>-[----->+<]>+.[----->++<]>.[++>-----<]>.+++[->++<]>.[-->+<]>---.[--->++<]>.[++>---<]>.++++[->++<]>.[->+++
++<]>+.+++++.[-->+++<]>++.>-[----->+<]>--.+++++.[-->+++<]>.>-[----->+<]>--.+++++.--[-->+++<]>-.-[->++++<]>+.+++++
.[->++<]>.[-->+<]>-----.+++++.[--->++++<]>-.+[--->++<]>+.+++++.---[->++<]>-.-[-->+<]>-.+++++.[--->+++++<]>.[-->+<
]>++++.+++++++.+[--->++++<]>+.-[->++++<]>+.+++++++.+[->++<]>+.+++[++>---<]>.+++++++.[->++<]>+.-[->+++++<]>+.+++++
++.-[->++++++<]>+.[--->++<]>-.+++++++.-[->++++++<]>.+[--->++<]>-.+++++++.++[->++<]>.++[++>---<]>+.--.+++++[->++<]
>.[-->+<]>---.--.-[--->+<]>+.[->+++<]>.--.[----->+<]>++.>-[----->+<]>-.-.----[->++<]>-.>-[----->+<]>-.--.++[-->++
+<]>+.-[--->++<]>.++.++[-->+++<]>+.>-[----->+<]>-.++.---[->++<]>-.+[-->+<]>+.++.---[->++<]>.[-->+<]>+.+++.+++++++
++++++.>-[----->+<]>.+++++.[->++<]>.>-[----->+<]>.++++++.[--->++++<]>.-[--->++<]>+.+++++.+++++++++++++++.+[++>---
--<]>+.----.-[-->+++<]>+.-[++>-----<]>+.-----.+[--->++<]>+.+[++>---<]>-..+[->++<]>+.-[->+++<]>-.--.+[->++++++<]>.
[------>+<]>-.-----.[----->+<]>.[-->+<]>---..>+[--->++<]>++.-[--->++<]>--.++[-->+++<]>++.>-[----->+<]>++.-----.>+
[--->++<]>.>-[----->+<]>++.-----.-[--->+<]>++.+[->+++<]>.--[->++<]>-.-[-->+<]>---.+++.>+[--->++<]>+.>-[----->+<]>
-.+++.++[->++<]>+.-[-->+<]>--.-----.++[-->+++<]>++.[->++++<]>--.----[-->+++<]>.[--->++<]>++.[->++<]>+.+[-->+<]>.+
.-[->++<]>-.--[----->+++<]>.+++[->++<]>+.-[-->+<]>---.+[->++<]>.[-->+<]>-----.++++.+[-->+++<]>+.[-->+<]>+++++.-.-
-[--->++<]>-.+++[++>---<]>.-.>+[--->++<]>.[-->+<]>++++++.-.[--->++++<]>+.-[---->+++<]>+.-.[-->+++++<]>+.---[++>--
-<]>.-.[----->+<]>+.[->+++++<]>.-[-->+++<]>--.-[--->++<]>.--.>-[--->+<]>.>-[----->+<]>-.++.[->++<]>.[-->+<]>--..+
[--->++++<]>--.>-[----->+<]>-.--.++[-->+++<]>++.-[->++++<]>+.>-[--->+<]>----.[--->++<]>+++.-[->++<]>-.-[-->+<]>--
.+[--->+++++<]>.[----->+++<]>-.-[--->++<]>+.-[++>---<]>+.[->++<]>+.-[-->+<]>.[->++<]>.[-->+<]>.---[->++<]>-.+[-->
+<]>+++.---[-->+++<]>-.[++>-----<]>.-[->++++++<]>-.-[--->++<]>+.
*/
package axl;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class AXL {

    public static final int CLASSES_VERSION = 49;
    public static final boolean compile_local_var_ref = true;

    public int test(String a, int b)
    {
        System.out.println(a);
        System.out.println(b);
        System.out.println(this);
        int c = 2;
        return c;
    }

    public static void main(String[] args) {

        boolean is_compile = true;

        if(is_compile)
        {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
            MethodVisitor mv;

            cw.visit(CLASSES_VERSION, ACC_PUBLIC , "Test", null, "java/lang/Object", null);

            cw.visitSource("Test.java", null);

            {
                mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
                mv.visitInsn(RETURN);
                mv.visitMaxs(1, 1);
                mv.visitEnd();
            }

            {
                mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Hello AXL!");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
                mv.visitMaxs(1, 1);
                mv.visitInsn(RETURN);
                mv.visitEnd();
            }
            cw.visitEnd();

            byte[] bytes = cw.toByteArray();
            try (FileOutputStream stream = new FileOutputStream("./out/Test.class")) {
                stream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}