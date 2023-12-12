package Disciplines;

import Disciplines.Discipline;

import java.util.ArrayList;

public class Programmer extends Discipline {
    private boolean sportProgramming;
    public Programmer()
    {
        setHours(0);
        setSuccess(false);
        setCertification(false);
        setName("Программирование");
        setDifTests("Сдать курсовой проект.", "Принять участие в создании проекта по программированию.",
                "Принять участие в соревнованиях по спортивному программированию");
    }
    @Override
    public int doLesson() {
        setHours(getHours()+1);
        certify();
        return getHours()+1;
    }

    @Override
    public int startTest() {
        int randomMark=(int)(Math.random()*4)+2;
        addMark(String.valueOf(randomMark));
        if (randomMark>2)
        {
            setSuccess(true);
        }
        certify();
        return randomMark;
    }

    @Override
    public void certify() {
        if (getHours()>0&&getSuccess()&&getCountOfMarks()>=3&&sportProgramming)
        {
            setCertification(true);
        }
    }
    public void joinToProekt()
    {
        setCertification(true);
        if (getCountOfMarks()<3)
        {
            while (getCountOfMarks()<3)
            {
                addMark(String.valueOf(5));
            }
        }
        if (getHours()==0)
        {
            setHours(1);
        }
        setSuccess(true);
        this.sportProgramming=true;
    }
    public int participateSportProgramming()
    {
        int position=(int)(Math.random()*100)+1;
        sportProgramming=true;
        certify();
        return position;
    }
}
