package com.example.quiz;

public class question {
    private String Questions[] = {
            "what is the smallest country in the world?",

            "what is the largest country in the world?",

            "what is the capital of vietnam?",

            "what is the longest river in the world?",

            "How many colors are in the rainbow?"
    };

    private String Ranswer[] = {
            "Vatican City",
            "Russia",
            "Hà Nội",
            "River Nile",
            "7"
    };

    private String Answer[][] ={
            {"Vatican City", "England","USA", "China"},
            {"Viet Nam", "China", "Russia", "Japan"},
            {"Hà Nội", "Tp Hồ Chí Minh", "Đà Nẵng", "Quảng Ngãi"},
            {"River Pink", "River Nile", "River Love Peacock", "River 9 dragons"},
            {"4","5","6","7"},

    };

    public String getQuestion(int index)
    {
        return Questions[index];
    }

    public String getChoice1(int index)
    {
        return Answer[index][0];
    }

    public String getChoice2(int index)
    {
        return Answer[index][1];
    }

    public String getChoice3(int index)
    {
        return Answer[index][2];
    }

    public String getChoice4(int index)
    {
        return Answer[index][3];
    }

    public String getCorrectAnswer(int index)
    {
        return Ranswer[index];
    }
}
