package nl.torquelink.presentation.screens.generic

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.text_logo
import torquelink.composeapp.generated.resources.text_logo_dark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseCompactScreenLayout(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
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
                selectedTabIndex = 1,
                divider = {
                    Spacer(modifier = Modifier.height(5.dp))
                },
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions.first()),
                        height = 5.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Tab(
                    selected = true,
                    onClick = {
//                        scope.launch {
//                            pagerState.animateScrollToPage(index)
//                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                )
                Tab(
                    selected = false,
                    onClick = {
//                        scope.launch {
//                            pagerState.animateScrollToPage(index)
//                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                )
                Tab(
                    selected = false,
                    onClick = {
//                        scope.launch {
//                            pagerState.animateScrollToPage(index)
//                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                )
            }
        }
    }
}