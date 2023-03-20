package ru.load.jmh.demo.stringbuilder;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.SampleTime})
@Measurement(iterations = 3,  time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class StringBuilderExampleTest {
    /*
        https://habr.com/ru/company/piter/blog/358898/
        Неиспользование построителя строк внутри цикла

        Если вы не пользуетесь построителем строк внутри цикла, то производительность кода сильно падает.
        В упрощенной реализации мы наращивали бы строку внутри цикла при помощи оператора +=, прикрепляя
        таким образом новую часть строки к уже имеющейся. Проблема с данным подходом заключается в том,
        что при каждой итерации цикла будет выделяться новая строка, а старую строку на каждой итерации
        придется копировать в новую.

        Даже сама по себе эта операция затратна, не говоря уже о лишней нагрузке, связанной с дополнительной
        сборкой мусора, необходимой при создании и отбрасывании такого количества строк.
        Воспользовавшись StringBuilder, мы ограничим количество операций выделения памяти, что позволит нам сильно
        повысить производительность.

        Сравнение:

        Benchmark                                                                           Mode     Cnt    Score    Error  Units
        StringBuilderExampleTest.stringAppendBuilderLoop                                  sample  406479    0,061 �  0,001  ms/op
        StringBuilderExampleTest.stringAppendLoop                                         sample     336   74,885 �  1,944  ms/op
     */
    private StringBuilderExample sbe = new StringBuilderExample();

//    @Benchmark
    @Test
    public void stringAppendLoop() {
        sbe.stringAppendLoop();
    }

//    @Benchmark
    @Test
    public void stringAppendBuilderLoop() {
        sbe.stringAppendBuilderLoop();
    }

    @Benchmark
    @Test
    public void stringAppendLoopBenchmark() {
        //Вызов метода с быстрой склейкой
        sbe.stringAppendBuilderLoop();
    }

}