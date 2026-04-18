# BHILANI Interoperability by kantini, chanchali

Run SDK

    Android Studio

Usage

    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.withContext
    import rust.interop.bridge.* // JNI/UniFFI generated code
    
    /**
     * Pure logic to fetch data from the Rust bridge.
     * Returns the response or throws an exception.
     */
    suspend fun fetchDataFromRust(page: Int): FilterResponse {
        // 1. Prepare parameters
        val params = FilterParams(
            null, null, null, null, 
            page.toString(), 
            null
        )
    
        // 2. Switch to IO thread for the JNI/Rust call
        return withContext(Dispatchers.IO) {
            // 3. Call the bridge function
            fetchInteroperability(params)
        }
    }

Screenshot
<img width="467" height="798" alt="Screenshot (196)" src="https://github.com/user-attachments/assets/30fde361-6638-47d1-ae32-1fc14a028672" />

**@AIAmitSuri, Co-creator/Co-founder (🙏 Mata Shabri 🙏)**
