package com.example.sopt_homework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

private lateinit var profileAdapter: ProfileAdapter

class RecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        /*val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        val point = Point()
        display.getSize(point)
        val screenWidth = point.x
        val photoWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,120f,this.resources.displayMetrics).toInt()
        val columnsCount = screenWidth / photoWidth*/

        profileAdapter = ProfileAdapter(this)
        //main_rcv.layoutManager = GridLayoutManager(this,columnsCount)
        main_rcv.layoutManager = LinearLayoutManager(this)
        main_rcv.adapter = profileAdapter

        val helper : ItemTouchHelper = itemTouchHelper(profileAdapter)
        helper.attachToRecyclerView(main_rcv)

        profileAdapter.data = mutableListOf(
            ProfileData("이름","이형준","작성날짜","10월 22일","안녕하십니까! 저는 YB 이형준입니다!"),
            ProfileData("나이","27","작성날짜","10월 22일","94년생이라서 너무 슬픕니다ㅜㅜ"),
            ProfileData("사는곳","인천","작성날짜","10월 22일","인천 청라국제도시에 살고 있습니다"),
            ProfileData("취미","축구","작성날짜","10월 22일","첼시가 우승할듯ㅎ"),
            ProfileData("롤모델","손흥민","작성날짜","10월 22일","NICE ONE SONNY!!"),
            ProfileData("파트","안드","작성날짜","10월 22일","안드로이드 재밌네")

        )
        profileAdapter.notifyDataSetChanged()


        profileAdapter.setItemClickListener(object : ProfileAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
              Log.d("SSS","${position}번 리스트 선택")
                val intent = Intent(baseContext,ResultActivity::class.java)

                intent.putExtra("title", profileAdapter.data[position].title.toString())
                intent.putExtra("subtitle",profileAdapter.data[position].subTitle.toString())
                intent.putExtra("date_title", profileAdapter.data[position].date_title.toString())
                intent.putExtra("date_subTitle", profileAdapter.data[position].date_subTitle.toString())
                intent.putExtra("contents", profileAdapter.data[position].contents.toString())

                startActivity(intent)
            }

        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.linear ->{
                main_rcv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                main_rcv.adapter = profileAdapter
                return true
            }
            R.id.grid ->{
                main_rcv.layoutManager = GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
                main_rcv.adapter = profileAdapter
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}