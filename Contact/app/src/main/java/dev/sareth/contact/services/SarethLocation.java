package dev.sareth.contact.services;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import dev.sareth.contact.listeners.CallbackListener;
import dev.sareth.contact.models.ContactLocation;

public class SarethLocation implements LocationListener {

    private LocationManager mLocationManager;
    private final Context context;
    private final CallbackListener.location listener;

    public SarethLocation(Context context, CallbackListener.location listener) {
        this.context = context;
        this.listener = listener;
    }

    public void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Please grant Location permission", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L,
                1F, this);
        Log.d("sareth_location", "Location Services ONLINE");
    }

    public void stopLocationUpdates() {
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        // Handle the new location update here
        double longitude, latitude;
        longitude = location.getLongitude();
        latitude = location.getLatitude();

        ContactLocation contactLocation = new ContactLocation(latitude, longitude);
        this.listener.locationReceived(contactLocation);
        stopLocationUpdates();
        Log.d("sareth_location", "Location services finished");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Handle location provider status changes
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Handle when a location provider is enabled
        Log.d("sareth_location", "onProviderEnabled: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("sareth_location", "onProviderDisabled: " + provider);
    }
}
