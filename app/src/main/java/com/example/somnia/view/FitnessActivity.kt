package com.example.somnia.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignIn.getAccountForExtension
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import java.util.*
import java.util.Calendar
import java.util.concurrent.TimeUnit


val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE : Int = 1

class FitnessActivity: AppCompatActivity() {

    //1
    var fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
        .build()



    //account object to use with the API
    //var account = GoogleSignIn.getAccountForExtension(this, fitnessOptions)

    //permisos
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_account)

        val ret = findViewById<Button>(R.id.returnHome_button)
        ret.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }


        //2
        var account = getAccountForExtension(this, fitnessOptions)
        Log.d("HV",account.email)
        Log.d("HV", if (account == null) "isNull" else "notNull")

        (findViewById<Button>(R.id.log_in_google) as Button).setOnClickListener {


            //3
            if  (!GoogleSignIn.hasPermissions(account, fitnessOptions)){
                GoogleSignIn.requestPermissions(this, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                    account, fitnessOptions)

            } else{
                accessGoogleFit()
                startActivity(Intent(this, Home::class.java))
            }
        }


        /*val fitnessOptions: GoogleSignInOptionsExtension = FitnessOptions.builder()
            .addDataType(
                DataType.TYPE_STEP_COUNT_DELTA,
                FitnessOptions.ACCESS_READ
            )
            .build()

        val googleSignInAccount =
            GoogleSignIn.getAccountForExtension(this, fitnessOptions)

        val response =
            Fitness.getHistoryClient(this, googleSignInAccount)
                .readData(
                    DataReadRequest.Builder()
                        .read(DataType.TYPE_STEP_COUNT_DELTA)
                        .setTimeRange(
                            1000,//startTime.getMillis(),
                            10000,//endTime.getMillis(),
                            TimeUnit.MILLISECONDS
                        )
                        .build()
                )

        response.addOnCompleteListener(this){task ->
            Log.d("HV", "that's numberwang!!")
            if(task.isSuccessful) {
                Log.d("HV", task.result?.getDataSet(DataType.TYPE_STEP_COUNT_DELTA)?.dataPoints as String)
            }else{
                Log.d("HV", "whoops lol")
                Log.d("HV",if (task.isComplete) "Y" else "N")
                Log.d("HV",if (task.isCanceled) "Y" else "N")
                Log.d("HV",if (task.isSuccessful) "Y" else "N")
                Log.d("HV",task.exception.toString())
            }
        }*/



        //val intent = Intent(this, FitnessDataDownloadService::class.java)
        //intent.putExtra("N","UMBERWANG!")
        //intent.putExtra("response", response)
        //this.startService(intent)


        //val readDataResult: DataReadResponse = Tasks.await(response)
        //val dataSet = readDataResult.getDataSet(DataType.TYPE_STEP_COUNT_DELTA)

        //print(dataSet.dataPoints)


        //Thread(Runnable {
        //}).start()

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                accessGoogleFit()
            }
        }
    }

    //crea un cliente (API Client)
    private fun accessGoogleFit() {
        val cal: Calendar = Calendar.getInstance()
        cal.time = Date()
        val endTime: Long = cal.getTimeInMillis()
        cal.add(Calendar.YEAR, -1)
        val startTime: Long = cal.getTimeInMillis()
        val readRequest = DataReadRequest.Builder()
            .aggregate(
                DataType.TYPE_STEP_COUNT_DELTA,
                DataType.AGGREGATE_STEP_COUNT_DELTA
            )
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .bucketByTime(1, TimeUnit.DAYS)
            .build()
        val account = getAccountForExtension(this, fitnessOptions)
        Fitness.getHistoryClient(this, account)
            .readData(readRequest)
            .addOnSuccessListener { response ->
                // Use response data here
                Log.d("HV","bueno")
                Toast.makeText(this, "OnSuccess()", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Log.d("HV","no bueno")
                Toast.makeText(this, "OnFailure()" + e.toString(), Toast.LENGTH_LONG).show() }
    }

    /*
    private fun findFitnessDataSources() { // [START find_data_sources]
        // Note: Fitness.SensorsApi.findDataSources() requires the ACCESS_FINE_LOCATION permission.
        Fitness.getSensorsClient(this, account)
            .findDataSources(
                DataSourcesRequest.Builder()
                    .setDataTypes(DataType.TYPE_LOCATION_SAMPLE)
                    .setDataSourceTypes(DataSource.TYPE_RAW)
                    .build())
            .addOnSuccessListener { dataSources ->
                for (dataSource in dataSources) {
                    Log.i(TAG, "Data source found: $dataSource")
                    Log.i(TAG, "Data Source type: " + dataSource.dataType.name)
                    // Let's register a listener to receive Activity data!
                    if (dataSource.dataType == DataType.TYPE_LOCATION_SAMPLE && dataPointListener == null) {
                        Log.i(TAG, "Data source for LOCATION_SAMPLE found!  Registering.")
                        registerFitnessDataListener(dataSource, DataType.TYPE_LOCATION_SAMPLE)
                    }
                }
            }
            .addOnFailureListener { e -> Log.e(TAG, "failed", e) }
        // [END find_data_sources]
    }

    //acceder al sensor client (Api del sensor)
    private fun findFitnessDataSources_2(){
        val response: Task<Void> =
            Fitness.getSensorsClient(this, account)
                .add(
                    SensorRequest.Builder()
                        .setDataType(DataType.TYPE_HEART_RATE_BPM)
                        .setSamplingRate(
                            1,
                            TimeUnit.MINUTES
                        ) // sample once per minute
                        .build(), {task ->
                            Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                            }
                )
    }

    /*private fun buildBLE() {
        val callback: BluetoothManager = object : BluetoothManager() {
            override fun onDeviceFound(device: BleDevice) {
                Log.d(FragmentActivity.TAG, "Found bluetooth Device")
                // A device that provides the requested data types is available
                val pendingResult: PendingResult<Status> =
                    Fitness.BleApi.claimBleDevice(mClient, device)
                Log.d(FragmentActivity.TAG, "Claimed bluetooth Device")
            }

            override fun onScanStopped() {
                // The scan timed out or was interrupted
                Log.d(FragmentActivity.TAG, "Scan was interruped")
            }
        }
        val request = StartBleScanRequest.Builder()
            .setDataTypes(DataType.TYPE_HEART_RATE_BPM)
            .setBleScanCallback(callback)
            .build()
        if (mClient != null) {
            val pendingResult: PendingResult<Status> =
                Fitness.BleApi.startBleScan(mClient, request)
            Log.d(FragmentActivity.TAG, "Find Sources")
            Log.d(FragmentActivity.TAG, "Pending result: " + pendingResult.toString())
        } else {
            Log.d(FragmentActivity.TAG, "API client is null")
        }
    }*/


    /*private val RC_SIGN_IN = 9001

    private var mSignInClient: GoogleSignInClient? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val options =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Drive.SCOPE_FILE)
                .build()
        mSignInClient = GoogleSignIn.getClient(this, options)
    }

    private fun signIn() {
        // Launches the sign in flow, the result is returned in onActivityResult
        val intent = mSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task.isSuccessful) {
                // Sign in succeeded, proceed with account
                val acct = task.result
            } else {
                // Sign in failed, handle failure and update UI
                // ...
            }
        }
    }*/

    /*private var fitnessOptions: FitnessOptions? = null
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
    }*/

    */
}