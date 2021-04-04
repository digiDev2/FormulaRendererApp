package com.theleafapps.pro.formularendererapp.models

data class FormulaResponse(
    val checked: String,
    val endsWithDot: Boolean,
    val identifiers: List<String>,
    val success: Boolean
)