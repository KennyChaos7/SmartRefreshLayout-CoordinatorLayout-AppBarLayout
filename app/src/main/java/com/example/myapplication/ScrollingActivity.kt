package com.example.myapplication

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityScrollingBinding
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var radapter: SimAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityScrollingBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        setSupportActionBar(findViewById(R.id.toolbar))
//        binding.toolbarLayout.title = title
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        setContentView(R.layout.activity_scrolling)
//        setContentView(R.layout.activity_scrolling2)
        findViewById<RecyclerView>(R.id.recyclerview).run {
            this.layoutManager = LinearLayoutManager(this@ScrollingActivity)
            radapter = SimAdapter()
            this.adapter  = radapter
            radapter.newAdd()
        }
        findViewById<SmartRefreshLayout>(R.id.smartrefreshlayout).run {
            this.setOnRefreshListener {
                radapter.newAdd()
                it.finishRefresh()
            }
            this.setOnLoadMoreListener {
                radapter.add()
                it.finishLoadMore()
            }
        }
        findViewById<CoordinatorLayout>(R.id.coordinatorlayout).run {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}