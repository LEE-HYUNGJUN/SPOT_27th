## Seminar 02. RecyclerView
--------------------------------
### 1. ProfileData
<pre>
data class ProfileData (
    var title : String,
    var subTitle : String,
    var date_title : String,
    var date_subTitle : String,
    var contents : String
)
</pre>
필요로하는 데이터들을 변수로 가지고 있는 Data class를 만들어준다.

### 2. ProfileAdpater
<pre>
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)

        return ProfileViewHolder(view)
    }
</pre>
<pre>
 override fun getItemCount(): Int = data.size
</pre>
<pre>
override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

</pre>
Adapter는 3가지 함수(onCreateViewHolder, getItemCount, onBindViewHolder)를 꼭 오버라이드 해주어야한다.

RecylerView의 item을 클릭했을때 이벤트를 발생시키기 위해 holder를 이용해 clickListner를 설정해준다. 

<pre>
interface ItemClickListener{
        fun onClick(view : View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
</pre>
ItemClickLister가 동작할수 있게 설정해준다.

### 3.ProfileViewHolder
<pre>
class ProfileViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private  val title : TextView = itemView.findViewById(R.id.title_txt)
    private val subtitle : TextView = itemView.findViewById(R.id.subtitle_txt)

    fun onBind(data : ProfileData){
        title.text = data.title
        subtitle.text = data.subTitle
    }
}
</pre>
item_list에서 보여줄 view들을 findViewById로 가져온다. 각각 변수에 가져온 view를 넣어주고, TextView에 데이터를 넣기위해 onBind 함수안에 title.text = data.title을 해준다.

### 4. RecyclerView
- GridLayout으로 설정하고 싶은 경우
<pre>
profileAdapter = ProfileAdapter(this)
main_rcv.layoutManager = GridLayoutManager(this,3)
main_rcv.adapter = profileAdapter
</pre>

- LinearLayout으로 설정하고 싶은 경우
<pre>
profileAdapter = ProfileAdapter(this)
main_rcv.layoutManager = LinearLayoutManager(this)
main_rcv.adapter = profileAdapter
</pre>

<pre>
profileAdapter.data = mutableListOf(
            ProfileData("이름","이형준","작성날짜","10월 22일","안녕하십니까! 저는 YB 이형준입니다!"),
            ProfileData("나이","27","작성날짜","10월 22일","94년생이라서 너무 슬픕니다ㅜㅜ"),
            ProfileData("사는곳","인천","작성날짜","10월 22일","인천 청라국제도시에 살고 있습니다"),
            ProfileData("취미","축구보기","작성날짜","10월 22일","첼시가 우승할듯ㅎ"),
            ProfileData("좋아하는 축구선수","손흥민","작성날짜","10월 22일","NICE ONE SONNY!!"),
            ProfileData("파트","안드로이드","작성날짜","10월 22일","안드로이드 재밌네")

        )
        profileAdapter.notifyDataSetChanged()
</pre>
필요로 하는 데이터들의 값을 위처럼 넣어준다. Adapter를 통해 ProfileData에 데이터를 넣어준다. 

<pre>
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
</pre>
RecyclerView의 아이템을 클릭했을때 ResultActivity를 호출하기 위해 Intent에 putExtra를 이용해 데이터들을 넣어준다.

- Move & Swipe의 경우 실행하는 코드
<pre>
val helper : ItemTouchHelper = itemTouchHelper(profileAdapter)
helper.attachToRecyclerView(main_rcv)
</pre>
ItemTouchHelper함수에서 반한된 helper를 attachToRecyclerView()를 이용해 main_rcv라는 id를 가진 RecyclerView에 적용한다.

### 5.ItemTouchHelper
<pre>
fun itemTouchHelper(adapter: ProfileAdapter) : ItemTouchHelper{
    val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.START or ItemTouchHelper.END,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        
</pre>
itemTouchHelper함수를 정의해서 인자로 ProfileAdapter를 받고, 결과는 ItemTouchHelper로 return한다.

helper변수에 ItemTouchHelper(object : ItemTouchHelper.SimpleCallback()을 호출한다.

move의 경우와 swipe의 경우에 어떨때 동작할지를  ItemTouchHelper.DOWN or ItemTouchHelper.UP 등으로 설정해준다.

<pre>
override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val draggedPosition = viewHolder.adapterPosition
            val targetPosition = target.adapterPosition
            adapter.notifyItemMoved(draggedPosition, targetPosition)
            Log.d("tag", adapter.data.toString())
            return false
    }       
</pre>
SimpleCallback에서는 onMove 와 onSwiped 함수를 오버라이드 해야한다.

draggedPosition은 내가 클릭했을때의 position이고, targetPosition은 item을 누르고 옮길때 바꾸려고 하는 목적지 item이다.

adapter.notifyItemMoved(draggedPosition, targetPosition)를 이용해 서로 위치를 바꿔준다.
<pre>
 override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipePosition = viewHolder.adapterPosition
            adapter.data.removeAt(swipePosition)
            adapter.notifyItemRemoved(swipePosition)
        }
</pre>
swipe의 경우는 draggedPosition만 필요하다. swipe시에 데이터를 지워야 하므로 removeAt()을 이용한다 .

후에 adapter.notifyItemRemoved(draggedPosition)를 이용하면 item을 삭제할 수 있다.






