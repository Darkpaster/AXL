import axl.general.*;
import axl.lexer.Token;
import axl.parser.ast.AstGetLocalVar;
import axl.parser.ast.AstSetLocalVar;
import axl.parser.ast.AstLocalVarDefinition;
import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class AXL {
    public static String file;

    public static void main(String[] args) throws IOException {
            String a = "str";
            String b = "st";
            System.out.println(a);
            float b2 = 0;

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS+ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);

        cw.visitSource("Test.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);

            AstLocalVarDefinition.add("args", new Token(Token.Type.OBJECT, new ValueString("[Ljava/lang/String;")));

            String var_name = "var1";
            Token type = new Token(Token.Type.OBJECT, new ValueString("Ljava/lang/String;"));
            ValueString value = new ValueString("Hello world");
            AstLocalVarDefinition.add(var_name, type);
            AstSetLocalVar var1 = new AstSetLocalVar(
                    AstLocalVarDefinition.get_var(var_name).id,
                    AstLocalVarDefinition.get_var(var_name).type.getType(),
                    value
            );
            var1.codegen(mv);

            String var_name2 = "num";
            Token type2 = new Token(Token.Type.CHAR, new ValueChar('h'));
            AstLocalVarDefinition.add(var_name2, type2);
            AstSetLocalVar var2 = new AstSetLocalVar(
                    AstLocalVarDefinition.get_var(var_name2).id,
                    AstLocalVarDefinition.get_var(var_name2).type.getType(),
                    type2.getValue()
            );
            var2.codegen(mv);


            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

            AstGetLocalVar var_get = new AstGetLocalVar(
                    var_name2,
                    AstLocalVarDefinition.get_var(var_name2).type.getType()
            );

            var_get.codegen(mv);

            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "("+var_get.get_type_jvm()+")V");
            mv.visitMaxs(1, AstLocalVarDefinition.vars_i);
            mv.visitInsn(RETURN);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        try (FileOutputStream stream = new FileOutputStream("C:\\Users\\home\\Documents\\GitHub\\AXL\\out\\Test.class")) {
            stream.write(bytes);
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