package rust.android.rustjni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rust.interop.bridge.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var currentPage by remember { mutableIntStateOf(1) }
            var resultState by remember { mutableStateOf<FilterResponse?>(null) }
            var isLoading by remember { mutableStateOf(false) }

            val totalPages = resultState?.pagination?.totalPages?.toInt() ?: 1

            LaunchedEffect(currentPage) {
                isLoading = true
                try {
                    val params = FilterParams(null, null, null, null, currentPage.toString(), null)

                    // Switch to IO thread for the Rust JNI call
                    val response = withContext(Dispatchers.IO) {
                        fetchInteroperability(params)
                    }
                    resultState = response
                } catch (e: Exception) {
                } finally {
                    isLoading = false
                }
            }

            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Interoperability SDK", style = MaterialTheme.typography.headlineMedium)
                        Text("Rust and Android NDK", style = MaterialTheme.typography.headlineSmall)

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { if (currentPage > 1) currentPage-- },
                                enabled = !isLoading && currentPage > 1
                            ) { Text("Previous") }

                            Text("Page $currentPage of $totalPages", style = MaterialTheme.typography.bodyLarge)

                            Button(
                                onClick = { if (currentPage < totalPages) currentPage++ },
                                enabled = !isLoading && currentPage < totalPages
                            ) { Text("Next") }
                        }

                        if (isLoading) {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
                        }

                        resultState?.let { response ->
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(response.data) { item ->
                                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(item.title, style = MaterialTheme.typography.titleMedium)
                                            Text("Integration: ${item.integration}", style = MaterialTheme.typography.bodyMedium)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}