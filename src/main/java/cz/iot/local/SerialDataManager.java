package cz.iot.local;

import cz.iot.utils.PacketManager;
import jssc.*;

import java.util.ArrayList;

/**
 * Created by Michal on 13. 7. 2015.
 */
public class SerialDataManager implements DataManager {

    private SerialPort serialPort;
    private boolean isRunning;
    private PacketManager manager;
    private StringBuilder sb = new StringBuilder();

    public SerialDataManager(PacketManager manager) {
        this.manager = manager;
        isRunning = true;
        initSerialPort();
    }

    public void close() {
        isRunning = false;
    }

    public void run() {

        while (isRunning) {
            collectData();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void collectData() {
        try {
            writeLine("AT+REQUEST?");
            String data = readLine();
            if (data.startsWith("AT+REQUEST START")) {
                ArrayList<Packet> packets = new ArrayList<>();
                int count = Integer.parseInt(data.split(",")[1]);
                for (int i = 0; i < count; i++) {
                    data = readLine();
                    packets.add(new Packet(data.substring(0, 8), data.substring(8)));
                }
                data = readLine();
                if (!data.equalsIgnoreCase("AT+REQUEST END")) {
                    throw new Exception("serial line error - bad end");
                }
                if (packets.size() > 0) {
                    //System.out.println("=====PACKETS=====");
                    for (Packet p : packets) {
                        //System.out.println(p);
                        manager.put(p);
                    }
                }
            } else {
                throw new Exception("serial line error - bad start");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] getPorts() {
        return SerialPortList.getPortNames();
    }

    public void initSerialPort() {
        try {
            //serialPort = new SerialPort(getPorts()[0]);
            serialPort = new SerialPort("COM5");
            serialPort.openPort();
            serialPort.setParams(19200, 8, 1, 0);
            serialPort.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.getEventType() == 1) {
                        try {
                            synchronized (serialPort) {
                                sb.append(serialPort.readString());
                            }
                        } catch (SerialPortException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            while (!sb.toString().endsWith("AT+UNKNOWN\r\n")) {
                writeLine("ahoj");
                Thread.sleep(80);
            }
            synchronized (serialPort) {
                sb = new StringBuilder();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void writeLine(String message) throws SerialPortException {
        serialPort.writeString(message + "\r\n");
    }

    public String readLine() {
        while (sb.indexOf("\r\n") == -1) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = "";
        synchronized (serialPort) {
            data = sb.substring(0, sb.indexOf("\r\n"));
            sb = new StringBuilder(sb.substring(sb.indexOf("\r\n") + 2));
        }
        return data;
    }

}