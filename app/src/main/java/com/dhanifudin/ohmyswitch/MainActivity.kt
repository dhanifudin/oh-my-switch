package com.dhanifudin.ohmyswitch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhanifudin.ohmyswitch.adapters.ItemAdapter
import com.dhanifudin.ohmyswitch.models.Item
import com.dhanifudin.ohmyswitch.models.Switch
import com.dhanifudin.ohmyswitch.models.ItemsData
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var switchesRecyclerView: RecyclerView
    private var items: ArrayList<Item> = arrayListOf()

    companion object {
        const val ITEM_KEY = "Item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchesRecyclerView = findViewById(R.id.rv_switches)
        switchesRecyclerView.setHasFixedSize(true)

        items.addAll(ItemsData.items)
        showSwitches()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSwitches() {
        switchesRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(items)
        switchesRecyclerView.adapter = adapter
        adapter.setOnItemClickCallback(object: ItemAdapter.OnItemClickCallback {
            override fun onItemClicked(item: Switch) {
                Toast.makeText(this@MainActivity, "Opening detail of ${item.title}, please wait!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(ITEM_KEY, item)
                startActivity(intent)
            }
        })
    }
}