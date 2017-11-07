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
        String day = new SimpleDateFormat("E", Locale.CHINESE).format(new Date());
        String info = "";
        switch (day) {
            case "星期一":
                info = CurriculumEnum.Monday.getClassInfo();
                break;
            case "星期二":
                info = CurriculumEnum.Tuesday.getClassInfo();
                break;
            case "星期三":
                info = CurriculumEnum.Wednesday.getClassInfo();
                break;
            case "星期四":
                info = CurriculumEnum.Thursday.getClassInfo();
                break;
            case "星期五":
                info = CurriculumEnum.Friday.getClassInfo();
                break;
            default:
        }
        return ((!"星期六".equals(day)) ? "今天课程:\n" : "") + info + (("星期五".equals(day) || "星期六".equals(day)) ? "" : "晚自习:A301");
    }
}
