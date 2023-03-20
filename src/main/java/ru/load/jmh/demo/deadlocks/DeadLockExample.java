package ru.load.jmh.demo.deadlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
    /*
     *  Этот код создает два потока, каждый из которых блокирует два ресурса lock1 и lock2.
     *  Поток 1 блокирует lock1, затем присваивает value1 значение 1, затем блокирует lock2 и
     *  читает значение из value2. Поток 2 делает то же самое в обратном порядке.
     *
     *  Когда оба потока запущены, они будут заблокированы в ожидании друг друга, и программа зависнет.
     *  Чтобы избежать deadlock, необходимо убедиться, что потоки блокируют ресурсы в одном и том же
     *  порядке. Если два потока блокируют одни и те же ресурсы в разном порядке, это может привести к
     *  deadlock.
     */
    final Lock lock1 = new ReentrantLock();
    final Lock lock2 = new ReentrantLock();
    String value1 = new String("1");
    String value2 = new String("2");
    public String putToV1GetFromV2(Integer number) {
        String v2Tmp = null;
        try {
            if (lock1.tryLock(2, TimeUnit.SECONDS)){
                System.out.println("Thread 1: locked lock1");
                value1 = String.valueOf(number);
                System.out.println("Thread 1: write value1 " + value1);
                if (lock2.tryLock(2, TimeUnit.SECONDS)){
                    System.out.println("Thread 1: locked lock2");
                    v2Tmp = value2;
                    System.out.println("Thread 1: read from value2 " +  v2Tmp);
                    lock2.unlock();
                }
                lock1.unlock();
                return v2Tmp;
            }
        } catch (InterruptedException e) {
            lock2.unlock();
            lock1.unlock();
            System.out.println(e);
        }
        return null;
    }

    public String putToV2GetFromV1(Integer number) {
        /*
         * Не вызывает deadlock. Код вызывает блокировку lock в такой же последовательности, как и putToV1GetFromV2
         */
        String v1Tmp = null;
        try {
            if (lock1.tryLock(2, TimeUnit.SECONDS)){
                System.out.println("Thread 2: locked lock1");
                v1Tmp = value1;
                System.out.println("Thread 2: read from value1 " + v1Tmp);
                if (lock2.tryLock(2, TimeUnit.SECONDS)){
                    System.out.println("Thread 2: locked lock2");
                    value2 = String.valueOf(number);
                    System.out.println("Thread 2: write value2 " + value2);
                    lock2.unlock();
                }
                lock1.unlock();
                return v1Tmp;
            }
        } catch (InterruptedException e) {
            lock2.unlock();
            lock1.unlock();
            System.out.println(e);
        }
        return null;
    }
}