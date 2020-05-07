package com.swapnil.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {
    public EarthQuakeAdapter(Context context, List<EarthQuake> earthquakes) {
        super(context, 0, earthquakes);
    }

    private static final String LOCATION_SEPARATOR = " of ";

    private String formatDate(Date time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL DD, yyyy");
        return dateFormat.format(time);
    }

    private String formatTime(Date time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(time);
    }

    private String formatMagnitude(Double magnitude) {
        DecimalFormat Magnitudeformat = new DecimalFormat("0.0");
        return Magnitudeformat.format(magnitude);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_style, parent, false);
        }
        String primaryLocation;
        String locationOffset;

        EarthQuake currentEarthquake = getItem(position);
        Date timeInMilli = new Date(currentEarthquake.getTimeInMilliseconds());
        TextView date = (TextView) listView.findViewById(R.id.earthquakeDate);
        String formatDate = formatDate(timeInMilli);
        date.setText(formatDate);
        TextView magnitude = (TextView) listView.findViewById(R.id.earthquakeMagnitude);
        String fomatMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitude.setText(fomatMagnitude);
        TextView time = (TextView) listView.findViewById(R.id.earthquakeTime);
        String formatTime = formatTime(timeInMilli);
        time.setText(formatTime);
        String originalLocation = currentEarthquake.getLocation();
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        TextView LocationOffSetView = (TextView) listView.findViewById(R.id.earthquakeLocation_Offset);
        LocationOffSetView.setText(locationOffset);
        TextView LocationPrimary = (TextView) listView.findViewById(R.id.earthquakePrimary_Location);
        LocationPrimary.setText(primaryLocation);

        return listView;

    }
}
