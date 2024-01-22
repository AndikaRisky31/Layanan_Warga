package com.celestial.layang.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle the incoming message here
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.notification != null) {
            Log.d(TAG, "Notification Message Body: ${remoteMessage.notification!!.body}")
        }
    }

    companion object {
        private const val TAG = "MyFirebaseMessaging"
    }
}
