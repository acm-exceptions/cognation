package com.exceptions.cognation;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class WorkSchedule extends AppCompatActivity {

    ExtendedCalendarView calendarView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (ExtendedCalendarView)findViewById(R.id.calendar);
        textView2 = (TextView)findViewById(R.id.textView2);

        AddEvents();
    }

    void AddEvents() {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_RED);
        values.put(CalendarProvider.DESCRIPTION, "Some Description");
        values.put(CalendarProvider.LOCATION, "Some location");
        values.put(CalendarProvider.EVENT, "Event name");
        Calendar cal = Calendar.getInstance();
        cal.set(2016,10,28,9,39);
        TimeZone tz = TimeZone.getDefault();
        int julianDay = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));
        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, julianDay);
        TimeZone tz2 = TimeZone.getDefault();
        cal.set(2016,10,28,10,39);
        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz2.getOffset(cal.getTimeInMillis())));
        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);
        Uri uri = getContentResolver().insert(CalendarProvider.CONTENT_URI, values);

        ContentValues values2 = new ContentValues();
        values2.put(CalendarProvider.COLOR, Event.COLOR_GREEN);
        values2.put(CalendarProvider.DESCRIPTION, "Some Description");
        values2.put(CalendarProvider.LOCATION, "Some location");
        values2.put(CalendarProvider.EVENT, "Event name");
        cal.set(2016,10,25,9,39);
        TimeZone tz3 = TimeZone.getDefault();
        int julianDay2 = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz3.getOffset(cal.getTimeInMillis())));
        values2.put(CalendarProvider.START, cal.getTimeInMillis());
        values2.put(CalendarProvider.START_DAY, julianDay2);
        TimeZone tz4 = TimeZone.getDefault();
        cal.set(2016,10,25,10,39);
        int endDayJulian2 = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz4.getOffset(cal.getTimeInMillis())));
        values2.put(CalendarProvider.END, cal.getTimeInMillis());
        values2.put(CalendarProvider.END_DAY, endDayJulian2);
        Uri uri2 = getContentResolver().insert(CalendarProvider.CONTENT_URI, values2);
    }
}
