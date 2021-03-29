import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int counter;
    private Lock lock;

    public Counter() {
        this.counter = 0;
        this.lock = new ReentrantLock();
    }
    public int getCounter() {
        return counter;
    }

    public void resetCounter() {
        try {
            lock.lock();
            counter = 0;
            System.out.println(counter);
        } finally {
            lock.unlock();
        }
    }

    public void decrementCounter() {
        try {
            lock.lock();
            counter--;
            System.out.println(counter);
        } finally {
            lock.unlock();
        }
    }

    public void incrementCounter() {
        try {
            lock.lock();
            counter++;
            System.out.println(counter);
        } finally {
            lock.unlock();
        }
    }
}