package com.example.somnia.view

import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.BleDevice
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.request.BleScanCallback
import com.google.android.gms.fitness.request.OnDataPointListener


class SensorActivity : AppCompatActivity(), OnDataPointListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private val REQUEST_OAUTH = 1
    private val AUTH_PENDING = "auth_state_pending"
    private var authInProgress = false
    private var mApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bleScanCallbacks: BleScanCallback = object : BleScanCallback() {
            override fun onDeviceFound(device: BleDevice) {
                // A device that provides the requested data types is available
            }

            override fun onScanStopped() {
                // The scan timed out or was interrupted
            }
        }

        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING)
        }

        mApiClient = GoogleApiClient.Builder(this)
            .addApi(Fitness.SENSORS_API)
            .addScope(Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()

        /*val response: Task<Void> = Fitness.getBleClient(
            this,
            GoogleSignIn.getLastSignedInAccount(this)
        )
            .startBleScan(
                Arrays.asList(DataType.TYPE_STEP_COUNT_DELTA),
                1000, bleScanCallbacks
            )*/
    }
    override fun onDataPoint(p0: DataPoint?) {
        TODO("Not yet implemented")
    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        if (!authInProgress) {
            try {
                authInProgress = true
                p0.startResolutionForResult(this@SensorActivity, REQUEST_OAUTH)
            } catch (e: SendIntentException) {
            }
        } else {
            Log.e("GoogleFit", "authInProgress")
        }
    }

    override fun onStart() {
        super.onStart()
        mApiClient?.connect(); //inicia instancia GoogleApiClient
    }
}