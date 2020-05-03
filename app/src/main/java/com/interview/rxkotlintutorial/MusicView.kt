package com.interview.rxkotlintutorial

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


data class MusicView(
    private var _visible: Boolean,
    private var _key: Int,
    private var _keyvalues:List<String>,
    private var _priotization:String
) : BaseObservable() {



    var visible: Boolean
        @Bindable
        get() = _visible
        set(value) {
            _visible = value
           notifyPropertyChanged(com.interview.rxkotlintutorial.BR._all)
        }

    var key: Int
       // @Bindable
        get() = _key
        set(value) {
            _key = value
           // notifyPropertyChanged(BR.url)
        }


    var keyvalues: List<String>
        // @Bindable
        get() = _keyvalues
        set(value) {
            _keyvalues = value
            // notifyPropertyChanged(BR.url)
        }

    var priotization: String
        @Bindable
        get() = _priotization
        set(value) {
            _priotization = value
            notifyPropertyChanged(com.interview.rxkotlintutorial.BR._all)
        }

}