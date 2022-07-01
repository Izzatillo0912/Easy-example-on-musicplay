package com.example.playaudioeasy

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.io.IOException
import java.security.Permission
import java.security.Permissions

class MainActivity : AppCompatActivity() {

    var url : String = "https://lovemusic.uz/uploads/files/2022-01/1641036552_019_-the-free-dance-the-night-away-radio-mix.mp3"
    var onlineMediaPlayer = MediaPlayer()
    var offlineMediaPlayer = MediaPlayer()
    var offlineMusicList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        offlineMusicList.add(R.raw.my_nued_ahh)
        offlineMusicList.add(R.raw.miyagi_samurai)
        onlineMediaPlayer.setDataSource(url)
    }

    fun AudioPlay(view: View) {
        if (haveNetwork() && !onlineMediaPlayer.isPlaying) {
            Toast.makeText(this, "Musiqa eshitirildi", Toast.LENGTH_SHORT).show()
            try {
                onlineMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                onlineMediaPlayer.prepare()
                onlineMediaPlayer.start()
            } catch (e : IOException) {
                e.printStackTrace()
            }
        }
        else {
            if (!haveNetwork()) {
                Toast.makeText(this, "No internet ", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Music stopped", Toast.LENGTH_SHORT).show()
                onlineMediaPlayer.stop()
                offlineMediaPlayer.stop()
            }

        }
    }

    fun OfflineMusic1(view: View) {
        Toast.makeText(this, "Offline 1 - Music started", Toast.LENGTH_SHORT).show()
        onlineMediaPlayer.stop()
        offlineMediaPlayer.stop()
        offlineMediaPlayer = MediaPlayer.create(this, offlineMusicList[0])
        offlineMediaPlayer.start()

    }

    fun OfflineMusic2(view: View) {
        Toast.makeText(this, "Offline 2 - Music started", Toast.LENGTH_SHORT).show()
        onlineMediaPlayer.stop()
        offlineMediaPlayer.stop()
        offlineMediaPlayer = MediaPlayer.create(this, offlineMusicList[1])
        offlineMediaPlayer.start()
    }

    fun haveNetwork() : Boolean{
        var haveWIFI = false
        var haveMobile = false
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        for (info in networkInfo) {
            if (info.typeName.equals("WIFI", ignoreCase = true)) if (info.isConnected) haveWIFI = true
            if (info.typeName.equals("MOBILE", ignoreCase = true)) if (info.isConnected) haveMobile = true
        }
        return when(true) {
            haveWIFI -> true
            haveMobile -> true
            else -> false
        }
    }
}
