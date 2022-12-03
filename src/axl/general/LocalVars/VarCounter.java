package axl.general.LocalVars;

import axl.LOGGER;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class VarCounter {
    public int id_count = 0;
    public ArrayList<Var> vars = new ArrayList<>();

    private VarCounter parent = null;

    public VarCounter(){}

    public VarCounter(VarCounter parent)
    {
        this.parent = parent;
        this.id_count = parent.id_count;
    }

    public void add(Var var)
    {
        for(Var current: vars)
            if(current.name.equals(var.name))
                LOGGER.log("[CODE-GEN] повторная инициализация переменной \""+var.name+'"', true);
        var.id = id_count++;
        vars.add(var);
    }

    public Var get(String name)
    {
        for(Var current: vars)
            if(current.name.equals(name))
                return current;

        if(parent != null)
            return parent.get(name);

        LOGGER.log("[CODE-GEN] переменная \""+name+"\" не инициализированна", true);
        return new Var("0", "0");
    }

    public static class Var
    {
        public String name;
        public String type;
        public int id = 141592;

        public Label start;
        public Label end;

        public Var(String name, String type)
        {
            this.name = name;
            this.type = type;
        }

        public int get_id()
        {
            if(id == 141592) LOGGER.log("[CODE-GEN] у переменной \""+name+"\" нет своего id", true);
            return id;
        }

        public void gen_ref(MethodVisitor mv)
        {
            mv.visitLocalVariable(name, type, null, start, end, id);
        }
    }
}
