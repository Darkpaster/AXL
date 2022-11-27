package axl.general.LocalVars;

import axl.general.Ast;

public class AstLocalVarDefinit extends Ast {
    public String name;
    public String type;

    public AstLocalVarDefinit(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
