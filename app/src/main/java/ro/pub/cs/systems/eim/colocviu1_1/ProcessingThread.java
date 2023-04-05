package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread{
    private Context context = null;
    private boolean isRunning = true;
    private String command;

    public ProcessingThread(Context context, String command) {
        this.context = context;
        this.command = command;
    }
    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        sleep();
        sendMessage();

        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("BROADCAST");
        Log.d("[LOG]", "COMANDA PRIMITA" + command);
        intent.putExtra(Constants.DATA, new Date(System.currentTimeMillis()) + " instruction: " + command);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
