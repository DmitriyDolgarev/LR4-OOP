package Dolgarev;

import Disciplines.Chemist;
import Disciplines.Matician;
import Disciplines.Phisicist;
import Disciplines.Programmer;

import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static Matician matician = new Matician();
    private static Phisicist phisicist = new Phisicist();
    private static Chemist chemist = new Chemist();
    private static Programmer programmer = new Programmer();
    private static Zachetka zachetka = new Zachetka();
    private static Scanner scanner = new Scanner(System.in);

    public static int readChoice() {
        int readed = -1;
        try {
            readed = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода!");
            readChoice();
        }
        return readed;
    }

    public static void mainMenu() {
        int choice = -1;
        do {
            System.out.println("Моя зачетка" +
                    "\n______________________________" +
                    "\nГлавное меню: " +
                    "\n1) Выбрать предмет." +
                    "\n2) Добавить предмет." +
                    "\n3) Показать общую информацию по всем предметам" +
                    "\n0) Выход.");
            choice = readChoice();
            switch (choice) {
                case 1:
                    chooseDiscipline();
                    break;
                case 2:
                    addDiscipline();
                    break;
                case 3:
                    showGeneralInfo();
                case 0:
                    break;
                default:
                    System.out.println("Неопознанная команда");
            }
        }
        while (choice != 0);
    }

    public static void chooseDiscipline() {
        int choice = -1;
        if (zachetka.getCountOfDisciplines() == 0) {
            System.out.println("Чтобы выбрать предмет, сначала добавьте его.");
        } else {
            do {
                for (int i = 0; i < zachetka.getCountOfDisciplines(); ++i) {
                    System.out.println((i + 1) + ") " + zachetka.getNameOfDiscipline(i));
                }
                System.out.println("0) Выход.");
                choice = readChoice();
                if (choice != 0) {
                    disciplineMenu(choice - 1);
                }
            }
            while (choice != 0);
        }
    }

    public static void addDiscipline() {
        int choice = -1;
        String[] list = makeListOfDisciplines();
        if (list.length == 0) {
            System.out.println("Вы добавили все возможные предметы");
        } else {
            System.out.println("Какой предмет вы хотите добавить?:");
            for (int i = 0; i < list.length; ++i) {
                System.out.println((i + 1) + ") " + list[i]);
            }
            choice = readChoice();
            --choice;
            switch (list[choice]) {
                case "Математика":
                    zachetka.addDiscipline(matician);
                    break;
                case "Физика":
                    zachetka.addDiscipline(phisicist);
                    break;
                case "Химия":
                    zachetka.addDiscipline(chemist);
                    break;
                case "Программирование":
                    zachetka.addDiscipline(programmer);
                    break;
                default:
                    System.out.println("Неопознанная команда");
            }
            System.out.println("Предмет " + list[choice] + " добавлен!");
        }
    }

    public static void disciplineMenu(int index) {
        int choice = -1;
        do {
            System.out.println("Меню предмета:" +
                    "\n1) Посмотреть количество часов." +
                    "\n2) Посмотреть сдана ли контрольная работа." +
                    "\n3) Посмотреть аттестацию." +
                    "\n4) Посетить занятие." +
                    "\n5) " + zachetka.getNameOfTest(index) +
                    "\n6) " + zachetka.getNameOfWork(index) +
                    "\n7) " + zachetka.getNameOfSecondWork(index) +
                    "\n8) Посмотреть оценки" +
                    "\n0) Выход.");
            choice = readChoice();
            switch (choice) {
                case 1:
                    printCountHours(index);
                    break;
                case 2:
                    printSuccess(index);
                    break;
                case 3:
                    printCertification(index);
                    break;
                case 4:
                    doLesson(index);
                    break;
                case 5:
                    doTest(index);
                    break;
                case 6:
                    doWork(index);
                    break;
                case 7:
                    doSecondWork(index);
                    break;
                case 8:
                    checkMarks(index);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неопознанная команда");
            }
        }
        while (choice != 0);
    }

    public static String[] makeListOfDisciplines() {
        ArrayList<String> list = new ArrayList<>();
        boolean rav = false;
        String[] listDis = {"Математика", "Физика", "Химия", "Программирование"};
        if (zachetka.getCountOfDisciplines() == 0) {
            return listDis;
        } else {
            for (String discipline : listDis) {
                rav = false;
                for (int i = 0; i < zachetka.getCountOfDisciplines(); ++i) {
                    if (discipline == zachetka.getNameOfDiscipline(i)) {
                        rav = true;
                    }
                }
                if (rav == false) {
                    list.add(discipline);
                }
            }
            String[] finalList = new String[list.size()];
            for (int i = 0; i < finalList.length; ++i) {
                finalList[i] = list.get(i);
            }
            return finalList;
        }
    }

    public static void printCountHours(int index) {
        System.out.println("Количество часов- " + zachetka.getHours(index));
    }

    public static void printSuccess(int index) {
        if (zachetka.getSuccess(index)) {
            System.out.println("Тест сдан!");
        } else {
            System.out.println("Тест не сдан.");
        }
    }

    public static void printCertification(int index) {
        if (zachetka.getCertification(index)) {
            System.out.println("Вы аттестованы!");
        } else {
            System.out.println("Вы не аттестованы.");
        }
    }

    public static void showGeneralInfo() {
        if (zachetka.getCountOfDisciplines() == 0) {
            System.out.println("Прежде чем посмотреть информацию по всем предметам вам нужно иметь в зачетке хотя бы 1 предмет.");
        } else {
            for (int i = 0; i < zachetka.getCountOfDisciplines(); ++i) {
                System.out.println(zachetka.getNameOfDiscipline(i) + ": ");
                printCountHours(i);
                printSuccess(i);
                printCertification(i);
            }
        }
    }

    public static void checkMarks(int index) {
        if (zachetka.getCountOfMarks(index) == 0) {
            System.out.println("У вас нет оценок по этому предмету.");
        } else {
            int[] marks = zachetka.getMarksOfDiscipline(index);
            float srMark = 0;
            System.out.print("Оценки по предмету: ");
            for (int i = 0; i < marks.length; ++i) {
                System.out.print(marks[i] + "  ");
                srMark += marks[i];
            }
            System.out.println("\nСредний балл- " + srMark / marks.length);
        }
    }

    public static void doWork(int index) {
        int value;
        switch (zachetka.getNameOfDiscipline(index)) {
            case "Математика":
                System.out.println("Вы приняли участие в олимпиаде. Вы заняли " + zachetka.doWork(index) + " место! Теперь вы аттестованы!");
                break;
            case "Физика":
                value = zachetka.doWork(index);
                System.out.println("Вы приняли участие в параде проектов, теперь вам необязательно сдавать лабораторную работу");
                break;
            case "Химия":
                value = zachetka.doWork(index);
                System.out.println("Вы написали исследовательскую работу, теперь вы аттестованы по химии!");
                break;
            case "Программирование":
                value = zachetka.doWork(index);
                System.out.println("Вы приняли участие в создании проекта, за это вы аттестованы!");
            default:
                System.out.println("Неопознанный предмет");
        }
    }

    public static void doSecondWork(int index) {
        switch (zachetka.getNameOfDiscipline(index)) {
            case "Математика":
                Matician matician1 = (Matician) zachetka.getDiscipline(index);
                System.out.println("Вы приняли участие в " + matician1.getCountPositions() + " олимпиадах. Лучший результат- " +
                        +zachetka.doSecondWork(index) + " место.");
                break;
            case "Физика":
                if (zachetka.doSecondWork(index) > 2) {
                    System.out.println("Вы сдали лабораторную работу на " + zachetka.doSecondWork(index));
                } else {
                    System.out.println("Вы не смогли сдать лабораторную работу, попробуйте еще раз.");
                }
                break;
            case "Химия":
                if (zachetka.doSecondWork(index) > 2) {
                    System.out.println("Вы сдали лабораторную работу на " + zachetka.doSecondWork(index));
                } else {
                    System.out.println("Вы не смогли сдать лабораторную работу, попробуйте еще раз.");
                }
                break;
            case "Программирование":
                System.out.println("Вы приняли участие в соревнованиях по спортивному ориентированию. " +
                        "Вы заняли " + zachetka.doSecondWork(index) + " место.");
                break;
            default:
                System.out.println("Неопознанный предмет");
        }
    }

    public static void doTest(int index) {
        int value= zachetka.doTest(index);
        switch (zachetka.getNameOfDiscipline(index)) {
            case "Математика":
                System.out.println("Тест выполнен на оценку "+value);
                break;
            case "Физика":
                System.out.println("Контрольная работа сдана на оценку "+value);
                break;
            case "Химия":
                System.out.println("Контрольная работа сдана на оценку "+value);
                break;
            case "Программирование":
                System.out.println("Курсовой проект сдан на оценку "+value);
                break;
            default:
                System.out.println("Неопознанный предмет");
        }
    }
    public static void doLesson(int index)
    {
        int value= zachetka.doLesson(index);
        switch (zachetka.getNameOfDiscipline(index)) {
            case "Математика":
                System.out.println("Занятие по математике посещено! " +
                        "Посещаемость: "+value+" часов из 3.");
                break;
            case "Физика":
                System.out.println("Занятие по физике посещено." +
                        " Посещаемость: "+value+" часов из 2.");
                break;
            case "Химия":
                System.out.println("Пара химии посещена. Посещаемость: "+value);
                break;
            case "Программирование":
                System.out.println("Пара по программированию посещена. Посещаемость: " +
                        value+" часов.");
                break;
            default:
                System.out.println("Неопознанный предмет");
        }
    }
}