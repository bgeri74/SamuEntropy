package batfai.samuentropy.brainboard7.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gery on 2016. 09. 02..
 */
public class Database extends SQLiteOpenHelper {

    SQLiteDatabase database;
    Cursor lastResult;

    public Database(Context context){
        super(context, "game_database.db", null, 1);
        //database = get
    }

    public Cursor getResult(String sql) {
        database = this.getReadableDatabase();
        lastResult = database.rawQuery(sql, null);
        return lastResult;
    }

    public Cursor getLastResult(){
        return lastResult;
    }

    public void executeQuery(String query){
        database = this.getWritableDatabase();
        try {
            lastResult.close();
        }
        catch(NullPointerException e){
            Log.d("gery", "database was not opened.");
        }
        lastResult = database.rawQuery(query, null);

        //lastResult.close();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        database = sqLiteDatabase;
        Log.d("gery", "oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        database = sqLiteDatabase;
        Log.d("gery", "onupgrade");
    }

}
