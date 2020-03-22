package com.example.testproject

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray


class MyRecyclerAdapter (fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext: Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val View = view;

        lateinit var layout: LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder() {

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()
        holder.titleTextView.setText(
            "GitHub iD" + dataSource.getJSONObject(position).getString("login").toString()
        )
        holder.detailTextView.setText(
          "GitHun-Node : " + dataSource.getJSONObject(position).getString("node_id").toString()
        )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("avatar_url").toString())
            .into(holder.image)

        holder.layout.setOnClickListener {

//          Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()

//          val head:String = dataSource.getJSONObject(position).getString("login").toString()
//          val body:String = dataSource.getJSONObject(position).getString("node_id").toString()
//          val img:String = dataSource.getJSONObject(position).getString("avatar_url").toString()
            val url: String = dataSource.getJSONObject(position).getString("url").toString()
//            Toast.makeText(thiscontext, url.toString(), Toast.LENGTH_SHORT).show()


//
            // สร้าง Dialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(thisActivity)
            // กำหนดข้ิอความให้ Dialog
            builder.setMessage(
                "URL : ${url}"
            )

//            // กำหนดปุ่มยืนยัน
//            builder.setPositiveButton("ตกลง") { _, _ ->
//                Toast.makeText(thiscontext, holder.detailTextView.text, Toast.LENGTH_SHORT).show()
//            }

            // กำหนดปุ่มยกเลิก
            builder.setNegativeButton("ยกเลิก") { _, _ ->
//                Toast.makeText(thiscontext, "ขอบคุณครับ", Toast.LENGTH_SHORT).show()
            }

            // แสดง Dialog
            builder.show()
//

        }
    }
}


