package ru.load.jmh.demo.deadlocks;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(time = 10, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode({Mode.SampleTime})
@Measurement(iterations = 5,  time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark) //Benchmark или Group. Для тестирования блокировок, здесь не подойдет тип изоляции Thread
public class DeadLockExampleTest {
    /*
        Этот код создает два потока, каждый из которых блокирует два ресурса: Lock и BlockingQueue.
        Поток 1 блокирует lock1, затем добавляет элемент в queue1, затем блокирует lock2 и
        добавляет элемент в queue2. Поток 2 делает то же самое в обратном порядке.
     */
    private DeadLockExample example = new DeadLockExample();
    @Benchmark
    @Test
    @Group
    public void putToQ1() {
        example.putToQ1(1);
    }

    @Benchmark
    @Test
    @Group
    public void putToQ2() {
        example.putToQ2(2);
    }
}