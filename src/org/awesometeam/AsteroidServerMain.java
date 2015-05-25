package org.awesometeam;

public class AsteroidServerMain {

    public static void main(String[] args) {
        AsteroidServerReceiver asr = new AsteroidServerReceiver();
        asr.startThread();
    }

}
