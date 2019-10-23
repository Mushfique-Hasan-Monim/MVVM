package monim.blackice.business.util

import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import android.widget.TextView

class StringUtil {

    lateinit var ss: SpannableString
    fun addForegroundColorSpan(color: Int, start: Int, end: Int): StringUtil {
        ss.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
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