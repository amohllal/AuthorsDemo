package com.assignment.clientapp.presentation.core

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseInstanceIDService : FirebaseMessagingService() {

    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        var title = ""
        var message = ""
        var destination = ""
        if (remoteMessage.data.isNotEmpty()) {
            remoteMessage.apply {
                title = data["title"].toString()
                message = data["message"].toString()
                destination = data["author"].toString()
            }

            val notificationModel = NotificationModel(title, message, destination)
            this.makeNotification(notificationModel)
        }
    }

}