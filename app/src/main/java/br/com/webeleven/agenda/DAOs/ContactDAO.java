package br.com.webeleven.agenda.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.webeleven.agenda.Entities.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public Contact store(Contact contact) {
        if (contact.hasId()) {
            return update(contact);
        }

        return create(contact);
    }

    private Contact update(Contact contact) {
        SQLiteDatabase readableDatabase = getReadableDatabase();

        readableDatabase.update(
                "contacts",
                getContetValues(contact),
                "id = ?",
                new String[] {String.valueOf(contact.getId())}
        );

        return contact;
    }

    public Contact create(Contact contact) {
        SQLiteDatabase readableDatabase = getReadableDatabase();

        readableDatabase.insert("contacts", null, getContetValues(contact));

        return contact;
    }

    private ContentValues getContetValues(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", contact.getName());
        contentValues.put("address", contact.getAddress());
        contentValues.put("phone", contact.getPhone());
        contentValues.put("email", contact.getEmail());
        contentValues.put("score", contact.getScore());

        return contentValues;
    }

    public List<Contact> getAllContacts() {
        String query = "SELECT * FROM contacts;";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        List<Contact> contacts = new ArrayList<Contact>();

        if (c.moveToFirst()) {
            while (c.moveToNext()) {
                Contact contact = new Contact();

                contact
                        .setId(c.getInt(c.getColumnIndex("id")))
                        .setName(c.getString(c.getColumnIndex("name")))
                        .setAddress(c.getString(c.getColumnIndex("address")))
                        .setPhone(c.getString(c.getColumnIndex("phone")))
                        .setEmail(c.getString(c.getColumnIndex("email")))
                        .setScore(c.getFloat(c.getColumnIndex("score")));

                contacts.add(contact);
            }
        }

        c.close();

        return contacts;
    }

    public void delete(Contact contact) {
        SQLiteDatabase readableDatabase = getReadableDatabase();

        String[] params = {String.valueOf(contact.getId())};

        readableDatabase.delete("contacts", "id = ?", params);
    }
}