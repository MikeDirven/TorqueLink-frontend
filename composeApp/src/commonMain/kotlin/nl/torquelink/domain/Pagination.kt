package nl.torquelink.domain

data class Pagination(
    val page: Int,
    val size: Int,
    val hasNext: Boolean,
)
