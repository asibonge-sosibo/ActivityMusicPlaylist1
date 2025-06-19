package vcmsa.asibonge.activitymusicplaylist

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityPlaylist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist)

        val displayList = findViewById<TextView>(R.id.displayList)
        val showTwoOrMore = findViewById<Button>(R.id.showTwoOrMore)
        val backButton = findViewById<Button>(R.id.backButton)
        val displayListButton = findViewById<Button>(R.id.displayListButton)

        val items = intent.getStringArrayListExtra("items") ?: arrayListOf()
        val categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        val quantities = intent.getIntegerArrayListExtra("quantities") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()



        fun displayAll() {
            val output = StringBuilder()
            // Table headers with fixed-width columns
            output.append(String.format("%-5s%-15s%-15s%-10s%-20s\n", "No", "Item", "Category", "Qty", "Comment"))
            output.append("----------------------------------------------------------------------\n")

            for (i in items.indices) {
                output.append(
                    String.format(
                        "%-5s%-15s%-15s%-10s%-20s\n",
                        i + 1,
                        items[i],
                        categories[i],
                        quantities[i],
                        comments[i]
                    )
                )
            }
            displayList.text = output.toString()
        }

        fun displayTwoOrMore() {
            val output = StringBuilder()
            for (i in items.indices) {
                if (quantities[i] >= 2) {
                    output.append("${items[i]} (Qty: ${quantities[i]})\n")
                }
            }
            displayList.text = output.toString()
        }

        displayListButton.setOnClickListener {
            displayAll()
        }

        showTwoOrMore.setOnClickListener {
            displayTwoOrMore()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
