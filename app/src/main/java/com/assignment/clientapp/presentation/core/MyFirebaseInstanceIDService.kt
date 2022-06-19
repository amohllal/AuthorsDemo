package com.assignment.clientapp.presentation.core

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseInstanceIDService : FirebaseMessagingService() {

    private lateinit var notificationModel: NotificationModel
    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            remoteMessage.apply {
                val title = data["title"].toString()
                val message = data["message"].toString()
                val destination = data["author"].toString()
                notificationModel = NotificationModel(title, message, destination)
            }
            this.makeNotification(notificationModel)
        }
    }

}