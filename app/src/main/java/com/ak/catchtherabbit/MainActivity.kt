package com.ak.catchtherabbit

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.ak.catchtherabbit.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var  binding: ActivityMainBinding
    var scor : Int = 0
    var runnable:Runnable = Runnable {  }
    var handler = Handler(Looper.getMainLooper())
    var imageList = ArrayList<ImageView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = this.getSharedPreferences("com.ak.catchtherabbit", MODE_PRIVATE)

        val data = sharedPreferences.getInt("data",0)

        if (data!=null){
            binding.previousScore.text = "previous Score:$data"
        }

        imageList.add(binding.image0)
        imageList.add(binding.image1)
        imageList.add(binding.image2)
        imageList.add(binding.image3)
        imageList.add(binding.image4)
        imageList.add(binding.image5)
        imageList.add(binding.image6)
        imageList.add(binding.image7)
        imageList.add(binding.image8)
        imageList.add(binding.image9)
        imageList.add(binding.image10)
        imageList.add(binding.image11)
        imageList.add(binding.image12)
        imageList.add(binding.image13)
        imageList.add(binding.image14)
        imageList.add(binding.image15)


       hideİmage()

      object:CountDownTimer(30000,1000){
          override fun onTick(p0: Long) {
              binding.time.text = "${p0/1000}"
          }

          override fun onFinish() {
              handler.removeCallbacks(runnable)
              for (image in imageList){
                  image.visibility = View.INVISIBLE
              }
              val alert = AlertDialog.Builder(this@MainActivity)
              alert.setTitle("Game Over")
              alert.setMessage("Are you ready to try your luck again?")
              alert.setPositiveButton("Yes"){dialog,wich ->
                  val intent = intent
                  finish()
                  startActivity(intent)

              }
              alert.setNegativeButton("No"){dialog,wich ->
                  finish()

              }

            alert.show()
          }

      }.start()


    }

fun increaseScore(view: View){
    scor+=1
    binding.scor.text= "Skor:$scor"
    sharedPreferences.edit().putInt("data",scor).apply()

}
fun hideİmage(){

    runnable = object :Runnable {
        override fun run() {
            for (image in imageList){
                image.visibility = View.INVISIBLE
            }
            val random = java.util.Random()
            val index = random.nextInt(16)
            imageList[index].visibility = View.VISIBLE
           handler.postDelayed(runnable,500)
        }
    }
  handler.post(runnable)

}

}
