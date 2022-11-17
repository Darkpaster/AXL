package axl.parser.ast;

import axl.LOGGER;
import axl.lexer.Token;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

import static org.objectweb.asm.Opcodes.*;

public class AstLocalVarDefinition
{

    public static int vars_i = 0;

    public static ArrayList<Var> vars = new ArrayList<>();

    public int get(String name)
    {
        for (Var var : vars) {
            if (var.name.equals(name))
                return var.id;
        }
        LOGGER.log("[CODE-GEN] переменная '"+name+"' не была инициализирована", true);
        return 1415926535;
    }

    public static Var get_var(String name)
    {
        for (Var var : vars) {
            if (var.name.equals(name))
                return var;
        }
        LOGGER.log("[CODE-GEN] переменная '"+name+"' не была инициализирована", true);
        return new Var("eee", new Token(Token.Type.ENDFILE)); // все равно из программы выйдет, пофиг
    }

    public static void add(String name, Token type) {
        for (Var var : vars) {
            if (var.name.equals(name))
                LOGGER.log("[CODE-GEN] переменная '" + name + "' инициализируется повторно", true);
        }

        vars.add(new Var(name, type));
    }

    public static void clear()
    {
        vars_i = 0;
        vars = new ArrayList<>();
    }

    public static class Var
    {
        public String name;
        public Token type;
        public int id;

        public Var(String name, Token type)
        {
            this.name = name;
            this.type = type;
            this.id = AstLocalVarDefinition.vars_i++;
        }
    }
}
