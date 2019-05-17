package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AuxiliarSQLite extends SQLiteOpenHelper {

    String SQL_Tabla = "CREATE TABLE Reservacion ("
            +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"Nombre TEXT)";

    public AuxiliarSQLite(Context context, String DBname,
                          SQLiteDatabase.CursorFactory factory,
                          int version){
        super(context,DBname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Reservacion");
        db.execSQL(SQL_Tabla);
    }
}
