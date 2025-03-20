package nl.torquelink.presentation.screens.generic.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.torquelink.domain.enums.BaseScreenTabs
import org.jetbrains.compose.resources.painterResource

@Composable
fun DefaultTabIcon(
    tab: BaseScreenTabs,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(tab.icon),
        contentDescription = null,
        modifier = modifier
    )
}