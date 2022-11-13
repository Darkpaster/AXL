import axl.LOGGER;
import axl.lexer.Lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AXL {
    public static String file;

    public static void main(String[] args) throws IOException {

//        ClassWriter cw = new ClassWriter(0);
//        FieldVisitor fv;
//        MethodVisitor mv;
//        AnnotationVisitor av0;
//
//        cw.visit(49, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);
//
//        cw.visitSource("Test.java", null);
//
//        {
//            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//            mv.visitVarInsn(ALOAD, 0);
//            mv.visitMethodInsn(INVOKESPECIAL,
//                    "java/lang/Object",
//                    "<init>",
//                    "()V");
//            mv.visitInsn(RETURN);
//            mv.visitMaxs(1, 1);
//            mv.visitEnd();
//        }
//        {
//            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
//
//
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
////            mv.visitLdcInsn("hello");
//            AstSum math = new AstSum();
//            math.left = new ValueByte((byte) 21);
//            math.right = new ValueShort((short) 1233);
//            math.codegen(mv);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V");
//            mv.visitInsn(RETURN);
//            mv.visitMaxs(3, 1);
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