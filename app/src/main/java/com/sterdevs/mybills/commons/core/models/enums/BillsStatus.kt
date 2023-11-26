package com.sterdevs.mybills.commons.core.models.enums

enum class BillsStatus(val value: String) {
    UNPAID("Unpaid"),
    PAID("Paid"),
    VALIDATING("Validating"),
    DEADLINE_EXPIRED("Deadline_expired")
}