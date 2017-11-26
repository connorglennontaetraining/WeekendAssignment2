package connorglennontaetraining.at.gmail.com.weekendassignment2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application{
    static  Application mApplication;
    static Context mContext;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;

    public static Application getApplication()
    {
        return mApplication;
    }

    public static Context getContext()
    {
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyApp", "onCreate");
        mApplication = this;
        mContext = this;

        sharedPreferences = getSharedPreferences("connorglennontaetraining.at.gmail.com.weekendassignment2", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        Fresco.initialize(this);
        Realm.init(this);

        Realm.deleteRealm(Realm.getDefaultConfiguration());

        //TODO: look at moving this key to a secure location.
        if(sharedPreferences.getString("ENCKEY", null) == null)
        {
            byte[] key = new byte[64];
            new SecureRandom().nextBytes(key);
            String enckey = Base64.encodeToString(key, Base64.DEFAULT);
            sharedPreferencesEditor.putString("ENCKEY", enckey).apply();
        }

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(Base64.decode(sharedPreferences.getString("ENCKEY", null), Base64.DEFAULT))
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
