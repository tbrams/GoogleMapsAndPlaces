package brams.dk.googlemapsandplaces;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServiceOk()) {
            init();
        }
    }


    private void init() {

        Button btn = findViewById(R.id.btnMap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(MainActivity.this, MapActivity.class );
                startActivity(mapIntent);
            }
        });
    }

    public boolean isServiceOk() {
        Log.d(TAG, "isServiceOk: Checking Google Play Services Availability...");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (available == ConnectionResult.SUCCESS) {
            // Yep
            return true;

        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            // There is a minor issue, like a version - something fixable, launch the dialog directly
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        } else {
            Toast.makeText(this, "Need Play Services for Goolge Maps", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
