package nl.torquelink.presentation.screens.generic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.PrimaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.presentation.screens.generic.components.DefaultTabIcon
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.text_logo
import torquelink.composeapp.generated.resources.text_logo_dark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseCompactScreenLayout(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    activeTab: BaseScreenTabs,
    onTabSwitch: (BaseScreenTabs) -> Unit,
    profileAvatar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar (
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                title = {
                    Image(
                        painter = painterResource(
                            if(isSystemInDarkTheme())
                                Res.drawable.text_logo_dark
                            else
                                Res.drawable.text_logo
                        ),
                        contentDescription = null
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ){
                        Icon(Icons.Default.Search, null)
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            TabRow(
                selectedTabIndex = activeTab.ordinal,
                indicator = { tabPositions ->
                    PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions.first()),
                        height = 5.dp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BaseScreenTabs.entries.forEach {
                    Tab(
                        selected = it.ordinal == activeTab.ordinal,
                        onClick = {
                            onTabSwitch(it)
                        },
                        icon = {
                            if(it.isAvatar)
                                profileAvatar?.invoke()
                                    ?: DefaultTabIcon(it)
                            else DefaultTabIcon(it)
                        }
                    )
                }
            }
        }
    }
}