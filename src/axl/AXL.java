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

import axl.general.Ast;
import axl.general.LocalVars.AstLocalVarDefinit;
import axl.general.LocalVars.AstSetLocalVar;
import axl.general.Methods.AstMethodDefinit;
import axl.general.Values.ValueInt;
import axl.general.imports.Imports;
import axl.lib.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.objectweb.asm.Opcodes.*;

public class AXL {

    public static final int CLASSES_VERSION = 49;
    public static final boolean compile_local_var_ref = true;
    public static final boolean SAVE_LOG = false;

    public static String type_main_class;

    public void test_method() {
        int test1 = 23;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        boolean is_compile = true;

        Imports.import_mul("java.lang");
        System.out.println(Imports.getClass("String").getName());

        Field field = Imports.getClass("System").getDeclaredField("out");
        Method method = field.getType().getMethod("println", String.class);
        System.out.println(field);
        System.out.println(method.getDeclaringClass().getSuperclass());

        if(is_compile)
        {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
            MethodVisitor mv;

            cw.visit(CLASSES_VERSION, ACC_PUBLIC , "Test", "java/lang/Object", null);

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

            {
                ArrayList<Ast> body = new ArrayList<>();
                body.add(new AstLocalVarDefinit("test", "I"));
                body.add(new AstSetLocalVar("test", new ValueInt((byte) 23)));
                AstMethodDefinit method_ast = new AstMethodDefinit("test_method", body, ACC_PUBLIC, "()V");
                method_ast.codegen(cw);
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