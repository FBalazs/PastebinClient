package bwp.pastebinclient.interactor

class ResponseException(val httpCode: Int, val httpMessage: String, val httpErrorBody: String) : Exception("Unexpected response: code=$httpCode, message=\"$httpMessage\", error body: $httpErrorBody")