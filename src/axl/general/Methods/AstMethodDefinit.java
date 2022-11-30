package axl.general.Methods;

import axl.AXL;
import axl.general.Ast;
import axl.general.LocalVars.AstGetLocalVar;
import axl.general.LocalVars.AstLocalVarDefinit;
import axl.general.LocalVars.AstSetLocalVar;
import axl.general.LocalVars.VarCounter;
import axl.lib.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.RETURN;

public class AstMethodDefinit extends Ast {

    public String name;
    public ArrayList<Ast> body;
    public int mod;
    public String descriptor; // (Ljava.lang.String;I)V
    public ArrayList<String> exceptions = new ArrayList<>();

    public AstMethodDefinit(String name, ArrayList<Ast> body, int mod, String descriptor)
    {
        this.name = name;
        this.body = body;
        this.mod = mod;
        this.descriptor = descriptor;
    }

    @Override
    public void codegen(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(mod, name, descriptor, null, get_exceptions());
        VarCounter vc = new VarCounter();

        int line = 0;
        int current_line = 0;

        Label start_method = new Label();
        Label end_method = new Label();
        mv.visitLabel(start_method);

        if((mod&ACC_STATIC) != ACC_STATIC) {
            vc.add(new VarCounter.Var("this", cw.get_type_jvm()));
            VarCounter.Var var = vc.get("this");
            var.start = start_method;
            var.end = end_method;
        }

        Label last_label = start_method;
        for(Ast ast: body)
        {
            if(AXL.compile_line_counter)
                if(ast.line != 0)
                    current_line = ast.line;

            if(ast instanceof AstSetLocalVar)
                ((AstSetLocalVar) ast).var = vc.get(((AstSetLocalVar) ast).name);
            else if(ast instanceof AstGetLocalVar)
                ((AstGetLocalVar) ast).var = vc.get(((AstGetLocalVar) ast).name);

            Label label = new Label();
            if(ast instanceof AstLocalVarDefinit)
            {
                vc.add(new VarCounter.Var(((AstLocalVarDefinit) ast).name, ((AstLocalVarDefinit) ast).type));
                if(AXL.compile_local_var_ref) {
                    VarCounter.Var var = vc.get(((AstLocalVarDefinit) ast).name);
                    var.start = last_label;
                    var.end = label;
                    line = current_line;
                }
            }

            if(AXL.compile_local_var_ref)
                if(ast instanceof AstGetLocalVar)
                    ((AstGetLocalVar)ast).var.end = label;
                else if(ast instanceof AstSetLocalVar)
                    ((AstSetLocalVar)ast).var.end = label;
            if(AXL.compile_line_counter)
                if(line != current_line) {
                    mv.visitLineNumber(current_line, last_label);
                    line = current_line;
                }
            ast.codegen(mv);

            last_label = label;
            mv.visitLabel(label);
        }

        if(descriptor.charAt(descriptor.length()-1) == 'V')
            mv.visitInsn(RETURN);

        mv.visitLabel(end_method);

        if(!AXL.compile_local_var_ref)
            return;

        for (VarCounter.Var var: vc.vars)
            var.gen_ref(mv);
    }

    public String[] get_exceptions()
    {
        String[] exceptions = new String[this.exceptions.size()];

        for(int i = 0; i < this.exceptions.size(); i++)
            exceptions[i] = this.exceptions.get(i);

        return exceptions;
    }
}
