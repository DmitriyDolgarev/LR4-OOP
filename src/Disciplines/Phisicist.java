package Disciplines;

import Disciplines.Discipline;

import java.util.ArrayList;

public class Phisicist extends Discipline {
    private boolean laboratoryWork;
    public Phisicist()
    {
        setHours(0);
        setSuccess(false);
        setCertification(false);
        setName("Физика");
        setDifTests("Сдать контрольную работу.", "Принять участиe в параде проектов.", "Сдать лабораторную работу");
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
        if (getHours()>=2&&getSuccess()&&getCountOfMarks()>=3&&laboratoryWork)
        {
            setCertification(true);
        }
    }
    public void participateParadeOfProject()
    {
        setSuccess(true);
    }
    public int doLaboratoryWork()
    {
        int random=(int)(Math.random()*4)+2;
        if (random>2)
        {
            this.laboratoryWork=true;
            addMark(String.valueOf(random));
            certify();
        }
        return random;
    }
}
