package io.vproxy.javaagent;

import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractClassTransformer extends AbstractTransformer {
    private final Set<String> classNames = new HashSet<>();

    protected AbstractClassTransformer(String className) {
        this.classNames.add(className);
    }

    protected AbstractClassTransformer(Collection<String> className) {
        this.classNames.addAll(className);
    }

    @Override
    protected final boolean transform(ClassNode node) {
        if (classNames.contains(node.name)) {
            return transform(node, node.name);
        }
        return false;
    }

    abstract protected boolean transform(ClassNode node, String classname);
}
