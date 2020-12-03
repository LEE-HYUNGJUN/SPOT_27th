package com.example.sopt_homework.Fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_homework.Adapter.ProfileAdapter
import com.example.sopt_homework.ProfileData
import com.example.sopt_homework.R
import com.example.sopt_homework.Activity.ResultActivity
import com.example.sopt_homework.Adapter.KakakAdapter
import com.example.sopt_homework.KakaoData
import com.example.sopt_homework.ResponseData.ResponseKakaoData
import com.example.sopt_homework.Util.itemTouchHelper
import com.example.sopt_homework.api.KakaoServiceimpl
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_port.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PortFragment : Fragment() {

    private lateinit var kakaoAdpater : KakakAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true) //이 명령이 있어야 액티비티보다 프래그먼트의 메뉴가 우선된다.
        return inflater.inflate(R.layout.fragment_port, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var text_search : String = et_search.text.toString()

        fun removeHTMLTag(str : String) : String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(str).toString()
            }
        }

        bt_search.setOnClickListener {
            kakaoAdpater = KakakAdapter(view.context)
            rcv_kakao.layoutManager = LinearLayoutManager(view.context)
            rcv_kakao.adapter = kakaoAdpater

            val call : Call<ResponseKakaoData> = KakaoServiceimpl.service.getWebSearch(et_search.text.toString())
            call.enqueue(object : Callback<ResponseKakaoData>{
                override fun onFailure(call: Call<ResponseKakaoData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseKakaoData>,
                    response: Response<ResponseKakaoData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            kakaoAdpater.data = mutableListOf(
                                KakaoData(removeHTMLTag(it.documents[0].title),removeHTMLTag(it.documents[0].datetime),removeHTMLTag(it.documents[0].contents)),
                                KakaoData(removeHTMLTag(it.documents[1].title),removeHTMLTag(it.documents[1].datetime),removeHTMLTag(it.documents[1].contents)),
                                KakaoData(removeHTMLTag(it.documents[2].title),removeHTMLTag(it.documents[2].datetime),removeHTMLTag(it.documents[2].contents)),
                                KakaoData(removeHTMLTag(it.documents[3].title),removeHTMLTag(it.documents[3].datetime),removeHTMLTag(it.documents[3].contents)),
                                KakaoData(removeHTMLTag(it.documents[4].title),removeHTMLTag(it.documents[4].datetime),removeHTMLTag(it.documents[4].contents))
                            )
                            kakaoAdpater.notifyDataSetChanged()
                        }?:let {
                        showError(response.errorBody())
                    }
                }
                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(view.context,ob.getString("message"), Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}