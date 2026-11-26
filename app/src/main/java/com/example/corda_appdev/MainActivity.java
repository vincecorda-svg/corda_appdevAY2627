
package com.example.corda_appdev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.corda_appdev.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STATION_CODE = "extra_station_code";
    public static final String EXTRA_STATUS = "extra_status";
    public static final String EXTRA_ISSUE_NOTE = "extra_issue_note";

    private ActivityMainBinding binding;

    private int stationIndex = 0;

    private final String[] stationCodes = {
            "PC-07", "PC-08", "PC-09"
    };

    private final String[] stationNames = {
            "Android Studio SDK",
            "Java Development Workstation",
            "Network Testing Workstation"
    };

    private final String[] stationStatuses = {
            "Needs Check",
            "Ready",
            "Issue Logged"
    };

    private final String[] stationNotes = {
            "Emulator slow to boot",
            "No issues reported",
            "Network connection needs checking"
    };

    private final String[] stationSpecs = {
            "16GB RAM • Emulator KVM • USB Port Checked",
            "8GB RAM • Java JDK • Compiler Checked",
            "8GB RAM • LAN Connection • Network Checked"
    };

    // Receive inspection results
    private final ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            String updatedStatus =
                                    result.getData().getStringExtra(EXTRA_STATUS);

                            String updatedNote =
                                    result.getData().getStringExtra(EXTRA_ISSUE_NOTE);

                            if (updatedStatus != null && updatedNote != null) {

                                stationStatuses[stationIndex] = updatedStatus;
                                stationNotes[stationIndex] = updatedNote;

                                updateStationDisplay();

                                Toast.makeText(
                                        MainActivity.this,
                                        "Updated " + stationCodes[stationIndex]
                                                + " -> " + updatedStatus,
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Display initial workstation
        updateStationDisplay();

        // INSPECT BUTTON
        binding.btnInspectStation.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    StationDetailActivity.class
            );

            intent.putExtra(EXTRA_STATION_CODE, stationCodes[stationIndex]);
            intent.putExtra(EXTRA_STATUS, stationStatuses[stationIndex]);
            intent.putExtra(EXTRA_ISSUE_NOTE, stationNotes[stationIndex]);

            detailLauncher.launch(intent);
        });

        // SWITCH STATION BUTTON
        binding.btnSwitchStation.setOnClickListener(v -> {

            stationIndex++;

            if (stationIndex >= stationCodes.length) {
                stationIndex = 0;
            }

            updateStationDisplay();

            Toast.makeText(
                    MainActivity.this,
                    "Switched to " + stationCodes[stationIndex],
                    Toast.LENGTH_SHORT
            ).show();
        });

        // CONTACT MAINTENANCE BUTTON
        binding.btnContactMaintenance.setOnClickListener(v -> {

            Intent dialIntent = new Intent(Intent.ACTION_DIAL);

            dialIntent.setData(Uri.parse("tel:09123456789"));

            startActivity(dialIntent);
        });

        // SHARE STATUS BUTTON
        binding.btnShareReport.setOnClickListener(v -> {

            String report =
                    "COMPUTER LAB - WORKSTATION READINESS\n\n" +
                            "Station: " + stationCodes[stationIndex] + "\n" +
                            "Workstation: " + stationNames[stationIndex] + "\n" +
                            "Status: " + stationStatuses[stationIndex] + "\n" +
                            "Note: " + stationNotes[stationIndex] + "\n" +
                            "Specifications: " + stationSpecs[stationIndex];

            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, report);

            startActivity(
                    Intent.createChooser(shareIntent, "Share Station Report")
            );
        });
    }

    // UPDATE STATION INFORMATION
    private void updateStationDisplay() {

        binding.tvStationCode.setText(
                stationCodes[stationIndex] + " - " +
                        stationNames[stationIndex]
        );

        binding.tvStationStatus.setText(
                stationStatuses[stationIndex]
        );

        binding.tvStationNote.setText(
                "Note: " + stationNotes[stationIndex]
        );

        binding.tvStationSpec.setText(
                stationSpecs[stationIndex]
        );
    }
}
