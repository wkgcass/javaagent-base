package io.vproxy.javaagent.sample;

import java.lang.instrument.Instrumentation;

public class Premain {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new BigIntegerTransformer());
    }
}
