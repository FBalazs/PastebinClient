package bwp.pastebinclient.mock

import bwp.pastebinclient.model.UserInfo
import bwp.pastebinclient.network.PasteApi
import retrofit2.Call

class MockPasteApi : PasteApi {
    override fun createPaste(
        apiDevKey: String,
        pasteCode: String,
        pasteTitle: String?,
        pasteFormat: String?,
        apiPastePrivate: Int?,
        expireTime: String?,
        userKey: String?,
        folderKey: String?,
        apiOption: String
    ): Call<String> {
        return MockCall("testpastekey")
    }

    override fun listUserPastes(
        apiDevKey: String,
        userKey: String,
        apiResultsLimit: Int?,
        apiOption: String
    ): Call<String> {
        return MockCall("<paste>\n" +
                "\t<paste_key>RG8R1Myz</paste_key>\n" +
                "\t<paste_date>1449085106</paste_date>\n" +
                "\t<paste_title>Digitális technika SZUPER_EXTRA10</paste_title>\n" +
                "\t<paste_size>4316</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>1</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/RG8R1Myz</paste_url>\n" +
                "\t<paste_hits>6</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>gXmXfqCU</paste_key>\n" +
                "\t<paste_date>1449341990</paste_date>\n" +
                "\t<paste_title>Digitális technika SZUPER_EXTRA12</paste_title>\n" +
                "\t<paste_size>2284</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>1</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/gXmXfqCU</paste_url>\n" +
                "\t<paste_hits>2</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>Ayz3MBEe</paste_key>\n" +
                "\t<paste_date>1477464461</paste_date>\n" +
                "\t<paste_title>Fifo</paste_title>\n" +
                "\t<paste_size>685</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>1</paste_private>\n" +
                "\t<paste_format_long>Java</paste_format_long>\n" +
                "\t<paste_format_short>java</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/Ayz3MBEe</paste_url>\n" +
                "\t<paste_hits>15</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>tcw7s3dn</paste_key>\n" +
                "\t<paste_date>1502104005</paste_date>\n" +
                "\t<paste_title>Crash on CreativeTab render</paste_title>\n" +
                "\t<paste_size>15613</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>0</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/tcw7s3dn</paste_url>\n" +
                "\t<paste_hits>56</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>AcrXWVrz</paste_key>\n" +
                "\t<paste_date>1504165650</paste_date>\n" +
                "\t<paste_title>ElysiumCraft additional mod download links</paste_title>\n" +
                "\t<paste_size>414</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>1</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/AcrXWVrz</paste_url>\n" +
                "\t<paste_hits>45</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>qWciEAXn</paste_key>\n" +
                "\t<paste_date>1504187833</paste_date>\n" +
                "\t<paste_title>build.gradle</paste_title>\n" +
                "\t<paste_size>1951</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>1</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/qWciEAXn</paste_url>\n" +
                "\t<paste_hits>3</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>WHpdDZLj</paste_key>\n" +
                "\t<paste_date>1518891812</paste_date>\n" +
                "\t<paste_title>KvantumAlmak.java</paste_title>\n" +
                "\t<paste_size>4876</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>0</paste_private>\n" +
                "\t<paste_format_long>Java</paste_format_long>\n" +
                "\t<paste_format_short>java</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/WHpdDZLj</paste_url>\n" +
                "\t<paste_hits>85</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>dhb8zDBt</paste_key>\n" +
                "\t<paste_date>1555177518</paste_date>\n" +
                "\t<paste_title>Framework security issue</paste_title>\n" +
                "\t<paste_size>359</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>1</paste_private>\n" +
                "\t<paste_format_long>Java</paste_format_long>\n" +
                "\t<paste_format_short>java</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/dhb8zDBt</paste_url>\n" +
                "\t<paste_hits>7</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>pAuvu8Cv</paste_key>\n" +
                "\t<paste_date>1619112999</paste_date>\n" +
                "\t<paste_title>asd</paste_title>\n" +
                "\t<paste_size>3</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>2</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/pAuvu8Cv</paste_url>\n" +
                "\t<paste_hits>1</paste_hits>\n" +
                "</paste>\n" +
                "<paste>\n" +
                "\t<paste_key>ubbGcfEX</paste_key>\n" +
                "\t<paste_date>1619124441</paste_date>\n" +
                "\t<paste_title>Untitled</paste_title>\n" +
                "\t<paste_size>4</paste_size>\n" +
                "\t<paste_expire_date>0</paste_expire_date>\n" +
                "\t<paste_private>0</paste_private>\n" +
                "\t<paste_format_long>None</paste_format_long>\n" +
                "\t<paste_format_short>text</paste_format_short>\n" +
                "\t<paste_url>https://pastebin.com/ubbGcfEX</paste_url>\n" +
                "\t<paste_hits>9</paste_hits>\n" +
                "</paste>")
    }

    override fun deletePaste(
        apiDevKey: String,
        userKey: String,
        pasteKey: String,
        apiOption: String
    ): Call<String> {
        return MockCall("")
    }

    override fun getUserInfo(
        apiDevKey: String,
        userKey: String,
        apiOption: String
    ): Call<UserInfo> {
        return MockCall(UserInfo(
            name = "Test User",
            email = "testuser@test.com",
            defaultFormatShort = "text",
            defaultExpiration = "N",
            avatarUrl = "@themes/img/guest.png",
            defaultPrivate = 0,
            accountType = 0
        ))
    }

    override fun getRawUserPaste(
        apiDevKey: String,
        userKey: String,
        pasteKey: String,
        apiOption: String
    ): Call<String> {
        return MockCall("Hello World!")
    }

    override fun getRawPublicPaste(pasteKey: String): Call<String> {
        return MockCall("Hello World!")
    }

    override fun createUserKey(
        apiDevKey: String,
        username: String,
        password: String
    ): Call<String> {
        return MockCall("testuserkey")
    }
}