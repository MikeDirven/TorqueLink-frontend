package nl.torquelink.presentation.screens.generic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
        modifier = if(tab.isAvatar) Modifier.background(
            color = MaterialTheme.colorScheme.tertiaryContainer,
            shape = CircleShape
        ) else Modifier
    )
}