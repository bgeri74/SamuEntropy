package batfai.samuentropy.brainboard7.Sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

/**
 * Created by gery on 2016. 08. 31..
 */
public class SoundEffect extends AsyncTask<Void, Void, Void> {
    private Context context;
    private int song;
    MediaPlayer player;

    public void setSong(int songId){
        song = songId;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public static boolean isPlaying = false;

    public SoundEffect(Context context, int songId){
        this.context = context;
        this.song = songId;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        player = MediaPlayer.create(context, song);

        player.setLooping(false);
        player.start();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.release();
            }
        });


        return null;
    }

}
