package com.example.mytimer

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var startBtn: Button
    lateinit var stopBtn: Button
    lateinit var resetBtn: Button

    var running: Boolean = false // 상태
    var pauseTime = 0L //멈춤 시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chronometer: Chronometer = findViewById(R.id.chronometer)
        startBtn = findViewById(R.id.startBtn)
        stopBtn = findViewById(R.id.stopBtn)
        resetBtn = findViewById(R.id.resetBtn)

        //화면 설정
        viewMode("stop")

        //시작 이벤트
        startBtn.setOnClickListener {

            //정지 상태일때만 실행
            if(!running){
                //기본 셋팅
                chronometer.base = SystemClock.elapsedRealtime() - pauseTime

                //시작
                chronometer.start()

                //화면 설정
                viewMode("start")
            }
        }

        //정지 이벤트
        stopBtn.setOnClickListener {

            //실행 상태일때만 실행
            if(running){
                //정지
                chronometer.stop()

                //정지 시간 저장
                pauseTime = SystemClock.elapsedRealtime() - chronometer.base

                //화면 설정
                viewMode("stop")
            }
        }

        //초기화 이벤트
        resetBtn.setOnClickListener {
            //기본 셋팅
            chronometer.base = SystemClock.elapsedRealtime()

            //정지 시간 초기화
            pauseTime = 0L

            //정지
            chronometer.stop()

            //화면 설정
            viewMode("stop")
        }

    }//onCreate

    //화면 설정
    private fun viewMode(mode: String){

        //활성화 처리
        if(mode == "start"){
            startBtn.isEnabled = false
            stopBtn.isEnabled = true
            resetBtn.isEnabled = true
            running = true
        }else{
            startBtn.isEnabled = true
            stopBtn.isEnabled = false
            resetBtn.isEnabled = false
            running = false
        }
    }
}