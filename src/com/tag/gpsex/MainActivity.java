package com.tag.gpsex;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WebView position;
	private TextView textInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		position = (WebView) findViewById(R.id.webView);
		position.getSettings().setJavaScriptEnabled(true);

		textInfo = (TextView) findViewById(R.id.textInfo);
		textInfo.setText("Location : ");

		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//For Retrieving new Location
		LocationListener locListener = new MyLocationListener();

		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				locListener);
	}

	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			
			// Retrieving Latitude
			location.getLatitude();
			// Retrieving getLongitude
			location.getLongitude();
			
			textInfo.setText("");
			String text = "My Current Location is:\nLatitude = "
					+ location.getLatitude() + "\nLongitude = "
					+ location.getLongitude();
			textInfo.setText(text);
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
					.show();

			// set Google Map on webview
			String url = "http://maps.google.com/staticmap?center="
					+ location.getLatitude() + "," + location.getLongitude()
					+ "&zoom=14&size=512x512&maptype=mobile/&markers="
					+ location.getLatitude() + "," + location.getLongitude();
			position.loadUrl(url);
		}

		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "GPS Disabled",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(), "GPS Enabled",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}
}
