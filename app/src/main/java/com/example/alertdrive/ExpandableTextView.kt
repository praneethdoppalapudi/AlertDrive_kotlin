package com.example.alertdrive

import android.content.Context
import android.content.res.TypedArray
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class ExpandableTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {

    private var originalText: CharSequence? = null
    private var trimmedText: CharSequence? = null
    private var bufferType: TextView.BufferType? = null
    private var trim = true
    private val trimLength: Int

    private val displayableText: CharSequence?
        get() = if (trim) trimmedText else originalText

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH)
        typedArray.recycle()

        setOnClickListener {
            trim = !trim
            setText()
            requestFocusFromTouch()
        }
    }

    private fun setText() {
        super.setText(displayableText, bufferType)
    }

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        originalText = text
        trimmedText = getTrimmedText(text)
        bufferType = type
        setText()
    }

    private fun getTrimmedText(text: CharSequence): CharSequence? {
        return if (originalText != null && originalText!!.length > trimLength) {
            SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS)
        } else {
            originalText
        }
    }

    companion object {

        private val DEFAULT_TRIM_LENGTH = 20
        private val ELLIPSIS = "....."
    }

}