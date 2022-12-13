package axl.lib;

public class ClassWriter extends org.objectweb.asm.ClassWriter {
    private String name;

    public ClassWriter(int flags) {
        super(flags);
    }

    public void visit(int version, int access, String name, String superName, String[] interfaces)
    {
        this.name = name;
        this.visit(version, access, name, null, superName, interfaces);
    }

    public String get_type_jvm()
    {
        return 'L'+this.name.replace('.', '/')+';';
    }
}
