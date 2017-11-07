package top.itning.curriculum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author wangn
 */
public class CurriculumClient {
    private CurriculumClient() {
    }

    public static String getClassInfo() {
        String day = new SimpleDateFormat("EE", Locale.US).format(new Date());
        String info = "";
        switch (day) {
            case "Mon":
                info = CurriculumEnum.Monday.getClassInfo();
                break;
            case "Tue":
                info = CurriculumEnum.Tuesday.getClassInfo();
                break;
            case "Wed":
                info = CurriculumEnum.Wednesday.getClassInfo();
                break;
            case "Thu":
                info = CurriculumEnum.Thursday.getClassInfo();
                break;
            case "Fri":
                info = CurriculumEnum.Friday.getClassInfo();
                break;
            default:
        }
        return ((!"星期六".equals(day)) ? "今天课程:\n" : "") + info + (("星期五".equals(day) || "星期六".equals(day)) ? "" : "晚自习:A301");
    }
}
