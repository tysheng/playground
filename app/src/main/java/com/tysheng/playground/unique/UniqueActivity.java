package com.tysheng.playground.unique;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tysheng.playground.R;

public class UniqueActivity extends AppCompatActivity {

//    PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique);

//        personDao = DBUtil.getInstance(this).getPersonDao();
//        personDao.deleteAll();
//        List<Person> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Person person = new Person();
//            person.setUid(i);
//            person.setName(Character.valueOf((char) i).toString());
//            list.add(person);
//        }
//        personDao.saveInTx(list);


    }

    public void delete() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cur.moveToNext()) {
            try {
                String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                System.out.println("The uri is " + uri.toString());
                cr.delete(uri, null, null);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

    public void click(View view) {
//        Person person = new Person();
//        person.setUid(1);
//        person.setName("insert");
//        personDao.insertOrReplace(person);
    }

    public void update(View view) {
//        Person person = new Person();
//        person.setUid(2);
//        person.setName("update");
//        personDao.update(person);
    }

    public void save(View view) {
//        Person person = new Person();
//        person.setUid(3);
//        person.setName("save");
//        personDao.save(person);

    }

    public void print(View view) {
//        List<Person> list = personDao.loadAll();
//        for (Person person : list) {
//            Timber.d(person.toString());
//        }
    }

    public void insertorreplace(View view) {

//        personDao.insertOrReplace(person);
    }

    public void delete(View view) {
        delete();
    }
}
