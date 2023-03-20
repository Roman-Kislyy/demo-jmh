package ru.load.jmh.demo.deadlocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.SampleTime})
@Timeout(time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3,  time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark) //Benchmark или Group. Для тестирования блокировок, здесь не подойдет тип изоляции Thread
public class DeadLockExampleTest {
    /*
     *   Этот код создает два потока, каждый из которых блокирует два ресурса lock1 и lock2.
     *   Поток 1 блокирует lock1, затем присваивает value1 значение 1, затем блокирует lock2 и
     *   читает значение из value2. Поток 2 делает то же самое в обратном порядке.
     */
    private DeadLockExample example = new DeadLockExample();
    @Benchmark
    @Test
    @Group
    public void putToV1GetFromV2(){
        /*
         * Метод putToV1GetFromV2 сохраняет значение в поле value1 класса DeadLockExample,
         * а возвращает значение из value2.
         */
        String v2 = example.putToV1GetFromV2(1);
//        Assertions.assertEquals("2", v2, "Test1: Value2 is wrong.");
    }
    @Benchmark
    @Test
    @Group
    public void putToV2GetFromV1(){
        /*
         * Метод putToV2GetFromV1 сохраняет значение в поле value2 класса DeadLockExample,
         * а возвращает значение из value1.
         */
        String v1 = example.putToV2GetFromV1(2);
//        Assertions.assertEquals("1", v1, "Test2: Value1 is wrong.");
    }
}