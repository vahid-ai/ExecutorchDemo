package com.example.executorchllamademo.models

data class FeatureRequest(
    val entity_id: String,
    val features: List<String>,
    val additional_params: Map<String, Any> = emptyMap()
)

data class FeatureResponse(
    val features: Map<String, Any>,
    val metadata: Map<String, Any>
)