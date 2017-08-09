package br.com.webeleven.agenda.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.webeleven.agenda.Entities.Contact;

/**
 * Created by rodrigo on 09/08/17.
 */

public class ContactDAO extends SQLiteOpenHelper {
    public ContactDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE contacts (" +
                        "id INTEGER PRIMARY KEY, " +
                        "name VARCHAR(50) NOT NULL, " +
                        "address VARCHAR(255), " +
                        "phone VARCHAR(18), " +
                        "email VARCHAR(255), " +
                        "score REAL);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS contacts";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void create(Contact contact) {
        SQLiteDatabase readableDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", contact.getName());
        contentValues.put("address", contact.getAddress());
        contentValues.put("phone", contact.getPhone());
        contentValues.put("email", contact.getEmail());
        contentValues.put("score", contact.getScore());

        readableDatabase.insert("contacts", null, contentValues);
    }
}