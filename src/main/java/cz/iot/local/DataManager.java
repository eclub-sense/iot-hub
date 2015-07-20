package cz.iot.local;

/**
 * Created by Michal on 13. 7. 2015.
 */
public interface DataManager extends Runnable {

    void collectData();

    void close();
}
