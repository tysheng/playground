package com.tysheng.playground.scroll_rv;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tysheng.playground.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ScrollRvActivity extends AppCompatActivity {
    private RecyclerView mRv;

    public List<ContactsBean> getContactsList(Context context) {

        List<ContactsBean> list = new ArrayList<>();
        ContactsBean bean;
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        Timber.d("cursor.getCount():" + cursor.getCount());

        while (cursor.moveToNext()) {
            bean = new ContactsBean();

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));//姓名

            writeNumbers(resolver, name, bean);
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    private void writeNumbers(final ContentResolver contentResolver, String name, final ContactsBean bean) {
        Timber.d("开始查询 Data 表 : 查询联系人：" + name);

        Cursor dataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Data.DATA1},
                ContactsContract.Data.DISPLAY_NAME_PRIMARY + "= ? ",
                new String[]{name}, null);
        if (dataCursor == null) {
            Timber.w("dataCursor == null ");

            return;
        }
        if (dataCursor.getCount() > 0) {
            bean.setName(name);
            Timber.w(" 电话信息 -- size: " + dataCursor.getCount());
            while (dataCursor.moveToNext()) {
                final String number = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DATA1));
                if (TextUtils.isEmpty(number)) {
                    continue;
                }
                String solved = number.replaceAll("[\\s+\\-]", "");
                if (TextUtils.isDigitsOnly(solved)) {
                    bean.getNumList().add(number);
                    Timber.w(" 电话信息 -- number: " + number);
                } else {
                    Timber.w(" 电话信息(异常) -- number: " + number);
                }
            }
            dataCursor.close();
        } else {
            Timber.w(" 无电话信息 -- name: " + name);
        }
    }

    private Gson gson = new Gson();
    SharedPreferences defaultSharedPreferences;


    public void getNumber(ContentResolver cr) {
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        // use the cursor to access the contacts
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            // get display name
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            // get phone number
            System.out.println(".................." + phoneNumber);
        }
        phones.close();

    }


    public List<ContactsBean> getPhone(Context context) {
        List<ContactsBean> contactList = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        String[] PROJECTION = new String[]{
                ContactsContract.RawContacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Photo.CONTACT_ID};

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String filter = "" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " > 0 "
//                + "and (" + ContactsContract.CommonDataKinds.Phone.TYPE
//                + "=" + ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
//                + " or "
//                + ContactsContract.CommonDataKinds.Phone.TYPE
//                + "=" + ContactsContract.CommonDataKinds.Phone.TYPE_HOME
//                + " or "
//                + ContactsContract.CommonDataKinds.Phone.TYPE
//                + "=" + ContactsContract.CommonDataKinds.Phone.TYPE_WORK
//                + " or "
//                + ContactsContract.CommonDataKinds.Phone.TYPE
//                + "=" + ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE
//                + " )"
                ;
        String order = ContactsContract.Contacts.DISPLAY_NAME + " ASC";// LIMIT " + limit + " offset " + lastId + "";

        Cursor phoneCur = cr.query(uri, PROJECTION, filter, null, order);
        String lastContactId = null;
        while (phoneCur.moveToNext()) {
            String name = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phone = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String id = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Photo.CONTACT_ID));
            if (TextUtils.equals(lastContactId, id)) {
                contactList.get(contactList.size() - 1).addPhone(phone);
            } else {
                ContactsBean dto = new ContactsBean();
                dto.setName(name);
                dto.addPhone(phone);
            dto.setPhotoUrl( phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));
                dto.setContactId(id);
                contactList.add(dto);
            }
            lastContactId = id;
        }
        phoneCur.close();

        return contactList;
    }

    private String key0 = "contact";
    private String key1 = "contact1";

    private String key() {
        return key1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_content, null) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        mRv.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemClickListener((adapter, view, position) -> Timber.d("position " + position));
        getList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseQuickAdapter::setNewData);
    }

    private Flowable<List<String>> getList() {
        return Flowable.create(e -> {
            List<String> list = new ArrayList<>();
            List<ContactsBean> contactsList;
//            String result = defaultSharedPreferences.getString(key(), null);
//            if (!TextUtils.isEmpty(result)) {
//                contactsList = gson.fromJson(result, new TypeToken<List<ContactsBean>>() {
//                }.getType());
//            } else {
//                contactsList = getPhone(ScrollRvActivity.this);
//                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
//                edit.putString(key(), gson.toJson(contactsList));
//                edit.apply();
//            }

            contactsList = getPhone(ScrollRvActivity.this);
            for (ContactsBean bean : contactsList) {
                list.add(bean.toString());
            }
            e.onNext(list);
            e.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 联系人集合Bean类
     */

    public static class ContactsBean {

        private String name;
        private List<String> numList;
        private String contactId;
        private String photoUrl;

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getNumList() {
            return numList;
        }

        public void setNumList(List<String> numList) {
            this.numList = numList;
        }

        public void addPhone(String phone) {
            if (numList == null) {
                numList = new ArrayList<>();
            }
            numList.add(phone);
        }

        public ContactsBean() {
            numList = new ArrayList<>();
        }

        public ContactsBean(String displayName, List<String> numList) {
            super();
            this.name = displayName;
            if (numList == null) {
                this.numList = new ArrayList<>();
            } else {
                this.numList = numList;
            }
        }

        @Override
        public String toString() {
            return "ContactsBean{" +
                    "name='" + name + '\'' +
                    ", numList=" + numList +
                    ", contactId='" + contactId + '\'' +
                    '}';
        }
    }

}
