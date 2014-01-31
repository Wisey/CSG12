package uk.ac.aber.group12.walkingtour;

        import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by dpb1 on 30/01/2014.
 */
public class ThreadService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        //  Log.d(TAG, "FirstService started");
        this.stopSelf();
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //    Log.d(TAG, "FirstService destroyed");
    }

}