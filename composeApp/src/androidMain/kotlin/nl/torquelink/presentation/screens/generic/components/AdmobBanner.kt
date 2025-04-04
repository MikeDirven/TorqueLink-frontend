package nl.torquelink.presentation.screens.generic.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdmobBanner(
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            // on below line specifying ad view.
            AdView(context).apply {
                // on below line specifying ad size
                //adSize = AdSize.BANNER
                // on below line specifying ad unit id
                // currently added a test ad unit id.
                setAdSize(AdSize.LEADERBOARD)
//                adUnitId = "ca-app-pub-2042983441463835/6993294502"
                adUnitId = "ca-app-pub-3940256099942544/9214589741"
                // calling load ad to load our ad.
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}