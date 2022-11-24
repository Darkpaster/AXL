package axl.general.Class;

import axl.general.Ast;
import org.objectweb.asm.ClassWriter;

import java.util.ArrayList;

import static axl.AXL.CLASSES_VERSION;

public class AstClassDefinit extends Ast {

    public String name;
    public ArrayList<Ast> body;
    public int mod;
    public String super_name = "java/lang/Object";
    public ArrayList<String> interfaces = new ArrayList<>();

    public AstClassDefinit(String name, ArrayList<Ast> body, int mod)
    {
        this.name = name;
        this.body = body;
        this.mod = mod;
    }

    @Override
    public void codegen() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);

        cw.visit(
                CLASSES_VERSION,
                mod,
                name,
                null,
                super_name,
                get_interfaces()
        );

        cw.visitSource("Test.java", null);
    }

    public String[] get_interfaces()
    {
        String[] interfaces = new String[this.interfaces.size()];

        for(int i = 0; i < this.interfaces.size(); i++)
            interfaces[i] = this.interfaces.get(i);

        return interfaces;
    }
}
