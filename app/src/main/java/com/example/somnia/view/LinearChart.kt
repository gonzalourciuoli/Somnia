package com.example.somnia.view
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHealth
import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.graphics.vector.GradientKey
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*


class LinearChart: AppCompatActivity()  {

    val ages = ArrayList<Int>()
    val hours4 = ArrayList<Int>()

    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dataCollection : CollectionReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_chart)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        dataCollection = db.collection("linear_chart")


        val lineChart : AnyChartView = findViewById(R.id.linear_chart)
        APIlib.getInstance().setActiveAnyChartView(lineChart)
        createRangeChart(lineChart)
        lineChart.setBackgroundColor("#9C7DFF")

        initBluetooth()

    }

    fun createRangeChart(lineChart: AnyChartView){

        val line = AnyChart.line()
        val background = line.background()
        val entries = ArrayList<DataEntry>()

        line.animation(true)


        dataCollection
            .get()
            .addOnSuccessListener {

                for (document in it) {
                    ages.add(Integer.parseInt(document.get("xValue").toString()!!))
                    hours4.add(Integer.parseInt(document.get("yValue").toString()!!))
                }
                for (i in ages.indices){
                    entries.add(ValueDataEntry(ages[i],hours4[i]))
                }
                val set = Set.instantiate()
                set.data(entries)

                val lineMap: Mapping = set.mapAs("{ x: 'x', value: 'value' }")

                val l: Line = line.line(lineMap)
                l.name("People")
                l.hovered().markers().enabled(true)
                l.hovered().markers().type(MarkerType.CIRCLE).size(4.0)
                l.tooltip().position("right").anchor(Anchor.LEFT_CENTER).offsetX(5.0).offsetY(5.0)


                line.title("AVERAGE OF POPULATION")
                line.yAxis(0).title("Hours of sleep")
                line.xAxis(0).title("Age")
                line.legend(true)

                val range_colors = arrayOf("#9933FF")
                line.palette(range_colors)
                background.stroke("#663399")
                //background.fill(GradientKey("#dcd0ff",0,1))
                lineChart.setChart(line)
            }
            .addOnFailureListener { exception ->
                Log.w("Linear chart data", "Error getting documents: ", exception)
            }

    }

    private fun initBluetooth(){
        val REQUEST_ENABLE_BT = 0
        val REQUEST_DISCOVER_BT = 1

        var bluetoothHealth: BluetoothHealth? = null

        // Get the default adapter
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(this, "Bluetooth is not aviable", Toast.LENGTH_LONG).show()
        }

        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        val profileListener = object : BluetoothProfile.ServiceListener {

            override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
                if (profile == BluetoothProfile.HEALTH) {
                    bluetoothHealth = proxy as BluetoothHealth
                }
            }

            override fun onServiceDisconnected(profile: Int) {
                if (profile == BluetoothProfile.HEALTH) {
                    bluetoothHealth = null
                }
            }
        }

        // Establish connection to the proxy.
        //bluetoothAdapter?.getProfileProxy(this, profileListener, BluetoothProfile.HEALTH)

        // ... call functions on bluetoothHeadset

        // Close proxy connection after use.
        //bluetoothAdapter?.closeProfileProxy(BluetoothProfile.HEALTH, bluetoothHealth)
    }


}

