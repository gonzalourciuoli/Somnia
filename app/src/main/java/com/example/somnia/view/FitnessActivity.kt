package com.example.somnia.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType


class FitnessActivity: AppCompatActivity() {
    private var fitnessOptions: FitnessOptions? = null
    var GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFitnessOption();
        checkFitInstalled();
    }

    private fun checkFitInstalled() {
        if (isGoogleFitPermissionGranted()) {
            // do whatever you need here
        } else {
            requestGoogleFitPermission()
        }
    }

    private fun isGoogleFitPermissionGranted(): Boolean {
        return GoogleSignIn.hasPermissions(
                GoogleSignIn.getLastSignedInAccount(this),
                fitnessOptions!!
            )
    }

    private fun setFitnessOption() {
        fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT)
            .build()
    }

    private fun requestGoogleFitPermission() {
        val account = GoogleSignIn.getAccountForExtension(
            this,
            fitnessOptions!!
        )
        GoogleSignIn.requestPermissions(
            this,
            GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
            account,
            fitnessOptions!!
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(this,"Permission granted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,"Result code" + resultCode, Toast.LENGTH_LONG).show()
        }
    }
}