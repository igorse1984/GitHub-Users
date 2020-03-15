package developer.igorsharov.githubusers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorRes

fun getClickableUrlText(context: Context, text: String): SpannableString =
    SpannableString(text).apply {
        setSpan(
            object : ClickableSpan() {
                override fun onClick(textView: View) {
                    openUrlExternal(context, text)
                }

                override fun updateDrawState(textPaint: TextPaint) {
                    textPaint.color = getColor(context, R.color.colorBlue)
                    textPaint.isUnderlineText = true
                }
            },
            0,
            length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

fun openUrlExternal(context: Context, url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).run {
        context.startActivity(this)
    }
}

fun getColor(context: Context, @ColorRes color: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        context.resources.getColor(color, context.resources.newTheme())
    else
        context.resources.getColor(color)