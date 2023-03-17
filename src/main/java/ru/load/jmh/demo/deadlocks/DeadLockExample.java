package ru.load.jmh.demo.deadlocks;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
    /*
        Этот код создает два потока, каждый из которых блокирует два ресурса: Lock и BlockingQueue.
        Поток 1 блокирует lock1, затем добавляет элемент в queue1, затем блокирует lock2 и
        добавляет элемент в queue2. Поток 2 делает то же самое в обратном порядке.

        Когда оба потока запущены, они будут заблокированы в ожидании друг друга, и программа зависнет.
        Чтобы избежать deadlock, необходимо убедиться, что потоки блокируют ресурсы в одном и том же
        порядке. Если два потока блокируют одни и те же ресурсы в разном порядке, это может привести к
        deadlock.
     */
    final Lock lock1 = new ReentrantLock();
    final Lock lock2 = new ReentrantLock();
    final BlockingQueue<Integer> queue1 = new LinkedBlockingQueue<>();
    final BlockingQueue<Integer> queue2 = new LinkedBlockingQueue<>();
    public void putToQ1(Integer q1) {
        try {
            lock1.lock();
            System.out.println("Thread 1: locked lock1");
            queue1.put(1);
            System.out.println("Thread 1: added to queue1");
            lock2.lock();
            System.out.println("Thread 1: locked lock2");
            queue2.put(2);
            System.out.println("Thread 1: added to queue2");
            lock2.unlock();
            lock1.unlock();
        } catch (InterruptedException e) {}
    }
    public void putToQ2(Integer q2) {
        try {
            lock2.lock();
            System.out.println("Thread 2: locked lock2");
            queue2.put(3);
            System.out.println("Thread 2: added to queue2");
            lock1.lock();
            System.out.println("Thread 2: locked lock1");
            queue1.put(4);
            System.out.println("Thread 2: added to queue1");
            lock1.unlock();
            lock2.unlock();
        } catch (InterruptedException e) {}
    }
}