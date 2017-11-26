package connorglennontaetraining.at.gmail.com.weekendassignment2.Utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;

import connorglennontaetraining.at.gmail.com.weekendassignment2.MyApp;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class Media implements IMedia {
    private static Media mMedia;

    public static Media getMedia()
    {
        if(mMedia == null) {
            synchronized (Media.class) {
                if(mMedia == null){
                    mMedia = new Media();
                }
            }
        }
        return mMedia;
    }

    private MediaPlayer mMediaPlayer;

    private Media() {
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void playSound(String uri) {
        Uri soundUri = Uri.parse(uri);

        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(MyApp.getContext(), soundUri);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mMediaPlayer.start();
                }
            });
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
