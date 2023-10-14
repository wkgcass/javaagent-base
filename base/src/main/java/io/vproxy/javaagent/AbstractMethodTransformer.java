package io.vproxy.javaagent;

import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractMethodTransformer extends AbstractClassTransformer {
    private final Set<MethodSignature> methods = new HashSet<>();

    public AbstractMethodTransformer(String className, String methodName, String methodDesc) {
        super(className);
        methods.add(new MethodSignature(methodName, methodDesc));
    }

    public AbstractMethodTransformer(String className, Collection<MethodSignature> methods) {
        super(className);
        this.methods.addAll(methods);
    }

    protected final boolean transform(ClassNode node, String classname) {
        for (var m : node.methods) {
            if (methods.contains(new MethodSignature(m.name, m.desc))) {
                return transform(node, classname, m);
            }
        }
        return false;
    }

    abstract protected boolean transform(ClassNode node, String classname, MethodNode m);

    public static final class MethodSignature {
        public final String name;
        public final String desc;

        public MethodSignature(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MethodSignature that = (MethodSignature) o;

            if (!Objects.equals(name, that.name)) return false;
            return Objects.equals(desc, that.desc);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (desc != null ? desc.hashCode() : 0);
            return result;
        }
    }
}
