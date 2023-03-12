package by.bsuir.client.service;


import by.pojo.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class ClientConnectionModule {
    private Socket socket;
    private String serverIp;
    private int serverPort;

    public ClientConnectionModule(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public ClientConnectionModule() {
        super();
    }

    private void sendObject(Serializable object) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    private <T> T receiveObject() throws IOException, ClassNotFoundException {

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (T) objectInputStream.readObject();
    }

    public void connectToServer() throws IOException {
        socket = new Socket(serverIp, serverPort);
    }

    public ArrayList<Client> getAllClients() {
        try {
            sendObject("GET_ALL_CLIENTS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createClient(Client client) {
        try {
            sendObject("CREATE_CLIENT");
            sendObject(client);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteClient(int clientId) {
        try {
            sendObject("DELETE_CLIENT");
            sendObject(clientId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateClient(Client client) {
        try {
            sendObject("UPDATE_CLIENT");
            sendObject(client);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CarRow> LoadCarsInTable() {
        try {
            sendObject("LOAD_CARS_IN_TABLE");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Car> loadClientCarsInCB(int clientId) {
        try {
            sendObject("LOAD_CLIENT_CARS_IN_CB");
            sendObject(clientId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Car> getAllCars() {
        try {
            sendObject("GET_ALL_CARS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createCar(Car car) {
        try {
            sendObject("CREATE_CAR");
            sendObject(car);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCar(int carId) {
        try {
            sendObject("DELETE_CAR");
            sendObject(carId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateCar(Car car) {
        try {
            sendObject("UPDATE_CAR");
            sendObject(car);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SchedulePrint> getSchedulesToPrint() {
        try {
            sendObject("GET_SCHEDULES_TO_PRINT");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Schedule> getAllSchedules() {
        try {
            sendObject("GET_ALL_SCHEDULES");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteSchedule(int scheduleId) {
        try {
            sendObject("DELETE_SCHEDULE");
            sendObject(scheduleId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getWorkingHours() {
        try {
            sendObject("GET_WORKING_HOURS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Lift> getAllLifts() {
        try {
            sendObject("GET_ALL_LIFTS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Operation> getAllOperations() {
        try {
            sendObject("GET_ALL_OPERATIONS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOperation(int operationId) {
        try {
            sendObject("DELETE_OPERATION");
            sendObject(operationId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createOperation(Operation operation) {
        try {
            sendObject("CREATE_OPERATION");
            sendObject(operation);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateOperation(Operation operation) {
        try {
            sendObject("UPDATE_OPERATION");
            sendObject(operation);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Parts> getAllParts() {
        try {
            sendObject("GET_ALL_PARTS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePart(int partId) {
        try {
            sendObject("DELETE_PART");
            sendObject(partId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createPart(Parts part) {
        try {
            sendObject("CREATE_PART");
            sendObject(part);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updatePart(Parts part) {
        try {
            sendObject("UPDATE_PART");
            sendObject(part);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createSchedule(Schedule schedule) {
        try {
            sendObject("CREATE_SCHEDULE");
            sendObject(schedule);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<InfoToBarChart> getWorkLoadInfoToBarChart(LocalDateTime date) {
        try {
            sendObject("GET_WORKLOAD_INFO_TO_BARCHART");
            sendObject(date);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(User user) {
        try {
            sendObject("LOGIN");
            sendObject(user);
            return receiveObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createUser(User user) {
        try {
            sendObject("CREATE_USER");
            sendObject(user);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAllUsers() {
        try {
            sendObject("GET_ALL_USERS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int userId) {
        try {
            sendObject("DELETE_USER");
            sendObject(userId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(User user) {
        try {
            sendObject("UPDATE_USER");
            sendObject(user);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<InfoToBarChart> getRevenueInfoToBarChart(LocalDateTime date) {
        try {
            sendObject("GET_REVENUE_INFO_TO_BARCHART");
            sendObject(date);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

