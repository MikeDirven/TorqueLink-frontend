package nl.torquelink.domain.exceptions

actual val Exception.localMessage: String
    get() = this.localizedMessage