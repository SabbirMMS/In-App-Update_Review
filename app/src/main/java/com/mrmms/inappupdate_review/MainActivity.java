package com.mrmms.inappupdate_review;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare an instance of InAppUpdateHelper
    InAppUpdateHelper inAppUpdateHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the InAppUpdateHelper
        inAppUpdateHelper = new InAppUpdateHelper(MainActivity.this);

        // Request a flexible in-app update
        // Pass "true" for an immediate update if required
        inAppUpdateHelper.launchInAppUpdate(false);

        // Request the in-app review dialog
        // The maxOpen parameter specifies the number of app launches before the review dialog is shown
        // For example, if the value is set to 5, the review dialog will appear on the 6th launch of the app
        inAppUpdateHelper.requestInAppReview(5);

    }
}