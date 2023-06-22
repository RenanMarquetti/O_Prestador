package com.example.oprestador.lnicial.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.ColorInt
import com.example.oprestador.R

class LoadingBotton: FrameLayout {

    private lateinit var button: Button
    private lateinit var progress: ProgressBar

    private var text: String? = null


    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        setup(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.button_loading, this)

        val taypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingBotton, 0, 0)
        text = taypedArray.getString(R.styleable.LoadingBotton_text)

        button = getChildAt(0) as Button
        progress = getChildAt(1) as ProgressBar

        button.text = text
        button.isEnabled = false

        taypedArray.recycle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        button.isEnabled = enabled
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

    public fun showProgressBar(enabled: Boolean) {

        if(enabled){
            button.isEnabled = false
            button.text = ""
            progress.visibility = View.VISIBLE
        } else {
            button.isEnabled = true
            button.text = text
            progress.visibility = View.GONE
        }

    }

}