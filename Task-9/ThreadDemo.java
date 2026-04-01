import java.util.LinkedList;
import java.util.Queue;

//Thread Lifecycle — using Runnable + Thread states
class LifecycleDemo implements Runnable {
    private final String name;

    LifecycleDemo(String name) { this.name = name; }

    @Override
    public void run() {
        System.out.println("  [RUNNING]    " + name + " started  | thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);   // TIMED_WAITING state
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("  [TERMINATED] " + name + " finished | thread: " + Thread.currentThread().getName());
    }
}

//Synchronization — shared counter, two threads incrementing it
class SharedCounter {
    private int count = 0;

    // synchronized method — only one thread at a time
    public synchronized void increment(String threadName) {
        int before = count;
        count++;
        System.out.printf("  %-10s read %d → wrote %d%n", threadName, before, count);
    }

    public synchronized int getCount() { return count; }
}

//wait() / notify() — Producer-Consumer with a bounded queue
class BoundedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int            capacity;

    BoundedBuffer(int capacity) { this.capacity = capacity; }

    // Producer calls this
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("  [PRODUCER]  buffer full  — waiting...");
            wait();                          // releases lock, waits for notify
        }
        queue.add(item);
        System.out.println("  [PRODUCER]  produced " + item + "  | buffer: " + queue);
        notify();                            // wake up a waiting consumer
    }

    // Consumer calls this
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("  [CONSUMER]  buffer empty — waiting...");
            wait();
        }
        int item = queue.poll();
        System.out.println("  [CONSUMER]  consumed " + item + "  | buffer: " + queue);
        notify();                            // wake up a waiting producer
        return item;
    }
}


public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        //Section 1: Thread Lifecycle
        System.out.println("┌─ 1. Thread Lifecycle ───────────────────────────────┐");

        Thread t1 = new Thread(new LifecycleDemo("Task-A"), "Worker-1");
        Thread t2 = new Thread(new LifecycleDemo("Task-B"), "Worker-2");

        System.out.println("  [NEW]        t1 state: " + t1.getState());   // NEW
        t1.start();
        t2.start();
        System.out.println("  [STARTED]    t1 state: " + t1.getState());   // RUNNABLE or TIMED_WAITING

        t1.join();   // main waits for t1
        t2.join();   // main waits for t2
        System.out.println("  [JOINED]     t1 state: " + t1.getState());   // TERMINATED
        System.out.println();

        //Section 2: Synchronization
        System.out.println("┌─ 2. Synchronized Shared Counter ────────────────────┐");

        SharedCounter counter = new SharedCounter();

        Thread inc1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) counter.increment("Thread-A");
        });
        Thread inc2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) counter.increment("Thread-B");
        });

        inc1.start();
        inc2.start();
        inc1.join();
        inc2.join();

        System.out.println("  Final count: " + counter.getCount() + " (expected 6)");
        System.out.println();

        //Section 3: wait() / notify() — Producer-Consumer
        System.out.println("┌─ 3. Producer-Consumer (wait/notify) ───────────────┐");

        BoundedBuffer buffer = new BoundedBuffer(2);   // capacity = 2

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                    Thread.sleep(300);    // slower than producer → forces wait()
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        System.out.println();
        System.out.println("┌─ All threads completed ─────────────────────────────┐");
        System.out.println("  Done.");
    }
}