package axl.general.Math;

import axl.general.Ast;
import axl.general.Values.Value;

public class AstBinary extends AstMath {
    private Ast left;
    private Ast right;

    public AstBinary(Ast left, Ast right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public String get_type_jvm() {
        return Value.priority_type_jvm(left.get_type_jvm(), right.get_type_jvm());
    }
}
