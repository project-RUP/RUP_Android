import com.google.gson.annotations.SerializedName

data class MemberExistRes(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: ResultData
)

data class ResultData(
    @SerializedName("exist")
    val exist: Boolean
)
