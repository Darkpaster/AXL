package axl.general.Math;

import axl.general.Ast;

public class AstUnary extends AstMath {
    protected Ast current;

    public AstUnary(Ast current)
    {
        this.current = current;
    }

    @Override
    public String get_type_jvm() {
        return current.get_type_jvm();
    }
}
