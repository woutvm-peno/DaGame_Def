package andreas.gps;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mainInt extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    //    variables

    private GoogleMap mMap;



    // own created functions

    public void showPopup(View v) {
        Log.i("abc", "Popupfunctie");
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(this, v);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.main_menu, popup.getMenu());
        popup.show();
    }

    // switch to other activity functions

    public void switchGameMode(View view) {
        Intent intent = new Intent(this, gameMode.class);
        startActivity(intent);
    }
    public void switchData1(View view) {
        Intent intent = new Intent(this, data1.class);
        startActivity(intent);
    }
    public void switchData2(View view) {
        Intent intent = new Intent(this, data2.class);
        startActivity(intent);
    }
    public void switchData3(View view) {
        Intent intent = new Intent(this, data3.class);
        startActivity(intent);

    }public void switchMiniggame1(View view) {
        Intent intent = new Intent(this, minigame1.class);
        startActivity(intent);
    }
    public void switchMiniggame2(View view) {
        Intent intent = new Intent(this, minigame2.class);
        startActivity(intent);
    }



    // build in functions

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_buttons, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DaGame");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        // Add a marker in Sydney and move the camera
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);


    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            double loc_latitude = location.getLatitude();
            String loc_longtitude = String.valueOf(location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc));
            }

    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.gameMode) {
            switchGameMode(null);
        }

            return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.data1) {
            switchData1(null);
        }
        else if (id == R.id.data2) {
            switchData2(null);
        }
        else if (id == R.id.data3) {
            switchData3(null);
        }
        else if (id == R.id.minigame1) {
            switchMiniggame1(null);
        }
        else if (id == R.id.minigame2) {
            switchMiniggame2(null);
        }


        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;*/
        return super.onOptionsItemSelected(menuItem);
    }
}

