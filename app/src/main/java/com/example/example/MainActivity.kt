package com.example.example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mService: MyService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to Service, cast the IBinder and get Service instance
            val binder = service as MyService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = MyRepository.instance
        val viewModelFactory = MyViewModelFactory(repository)
        val viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(MyViewModel::class.java)

        viewModel.number.observe(this, Observer {
            if ( null != it ) {
                binding.textView.text = it.toString()
            }
        })

        updateNumber()
    }

    override fun onStart() {
        super.onStart()
        // Bind to Service
        Intent(this, MyService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

   fun onButtonClick(v: View) {
        if (mBound) {
            // Call the setRandomNumber() fun from the MyService
            mService.setRandomNumber()
        }
    }

    fun showToast() {
        Toast.makeText(this@MainActivity, "It's a toast!", Toast.LENGTH_SHORT).show()
    }

    fun updateNumber() {
        val handler: Handler = Handler()
        val run = object : Runnable {
            override fun run() {
              handler.postDelayed(this, 2000)
                if (mBound) {
                    // Call the setRandomNumber() fun from the MyService
                    mService.setRandomNumber()
                    //showToast()
                }
            }
        }
        handler.post(run)
    }
}
