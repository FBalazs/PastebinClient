package bwp.pastebinclient

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: PastebinClientApplicationComponent
    get() {
        return (this.applicationContext as PastebinClientApplication).injector
    }

val Fragment.injector: PastebinClientApplicationComponent
    get() {
        return (this.context!!.applicationContext as PastebinClientApplication).injector
    }
