/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * ThreadLinker
 */

import java.util.concurrent.*;

/**
 * ThreadLinker object for synchronized communication between two threads.
 * Stores String messages in separate queues for each thread.
 *
 * @author Misha Kotlik, Sam Xu
 * @since 2016-05-16
 */
public class ThreadLinker {
    //Instance variables
    private LinkedBlockingQueue<String> queueA;
    private LinkedBlockingQueue<String> queueB;
    private int sizeA;
    private int sizeB;
    private String threadIDA;
    private String threadIDB;
    private int waitTimeout = 200; //timeout in millis
    //Could also set custom wait times in constructor or method

    //Constructor
    public ThreadLinker(String threadIDA, String threadIDB) {
        this.threadIDA = threadIDA;
        this.threadIDB = threadIDB;
        queueA = new LinkedBlockingQueue<String>();
        queueB = new LinkedBlockingQueue<String>();
        sizeA = 0;
        sizeB = 0;
    }

    //Accessors
    public synchronized String retrieveA() {
        if (! hasMsgForA()) {
            try {
                wait(waitTimeout);
            } catch (InterruptedException e) {
                System.out.println("EXCEPTION: ThreadLinker retrieval for thread " + threadIDA + " was interrupted.");
            }
        }
        //Confirm that message has been deposited for A
        if (hasMsgForA()) {
            sizeA--;
            //not sure we should notify on retrieval, since no one should wait on this
            //notifyAll(); //notify all threads (aka B) that message was retrieved
            return queueA.poll();
        } else { //wait stopped on timeout, no new message
            return "LINKER_TIMEOUT";
        }
    }

    public synchronized String retrieveB() {
        if (! hasMsgForB()) {
            try {
                wait(waitTimeout);
            } catch (InterruptedException e) {
                System.out.println("EXCEPTION: ThreadLinker retrieval for thread " + threadIDB + " was interrupted.");
            }
        }
        //Confirm that message has been deposited for A
        if (hasMsgForB()) {
            sizeB--;
            //not sure we should notify on retrieval, since no one should wait on this
            //notifyAll(); //notify all threads (aka B) that message was retrieved
            return queueB.poll();
        } else { //wait stopped on timeout, no new message
            return "LINKER_TIMEOUT";
        }
    }

    //should this be synchronized too?
    public boolean hasMsgForA() {
        return sizeA > 0;
    }

    public boolean hasMsgForB() {
        return sizeB > 0;
    }

    //Mutators
    public synchronized void updateA(String message) {
        try {
            queueA.put(message);
            sizeA++;
            notifyAll();
        } catch (InterruptedException e) {
            System.out.println("EXCEPTION: Unexpected InterruptedException on updateA() for thread " + threadIDB);
        }
    }

    public synchronized void updateB(String message) {
        try {
            queueB.put(message);
            sizeB++;
            notifyAll();
        } catch (InterruptedException e) {
            System.out.println("EXCEPTION: Unexpected InterruptedException on updateB() for thread " + threadIDA);
        }
    }

}