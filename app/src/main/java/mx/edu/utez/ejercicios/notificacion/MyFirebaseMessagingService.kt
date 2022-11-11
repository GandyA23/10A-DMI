package mx.edu.utez.ejercicios.notificacion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import mx.edu.utez.ejercicios.MenuActivity
import mx.edu.utez.ejercicios.R

/**
 * Cuando llega un mensaje, llega a esta clase
 * */
class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        var messageStr = ""

        // En caso de que reciba un JSON, entra aquí
        if (message.data.isNotEmpty()) {
            messageStr = message.data.toString()
            Log.d("Notification-data ->", messageStr)
        }

        // Si solo avisa, entonces entra aquí
        message.notification?.let {
            messageStr = it.body.toString()
            Log.d("Notification-notify ->", messageStr)
        }

        createNotification(messageStr)

        super.onMessageReceived(message)
    }

    /**
     * Cuanod firebase tenga una nueva token para el dispositivo*/
    override fun onNewToken(token: String) {
        sendTokenToServer(token)

        super.onNewToken(token)
    }

    fun sendTokenToServer(token: String) {
        // Guardalo en firebase o en el servicio
    }

    /**
     * Muestra una notificación
     * */
    fun createNotification (message: String) {
        val intent = Intent(this, MenuActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        var channel = getString(R.string.app_name)
        var sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder = NotificationCompat.Builder(
            this,
            channel)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("UTEZ")
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(sound)
            .setContentIntent(pendingIntent)

        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Verifica la versión de Android para mostrar la notificacion
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel2 = NotificationChannel(channel, getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT)

            notificationManager.createNotificationChannel(channel2)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}