package com.blackice.business.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView

class StringUtil {

    lateinit var ss: SpannableString
    fun addForegroundColorSpan(color: Int, start: Int, end: Int): StringUtil {
        ss.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssu
    }
    fun addStyleSpan(style: Int, start: Int, end: Int): StringUtil {
        ss.setSpan(StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssu
    }

    fun addClickableSpan(cs: ClickableSpan, start: Int, end: Int): StringUtil {
        ss.setSpan(cs, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssu
    }

    fun setMovementMethod(textview: TextView): StringUtil {
        textview.setMovementMethod(LinkMovementMethod.getInstance())
        return ssu
    }

    fun getClickableSpan(url: String,context: Context): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {
                val uri: Uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent);
            }
        }
    }

    fun setSpannableString(textview: TextView) {
        textview.text = ss
    }

    fun getSS(text: String): StringUtil {
        ss = SpannableString(text)
        return ssu
    }

    fun setPrefixString(edt: EditText, prefixString: String) {
        edt.setText(prefixString)
        Selection.setSelection(edt.getText(), edt.getText().length);

        edt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(!s.toString().startsWith(prefixString)){
                    edt.setText(prefixString);
                    Selection.setSelection(edt.getText(), edt.getText().length);

                }

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }
        })
    }

    companion object {
        private var ssu: StringUtil = StringUtil()
        fun getInstance(): StringUtil {
            return ssu
        }

    }
}