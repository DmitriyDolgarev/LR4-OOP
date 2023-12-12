package Disciplines;

import Disciplines.Discipline;

public class Chemist extends Discipline {
    private boolean laboratoryWork;
    public Chemist()
    {
        setHours(0);
        setSuccess(true);
        setName("Химия");
        setDifTests("Сдать контрольную работу.", "Написать исследовательскую работу.", "Сдать лабораторную работу.");
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
        if (getHours()>=2&&getCountOfMarks()>=3&&laboratoryWork)
        {
            setCertification(true);
        }
    }
    public void writeResearchWork()
    {
        setCertification(true);
        if (getCountOfMarks()<3)
        {
            while (getCountOfMarks()<3)
            {
                addMark(String.valueOf(5));
            }
        }
        if (getHours()<2)
        {
            setHours(2);
        }
        setSuccess(true);
        this.laboratoryWork=true;
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
