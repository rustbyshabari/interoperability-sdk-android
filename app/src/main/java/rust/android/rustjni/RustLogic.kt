package rust.android.rustjni

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rust.interop.bridge.*

suspend fun fetchDataFromRust(pageNumber: Int): FilterResponse {
    val params = FilterParams(
        integration = null,
        developmentkit = null,
        language = null,
        crates = null,
        page = pageNumber.toString(),
        ids = null
    )
    return withContext(Dispatchers.IO) {
        fetchInteroperability(params)
    }
}