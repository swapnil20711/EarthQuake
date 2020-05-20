package com.swapnil.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;


import androidx.core.content.ContextCompat;

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

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
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
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        return listView;

    }
}
