# BHILANI Interoperability by kantini, chanchali

Run SDK

    Android Studio

Usage

    setContent {
        var currentPage by remember { mutableIntStateOf(1) }
        var resultState by remember { mutableStateOf<FilterResponse?>(null) }
        var isLoading by remember { mutableStateOf(false) }
    
        val totalPages = resultState?.pagination?.totalPages?.toInt() ?: 1
    
        LaunchedEffect(currentPage) {
            isLoading = true
            try {
                val params = FilterParams(
                    null, null, null, null, 
                    currentPage.toString(), 
                    null
                )
    
                // Execute the Rust JNI call on the IO dispatcher
                val response = withContext(Dispatchers.IO) {
                    fetchInteroperability(params)
                }
                resultState = response
            } catch (e: Exception) {
                // Handle error (e.g., show a Snackbar)
            } finally {
                isLoading = false
            }
        }
        
        // UI logic for displaying resultState and paging goes here
    }

Screenshot
<img width="467" height="798" alt="Screenshot (196)" src="https://github.com/user-attachments/assets/30fde361-6638-47d1-ae32-1fc14a028672" />

**@AIAmitSuri, Co-creator/Co-founder (🙏 Mata Shabri 🙏)**
