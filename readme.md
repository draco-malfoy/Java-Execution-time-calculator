Java - Excecution time calculator

Big notation has always been with us when we needed to determine which method/algorithm is time efficient.
But sometimes, even after knowing the time complexity of methods doesn't provide satifaction to the need of 
precise numbers to the actual execution time. 
Sometimes, we crave for the exact(not too exact) time taken or the time difference between two methods/algorithms.

Although the resultant time may not be same all the time, depending upon JIT, warm-up phase, measurement phase, platorm under consideration, garbage collection time etc., this should give a pretty close results(atleast for comparing two codes with each other).

Also read [Micro-Benchmarking Considered Harmful](https://web.archive.org/web/20190818002513/http://blogs.microsoft.co.il/sasha/2009/05/08/micro-benchmarking-considered-harmful/).

## Integration steps:

## Contribute:

You can contribute by logging issues or by improving the code.

## References:

https://javadevcentral.com/naive-benchmarking-framework-in-java

https://stackoverflow.com

--================================================================================================

## Possible ways to calculate elapsed time:

### Native to Java library - Pre Java 8


Both nanoTime and currentTimeInMillis have thier own pros and cons-
1. System.currentTimeMillis() is only accurate above 15ms. For really low values it can't be trusted.
2. nanoTime is guaranteed to be at least as resolute as currentTimeMillis
3. System.currentTimeMillis() returns what the OS thinks is the time since the Unix epoch. If the user (or the OS itself) changes the OS time in between calls to System.currentTimeMillis(), then you one could get strange results. nanoTime is unaffected by changes to the OS clock time, so is more reliable.
4. One slight advantage of currentTimeMillis is that it's an actual timestamp, and could be used to log start/end times as well, while nanoTime "can only be used to measure elapsed time and is not related to any other notion of system or wall-clock time."
5. System.currentTimeMillis() IS NOT a good approach for measuring the performance of algorithms, it measures the total time one experiences as a user watching the computer screen. It also includes time consumed by everything else running on ones computer in the background. This could make a huge difference in case one has a lot of programs running on your workstation.
P.S: Refer to comments on [this](https://stackoverflow.com/a/180191) stackoverflow answer for more insights

### Native to Java library - Post Java 8

Instant start = Instant.now();
Thread.sleep(5000);
Instant end = Instant.now();
System.out.println(Duration.between(start, end));



### Third party library

### [Guava](https://guava.dev/releases/18.0/api/docs/com/google/common/base/Stopwatch.html) 

Note: Almost all of these library are not thread-safe.

```java
import com.google.common.base.Stopwatch;

Stopwatch timer;
timer = Stopwatch.createStarted();
//method whose time is to be calculated
timer.stop();
System.out.println(timer.elapsed(MILLISECONDS));
```

long seconds = TimeUnit.NANOSECONDS.toSeconds(after - before);

NumberFormat formatter = new DecimalFormat("#0.00000");
System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

Calendar calendar = Calendar.getInstance();
// Get start time (this needs to be a global variable).
Date startDate = calendar.getTime();
Calendar calendar = Calendar.getInstance();
// Get start time (this needs to be a global variable).
Date endDate = calendar.getTime();  
long sumDate = endDate.getTime() - startDate.getTime();

perf4j
https://dzone.com/articles/measuring-performance-using

Date

Date startDate = Calendar.getInstance().getTime();
long d_StartTime = new Date().getTime();
Thread.sleep(1000 * 4);
Date endDate = Calendar.getInstance().getTime();
long d_endTime = new Date().getTime();
System.out.format("StartDate : %s, EndDate : %s \n", startDate, endDate);
System.out.format("Milli = %s, ( D_Start : %s, D_End : %s ) \n", (d_endTime - d_StartTime),d_StartTime, d_endTime);
System.currentTimeMillis()

long startTime = System.currentTimeMillis();
Thread.sleep(1000 * 4);
long endTime = System.currentTimeMillis();
long duration = (endTime - startTime);  
System.out.format("Milli = %s, ( S_Start : %s, S_End : %s ) \n", duration, startTime, endTime );
System.out.println("Human-Readable format : "+millisToShortDHMS( duration ) );
Human Readable Format

public static String millisToShortDHMS(long duration) {
    String res = "";    // java.util.concurrent.TimeUnit;
    long days       = TimeUnit.MILLISECONDS.toDays(duration);
    long hours      = TimeUnit.MILLISECONDS.toHours(duration) -
                      TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
    long minutes    = TimeUnit.MILLISECONDS.toMinutes(duration) -
                      TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
    long seconds    = TimeUnit.MILLISECONDS.toSeconds(duration) -
                      TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
    long millis     = TimeUnit.MILLISECONDS.toMillis(duration) - 
                      TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration));

    if (days == 0)      res = String.format("%02d:%02d:%02d.%04d", hours, minutes, seconds, millis);
    else                res = String.format("%dd %02d:%02d:%02d.%04d", days, hours, minutes, seconds, millis);
    return res;
}

