package bwp.pastebinclient.network

import bwp.pastebinclient.model.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface PasteApi {
    @POST("api/api_post.php")
    @FormUrlEncoded
    fun createPaste(@Field("api_dev_key") apiDevKey: String,
                    @Field("api_paste_code") pasteCode: String,
                    @Field("api_paste_name") pasteTitle: String? = null,
                    @Field("api_paste_format") pasteFormat: String? = null,
                    @Field("api_paste_private") apiPastePrivate: Int? = null,
                    @Field("api_paste_expire_date") expireTime: String? = null,
                    @Field("api_user_key") userKey: String? = null,
                    @Field("api_folder_key") folderKey: String? = null,
                    @Field("api_option") apiOption: String = "paste"): Call<String>

    @POST("api/api_post.php")
    @FormUrlEncoded
    fun listUserPastes(@Field("api_dev_key") apiDevKey: String,
                       @Field("api_user_key") userKey: String,
                       @Field("api_results_limit") apiResultsLimit: Int? = null,
                       @Field("api_option") apiOption: String = "list"): Call<String>

    @POST("api/api_post.php")
    @FormUrlEncoded
    fun deletePaste(@Field("api_dev_key") apiDevKey: String,
                    @Field("api_user_key") userKey: String,
                    @Field("api_paste_key") pasteKey: String,
                    @Field("api_option") apiOption: String = "delete"): Call<String>

    @POST("api/api_post.php")
    @FormUrlEncoded
    fun getUserInfo(@Field("api_dev_key") apiDevKey: String,
                    @Field("api_user_key") userKey: String,
                    @Field("api_option") apiOption: String = "userdetails"): Call<UserInfo>

    @POST("api/api_raw.php")
    @FormUrlEncoded
    fun getRawUserPaste(@Field("api_dev_key") apiDevKey: String,
                        @Field("api_user_key") userKey: String,
                        @Field("api_paste_key") pasteKey: String,
                        @Field("api_option") apiOption: String = "show_paste"): Call<String>

    @GET("raw/{key}")
    fun getRawPublicPaste(@Path("key") pasteKey: String): Call<String>
}