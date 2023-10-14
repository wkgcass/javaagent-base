# javaagent-base

## Dependency

```xml
<dependency>
  <groupId>io.vproxy</groupId>
  <artifactId>javaagent-base</artifactId>
  <version>1.0.0</version>
</dependency>
```

```groovy
implementation 'io.vproxy:javaagent-base:1.0.0'
```

## How to use

Please refer to [the sample project](https://github.com/wkgcass/javaagent-base/tree/master/sample)
and [modify-gradle-compiler-args](https://github.com/vproxy-tools/modify-gradle-compiler-args) project.

> Notes:  
> You must `--add-exports` for both **your module** and `io.vproxy.javaagent` when compiling.  
> You should use `shadowJar` to bundle all classes into one fat jar and exclude `module-info.class` from the fat jar.

## Sample

```shell
./gradlew clean shadowJar
jshell -R-javaagent:./sample/build/libs/biginteger-agent.jar
```

in the jshell:

```
jsehll> var a = new BigInteger("1")
a ==> 1000
```

## Additional JVM Options

Add the following JVM options for the javaagent to work:

```shell
--add-opens java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED \
--add-opens java.base/jdk.internal.org.objectweb.asm.tree=ALL-UNNAMED
```
