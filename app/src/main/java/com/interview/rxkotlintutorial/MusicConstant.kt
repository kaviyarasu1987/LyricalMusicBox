package com.interview.rxkotlintutorial


import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import java.lang.Exception
import android.media.MediaPlayer.OnPreparedListener

import android.net.Uri





class MusicConstant {

    companion object{
        var musicNotes =HashMap<String,MediaPlayer>();
        var musicDatas = ArrayList<MusicData>();
       // var musicData = "eed_ d_ef_ ef_g f_ga gf_e f_ed_ eed_ d_ef_ ef_g f_ga gf_e gf_e bb ag_g_ ef_ gg f_ga gf_f_ bb ag_g_ ef_gg f_ga gf_f_ g_ab abc_ bc_ dd c_c_ dc_b ag_ ea bc_ ea bc ea bc_ ea bc g_a g_agf_e g_a g_agf_e g_a g_g_a f_gg f_g eef_ egf_ed_";
       // var lyrics = "unna nenachu nenachu urugi ponen mezhuga nenja odhachu odachu parandhu poona azhaga yaaro avalo ennai thendum kaatrin viralo yaaro avalo thaalaattum thaayin kuralo vaasam oosai ivai thanne endhan urave oh kanne unnaal ennai kanden kannai moodi kadhal konden paarvai ponalum paadhai neethaane kadhal thavira unnidam solla edhuvum illai"
        var  musicData = "aaa gagf_a gag f ed dgf_g fe dd af ef dc_ e fee afef dc_e fdd f e d c_ de f e d f e d c_ de f e d";
        var lyrics = "Kanne Kalaimane Kanni Mayil Ena Kanden Unai Naane Anthi Pagal Unnai Naan Paarkiren Aandavanai Ithaithaan Ketkiren Raa Ree Raa Ro O Raa Ree Ro Raa Ree Raa Ro O Raa Ree Ro"
        var musicSeries = 5;
        var musicView =HashMap<String,MusicView>();
        var musicList= ArrayList<HashMap<Int,ArrayList<MusicData>>>();
        fun downloadMediaPlayer(path:Int,key1:String,context:Context)
        {

            try {




                var player : MediaPlayer

                try {

                    player = MediaPlayer.create(context, path);
                    musicNotes.put(key1,player);

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            } catch (e: Exception) {
                println()
            }


        }


        fun getMediaPlayerSeries(context:Context)
        {
           // println();
          //  context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/c0",null,null);

            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/c"+musicSeries,null,null),"c"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/c_"+musicSeries,null,null),"c_"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/d"+musicSeries,null,null),"d"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/d_"+musicSeries,null,null),"d_"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/e"+musicSeries,null,null),"e"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/f"+musicSeries,null,null),"f"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/f_"+musicSeries,null,null),"f_"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/g"+musicSeries,null,null),"g"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/g_"+musicSeries,null,null),"g_"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/a"+musicSeries,null,null),"a"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/a_"+musicSeries,null,null),"a_"+musicSeries,context)
            downloadMediaPlayer(context.resources.getIdentifier("com.interview.rxkotlintutorial:raw/b"+musicSeries,null,null),"b"+musicSeries,context)

        }

        fun reclearAllSavedViewData()
        {
         musicView.forEach {

             it.value.visible = false;
             it.value.priotization = "";
         }

        }
        fun initializeFakeData()
        {

            var musicViewData = MusicView(true,0,listOf("c","c_"),"");
            musicViewData.visible = false
            musicView.put("0",musicViewData);
            var musicViewData1 = MusicView(true,1,listOf("d","d_"),"");
            musicViewData1.visible = false
            musicView.put("1",musicViewData1);
            var musicViewData2 = MusicView(true,2,listOf("e"),"");
            musicViewData2.visible = false
            musicView.put("2",musicViewData2);
            var musicViewData3 = MusicView(true,3,listOf("f","f_"),"");
            musicViewData3.visible = false
            musicView.put("3",musicViewData3);

            var musicViewData4 = MusicView(true,4,listOf("g","g_"),"");
            musicViewData4.visible = false
            musicView.put("4",musicViewData4);


            var musicViewData5 = MusicView(true,5,listOf("a","a_"),"");
            musicViewData5.visible = false
            musicView.put("5",musicViewData5);


            var musicViewData6 = MusicView(true,6,listOf("b"),"");
            musicViewData6.visible = false
            musicView.put("6",musicViewData6);

            /*var musicViewData7 = MusicView(true,7);
            musicViewData7.visible = true
            musicView.put("7",musicViewData7);*/

        }

        fun convertWesternNotesToMusicDataList()
        {
            musicDatas = ArrayList<MusicData>();
            // musicNotes = HashMap<String,MediaPlayer>();

            var musicCharGlobal=""
            try {

                           //var musicData = "ce de ce de ce deb bdca ce de cdea gabc bcbg def_g f_gf_ ed ef efe dc de bbc eeeea abc bcb bgggag bbc eeeea aabc bcded cbc abc cededc ce efg ga abag abc ce dedc ce efg gb abag eaae eaaeeb bcb agf_ gagfe"
            // musicData = "dbagd ddbage eecbaf fddcab dbagd ddbage ecba dddd edcag bbb bbb bdgab";
           // musicData = "fg g fg fe fg f g fe dc cf d_f d_d cc cd_ dd_ cb cd fd_d d d g d_ d d_ cb cd d_dcc c cd_d c b d d_dcc"
                var  musicArray = musicData.split(" ");
            var priotizationCount = 0;

            var highlightIndex:Float = 0f;
                var highlightTemp:Float = 0f;
            musicArray.forEachIndexed {StringIndex,stringit->
                var musicMap = HashMap<Int, ArrayList<MusicData>>();
                var musicString = stringit;

                if(highlightIndex>0) {
                    highlightIndex = highlightIndex+1

                }

                var count=1

                stringit.forEachIndexed { index, it ->

                        if (!it.toString().equals("_")) {

                            //highlightIndex=highlightIndex+( (lyrics.split(" ")[StringIndex].toString().length/stringit.length) * (index+1))
                            var hashCount:Float = stringit.count {

                                it.equals('_')

                            }.toFloat();

                            highlightIndex = highlightIndex+((lyrics.split(" ")[StringIndex].toString().length.toFloat()/(stringit.length.toFloat()-hashCount) ))
                            count++
                            var musicChar = it.toString()
                            if(index+1< musicString.length)
                                if(musicString.get(index+1).toString().equals("_"))
                                    musicChar = musicChar+musicString.get(index+1).toString()

                            musicCharGlobal = musicChar
                            var musicView = MusicConstant.musicView.filterValues {


                                it.keyvalues.contains(musicChar)

                            }.asIterable().first()

                            var musicData = MusicData(
                                musicNotes.get(musicChar.toString() + musicSeries)!!,
                                priotizationCount,
                                musicView.key.toInt() + 1,
                                musicChar.toString() + musicSeries,highlightIndex,musicList.size
                            )
                            musicDatas.add(musicData);
                            if (musicMap.contains(musicView.key.toInt())) {
                                musicMap.get(musicView.key.toInt())!!.add(musicData)
                            } else {
                                var list = ArrayList<MusicData>()
                                list.add(musicData)
                                musicMap.put(musicView.key.toInt(), list)
                            }
                            priotizationCount++


                        }


                    }

                        musicList.add(musicMap)

                }

            }
           catch (e:Exception)
           {

               println()
           }
println()

        }




    }
}