package com.example.sopt_homework.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_homework.Activity.ResultActivity
import com.example.sopt_homework.Adapter.ProfileAdapter
import com.example.sopt_homework.ProfileData
import com.example.sopt_homework.R
import com.example.sopt_homework.Util.itemTouchHelper
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment() {

    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileAdapter = ProfileAdapter(view.context)
        port_rcv.layoutManager = LinearLayoutManager(view.context)
        port_rcv.adapter = profileAdapter

        val helper : ItemTouchHelper = itemTouchHelper(profileAdapter)
        helper.attachToRecyclerView(port_rcv)

        profileAdapter.data = mutableListOf(
            ProfileData(
                "이름",
                "이형준",
                "작성날짜",
                "10월 22일",
                "안녕하십니까! 저는 YB 이형준입니다!"
            ),
            ProfileData(
                "나이",
                "27",
                "작성날짜",
                "10월 22일",
                "94년생이라서 너무 슬픕니다ㅜㅜ"
            ),
            ProfileData(
                "사는곳",
                "인천",
                "작성날짜",
                "10월 22일",
                "인천 청라국제도시에 살고 있습니다"
            ),
            ProfileData(
                "취미",
                "축구보기",
                "작성날짜",
                "10월 22일",
                "첼시가 우승할듯ㅎ"
            ),
            ProfileData(
                "좋아하는 축구선수",
                "손흥민",
                "작성날짜",
                "10월 22일",
                "NICE ONE SONNY!!"
            ),
            ProfileData(
                "파트",
                "안드로이드",
                "작성날짜",
                "10월 22일",
                "안드로이드 재밌네"
            )

        )
        profileAdapter.notifyDataSetChanged()

        profileAdapter.setItemClickListener(object :
            ProfileAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("SSS","${position}번 리스트 선택")
                val intent = Intent(view.context,
                    ResultActivity::class.java)
                intent.putExtra("data", profileAdapter.data[position])
                startActivity(intent)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.linear ->{
                port_rcv.layoutManager = LinearLayoutManager(view?.context)
                profileAdapter.layoutitem =
                    R.layout.item_list
                //profileAdapter.changelayout(R.layout.item_list)
                port_rcv.adapter = profileAdapter
                return true
            }
            R.id.grid ->{
                port_rcv.layoutManager = GridLayoutManager(view?.context,2)
                profileAdapter.layoutitem =
                    R.layout.list_grid
                //profileAdapter.changelayout(R.layout.list_grid)
                port_rcv.adapter = profileAdapter
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }

}