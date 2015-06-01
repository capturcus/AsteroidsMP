/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.clientnetworking;

import org.awesometeam.MenuState;

/**
 *
 * @author marcin
 */
public class TimeoutCounter implements Runnable {

    private int time;
    private Thread thread;
    private final int TIME = 30;

    public TimeoutCounter() {
        time = TIME;
        thread = new Thread(this, "TimeoutCounter Thread");
        thread.start();
    }

    public synchronized boolean checkTimeout() {
        return time < 0;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("sleep exception");
            }
            updateTime();
            if (checkTimeout()) {
                MenuState.timeout = true;
                thread.interrupt();
            }
        }
    }

    private synchronized void updateTime() {
        time--;
    }

    public synchronized void resetTime() {
        time = TIME;
    }

}
