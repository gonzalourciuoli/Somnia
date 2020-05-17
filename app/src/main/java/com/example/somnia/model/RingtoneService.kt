package com.example.somnia.model

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.somnia.R
import com.example.somnia.view.AlarmMangerActivity
import com.example.somnia.view.AlarmsActivity
import com.example.somnia.view.Home
import com.example.somnia.view.New_alarmActivity

class RingtoneService : Service() {
    companion object{
        lateinit var r: Ringtone
    }
    var id: Int = 0
    var isRunning: Boolean = false
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var state: String? = intent!!.getStringExtra("extra")
        assert(state!=null)
        playAlarm()
        when(state){
            "on" -> id =1
            "off" -> id = 0
        }
        if (!this.isRunning && id == 1){
            val principal = Intent(this, AlarmMangerActivity::class.java)
            startActivity(principal)
            playAlarm()
            this.isRunning = true
            this.id = 0


        }
        else if(this.isRunning && id == 0){
            r.stop()
            this.isRunning = false
            this.id = 0
        }
        else if(!this.isRunning && id == 0){
            this.isRunning = false
            this.id = 0
        }
        else if(this.isRunning && id == 1){
            this.isRunning = true
            this.id = 1
        }
        return START_NOT_STICKY
    }

    private fun playAlarm(){
        var alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri==null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        r = RingtoneManager.getRingtone(baseContext,alarmUri)
        r.play()
    }

    private fun fireNotification(){
        var mainintent: Intent = Intent(this, Home::class.java)
        var pi: PendingIntent = PendingIntent.getActivity(this,0,mainintent,0)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notify_manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notification: Notification = NotificationCompat.Builder(this).setContentTitle("title").setSmallIcon(
            R.mipmap.ic_launcher).setSound(defaultSoundUri).setContentText("click").setContentIntent(pi).setAutoCancel(true).build()
        notify_manager.notify(0,notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.isRunning = false
    }

}