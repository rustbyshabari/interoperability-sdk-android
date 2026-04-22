Welcome to **BHILANI**, an **Agentic Interop SDK Suite** by **Kantini, Chanchali**

Run SDK

    Android Studio

Usage

    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.withContext
    import rust.interop.bridge.*
    
    suspend fun fetchDataFromRust(pageNumber: Int): FilterResponse {
        val params = FilterParams(
            language = null,
            integration = null,
            crates = null,
            developmentkit = null,
            page = pageNumber.toString(),
            ids = null
        )
    
        return withContext(Dispatchers.IO) {
            fetchInteroperability(params)
        }        
    }

Screenshot (Page 1)
<img width="1080" height="2340" alt="android1" src="https://github.com/user-attachments/assets/1f6262a6-a993-4b08-abb8-73b22f3a8322" />

Screenshot (Page 4)
<img width="1080" height="2340" alt="android2" src="https://github.com/user-attachments/assets/a3f53810-c943-4aad-8105-97674b6016fa" />

**🙏 Mata Shabri 🙏**
