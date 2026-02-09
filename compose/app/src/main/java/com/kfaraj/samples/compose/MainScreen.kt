package com.kfaraj.samples.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun MainScreen() {
    val contentPadding = remember {
        object : PaddingValues {
            var paddingHolder by mutableStateOf(PaddingValues(0.dp))

            override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp =
                paddingHolder.calculateLeftPadding(layoutDirection)

            override fun calculateTopPadding(): Dp =
                paddingHolder.calculateTopPadding()

            override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp =
                paddingHolder.calculateRightPadding(layoutDirection)

            override fun calculateBottomPadding(): Dp =
                paddingHolder.calculateBottomPadding()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            contentPadding.paddingHolder = PaddingValues(Random.nextInt(0..8).dp)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_shuffle),
                            contentDescription = "Shuffle"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding + contentPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = 100
            ) { index ->
                Card(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Item: $index",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
