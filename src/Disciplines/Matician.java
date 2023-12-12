package Disciplines;

import Disciplines.Discipline;

import java.util.ArrayList;

public class Matician extends Discipline {
    private ArrayList<String> olympiadePositions=new ArrayList<>();
    public Matician()
    {
        setHours(0);
        setSuccess(false);
        setCertification(false);
        setName("Математика");
        setDifTests("Пройти тест.", "Принять участие в олимпиаде", "Показать информацию об участии в олимпиадах");
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
        if (getHours()>=3&&getSuccess()&&getCountOfMarks()>=3)
        {
            setCertification(true);
        }
    }
    public ArrayList<String> getOlympiadePositions()
    {
        return this.olympiadePositions;
    }
    public void setOlympiadePositions(ArrayList<String> olympiadePositions)
    {
        this.olympiadePositions=olympiadePositions;
    }
    public int getCountPositions()
    {
        return this.olympiadePositions.size();
    }
    public int participateOlympiade()
    {
        int position=(int)(Math.random()*100)+1;
        olympiadePositions.add(String.valueOf(position));
        setCertification(true);
        if (getCountOfMarks()<3)
        {
            while (getCountOfMarks()<3)
            {
                addMark(String.valueOf(5));
            }
        }
        if (getHours()<3)
        {
            setHours(3);
        }
        setSuccess(true);
        return position;
    }
    public int showResultsOfOlympiades()
    {
        int bestPosition=100;
        for (int i=0; i<olympiadePositions.size(); ++i)
        {
            if (Integer.parseInt(olympiadePositions.get(i))<bestPosition)
            {
                bestPosition=Integer.parseInt(olympiadePositions.get(i));
            }
        }
        return bestPosition;
    }
}
