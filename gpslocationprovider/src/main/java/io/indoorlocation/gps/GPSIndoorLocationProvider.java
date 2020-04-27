package io.indoorlocation.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import io.indoorlocation.core.IndoorLocation;
import io.indoorlocation.core.IndoorLocationProvider;


public class GPSIndoorLocationProvider extends IndoorLocationProvider implements android.location.LocationListener{

    private Context mContext;
    private boolean checkGPS = false;
    private boolean checkNetwork = false;
    private boolean isStarted = false;

    private long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private long MIN_TIME_BW_UPDATES = 5000;

    private LocationManager locationManager;

    public GPSIndoorLocationProvider(Context context) {
        super();
        mContext = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            checkNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
    }

    public GPSIndoorLocationProvider(Context context, long updateMinimumDistance, long updateMinimumTime) {
        super();
        mContext = context;
        MIN_DISTANCE_CHANGE_FOR_UPDATES = updateMinimumDistance;
        MIN_TIME_BW_UPDATES = updateMinimumTime;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            checkNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

    }

    @Override
    public void start() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.dispatchOnProviderError(new Error("Missing permission : " + Manifest.permission.ACCESS_FINE_LOCATION));
            return;
        }
        if (checkGPS) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            isStarted = true;
        }
        if (checkNetwork) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            isStarted = true;
        }
    }

    @Override
    public void stop() {
        locationManager.removeUpdates(this);
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    /*
        ANDROID LOCATION LISTENER
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Checked gps status
    }

    @Override
    public void onProviderEnabled(String provider) {
        this.dispatchOnProviderStarted();
    }

    @Override
    public void onProviderDisabled(String provider) {
        this.dispatchOnProviderStopped();
    }

    @Override
    public void onLocationChanged(Location location) {
        IndoorLocation il = new IndoorLocation(location, null);
        this.dispatchIndoorLocationChange(il);
    }

    @Override
    public boolean supportsFloor() {
        return false;
    }

}
