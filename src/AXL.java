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
import axl.general.*;
import axl.lexer.Lexer;
import axl.lexer.Token;
import axl.parser.Parser;
import axl.parser.ast.*;
import axl.parser.ast.math.AstMath;
import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static axl.lexer.Token.Type.ENDFILE;
import static axl.lexer.Token.Type.INT;
import static org.objectweb.asm.Opcodes.*;

public class AXL {
    public static String file;

    public static void main(String[] args) throws IOException, NoSuchMethodException, ClassNotFoundException, NoSuchFieldException {
        Lexer lexer = new Lexer("-1+-1");
        Parser parser = new Parser(lexer.getTokens());
        ArrayList<Token.Type> end = new ArrayList<>();
        end.add(ENDFILE);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(49, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);

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
//
            Ast math = parser.parse_expr(end);
            math.codegen(mv);
//            mv.visitLdcInsn("Hello");

            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "("+((AstMath)math).get_type_jvm()+")V");
            mv.visitMaxs(1, 1);
            mv.visitInsn(RETURN);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        try (FileOutputStream stream = new FileOutputStream("C:\\Users\\home\\Documents\\GitHub\\AXL\\out\\Test.class")) {
            stream.write(bytes);
        }
    }



//            AstRem math = new AstRem(
//                    new AstDiv(
//                            new ValueByte((byte) 100),
//                            new AstMul(
//                                    new ValueInt((byte) 2),
//                                    new ValueShort((byte) 5)
//                            )
//                    ),
//                    new AstPow(
//                            new AstSub(
//                                    new ValueDouble(100.0d),
//                                    new AstSum(
//                                            new ValueInt((byte) 5),
//                                            new ValueLong(5L)
//                                    )
//                            ),
//                            new ValueFloat(2f)
//                    )
//            ); // (100/(2*5))%((100-(5+5))**(2)) = 10
//            AstNot math = new AstNot(new ValueBoolean(false));

//            math.codegen(mv);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "("+math.get_type_jvm()+")V");

//        file = Files.readString(Paths.get("C:\\Users\\home\\Documents\\GitHub\\AXL\\src\\Test.axl"));
//
//        Lexer lexer = new Lexer(file);
//        LOGGER.save();

//        {
//            String i = "\0";
//            System.out.println(Arrays.equals(new byte[1], i.getBytes()));

//            Class<?> c = Class.forName("java.lang.System");
//            Field f = c.getField("out");
//            System.out.println(f);
//
//            Class<?> cc = Class.forName(f.getGenericType().getTypeName());
//
//            ArrayList<Class<?>> params = new ArrayList<>();
//            params.add(
//                    Class.forName("java.lang.String")
//            );
//
//            Method m = cc.getMethod("println", getParams(params));
//            System.out.println(m.getAnnotatedReturnType().getType().getTypeName());
//        }
//    }
//
//    public static Class<?>[] getParams(ArrayList<Class<?>> classes) {
//        Class<?>[] params = new Class<?>[classes.size()];
//        for (int i = 0; i < classes.size(); i++) {
//            params[i] = classes.get(i);
//        }
//        return params;
//    }
}
