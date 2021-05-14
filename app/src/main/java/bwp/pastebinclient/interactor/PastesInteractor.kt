package bwp.pastebinclient.interactor

import bwp.pastebinclient.interactor.event.CreateUserKeyEvent
import bwp.pastebinclient.interactor.event.GetPastesEvent
import bwp.pastebinclient.interactor.event.GetRawPasteEvent
import bwp.pastebinclient.model.PasteInfo
import bwp.pastebinclient.model.PasteList
import bwp.pastebinclient.network.PasteApi
import org.greenrobot.eventbus.EventBus
import org.simpleframework.xml.core.Persister
import java.lang.Exception
import javax.inject.Inject

class PastesInteractor @Inject constructor(private val pasteApi: PasteApi) {

    companion object {
        const val API_DEV_KEY = "8d848649139a71e536a2df7b8883a23a"
    }

    fun createUserKey(username: String, password: String) {
        val event = CreateUserKeyEvent()

        try {
            val call = pasteApi.createUserKey(API_DEV_KEY, username, password)
            val response = call.execute()
            event.code = response.code()
            if (!response.isSuccessful) {
                throw ResponseException(response.code(), response.message(), response.errorBody().toString())
            }
            event.userKey = response.body()
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getPublicRawPasteCode(pasteKey: String) {
        val event = GetRawPasteEvent()

        try {
            val call = pasteApi.getRawPublicPaste(pasteKey)
            val response = call.execute()
            event.code = response.code()
            if (!response.isSuccessful) {
                throw ResponseException(response.code(), response.message(), response.errorBody().toString())
            }
            event.rawPasteCode = response.body()
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getPastes(userKey: String?) {
        val event = GetPastesEvent()

        try {
            if (userKey != null) {
                val call = pasteApi.listUserPastes(API_DEV_KEY, userKey)
                val response = call.execute()
                event.code = response.code()
                if (response.code() != 200) {
                    throw Exception("Response code is not OK: ${response.code()} != 200")
                }
                val xmlString = "<pastes>${response.body()}</pastes>"
                val serializer = Persister()
                event.pastes = serializer.read(PasteList::class.java, xmlString).pastes
            } else {
                event.code = 200
                event.pastes = listOf()
            }
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }
}