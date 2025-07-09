package com.example.executorchllamademo.network

data class FeatureRequest(
    val entity_id: String,
    val features: List<String>,
    val additional_params: Map<String, Any> = emptyMap()
)

data class FeatureResponse(
    val features: Map<String, Any>,
    val metadata: Map<String, Any>
)

class FeastApiClient {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val baseUrl = "http://192.168.x.x:8000" // Your MacBook IP

    suspend fun getFeatures(
        entityId: String,
        features: List<String>,
        additionalParams: Map<String, Any> = emptyMap()
    ): FeatureResponse {
        val request = FeatureRequest(entityId, features, additionalParams)
        val requestBody = gson.toJson(request)
            .toRequestBody("application/json".toMediaType())

        val httpRequest = Request.Builder()
            .url("$baseUrl/get-features")
            .post(requestBody)
            .build()

        val response = client.newCall(httpRequest).execute()
        val responseBody = response.body?.string()

        return gson.fromJson(responseBody, FeatureResponse::class.java)
    }
}