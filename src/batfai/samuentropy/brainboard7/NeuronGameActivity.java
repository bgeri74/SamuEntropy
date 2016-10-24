/*
 * NeuronAnimActivity.java
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

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import batfai.samuentropy.brainboard7.Database.Database;
import batfai.samuentropy.brainboard7.Sound.BackgroundMusic;

/**
 *
 * @author nbatfai
 */
public class NeuronGameActivity extends android.app.Activity {

    private BackgroundMusic backgroundMusic;

    private Layout rootLayout;
    private Button upgradeButton;

    private Database database;
    private MediaPlayer player;

    private  ProgressBar progressBar;

    public static boolean canPressUpgradeBtn = false;

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //progressBar = (ProgressBar) findViewById(R.id.neurongame_progressbar);

        //progressBar = (ProgressBar) findViewById(R.id.neuron_game_progressbar);

        database = new Database(this);

        if(getPreferences(MODE_PRIVATE).getBoolean("STARTED_FOR_FIRST_TIME", true)){
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putBoolean("STARTED_FOR_FIRST_TIME", false);
            try {
                database.getWritableDatabase().execSQL(String.format("CREATE TABLE positions(ID INTEGER PRIMARY KEY NOT NULL, X INTEGER, Y INTEGER, type INTEGER)"));
            }
            catch(SQLiteException e){

            }
            editor.commit();



        }



        upgradeButton = (Button) findViewById(R.id.upgrade_btn);
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!canPressUpgradeBtn){
                    Toast.makeText(NeuronGameActivity.this, "Database still loading!", Toast.LENGTH_SHORT).show();

                }
                else{
                    canPressUpgradeBtn = false;
                    new AsyncTask<Void, Void, Void>(){
                        @Override
                        protected Void doInBackground(Void... voids) {
                            NorbironSurfaceView.saveNodes();
                            return null;
                        }

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                           // progressBar.setVisibility(View.VISIBLE);
                           // progressBar.bringToFront();
                            Toast.makeText(NeuronGameActivity.this, "Saving data", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            //progressBar.setVisibility(View.INVISIBLE);


                            Intent i = new Intent(NeuronGameActivity.this, UpgradeActivity.class);
                            startActivity(i);
                        }
                    }.execute();


                }



            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();

        canPressUpgradeBtn = false;
        player = MediaPlayer.create(this, R.raw.heroic);
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


        NorbironSurfaceView.saveNodes();




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(NeuronGameActivity.this, SplashActivity.class);
        startActivity(i);
        finish();

    }
}

