package axl.parser.ast;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ACC_SYNCHRONIZED;

public class AstModVar {
    public byte access = 0;
    /*
    0 - default
    1 - public
    2 - private
    3 - protected
     */
    public boolean is_static = false;
    public boolean is_final = false;

    public int get() {
        int mod = 0;

        if(access != 0)
            switch (access)
            {
                case 1 -> mod += ACC_PUBLIC;
                case 2 -> mod += ACC_PRIVATE;
                case 3 -> mod += ACC_PROTECTED;
            }
        if(is_static)
            mod += ACC_STATIC;
        if(is_final)
            mod += ACC_FINAL;

        return mod;
    }
}
