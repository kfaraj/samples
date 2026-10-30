package com.kfaraj.samples.darktheme.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * Displays a list of entries as a dialog.
 */
@Composable
fun ListPreference(
    title: String,
    entries: Array<String>,
    entryValues: Array<String>,
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var openDialog by rememberSaveable { mutableStateOf(false) }
    ListItem(
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        modifier = modifier
            .clickable(
                onClick = {
                    openDialog = true
                }
            ),
        supportingContent = entries.getOrNull(entryValues.indexOf(value))?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        leadingContent = {
            Spacer(
                modifier = Modifier.size(24.dp)
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        )
    )
    if (openDialog) {
        BasicAlertDialog(
            onDismissRequest = {
                openDialog = false
            }
        ) {
            Surface(
                shape = AlertDialogDefaults.shape,
                color = AlertDialogDefaults.containerColor,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.Start)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(weight = 1.0f, fill = false)
                            .padding(bottom = 24.dp)
                            .align(Alignment.Start)
                    ) {
                        val scrollState = rememberScrollState()
                        Column(
                            modifier = Modifier
                                .verticalScroll(scrollState)
                                .selectableGroup()
                        ) {
                            entries.forEachIndexed { index, entry ->
                                ListItem(
                                    headlineContent = {
                                        Text(
                                            text = entry
                                        )
                                    },
                                    modifier = Modifier
                                        .selectable(
                                            selected = entryValues[index] == value,
                                            role = Role.RadioButton,
                                            onClick = {
                                                openDialog = false
                                                onValueChange(entryValues[index])
                                            }
                                        )
                                        .padding(horizontal = 8.dp),
                                    leadingContent = {
                                        RadioButton(
                                            selected = entryValues[index] == value,
                                            onClick = null
                                        )
                                    },
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .align(Alignment.End)
                    ) {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }
                        ) {
                            Text(
                                text = stringResource(android.R.string.cancel)
                            )
                        }
                    }
                }
            }
        }
    }
}
