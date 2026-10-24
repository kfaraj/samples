package com.kfaraj.samples.darktheme.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kfaraj.samples.darktheme.ui.theme.AppTheme

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
    var showDialog by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    showDialog = true
                }
            )
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier.size(24.dp)
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = entries.getOrNull(entryValues.indexOf(value)) ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = {
                showDialog = false
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
                            color = AlertDialogDefaults.titleContentColor,
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
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .selectable(
                                            selected = value == entryValues[index],
                                            role = Role.RadioButton,
                                            onClick = {
                                                showDialog = false
                                                onValueChange(entryValues[index])
                                            }
                                        )
                                        .padding(horizontal = 24.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = value == entryValues[index],
                                        onClick = null
                                    )
                                    Text(
                                        text = entry,
                                        modifier = Modifier.padding(start = 16.dp),
                                        color = AlertDialogDefaults.textContentColor,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
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
                                showDialog = false
                            }
                        ) {
                            Text(
                                text = stringResource(android.R.string.cancel),
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListPreferencePreview() {
    AppTheme {
        ListPreference(
            title = "Theme",
            entries = arrayOf("Light", "Dark", "System default"),
            entryValues = arrayOf("light", "dark", "system_default"),
            value = "system_default",
            onValueChange = {}
        )
    }
}
