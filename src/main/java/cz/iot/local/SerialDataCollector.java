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
        initSerialPort();
        while (isRunning) {
            collectData();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void collectData() {
        try {
            writeLine("AT+REQUEST?");
            System.out.println(readLine());
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public String[] getPorts() {
        return SerialPortList.getPortNames();
    }

    public void initSerialPort() {
        try {
            //serialPort = new SerialPort(getPorts()[0]);
            serialPort = new SerialPort("COM4");
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void writeLine(String message) throws SerialPortException {
        serialPort.writeString(message+"\r\n");
    }

    public String readLine(){
        StringBuilder sb = new StringBuilder();
        /*while(serialPort.getInputBufferBytesCount()>0){

        }*/
        return null;
    }

}
