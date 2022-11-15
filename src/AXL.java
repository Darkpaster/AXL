import axl.LOGGER;
import axl.general.*;
import axl.lexer.Lexer;
import axl.parser.ast.Ast;
import axl.parser.ast.math.*;
import axl.parser.ast.math.bool.AstAnd;
import axl.parser.ast.math.bool.AstNot;
import axl.parser.ast.math.bool.AstOr;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.objectweb.asm.Opcodes.*;

public class AXL {
    public static String file;

    public static void main(String[] args) throws IOException {

//        boolean c = false;
//        boolean d = !c;
//
//
//
//        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//        MethodVisitor mv;
//
//        cw.visit(49, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);
//
//        cw.visitSource("Test.java", null);
//
//        {
//            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//            mv.visitVarInsn(ALOAD, 0);
//            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
//            mv.visitInsn(RETURN);
//            mv.visitMaxs(1,1);
//            mv.visitEnd();
//        }
//
//        {
//            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
//
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//
//            AstRem math = new AstRem(
//                    new AstDiv(
//                            new ValueByte((byte) 100),
//                            new AstMul(
//                                    new ValueByte((byte) 2),
//                                    new ValueByte((byte) 5)
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
//
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "("+math.get_type_jvm()+")V");
//            mv.visitMaxs(1,1);
//            mv.visitInsn(RETURN);
//            mv.visitEnd();
//        }
//        cw.visitEnd();
//
//        byte[] bytes = cw.toByteArray();
//        try (FileOutputStream stream = new FileOutputStream("C:\\Users\\home\\Documents\\GitHub\\AXL\\src\\Test.class")) {
//            stream.write(bytes);
//        }

        file = Files.readString(Paths.get("C:\\Users\\home\\Documents\\GitHub\\AXL\\src\\Test.axl"));

        Lexer lexer = new Lexer(file);
        LOGGER.save();

//        { yuyg
//            Class<?> c = Class.forName("java.lang.System");
//            Field f = c.getField("out");
//            System.out.println(f.getGenericType().getTypeName());
//
//            Class<?> cc = Class.forName(f.getGenericType().getTypeName());
//
//            ArrayList<Class<?>> params = new ArrayList<>();
//            params.add(String.class);
//
//            Method m = cc.getMethod("print", getParams(params));
//            System.out.println(m.getAnnotatedReturnType().getType().getTypeName());
//        }
    }

//    public static Class<?>[] getParams(ArrayList<Class<?>> classes) {
//        Class<?>[] params = new Class<?>[classes.size()];
//        for (int i = 0; i < classes.size(); i++) {
//            params[i] = classes.get(i);
//        }
//        return params;
//    }
}