package com.example.multiplechoice;
public class questions {
    private String Questions[] = {
            "Đây là ai?",

            "Ai đây là?",

            "Đây là ai?",

            "Ai là đây??",

            "Là ai đây?"
    };

    private String Ranswer[] = {
            "Donald Trump",
            "Putin",
            "Nguyễn Xuân Phúc",
            "Barack Obama",
            "Kim Jong Un"
    };

    private String Answer[][] ={
            {"Đỗ Nam Trung", "American","USA", "Donald Trump"},
            {"Nga", "Putin", "C4H6", "Kẻ hủy diệt"},
            {"Nguyễn Xuân Phúc", "Đỗ Xuân Phúc", "Phạm Xuân Phúc", "Dương Xuân Phúc"},
            {"Michele Obama", "Barack Obama", "Malia Obama", "Sha Sha Obama"},
            {"Thư kí Kim","Kim Kim","Kim Jong Un","Kim thêu"},

    };

    public String getQuestion(int index)
    {
        return Questions[index];
    }

    public String[] getChoice(int index)
    {
        return Answer[index];
    }


    public String getCorrectAnswer(int index)
    {
        return Ranswer[index];
    }
}
