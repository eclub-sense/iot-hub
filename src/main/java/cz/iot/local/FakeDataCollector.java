package cz.iot.local;

import cz.iot.utils.DataManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tom on 16. 7. 2015.
 */
public class FakeDataCollector implements DataCollector{
    private boolean isRunning = true;
    private DataManager manager;

    Packet p1 = new Packet("00000001","0041FF000000434D0000000000000000000000000000000000000000");
    Packet p2 = new Packet("00000001","0041FF0000002A820000000000000000000000000000000000000000");
    Packet p3 = new Packet("00000001","0041FF00000098550000000000000000000000000000000000000000");
    Packet p4 = new Packet("00000002","0041FF000000434D0000000000000000000000000000000000000000");
    Packet p5 = new Packet("00000002","0041FF0000002A820000000000000000000000000000000000000000");
    Packet p6 = new Packet("00000002","0041FF00000098550000000000000000000000000000000000000000");
    Packet p7 = new Packet("00000003","0041FF000000434D0000000000000000000000000000000000000000");
    Packet p8 = new Packet("00000003","0041FF0000002A820000000000000000000000000000000000000000");
    Packet p9 = new Packet("00000003","0041FF00000098550000000000000000000000000000000000000000");
    Random randomGenerator = new Random();
    private ArrayList<Packet> packetArrayList = new ArrayList<>();


    public FakeDataCollector(DataManager manager) {
        this.manager = manager;
        packetArrayList.add(p1);
        packetArrayList.add(p2);
        packetArrayList.add(p3);
        packetArrayList.add(p4);
        packetArrayList.add(p5);
        packetArrayList.add(p6);
        packetArrayList.add(p7);
        packetArrayList.add(p8);
        packetArrayList.add(p9);
    }

    @Override
    public void collectData() {
        for(int i=0;i<9;i++){
            if(randomGenerator.nextInt(2)==2){
                manager.put(packetArrayList.get(i));
            }
        }
    }

    @Override
    public void close() {
        isRunning=false;
    }

    @Override
    public void run() {
        while(isRunning){
            collectData();
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
