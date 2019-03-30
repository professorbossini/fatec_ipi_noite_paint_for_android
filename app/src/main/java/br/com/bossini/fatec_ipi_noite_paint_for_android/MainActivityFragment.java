package br.com.bossini.fatec_ipi_noite_paint_for_android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment {

    private DoodleView doodleView;

    private float acceleration;
    private float lastAcceleration;
    private float currentAcceleration;

    private boolean dialogOnScreen = false;

    private static final int ACCELERATION_THRESHOLD = 1000000;

    private static final int SAVE_IMAGE_PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View raiz =
                inflater.inflate (
                        R.layout.fragment_main,
                        container,
                        false
                );
        setHasOptionsMenu(true);
        doodleView = raiz.findViewById(R.id.doodleView);
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        lastAcceleration = SensorManager.GRAVITY_EARTH;
        acceleration = 0.0f;
        return raiz;
    }

    private SensorEventListener listener =
            new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];

                    lastAcceleration = currentAcceleration;
                    currentAcceleration =
                            x * x + y * y + z * z;

                    acceleration = currentAcceleration *
                            (currentAcceleration - lastAcceleration);

                    if (acceleration > ACCELERATION_THRESHOLD){
                        //confirmErase();
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
}
