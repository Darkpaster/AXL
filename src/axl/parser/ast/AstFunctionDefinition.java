package axl.parser.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.Objects;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class AstFunctionDefinition implements Ast {

    public AstModMethod mods;
    public ArrayList<Ast> body;
    public String name;
    public String type;

    public  AstFunctionDefinition(String name, String type, AstModMethod mods, ArrayList<Ast> body)
    {
        this.name = name;
        this.type = type;
        this.mods = mods;
        this.body = body;
    }

    @Override
    public void codegen(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(mods.get(), name, type, null, null);

        for(Ast ast: body)
            ast.codegen(mv);


        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }
}
