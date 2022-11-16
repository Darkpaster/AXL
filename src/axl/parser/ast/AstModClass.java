package axl.parser.ast;

import static org.objectweb.asm.Opcodes.*;

public class AstModClass implements Ast{
    public boolean is_public = false;
    public boolean is_abstract = false;
    public boolean is_interface = false;
    public boolean is_final = false;

    public int get() {
        int mod = 0;

        if(is_public)
            mod += ACC_PUBLIC;
        if(is_abstract)
            mod += ACC_ABSTRACT;
        if(is_interface)
            mod += ACC_INTERFACE;
        if(is_final)
            mod += ACC_FINAL;

        return mod;
    }
}
