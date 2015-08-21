package demoapp.akiraaman.demo_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ngquh_000 on 7/29/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DuLieuTaiKhoan.db";
    private static final String TABLE_NAME = "DuLieuTaiKhoan";
    private static final int VERSION = 1;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PASSWORD = "pass";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IP = "ip";
    private static final String COLUMN_PHONE = "nphone";
    private static final String TABLE_CREATE = "create table DuLieuTaiKhoan ( id text primary key not null, " +
            "pass text not null, name text not null, ip text not null, nphone text );";
    SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void insertTK(TaiKhoan kh){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,kh.getM_TK());
        values.put(COLUMN_PASSWORD, kh.getM_Matkhau());
        values.put(COLUMN_NAME, kh.getM_Ten());
        values.put(COLUMN_IP, kh.getM_Diachi());
        values.put(COLUMN_PHONE, kh.getM_Sdt());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteUser(String id){
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }


    public void deleteAll(){
        db = this.getWritableDatabase();
        db.delete(this.TABLE_NAME, null, null);
        db.close();

    }

    public void UpdateTK(String id, String pass){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, pass);
        db.update(TABLE_NAME, values, COLUMN_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public boolean searchTK(String tk){
        db = this.getReadableDatabase();
        String query = "select id from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String username;
        if(cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
                if (username.equals(tk)) {
                    db.close();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return false;
    }


    public String searchPass(String tk){
        db = this.getReadableDatabase();
        String query = "select id, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String username;
        String pass = "not found";
        if(cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
                if (username.equals(tk)) {
                    pass = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return pass;
    }

}
