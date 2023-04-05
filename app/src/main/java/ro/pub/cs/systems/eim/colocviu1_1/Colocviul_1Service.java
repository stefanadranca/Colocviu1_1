package ro.pub.cs.systems.eim.colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Colocviul_1Service extends Service {
    private ProcessingThread processingThread = null;
    public Colocviul_1Service() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String commands = intent.getStringExtra(Constants.COMMANDS);
        processingThread = new ProcessingThread(this, commands);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    public void onDestroy() {
        processingThread.stopThread();
    }
}