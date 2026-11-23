
package com.example.corda_appdev;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.corda_appdev.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LabCheckLifecycle";
    private static final String KEY_LOG = "key_lifecycle_log";

    private ActivityMainBinding binding;
    private final StringBuilder traceBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Restore logs after screen rotation
        if (savedInstanceState != null) {
            traceBuilder.append(
                    savedInstanceState.getString(KEY_LOG, "")
            );
        }

        recordEvent("onCreate");

        // Log Check-In Heartbeat Button
        binding.btnSimulateTick.setOnClickListener(v ->
                recordEvent("Manual check-in probe (pkg="
                        + getPackageName() + ")")
        );

        // Clear Log Button
        binding.btnClearLog.setOnClickListener(v -> {
            traceBuilder.setLength(0);
            binding.tvLifecycleLog.setText("Corda_Cleared");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recordEvent("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        recordEvent("onResume");
    }

    @Override
    protected void onPause() {
        recordEvent("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        recordEvent("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        recordEvent("onDestroy");
        super.onDestroy();
    }

    // Save logs during screen rotation
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_LOG, traceBuilder.toString());
        super.onSaveInstanceState(outState);
    }

    // Record lifecycle events
    private void recordEvent(String eventName) {

        String ts = new SimpleDateFormat(
                "HH:mm:ss",
                Locale.getDefault()
        ).format(new Date());

        String line = "[" + ts + "] " + eventName + "\n";

        traceBuilder.append(line);

        Log.d(TAG, line.trim());

        if (binding != null) {
            binding.tvLifecycleLog.setText(
                    traceBuilder.toString()
            );
        }
    }
}
