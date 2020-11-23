## Java - Excecution time calculator

Big notation has always been with us when we needed to determine which method/algorithm is time efficient.
But sometimes, even after knowing the time complexity of methods doesn't provide satifaction to the need of 
precise numbers to the actual execution time.

Sometimes, we need know the exact time taken or the time difference between two methods/algorithms.

Although the resultant time may not be same all the time, depending upon JIT, warm-up phase, measurement phase, platorm under consideration, garbage collection time etc., this should give a pretty close results(atleast for comparing two codes with each other).

Also read [Micro-Benchmarking Considered Harmful](https://web.archive.org/web/20190818002513/http://blogs.microsoft.co.il/sasha/2009/05/08/micro-benchmarking-considered-harmful/).

## Few ways to calculate elapsed time:

### Native to Java - Pre Java 8

#### Calendar class

```java
    long start = Calendar.getInstance().getTime().getTime();
    //method whose time is to be calculated
    long end = Calendar.getInstance().getTime().getTime();

    System.out.println(end-start);
```

#### Date class

```java
    long start = new Date().getTime();
    //method whose time is to be calculated
    long end = new Date().getTime();

    System.out.println(end-start);
```
#### System.nanoTime()

```java
    long start = System.nanoTime();
    //method whose time is to be calculated
    long end = System.nanoTime();

    System.out.println(end-start);
```

#### System.currentTimeMillis()

```java
    long start = System.currentTimeMillis();
    //method whose time is to be calculated
    long end = System.currentTimeMillis();

    System.out.println(end-start);
```

Both nanoTime and currentTimeInMillis have thier own pros and cons-
1. System.currentTimeMillis() is only accurate above 15ms. For really low values it can't be trusted.
2. nanoTime is guaranteed to be at least as resolute as currentTimeMillis
3. System.currentTimeMillis() returns what the OS thinks is the time since the Unix epoch. If the user (or the OS itself) changes the OS time in between calls to System.currentTimeMillis(), then you one could get strange results. nanoTime is unaffected by changes to the OS clock time, so is more reliable.
4. One slight advantage of currentTimeMillis is that it's an actual timestamp, and could be used to log start/end times as well, while nanoTime "can only be used to measure elapsed time and is not related to any other notion of system or wall-clock time."
5. System.currentTimeMillis() IS NOT a good approach for measuring the performance of algorithms, it measures the total time one experiences as a user watching the computer screen. It also includes time consumed by everything else running on ones computer in the background. This could make a huge difference in case one has a lot of programs running on your workstation.

**P.S:** Refer to comments on [this](https://stackoverflow.com/a/180191) stackoverflow answer for more insights

### Native to Java - Post Java 8

#### Instant class

```java
    Instant start = Instant.now();
    //method whose time is to be calculated
    Instant end = Instant.now();
    Duration between = Duration.between(start, end);

    System.out.println( between ); // PT1.001S
    System.out.format("%dD, %02d:%02d:%02d.%04d \n", between.toDays(),between.toHours(), 
        between.toMinutes(), between.getSeconds(), between.toMillis()); //0D, 00:00:01.1001 
```

### Third party library

Note: Almost all of these library are not thread-safe.

### [Guava Stopwatch](https://guava.dev/releases/18.0/api/docs/com/google/common/base/Stopwatch.html)

```java
    //import com.google.common.base.Stopwatch;

    Stopwatch timer;
    timer = Stopwatch.createStarted();
    //method whose time is to be calculated
    timer.stop();
    System.out.println(timer.elapsed(MILLISECONDS));
```
### [Apache Stopwatch](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/time/StopWatch.html)

```java
    //import org.apache.commons.lang3.time.StopWatch;

    Stopwatch timer;
    timer = Stopwatch.start();
    //method whose time is to be calculated
    timer.stop();
    System.out.println(millisToShortDHMS(timer.getTime()));
```
### [Spring Stopwatch](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/StopWatch.html)

```java
    //import org.springframework.util.StopWatch;

    Stopwatch timer;
    timer = new Stopwatch().start("Sorting with bubble sort");
    //perform bubble sort
    timer.stop();
    System.out.println(millisToShortDHMS(timer.getTotalTimeMillis()));
```
//Todo: add spring stopwatch's other usefull methods

### [Perf4j](https://github.com/perf4j/perf4j) 

[Refer.](https://dzone.com/articles/measuring-performance-using)

### [Joda Time](https://www.joda.org/joda-time/) 

[Refer.](https://www.baeldung.com/joda-time)

## Integration steps:

There are 3 ways to integrate this into your project/code:

### Copying code into java file

This is ideal when you need to quickly calculate the running time of code, which spans not more than a single class file.

For any java version : Copy contents of [this]() file into you file

For java version above 8 : //Todo

### Using class file in project's package

For any java version : Copy [this]() file into you package

For java version above 8 : //Todo

### Using a build tool

//Todo

## Contribute:

You can contribute by logging any issues or by futher improving the code.

## References:

https://stackoverflow.com

https://javadevcentral.com/naive-benchmarking-framework-in-java