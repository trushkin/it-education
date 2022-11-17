package by.bsuir.coursework.sto.application;

import by.bsuir.coursework.sto.car.Car;
import by.bsuir.coursework.sto.client.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class DatabaseCompleting {
    static final Random random = new Random();
    private static final List<String> nameList;

    static {
        nameList = new ArrayList<>();
        nameList.add("Владислав");
        nameList.add("Иван");
        nameList.add("Александр");
        nameList.add("Илья");
        nameList.add("Ростислав");
        nameList.add("Антон");
        nameList.add("Евгений");
        nameList.add("Роман");
        nameList.add("Никита");
        nameList.add("Матвей");

    }

    private static final List<String> surnameList;

    static {
        surnameList = new ArrayList<>();
        surnameList.add("Трухан");
        surnameList.add("Петров");
        surnameList.add("Иванов");
        surnameList.add("Юшкевич");
        surnameList.add("Панкевич");
        surnameList.add("Стремоус");
        surnameList.add("Ефимчик");
        surnameList.add("Родионов");
        surnameList.add("Медведев");
        surnameList.add("Комличенко");
    }

    private static final List<String> patronymicList;

    static {
        patronymicList = new ArrayList<>();
        patronymicList.add("Дмитриевич");
        patronymicList.add("Владимирович");
        patronymicList.add("Иванович");
        patronymicList.add("Ильич");
        patronymicList.add("Петрович");
        patronymicList.add("Александрович");
        patronymicList.add("Тимовеевич");
        patronymicList.add("Витальевич");
        patronymicList.add("Андрееевич");
        patronymicList.add("Олегович");
    }

    public static void fillClientsWithTestValues() throws SQLException {
        int clientIndex = 0;
        while (clientIndex < 10) {
            Client.addClient(nameList.get(random.nextInt(nameList.size())), surnameList.get(random.nextInt(surnameList.size())),
                    patronymicList.get(random.nextInt(patronymicList.size())), "37544" + random.nextInt(100000, 1000000));
            clientIndex++;
        }
    }


}
