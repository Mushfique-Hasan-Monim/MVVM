package monim.blackice.business.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        private var dateUtil: DateUtil = DateUtil()
        var FORMAT_ddMMyyyy = "ddMMyyyy"
        var FORMAT_yyyyddMM = "yyyyddMM"
        var FORMAT_EEEMMMdyyyy = "EEEMMMdyyyy"
        var FORMAT_hhmmssa = "hhmmssa"
        var FORMAT_hhmmss = "hhmmss"
        var FORMAT_hhmm = "hhmm"
        var FORMAT_hhmma = "hhmma"
        var FORMAT_EEEMMMdd = "EEEMMMdd"
        var FORMAT_EEEEMMMdd = "EEEEMMMdd"


        var SEPARATOR_DOT = "."
        var SEPARATOR_FORWARDSLASH = "/"
        var SEPARATOR_DASH = "-"
        var SEPARATOR_COLON = ":"
        var SEPARATOR_SPACE = " "


        fun getInstance(): DateUtil {
            return dateUtil
        }

    }
    var dateFormat: String = "yyyy.MM.dd"
    var formatFlag: String = "yyyyMMdd"
    var saparatorFlag: String = "."


    fun setFormatFlag(formatFlag: String): DateUtil {
        this.formatFlag = formatFlag
        return dateUtil;
    }

    fun setSaparatorFlag(saparatorFlag: String): DateUtil {
        this.saparatorFlag = saparatorFlag
        return dateUtil;
    }

    fun generateDateTimeFormat(): DateUtil {

        dateFormat = ""
        for (i in 0..formatFlag.length - 1) {
            if (i < formatFlag.length - 1) {
                if (formatFlag.get(i) == formatFlag.get(i + 1)) {
                    dateFormat = dateFormat + formatFlag.get(i)
                } else if (formatFlag.get(i + 1) != 'a') {
                    dateFormat = dateFormat + formatFlag.get(i) + saparatorFlag
                } else {
                    dateFormat = dateFormat + formatFlag.get(i) + " "
                }
            } else {
                dateFormat = dateFormat + formatFlag.get(i)
            }
        }
        return dateUtil;
    }


    fun buildDateTime(addDate: Int): String {
        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFOrmat: SimpleDateFormat = SimpleDateFormat(dateFormat)
        calendar.add(Calendar.DAY_OF_YEAR, addDate)
        return simpleDateFOrmat.format(calendar.time)
    }


}