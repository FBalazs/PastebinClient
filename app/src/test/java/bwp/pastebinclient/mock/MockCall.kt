package bwp.pastebinclient.mock

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockCall<T>(private val result: T) : Call<T> {
    override fun clone(): Call<T> {
        return this
    }

    override fun execute(): Response<T> {
        return Response.success(result)
    }

    override fun enqueue(callback: Callback<T>) {}

    override fun isExecuted(): Boolean {
        return false
    }

    override fun cancel() {}

    override fun isCanceled(): Boolean {
        return false
    }

    override fun request(): Request? {
        return null
    }

    override fun timeout(): Timeout? {
        return null
    }
}