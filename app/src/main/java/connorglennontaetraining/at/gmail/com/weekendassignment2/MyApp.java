package connorglennontaetraining.at.gmail.com.weekendassignment2;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application{
    public static  Application application;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyApp", "onCreate");
        Fresco.initialize(this);
        application = this;
        context = this;
    }
}
