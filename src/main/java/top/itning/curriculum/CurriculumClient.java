package top.itning.curriculum;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangn
 */
public class CurriculumClient {
    private CurriculumClient() {
    }

    public static String getClassInfo() {
        int day = Integer.parseInt(new SimpleDateFormat("F").format(new Date()));
        String info = "";
        switch (day) {
            case 1:
                info = CurriculumEnum.Monday.getClassInfo();
                break;
            case 2:
                info = CurriculumEnum.Tuesday.getClassInfo();
                break;
            case 3:
                info = CurriculumEnum.Wednesday.getClassInfo();
                break;
            case 4:
                info = CurriculumEnum.Thursday.getClassInfo();
                break;
            case 5:
                info = CurriculumEnum.Friday.getClassInfo();
                break;
            default:
        }
        return ((day != 6) ? "今天课程:\n" : "") + info + ((day == 5 || day == 6) ? "" : "晚自习:A301");
    }
}
