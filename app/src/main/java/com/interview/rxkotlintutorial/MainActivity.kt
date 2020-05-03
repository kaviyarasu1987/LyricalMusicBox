package com.interview.rxkotlintutorial

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener

import android.view.animation.AnimationUtils
import kotlin.collections.List;

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import com.interview.rxkotlintutorial.databinding.ActivityMainBinding
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule


import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.animation.TranslateAnimation
import kotlin.collections.ArrayList

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.collections.HashMap

import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView


class MainActivity : AppCompatActivity() {
var priortizationCount = -1;
    var lastMediaPlayer:MediaPlayer? = null;
var lastTag = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lyricsText =findViewById<TextView>(R.id.lyrics);
        lyricsText.text=MusicConstant.lyrics.toString()
        MusicConstant.initializeFakeData();

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.musicList = MusicConstant.musicView;
        var index=0;
        MusicConstant.getMediaPlayerSeries(this);
        MusicConstant.convertWesternNotesToMusicDataList();
     //   lyrics.text=MusicConstant.lyrics
        checkandUpdate();

     /*   fixedRateTimer("timer", false, 1500,  4300) {
            this@MainActivity.runOnUiThread {
               if(index<MusicConstant.musicList.size) {
                   createMusicView(MusicConstant.musicList[index])
                   index++
               }


            }
        }*/






    }
fun createMusicView(hashMap:HashMap<Int, ArrayList<MusicData>>)
    {
        var piano =findViewById<LinearLayout>(R.id.piano);
        piano.bringToFront();

        var linearLayout = LinearLayout(this);
        //var textview = TextView(this);
      //  textview.text="sdfsdfsd"
        //linearLayout.addView(textview);
        var layout = findViewById<ConstraintLayout>(R.id.constraintlayout)
        linearLayout.orientation = LinearLayout.HORIZONTAL;
        var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200);
        linearLayout.tag = hashMap;


        for(i in 0 until 7)
        {
            var button = Button(this);

            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT)
            params.weight = 1.0f;
            button.layoutParams = params;
            linearLayout.addView(button);
            button.visibility = View.INVISIBLE
            if(hashMap.containsKey(i)) {
                button.visibility = View.VISIBLE

            }


        }


        layout.addView(linearLayout);

        Timer("start a lyrics",false).schedule(500)
        {

            hideView(linearLayout);
        }

   }


    private fun hideView(view: View) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        var parentLayout = findViewById<ConstraintLayout>(R.id.constraintlayout);
        var displayMetrices = this.resources.displayMetrics;


        var slide = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            0.0f, Animation.RELATIVE_TO_SELF, 10f
        )

        slide.setDuration(6400)
        slide.setFillAfter(true)
        slide.setFillEnabled(true)
        slide.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {

                println("");

            }

            override fun onAnimationEnd(animation: Animation) {
                piano.tag = view.tag
                checkPrioritizedButton(piano.tag  as HashMap<Int, ArrayList<MusicData>>);
              //  view.visibility = View.GONE
                //priortizationCount = -1;
                (view.parent as ViewGroup).removeView(view)
                lastMediaPlayer = null
                lastTag = -1;
            }
        })

        view.startAnimation(slide)
    }
    fun playAsPerPrioritization(v: View) {

        try {


            var musicPlayKeyList = (v.parent as View).tag as HashMap<Int, ArrayList<MusicData>>;

            if(musicPlayKeyList.size>0) {
                var musicdata =
                    musicPlayKeyList.get(v.tag.toString().toInt()) as ArrayList<MusicData>;
                  var musicPlayer = getPlayerAsPerPriotization(musicdata,priortizationCount+1);

                if (musicPlayer!=null) {


                    if (lastMediaPlayer==null) {
                      //  lastMediaPlayer = musicPlayer


                    } else {

//                       lastMediaPlayer!!.stop()
  //                      lastMediaPlayer= musicPlayer
                    }
                    GlobalScope.launch {

                            var player = MediaPlayer.create(baseContext , baseContext.resources.getIdentifier("com.interview.rxkotlintutorial:raw/"+musicPlayer.westerNote,null,null));
                            player.start();


                    }


                  //  musicPlayer.start();


                    priortizationCount++
                    checkPrioritizedButton(musicPlayKeyList)
                }

            }
        }

       catch (e: Exception)
       {


       }
    }

    fun playAsPerPrioritizationPerList(v: View) {

        try {


           if(MusicConstant.musicView.get(v.tag)!!.visible == true) {

               val spannable = SpannableString(MusicConstant.lyrics)


               var musicPlayer = getPlayerAsPerPriotization(MusicConstant.musicDatas, priortizationCount + 1);

               spannable.setSpan(
                   BackgroundColorSpan(Color.parseColor("#87CEEB")),
                   0, Math.round(musicPlayer!!.spacedIndex.toDouble()).toInt(),
                   Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

               lyrics.text=spannable
               if (musicPlayer != null) {


                   GlobalScope.launch {

                       var player = MediaPlayer.create(
                           baseContext,
                           baseContext.resources.getIdentifier(
                               "com.interview.rxkotlintutorial:raw/" + musicPlayer.westerNote,
                               null,
                               null
                           )
                       );
                       player.start();


                   }


                   priortizationCount++
                   checkandUpdate()
               }
           }

        }

        catch (e: Exception)
        {


        }
    }


    fun getPlayerAsPerPriotization(musicList:ArrayList<MusicData>,prioritization:Int):MusicData?
    {


        for( musicdata in musicList)
        {
            if(prioritization == musicdata.prioritize) {
                return musicdata

            }


        }

        return null;

    }

    fun checkPrioritizedButton(musicList:HashMap<Int, ArrayList<MusicData>>)
    {
        var enabledButtonView = -1;
        var enabledMusicData:MusicData;
        lateinit var foundvalue:List<MusicData>;
     for((key,value) in musicList )
     {
         foundvalue = value.filter { it.prioritize==priortizationCount+1 }
         if(foundvalue.size>0) {
             enabledButtonView = foundvalue[0].westernIndex - 1
             enabledMusicData = foundvalue[0]
         }
     }

    var _key = 0;
     for((key,value) in MusicConstant.musicView)
     {
         if(key.toInt() == enabledButtonView) {
             value.visible = true
         }
         else {
             value.visible = false

         }
     }
    }

    fun checkandUpdate() {
        MusicConstant.reclearAllSavedViewData()

            var filteredData = MusicConstant.musicDatas.filter {

                it.prioritize == priortizationCount + 1
            }[0]

            var lyricsFilter = MusicConstant.musicDatas.filter {

                it.lyricIndex == filteredData.lyricIndex
            }

            lyricsFilter.forEachIndexed { StringIndex, stringit ->

                var musicView = MusicConstant.musicView.get("" + (stringit.westernIndex - 1))
                musicView!!.visible = true;
                if (musicView!!.priotization.length > 0)
                    musicView!!.priotization = musicView.priotization + "," + stringit.prioritize
                else
                    musicView.priotization = "" + stringit.prioritize

            }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item: MenuItem = menu.findItem(R.id.refresh)



        item.actionView.findViewById<AppCompatTextView>(R.id.appCompatTextView).setOnClickListener {
            lyrics.text = MusicConstant.lyrics
            priortizationCount= -1
            MusicConstant.reclearAllSavedViewData()
            MusicConstant.convertWesternNotesToMusicDataList()
            checkandUpdate()
        }
        return true
    }

    }



