# Sample grammar with matching parser

Simple project trying to build a Parser using zio-parser, but failing on a very simple structure.

Running the tests this error is reported:
```
  - Test Schema definition parsing
    Exception in thread "zio-fiber-108" java.lang.NoClassDefFoundError: Could not initialize class SchemaSyntax$
        at Schema$.parse(Main.scala:18)
        at SchemaSuite$.spec$$anonfun$3(SchemaSpec.scala:14)
        at zio.test.TestConstructor$.apply$$anonfun$1$$anonfun$1(TestConstructor.scala:22)
        at <empty>.SchemaSuite.spec(SchemaSpec.scala:14)
        Suppressed: java.lang.ExceptionInInitializerError: Exception java.lang.NullPointerException [in thread "ZScheduler-Worker-16"]
                at SchemaSyntax$.<clinit>(Main.scala:27)
                at Schema$.parse(Main.scala:18)
                at SchemaSuite$.spec$$anonfun$2(SchemaSpec.scala:11)
                at zio.test.TestConstructor$.apply$$anonfun$1$$anonfun$1(TestConstructor.scala:22)
```

This runtime issue seems to be caused by the definition of the `listOfValues` syntax option (in file `src/main/scala/Main.scala#27`); something is presumably wrongly defined, but I can not see what I am doing wrong. 😭


