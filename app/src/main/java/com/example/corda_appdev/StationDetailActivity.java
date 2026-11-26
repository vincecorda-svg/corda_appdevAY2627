
package com.example.corda_appdev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.corda_appdev.databinding.ActivityStationDetailBinding;

public class StationDetailActivity extends AppCompatActivity {

    private ActivityStationDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStationDetailBinding.inflate(
                getLayoutInflater()
        );

        setContentView(binding.getRoot());

        // INSPECTION STATUS OPTIONS
        String[] statuses = {
                "Ready",
                "Needs Check",
                "Issue Logged"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                statuses
        );

        binding.spStatus.setAdapter(adapter);

        // RECEIVE DATA FROM MAIN ACTIVITY
        Intent intent = getIntent();

        String code = intent.getStringExtra(
                MainActivity.EXTRA_STATION_CODE
        );

        String status = intent.getStringExtra(
                MainActivity.EXTRA_STATUS
        );

        String note = intent.getStringExtra(
                MainActivity.EXTRA_ISSUE_NOTE
        );

        if (code == null) {
            code = "PC-07";
        }

        if (status == null) {
            status = "Needs Check";
        }

        binding.tvDetailTitle.setText(
                "Inspect Station: " + code
        );

        if (note != null) {
            binding.etIssueNote.setText(note);
        }

        // SELECT CURRENT STATUS
        for (int i = 0; i < statuses.length; i++) {

            if (statuses[i].equals(status)) {
                binding.spStatus.setSelection(i);
                break;
            }
        }

        // SAVE INSPECTION
        binding.btnSaveInspection.setOnClickListener(v -> {

            String updatedNote = binding.etIssueNote
                    .getText()
                    .toString()
                    .trim();

            String selectedStatus = binding.spStatus
                    .getSelectedItem()
                    .toString();

            // REQUIRE NOTE WHEN ISSUE IS LOGGED
            if ("Issue Logged".equals(selectedStatus)
                    && updatedNote.isEmpty()) {

                Toast.makeText(
                        StationDetailActivity.this,
                        "Enter an issue note before saving.",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Intent reply = new Intent();

            reply.putExtra(
                    MainActivity.EXTRA_STATUS,
                    selectedStatus
            );

            reply.putExtra(
                    MainActivity.EXTRA_ISSUE_NOTE,
                    updatedNote.isEmpty()
                            ? "No issues reported"
                            : updatedNote
            );

            setResult(RESULT_OK, reply);

            finish();
        });
    }
}
