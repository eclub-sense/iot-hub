package cz.iot.local;

import cz.iot.main.Hub;
import cz.iot.remote.HubClient;
import cz.iot.utils.DataManager;
import jssc.*;

import java.util.Map;

/**
 * Created by Michal on 13. 7. 2015.
 */
public class SerialDataCollector implements DataCollector {

    private SerialPort serialPort;
    private boolean isRunning;
    private DataManager manager;
    private StringBuilder sb = new StringBuilder();

    public SerialDataCollector(DataManager manager) {
        this.manager = manager;
        isRunning = true;

    }

    public void close() {
        isRunning = false;
    }

    public void run() {
        System.out.println("jupii");
        initSerialPort();
        while (isRunning) {
        }


    }

    public void collectData() {

    }

    public String[] getPorts() {
        return SerialPortList.getPortNames();
    }

    public void initSerialPort() {
        /*
        try {
            //serialPort = new SerialPort(getPorts()[0]);

            serialPort = new SerialPort("COM4");

            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.addEventListener(new SerialPortEventListener() {
                public void serialEvent(SerialPortEvent serialPortEvent) {

                    try {
                        if (serialPortEvent.getEventType() == 1) {

                            sb.append(serialPort.readString());
                            int index = sb.toString().indexOf("\r\n");
                            if (index != -1) {
                                String data = sb.substring(0, index);
                                if (hub.getHubPackets().contains(new Packet(data.split(" ")[0]))) {
                                    hub.sendString("UUID:" + data.split(" ")[0] + " DATA:" + data.split(" ")[1]);
                                }
                                sb = new StringBuilder(sb.toString().substring(index + 2));
                            }

                        }
                    } catch (SerialPortException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }*/
    }


}
