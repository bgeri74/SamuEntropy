package batfai.samuentropy.brainboard7.Sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by gery on 2016. 08. 30..
 */
public class BackgroundMusic extends AsyncTask<Void, Void, Void> {

    private Context context;
    private int song;

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    private MediaPlayer player;

    public void setSong(int songId){
        song = songId;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public static boolean isPlaying = false;

    public BackgroundMusic(Context context, int songId){
        this.context = context;
        this.song = songId;

        player = MediaPlayer.create(context, song);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        player.setLooping(true);
        player.start();
    }



    @Override
    protected Void doInBackground(Void... voids) {



        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

//        player.setLooping(false);
       //// player.stop();
       // player.release();
       // player = null;

        Log.d("gery", "Cancelling");
    }
}
