package bwp.pastebinclient.interactor

import bwp.pastebinclient.interactor.event.GetPastesEvent
import bwp.pastebinclient.interactor.event.GetRawPasteEvent
import bwp.pastebinclient.model.PasteList
import bwp.pastebinclient.network.PasteApi
import org.greenrobot.eventbus.EventBus
import org.simpleframework.xml.core.Persister
import java.lang.Exception
import javax.inject.Inject

class PastesInteractor @Inject constructor(private val pasteApi: PasteApi) {

    val API_DEV_KEY = "8d848649139a71e536a2df7b8883a23a"

    fun getPublicRawPasteCode(pasteKey: String) {
        val event = GetRawPasteEvent()

        try {
            val call = pasteApi.getRawPublicPaste(pasteKey)
            val response = call.execute()
            event.code = response.code()
            if (response.code() != 200) {
                throw Exception("Response code is not OK: ${response.code()} != 200")
            }
            event.rawPasteCode = response.body()
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun createUserKey(username: String, password: String) {

    }

    fun getUserPastes(userKey: String) {
        val event = GetPastesEvent()

        try {
            val call = pasteApi.listUserPastes(API_DEV_KEY, userKey)
            val response = call.execute()
            event.code = response.code()
            if (response.code() != 200) {
                throw Exception("Response code is not OK: ${response.code()} != 200")
            }
            val xmlString = "<pastes>${response.body()}</pastes>"
            val serializer = Persister()
            event.pastes = serializer.read(PasteList::class.java, xmlString).pastes
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }
}