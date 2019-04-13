package com.example.catchtheapple

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game_.*
import java.util.*


class Game_Activity : AppCompatActivity() {
    var score: Int=0
    var imageArray=ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_)
        val intent_main=intent
        var time=intent_main.getLongExtra("time",0)
        time*=1000
        hideImages()
        imageArray= arrayListOf(img1,img2,img3,img4,img5,img6,img7,img8,img9)

        val timer=object: CountDownTimer(time,1000){
            override fun onFinish() {
               val intent_game= Intent(applicationContext,Game_End_Activity::class.java)
                handler.removeCallbacks(runnable)
                intent_game.putExtra("score",score)
                var name=intent.getStringExtra("name")
                intent_game.putExtra("name",name)
                startActivity(intent_game)

            }

            override fun onTick(millisUntilFinished: Long) {
               textView.text="Time: "+millisUntilFinished/1000
            }

        }
        timer.start()




    }

    fun hideImages(){

        runnable=object :Runnable{
            override fun run() {
                for( image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random = Random()
                val index=random.nextInt(9-0)
                imageArray[index].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }

    fun scoreInc(view: View){
        score++
        txtScore.text="Socre: "+score
    }


}
