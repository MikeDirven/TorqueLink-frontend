package nl.torquelink.presentation.screens.generic.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> PaginatedLazyColum(
    items: List<T>,
    loadNextPage: () -> Unit,
    isLoading: Boolean,
    hasMore: Boolean,
    modifier: Modifier = Modifier,
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement. Vertical = if (!reverseLayout) Arrangement. Top else Arrangement. Bottom,
    horizontalAlignment: Alignment. Horizontal = Alignment.Start,
    advertisement: @Composable (() -> Unit)? = null,
    itemContent: @Composable() (LazyItemScope.(index: Int, item: T) -> Unit)
) {
    val listState = rememberLazyListState()
    val endReached = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(endReached.value) {
        if (endReached.value && !isLoading && hasMore) {
            loadNextPage()
        }
    }

    LazyColumn(
        modifier = modifier,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        state = listState
    ) {
        itemsIndexed(items) { index, item ->
            itemContent(index, item)

            advertisement?.let { advertismentComponent ->
                if (((index + 1) % 5 == 0) || (items.size < 5 && index == items.lastIndex)) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth().height(98.dp).padding(top = 8.dp)
                    ) {
                        advertismentComponent()
                    }
                }
            }
        }

        if (isLoading) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                    )
                }
            }
        }
    }
}