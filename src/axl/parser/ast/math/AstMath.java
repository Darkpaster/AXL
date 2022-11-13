package axl.parser.ast.math;

import axl.parser.ast.Ast;
import reloc.org.objectweb.asm.MethodVisitor;

public class AstMath extends Ast {
    public Ast right;
    public Ast left;

    @Override
    public boolean is_math() {
        return true;
    }
}
