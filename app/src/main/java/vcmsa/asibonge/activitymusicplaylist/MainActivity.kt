package vcmsa.asibonge.activitymusicplaylist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val itemNames = ArrayList<String>()
        val categories = ArrayList<String>()
        val quantities = ArrayList<Int>()
        val comments = ArrayList<String>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val addItemButton = findViewById<Button>(R.id.PlaylistButton)
            val viewListButton = findViewById<Button>(R.id.GoToPlaylistButton)
            val exitButton = findViewById<Button>(R.id.ExitButton)

            addItemButton.setOnClickListener {
                showAddDialog()
            }

            viewListButton.setOnClickListener {
                val intent = Intent(this, ActivityPlaylist::class.java)
                val songtitle = null
                val ArtistName = null
                val SongRating = null
                val CommentText = null
                intent.putStringArrayListExtra("SongTitle", songtitle)
                intent.putStringArrayListExtra("ArtistName", ArtistName)
                intent.putIntegerArrayListExtra("SongRating", SongRating)

                intent.putStringArrayListExtra("CommentText", CommentText)
                startActivity(intent)
            }

            exitButton.setOnClickListener {
                finish()
            }
        }

        private fun showAddDialog() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter Item Details")
            val layout = layoutInflater.inflate(R.layout.dialog_layout, null)
            builder.setView(layout)

            val songtitle = layout.findViewById<android.widget.EditText>(R.id.songtitle)
            val ArtistName = layout.findViewById<android.widget.EditText>(R.id.ArtistName)
            val SongRating = layout.findViewById<android.widget.EditText>(R.id.SongRating)
            val CommentText = layout.findViewById<android.widget.EditText>(R.id.CommentText)

            builder.setPositiveButton("Add") { _, _ ->
                val name = songtitle.text.toString()
                val cat = ArtistName.text.toString()
                val qty = SongRating.text.toString().toIntOrNull()
                val com = CommentText.text.toString()

                if (name.isEmpty() || cat.isEmpty() || qty == null) {
                    Toast.makeText(this, "Please enter valid item details.", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                itemNames.add(name)
                categories.add(cat)
                quantities.add(qty)
                comments.add(com)

                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Cancel", null)
            builder.show()
        }
    }