package io.vproxy.javaagent.sample;

import io.vproxy.javaagent.AbstractMethodTransformer;
import jdk.internal.org.objectweb.asm.tree.*;
import jdk.internal.org.objectweb.asm.Opcodes;

public class BigIntegerTransformer extends AbstractMethodTransformer {
    public BigIntegerTransformer() {
        super("java/math/BigInteger", "<init>", "(Ljava/lang/String;)V");
    }

    @Override
    protected boolean transform(ClassNode node, String classname, MethodNode m) {
        var insnList = new InsnList();
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 1));
        insnList.add(new LdcInsnNode("000"));
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String",
            "concat", "(Ljava/lang/String;)Ljava/lang/String;"));
        insnList.add(new VarInsnNode(Opcodes.ASTORE, 1));
        m.instructions.insert(insnList);
        return true;
    }
}
