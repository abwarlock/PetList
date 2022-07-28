package com.example.petlist.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.petlist.models.OutsideWorkHourEvent
import com.example.petlist.utils.DialogUtils
import com.example.petlist.utils.WorkHrManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


abstract class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        workHourStatus(OutsideWorkHourEvent(true))
        WorkHrManager.getInstance(this).registerEvents(this)
    }

    override fun onPause() {
        super.onPause()
        WorkHrManager.getInstance(this).unRegisterEvents(this)
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun workHourStatus(event: OutsideWorkHourEvent) {
        if(event.checkStatus){
            if (!WorkHrManager.getInstance(this).isAppIsAllowedToUse()) {
                DialogUtils.showConfirmDialog(lifecycle, this) { _, _ ->
                    finishAffinity()
                }
            }
        }
    }
}