package com.facci.geolocalizaciondl;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivityDL extends AppCompatActivity {

    LocationManager locationManager;

    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_dl);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        List<String> ListaProvi = locationManager.getAllProviders();

        LocationProvider locationProvider = locationManager.getProvider(ListaProvi.get(0));



    }

    public void LatitudAndLongitud (View v){

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED ) {}

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*10*1000,10,locationListener);

    }



    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitud = location.getLongitude();
            latitud = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText) findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText) findViewById(R.id.txtLatitud);
                    txtLongitud.setText(longitud + "");//la otrqa forma es txtLongitud.setText(---"no me acuerdo buscar en internet"---(longitud))
                    txtLatitud.setText(latitud + "");
                    Toast.makeText(MainActivityDL.this, "Coordenadas encontradas", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
