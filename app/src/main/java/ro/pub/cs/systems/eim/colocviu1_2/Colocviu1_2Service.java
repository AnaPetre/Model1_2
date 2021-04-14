package ro.pub.cs.systems.eim.colocviu1_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Colocviu1_2Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.MAIN, "startService() method was invoked");

        // luam suma din intentie intr-o variabila noua cause it is easier
        int sum = intent.getIntExtra(Constants.CALCULATED_SUM, -1);

        // pornim un thread nou
        processingThread = new ProcessingThread(this, sum);
        processingThread.start();

        // pornim serviciul
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Service has stopped!");
        processingThread.stopThread();
    }

}
