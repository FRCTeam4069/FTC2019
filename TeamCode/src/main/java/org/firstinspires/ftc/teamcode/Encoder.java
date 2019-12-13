package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Encoder {
    private DigitalChannel digitalA;
    private DigitalChannel digitalB;
    private Object lock = new Object();
    private int position = 0;

    public Encoder(HardwareMap hardwareMap, String channelA, String channelB) {
        digitalA = hardwareMap.get(DigitalChannel.class, channelA);
        digitalB = hardwareMap.get(DigitalChannel.class, channelB);
        digitalA.setMode(DigitalChannel.Mode.INPUT);
        digitalB.setMode(DigitalChannel.Mode.INPUT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    boolean chA;
                    boolean chB;

                    synchronized (lock) {
                        chA = digitalA.getState();
                        chB = digitalB.getState();
                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }

    public int getPosition() {
        synchronized (lock) {
            return position;
        }
    }
}
