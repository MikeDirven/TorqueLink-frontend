package nl.torquelink.domain.utils.firebase

import android.content.Context
import android.os.Build
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

object FirebaseUtil {
    private fun FirebaseCrashlytics.setBaseFields() {
        this.setCustomKey(MANUFACTURER, Build.MANUFACTURER)
        this.setCustomKey(MODEL, Build.MODEL)
        this.setCustomKey(PRODUCT, Build.PRODUCT)
        this.setCustomKey(DEVICE, Build.DEVICE)
        this.setCustomKey(OS, "Android")
        this.setCustomKey(API_LEVEL, Build.VERSION.SDK_INT.toString())
    }


    fun initializeFirebase(context: Context) {
        FirebaseApp.initializeApp(context)
        initializeAndConfigureAnalytics(context)
        initializeAndConfigureCrashlytics()
    }

    private fun initializeAndConfigureAnalytics(context: Context) {
        val analytics = FirebaseAnalytics.getInstance(context)
        analytics.setAnalyticsCollectionEnabled(true)
    }

    private fun initializeAndConfigureCrashlytics() {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setBaseFields()
        crashlytics.isCrashlyticsCollectionEnabled = true
    }
    private const val MANUFACTURER = "Manufacturer"
    private const val MODEL = "Model"
    private const val PRODUCT = "Product"
    private const val DEVICE = "Device"
    private const val OS = "OS"
    private const val API_LEVEL = "API Level"
    private const val USER_ID = "User ID"
    private const val USER_NAME = "User Name"
}