com.google.common.base.Stopwatch g_SW = Stopwatch.createUnstarted();
g_SW.start();
Thread.sleep(1000 * 4);
g_SW.stop();
System.out.println("Google StopWatch  : "+g_SW);
Apache Commons LangJAR « StopWatch provides a convenient API for timings.

org.apache.commons.lang3.time.StopWatch sw = new StopWatch();
sw.start();     
Thread.sleep(1000 * 4);     
sw.stop();
System.out.println("Apache StopWatch  : "+ millisToShortDHMS(sw.getTime()) );
JODA-TIME

public static void jodaTime() throws InterruptedException, ParseException{
    java.text.SimpleDateFormat ms_SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    String start = ms_SDF.format( new Date() ); // java.util.Date

    Thread.sleep(10000);

    String end = ms_SDF.format( new Date() );       
    System.out.println("Start:"+start+"\t Stop:"+end);

    Date date_1 = ms_SDF.parse(start);
    Date date_2 = ms_SDF.parse(end);        
    Interval interval = new org.joda.time.Interval( date_1.getTime(), date_2.getTime() );
    Period period = interval.toPeriod(); //org.joda.time.Period

    System.out.format("%dY/%dM/%dD, %02d:%02d:%02d.%04d \n", 
        period.getYears(), period.getMonths(), period.getDays(),
        period.getHours(), period.getMinutes(), period.getSeconds(), period.getMillis());
}
Java date time API from Java 8 « A Duration object represents a period of time between two Instant objects.

Instant start = java.time.Instant.now();
    Thread.sleep(1000);
Instant end = java.time.Instant.now();
Duration between = java.time.Duration.between(start, end);
System.out.println( between ); // PT1.001S
System.out.format("%dD, %02d:%02d:%02d.%04d \n", between.toDays(),
        between.toHours(), between.toMinutes(), between.getSeconds(), between.toMillis()); // 0D, 00:00:01.1001 
Spring Framework provides StopWatch utility class to measure elapsed time in Java.

StopWatch sw = new org.springframework.util.StopWatch();
sw.start("Method-1"); // Start a named task
    Thread.sleep(500);
sw.stop();

sw.start("Method-2");
    Thread.sleep(300);
sw.stop();

sw.start("Method-3");
    Thread.sleep(200);
sw.stop();

System.out.println("Total time in milliseconds for all tasks :\n"+sw.getTotalTimeMillis());
System.out.println("Table describing all tasks performed :\n"+sw.prettyPrint());

System.out.format("Time taken by the last task : [%s]:[%d]", 
        sw.getLastTaskName(),sw.getLastTaskTimeMillis());

System.out.println("\n Array of the data for tasks performed « Task Name: Time Taken");
TaskInfo[] listofTasks = sw.getTaskInfo();
for (TaskInfo task : listofTasks) {
    System.out.format("[%s]:[%d]\n", 
            task.getTaskName(), task.getTimeMillis());
}

From http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking website (archive link):

"User time" is the time spent running your application's own code.
"System time" is the time spent running OS code on behalf of your application (such as for I/O).
getCpuTime() method gives you sum of those:

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class CPUUtils {

    /** Get CPU time in nanoseconds. */
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
            bean.getCurrentThreadCpuTime( ) : 0L;
    }

    /** Get user time in nanoseconds. */
    public static long getUserTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
            bean.getCurrentThreadUserTime( ) : 0L;
    }

    /** Get system time in nanoseconds. */
    public static long getSystemTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
            (bean.getCurrentThreadCpuTime( ) - bean.getCurrentThreadUserTime( )) : 0L;
    }

}
_________________________
public class Timer{
    private static long start_time;

    public static double tic(){
        return start_time = System.nanoTime();
    }

    public static double toc(){
        return (System.nanoTime()-start_time)/1000000000.0;
    }

}
That way you can time one or more actions:

Timer.tic();
// Code 1
System.out.println("Code 1 runtime: "+Timer.toc()+" seconds.");
// Code 2
System.out.println("(Code 1 + Code 2) runtime: "+Timer.toc()+"seconds");
Timer.tic();
// Code 3
System.out.println("Code 3 runtime: "+Timer.toc()+" seconds.");

