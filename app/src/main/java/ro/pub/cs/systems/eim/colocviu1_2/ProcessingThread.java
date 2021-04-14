package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread{

    // ce vrem sa afisam + contextul
    int sum = 0;
    private Context context = null;
    boolean isRunning = true;

    // constructor
    public ProcessingThread(Context context, int sum)
    {
        this.context = context;
        this.sum = sum;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());

        // exceptie => la fiecare 2s daca se intra pe cazul suma >=10, se afiseaza Toast-ul
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        // o noua intentie pentru a afisa suma >= 10
        while(isRunning) {
            Intent intent = new Intent();

            // ACTIONTYPE = ro.pub.cs.systems.eim.practicaltest01.sum, iar .sum de la variabila noastra
            intent.setAction(Constants.ACTIONTYPE);

            // pentru a trimite mesajul cu suma si data curenta
            intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                    new Date(System.currentTimeMillis()) + " sum is " + sum);

            // trimitem din contextul curent broadcast cu aceasta intentie
            context.sendBroadcast(intent);
        }

    }

    public void stopThread() {

        isRunning = false;

        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");


    }

}
