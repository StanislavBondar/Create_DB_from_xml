package com.cooperbros.createdbfromxml.appxmlparsing;

import android.content.ContentValues;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;

/**
 * parsing xml & set data to db
 */
public class XmlParser {

    final String LOG_TAG = "myLogs";
    public ArrayList<String> mArrayCountry = new ArrayList<String>();
    public XmlPullParser xpp;
    public String mCountry;
    public String mCity;
    public Integer mCityId;
    public ContentValues mContValues = new ContentValues();


    public XmlParser(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {

        try {

            xpp = xmlPullParser;

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:

                        //получаем название страны
                        if (xpp.getName().equals("country")) {

                            mArrayCountry.add(xpp.getAttributeValue(0));
                            mCountry = xpp.getAttributeValue(0);

                        }
                        //получаем ID города
                        if (xpp.getName().equals("city")) {
                            //City id
                            mCityId = Integer.valueOf(xpp.getAttributeValue(0));

                        }

                        break;
                    // конец тэга
                    case XmlPullParser.END_TAG:
                        break;

                    // содержимое тэга
                    case XmlPullParser.TEXT:

                        //находим город
                        mCity = xpp.getText();
                        Log.d(LOG_TAG, "text = " + xpp.getText());
                        break;


                    default:
                        break;
                }

                if (mCountry != null && mCityId != null && mCity != null) {
                    mContValues.put("country_name", mCountry);
                    mContValues.put("city_name", mCity);
                    mContValues.put("city_id", mCityId);

                    //записываем данные после проверки в БД, т.к. элементы инициализируются не за один цикл
                    try {
                        new DataBase().todo(mContValues);
                    } catch (SQLDataException e) {
                        e.printStackTrace();
                    }
                }
                // следующий элемент
                xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new DataBase().close();

    }

}
