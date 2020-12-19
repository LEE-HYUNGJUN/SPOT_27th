package com.example.sopt_homework.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sopt_homework.Adapter.DummyAdapter
import com.example.sopt_homework.DummyUser
import com.example.sopt_homework.R
import com.example.sopt_homework.ResponseData.ResponseDummyData
import com.example.sopt_homework.api.DummyServiceimpl
import kotlinx.android.synthetic.main.fragment_setting.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var dummyAdapter : DummyAdapter
class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dummyAdapter = DummyAdapter(view.context)
        dummy_rcv.layoutManager = LinearLayoutManager(view.context)
        dummy_rcv.adapter = dummyAdapter

        val call : Call<ResponseDummyData> = DummyServiceimpl.service.postDummy(2)
        call.enqueue(object : Callback<ResponseDummyData>{
            override fun onFailure(call: Call<ResponseDummyData>, t: Throwable) {}

            override fun onResponse(
                call: Call<ResponseDummyData>,
                response: Response<ResponseDummyData>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {

                        val firstname = it.data[0].first_name
                        dummyAdapter.data = mutableListOf(
                            DummyUser(
                                it.data[0].first_name,it.data[0].last_name,it.data[0].email
                            ),
                            DummyUser(
                                it.data[1].first_name,it.data[1].last_name,it.data[1].email
                            ),
                            DummyUser(
                                it.data[2].first_name,it.data[2].last_name,it.data[2].email
                            ),
                            DummyUser(
                                it.data[3].first_name,it.data[3].last_name,it.data[3].email
                            ),
                            DummyUser(
                                it.data[4].first_name,it.data[4].last_name,it.data[4].email
                            ),
                            DummyUser(
                                it.data[5].first_name,it.data[5].last_name,it.data[5].email
                            )
                        )
                        dummyAdapter.notifyDataSetChanged()
                    }?:let {
                    showError(response.errorBody())
                }
            }
            private fun showError(error: ResponseBody?) {
                val e = error ?: return
                val ob = JSONObject(e.string())
                Toast.makeText(view.context,ob.getString("message"),Toast.LENGTH_SHORT).show()
            }
        })
    }
}