package com.shem.todoapp160420033.util

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class TodoWorker(val context: Context, val params:WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        NotificationHelper(context)
            .createNotification(inputData.getString("title").toString(),
                                inputData.getString("message").toString())
        return Result.success()
    }
}
