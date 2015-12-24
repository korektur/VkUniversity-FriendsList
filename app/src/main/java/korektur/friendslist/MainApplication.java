package korektur.friendslist;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by korektur
 * 10/12/15.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
