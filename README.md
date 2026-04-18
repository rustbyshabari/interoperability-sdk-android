# BHILANI Interoperability by kantini, chanchali

Run SDK

    Android Studio

Usage

    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.withContext
    import rust.interop.bridge.*
    
    // Pure logic to fetch data from the Rust JNI bridge
    suspend fun fetchDataFromRust(pageNumber: Int): FilterResponse {
    
        // 1. Create the params object
        val params = FilterParams(
            language = null,
            integration = null,
            crates = null,
            developmentkit = null,
            page = pageNumber.toString(),
            ids = null
        )
    
        // 2. Execute the call on the IO thread
        return withContext(Dispatchers.IO) {
            fetchInteroperability(params)
        }        
    }

Screenshot
<img width="467" height="798" alt="Screenshot (196)" src="https://github.com/user-attachments/assets/30fde361-6638-47d1-ae32-1fc14a028672" />

**@AIAmitSuri, Co-creator/Co-founder (🙏 Mata Shabri 🙏)**
