package com.cooperbros.createdbfromxml.appxmlparsing;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.crashlytics.android.Crashlytics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public ArrayList<String> mCountry = new ArrayList<String>();
    public ArrayAdapter<String> mArrayAdapter;
    public ListView mListView;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);

        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this);
        try {
            dataBase.open();
        } catch (SQLDataException e) {
            e.printStackTrace();
        }

        XmlPullParser xmlPullParser = getResources().getXml(R.xml.country);

        mListView = (ListView) findViewById(R.id.listView);

        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCountry);

        mListView.setAdapter(mArrayAdapter);


        try {
            new XmlParser(xmlPullParser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}
