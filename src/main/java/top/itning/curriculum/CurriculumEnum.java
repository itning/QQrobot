package top.itning.curriculum;

/**
 * @author wangn
 */
public enum CurriculumEnum {
    Monday(
            "",
            "三四节:篮球 外聘2 地点:篮球场",
            "五六节:互联网程序设计 梁海红 地点:B313",
            "七八节:英语四六级 李鑫 地点:A301"
    ),
    Tuesday(
            "一二节:英语四六级 李鑫 地点:A301",
            "三四节:线性代数 徐延新 地点:A330",
            "",
            ""
    ),
    Wednesday(
            "一二节:互联网程序设计 梁海红 地点:B313",
            "三四节:JAVA程序设计 于洪 地点:B202",
            "五六节:模电数电 华晓杰 地点:B313",
            ""
    ),
    Thursday(
            "一二节:计算机组成原理 夏庆英 地点:B312",
            "三四节:线性代数 徐延新 地点:A401",
            "五六节:JAVA程序设计 于洪 地点:B317",
            "五六节:计算机组成原理 夏庆英 地点:B211"
    ),
    Friday(
            "一二节:模电数电 华晓杰 地点:A221",
            "",
            "",
            ""
    );

    private String class1;
    private String class2;
    private String class3;
    private String class4;

    CurriculumEnum(String class1, String class2, String class3, String class4) {
        this.class1 = class1;
        this.class2 = class2;
        this.class3 = class3;
        this.class4 = class4;
    }

    public String getClassInfo() {
        return class1 + "\n" + class2 + "\n" + class3 + "\n" + class4 + "\n";
    }

}
