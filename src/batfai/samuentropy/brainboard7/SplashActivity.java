/*
 * SplashActivity.java
 *
 * Norbiron Game
 * This is a case study for creating sprites for SamuEntropy/Brainboard.
 *
 * Copyright (C) 2016, Dr. Bátfai Norbert
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Ez a program szabad szoftver; terjeszthető illetve módosítható a
 * Free Software Foundation által kiadott GNU General Public License
 * dokumentumában leírtak; akár a licenc 3-as, akár (tetszőleges) későbbi
 * változata szerint.
 *
 * Ez a program abban a reményben kerül közreadásra, hogy hasznos lesz,
 * de minden egyéb GARANCIA NÉLKÜL, az ELADHATÓSÁGRA vagy VALAMELY CÉLRA
 * VALÓ ALKALMAZHATÓSÁGRA való származtatott garanciát is beleértve.
 * További részleteket a GNU General Public License tartalmaz.
 *
 * A felhasználónak a programmal együtt meg kell kapnia a GNU General
 * Public License egy példányát; ha mégsem kapta meg, akkor
 * tekintse meg a <http://www.gnu.org/licenses/> oldalon.
 *
 * Version history:
 *
 * 0.0.1, 2013.szept.29.
 */
package batfai.samuentropy.brainboard7;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

import batfai.samuentropy.brainboard7.Sound.BackgroundMusic;

class Splash extends Thread {

    SplashActivity act;
    android.graphics.drawable.AnimationDrawable anim;

    public Splash(android.graphics.drawable.AnimationDrawable anim, SplashActivity act) {
        this.anim = anim;
        this.act = act;
    }

    public void st() {
        android.content.Intent intent = new android.content.Intent();
        intent.setClass(act, NeuronGameActivity.class);
        act.startActivity(intent);
        anim.stop();

    }

    @Override
    public void run() {
        anim.start();
    }
}

/**
 *
 * @author nbatfai
 */
public class SplashActivity extends android.app.Activity {

    private Splash splash;

    private BackgroundMusic backgroundMusic;
    Button playButton, exitButton;
    private MediaPlayer player;

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        android.widget.ImageView iv = (android.widget.ImageView) findViewById(R.id.neuronanimation);

        iv.setBackgroundResource(R.drawable.neuron_animation);

        final android.graphics.drawable.AnimationDrawable anim = (android.graphics.drawable.AnimationDrawable) iv.getBackground();

        playButton = (Button) findViewById(R.id.play_btn);
        exitButton = (Button) findViewById(R.id.exit_btn);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splash.st();
                finish();

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        splash = new Splash(anim, this);
        runOnUiThread(splash);

    }

    @Override
    protected void onResume() {
        super.onResume();
       // backgroundMusic = new BackgroundMusic(this, R.raw.medieval);
       // backgroundMusic.execute();
        player = MediaPlayer.create(this, R.raw.medieval);
        player.setLooping(true);

        player.start();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.release();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        player.stop();
        player.release();
        //backgroundMusic.getPlayer().stop();
       // backgroundMusic.getPlayer().release();
       // backgroundMusic.cancel(true);



    }

    /*@Override
    public boolean onTouchEvent(android.view.MotionEvent evt) {
        if (evt.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            splash.st();
        }
        return true;
    }*/
}
