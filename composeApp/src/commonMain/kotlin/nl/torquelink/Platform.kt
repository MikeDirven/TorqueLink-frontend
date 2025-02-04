package nl.torquelink

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform