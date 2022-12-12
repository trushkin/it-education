package by.bsuir;

import by.bsuir.car.*;
import by.bsuir.charts.GetInfoToBarCharCommand;
import by.bsuir.client.CreateClientCommand;
import by.bsuir.client.DeleteClientCommand;
import by.bsuir.client.GetAllClientsCommand;
import by.bsuir.client.UpdateClientCommand;
import by.bsuir.lift.GetAllLiftsCommand;
import by.bsuir.operation.CreateOperationCommand;
import by.bsuir.operation.DeleteOperationCommand;
import by.bsuir.operation.GetAllOperationsCommand;
import by.bsuir.operation.UpdateOperationCommand;
import by.bsuir.parts.CreatePartCommand;
import by.bsuir.parts.DeletePartCommand;
import by.bsuir.parts.GetAllPartsCommand;
import by.bsuir.parts.UpdatePartCommand;
import by.bsuir.properties.GetWorkingHoursCommand;
import by.bsuir.schedule.CreateScheduleCommand;
import by.bsuir.schedule.DeleteScheduleCommand;
import by.bsuir.schedule.GetAllSchedules;
import by.bsuir.schedule.GetSchedulesToPrintCommand;
import by.bsuir.service.ClientConnector;
import by.bsuir.service.ConnectedClientInfo;
import by.bsuir.user.CreateUserCommand;
import by.bsuir.user.LoginCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final ConnectedClientInfo clientInfo;

    public ClientHandler(ConnectedClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    private void sendObject(Serializable object) throws IOException {

        Socket socket = clientInfo.getConnectionSocket();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    public <T> T receiveObject() throws IOException, ClassNotFoundException {

        Socket socket = clientInfo.getConnectionSocket();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (T) objectInputStream.readObject();
    }

    private static Logger logger = LogManager.getLogger();

    private void clientProcessing() throws IOException, ClassNotFoundException {
        ClientConnector clientConnector = new ClientConnector() {
            @Override
            public <T> T receiveObject() throws IOException, ClassNotFoundException {
                return ClientHandler.this.receiveObject();
            }

            @Override
            public void sendObject(Serializable object) throws IOException {
                ClientHandler.this.sendObject(object);
            }
        };
        while (true) {
            String command = receiveObject();
            logger.info("Received command: " + command);
            switch (command) {
                case "GET_ALL_CLIENTS" -> {
                    GetAllClientsCommand getAllClientsCommand = new GetAllClientsCommand(clientConnector);
                    getAllClientsCommand.execute();
                }
                case "CREATE_CLIENT" -> {
                    CreateClientCommand createClientCommand = new CreateClientCommand(clientConnector);
                    createClientCommand.execute();
                }
                case "DELETE_CLIENT" -> {
                    DeleteClientCommand deleteClientCommand = new DeleteClientCommand(clientConnector);
                    deleteClientCommand.execute();
                }
                case "UPDATE_CLIENT" -> {
                    UpdateClientCommand updateClientCommand = new UpdateClientCommand(clientConnector);
                    updateClientCommand.execute();
                }
                case "LOAD_CARS_IN_TABLE" -> {
                    LoadCarsInTableCommand getAllCarsCommand = new LoadCarsInTableCommand(clientConnector);
                    getAllCarsCommand.execute();
                }
                case "CREATE_CAR" -> {
                    CreateCarCommand createCarCommand = new CreateCarCommand(clientConnector);
                    createCarCommand.execute();
                }
                case "DELETE_CAR" -> {
                    DeleteCarCommand deleteCarCommand = new DeleteCarCommand(clientConnector);
                    deleteCarCommand.execute();
                }
                case "UPDATE_CAR" -> {
                    UpdateCarCommand updateCarCommand = new UpdateCarCommand(clientConnector);
                    updateCarCommand.execute();
                }
                case "GET_SCHEDULES_TO_PRINT" -> {
                    GetSchedulesToPrintCommand getSchedulesToPrintCommand = new GetSchedulesToPrintCommand(clientConnector);
                    getSchedulesToPrintCommand.execute();
                }
                case "LOAD_CLIENT_CARS_IN_CB" -> {
                    LoadClientCarsInCbCommand loadClientCarsInCbCommand = new LoadClientCarsInCbCommand(clientConnector);
                    loadClientCarsInCbCommand.execute();
                }
                case "DELETE_SCHEDULE" -> {
                    DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(clientConnector);
                    deleteScheduleCommand.execute();
                }
                case "GET_WORKING_HOURS" -> {
                    GetWorkingHoursCommand getWorkingHoursCommand = new GetWorkingHoursCommand(clientConnector);
                    getWorkingHoursCommand.execute();
                }
                case "GET_ALL_LIFTS" -> {
                    GetAllLiftsCommand getAllLiftsCommand = new GetAllLiftsCommand(clientConnector);
                    getAllLiftsCommand.execute();
                }
                case "GET_ALL_CARS" -> {
                    GetAllCarsCommand getAllCarsCommand = new GetAllCarsCommand(clientConnector);
                    getAllCarsCommand.execute();
                }
                case "GET_ALL_SCHEDULES" -> {
                    GetAllSchedules getAllSchedules = new GetAllSchedules(clientConnector);
                    getAllSchedules.execute();
                }
                case "GET_ALL_OPERATIONS" -> {
                    GetAllOperationsCommand getAllOperationsCommand = new GetAllOperationsCommand(clientConnector);
                    getAllOperationsCommand.execute();
                }
                case "DELETE_OPERATION" -> {
                    DeleteOperationCommand deleteOperationCommand = new DeleteOperationCommand(clientConnector);
                    deleteOperationCommand.execute();
                }
                case "CREATE_OPERATION" -> {
                    CreateOperationCommand createOperationCommand = new CreateOperationCommand(clientConnector);
                    createOperationCommand.execute();
                }
                case "UPDATE_OPERATION" -> {
                    UpdateOperationCommand updateOperationCommand = new UpdateOperationCommand(clientConnector);
                    updateOperationCommand.execute();
                }
                case "GET_ALL_PARTS" -> {
                    GetAllPartsCommand getAllPartsCommand = new GetAllPartsCommand(clientConnector);
                    getAllPartsCommand.execute();
                }
                case "CREATE_PART" -> {
                    CreatePartCommand createPartCommand = new CreatePartCommand(clientConnector);
                    createPartCommand.execute();
                }
                case "DELETE_PART" -> {
                    DeletePartCommand deletePartCommand = new DeletePartCommand(clientConnector);
                    deletePartCommand.execute();
                }
                case "UPDATE_PART" -> {
                    UpdatePartCommand updatePartCommand = new UpdatePartCommand(clientConnector);
                    updatePartCommand.execute();
                }
                case "CREATE_SCHEDULE" -> {
                    CreateScheduleCommand createScheduleCommand = new CreateScheduleCommand(clientConnector);
                    createScheduleCommand.execute();
                }
                case "GET_INFO_TO_BARCHART" -> {
                    GetInfoToBarCharCommand getInfoToBarCharCommand = new GetInfoToBarCharCommand(clientConnector);
                    getInfoToBarCharCommand.execute();
                }
                case "LOGIN" -> {
                    LoginCommand loginCommand = new LoginCommand(clientConnector);
                    loginCommand.execute();
                }
                case "CREATE_USER" -> {
                    CreateUserCommand createUserCommand = new CreateUserCommand(clientConnector);
                    createUserCommand.execute();
                }
                default -> throw new IllegalStateException("Unexpected value: " + command);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("start run");
        try {
            clientProcessing();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
        System.out.println("start run");
        super.run();
    }
}
