package by.bsuir;

import by.bsuir.car.*;
import by.bsuir.charts.GetRevenueToBarChartCommand;
import by.bsuir.charts.GetWorkLoadInfoToBarCharCommand;
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
import by.bsuir.schedule.GetAllSchedulesCommand;
import by.bsuir.schedule.GetSchedulesToPrintCommand;
import by.bsuir.service.ClientConnectorImpl;
import by.bsuir.service.ConnectedClientInfo;
import by.bsuir.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class ClientHandler extends Thread {
    private final ConnectedClientInfo clientInfo;

    private static Logger logger = LogManager.getLogger();
    private ApplicationContext applicationContext;

    public ClientHandler(ConnectedClientInfo clientInfo, ApplicationContext applicationContext) {
        this.clientInfo = clientInfo;
        this.applicationContext = applicationContext;
    }

    private void clientProcessing() throws IOException, ClassNotFoundException {
        ClientConnectorImpl clientConnector = applicationContext.getBean(ClientConnectorImpl.class, this.clientInfo);
        while (true) {
            String command = clientConnector.receiveObject();
            if (!command.equals("GET_WORKING_HOURS")) {
                logger.info("Received command: " + command);
            }
            switch (command) {
                case "GET_ALL_CLIENTS" -> {
                    GetAllClientsCommand getAllClientsCommand = applicationContext.getBean(GetAllClientsCommand.class, clientConnector);
                    getAllClientsCommand.execute();
                }
                case "CREATE_CLIENT" -> {
                    CreateClientCommand createClientCommand = applicationContext.getBean(CreateClientCommand.class, clientConnector);
                    createClientCommand.execute();
                }
                case "DELETE_CLIENT" -> {
                    DeleteClientCommand deleteClientCommand = applicationContext.getBean(DeleteClientCommand.class, clientConnector);
                    deleteClientCommand.execute();
                }
                case "UPDATE_CLIENT" -> {
                    UpdateClientCommand updateClientCommand = applicationContext.getBean(UpdateClientCommand.class, clientConnector);
                    updateClientCommand.execute();
                }
                case "LOAD_CARS_IN_TABLE" -> {
                    LoadCarsInTableCommand getAllCarsCommand = applicationContext.getBean(LoadCarsInTableCommand.class, clientConnector);
                    getAllCarsCommand.execute();
                }
                case "CREATE_CAR" -> {
                    CreateCarCommand createCarCommand = applicationContext.getBean(CreateCarCommand.class, clientConnector);
                    createCarCommand.execute();
                }
                case "DELETE_CAR" -> {
                    DeleteCarCommand deleteCarCommand = applicationContext.getBean(DeleteCarCommand.class, clientConnector);
                    deleteCarCommand.execute();
                }
                case "UPDATE_CAR" -> {
                    UpdateCarCommand updateCarCommand = applicationContext.getBean(UpdateCarCommand.class, clientConnector);
                    updateCarCommand.execute();
                }
                case "GET_SCHEDULES_TO_PRINT" -> {
                    GetSchedulesToPrintCommand getSchedulesToPrintCommand = applicationContext.getBean(GetSchedulesToPrintCommand.class, clientConnector);
                    getSchedulesToPrintCommand.execute();
                }
                case "LOAD_CLIENT_CARS_IN_CB" -> {
                    LoadClientCarsInCbCommand loadClientCarsInCbCommand = applicationContext.getBean(LoadClientCarsInCbCommand.class, clientConnector);
                    loadClientCarsInCbCommand.execute();
                }
                case "DELETE_SCHEDULE" -> {
                    DeleteScheduleCommand deleteScheduleCommand = applicationContext.getBean(DeleteScheduleCommand.class, clientConnector);
                    deleteScheduleCommand.execute();
                }
                case "GET_WORKING_HOURS" -> {
                    GetWorkingHoursCommand getWorkingHoursCommand = applicationContext.getBean(GetWorkingHoursCommand.class, clientConnector);
                    getWorkingHoursCommand.execute();
                }
                case "GET_ALL_LIFTS" -> {
                    GetAllLiftsCommand getAllLiftsCommand = applicationContext.getBean(GetAllLiftsCommand.class, clientConnector);
                    getAllLiftsCommand.execute();
                }
                case "GET_ALL_CARS" -> {
                    GetAllCarsCommand getAllCarsCommand = applicationContext.getBean(GetAllCarsCommand.class, clientConnector);
                    getAllCarsCommand.execute();
                }
                case "GET_ALL_SCHEDULES" -> {
                    GetAllSchedulesCommand getAllSchedules = applicationContext.getBean(GetAllSchedulesCommand.class, clientConnector);
                    getAllSchedules.execute();
                }
                case "GET_ALL_OPERATIONS" -> {
                    GetAllOperationsCommand getAllOperationsCommand = applicationContext.getBean(GetAllOperationsCommand.class, clientConnector);
                    getAllOperationsCommand.execute();
                }
                case "DELETE_OPERATION" -> {
                    DeleteOperationCommand deleteOperationCommand = applicationContext.getBean(DeleteOperationCommand.class, clientConnector);
                    deleteOperationCommand.execute();
                }
                case "CREATE_OPERATION" -> {
                    CreateOperationCommand createOperationCommand = applicationContext.getBean(CreateOperationCommand.class, clientConnector);
                    createOperationCommand.unExecute();
                }
                case "UPDATE_OPERATION" -> {
                    UpdateOperationCommand updateOperationCommand = applicationContext.getBean(UpdateOperationCommand.class, clientConnector);
                    updateOperationCommand.execute();
                }
                case "GET_ALL_PARTS" -> {
                    GetAllPartsCommand getAllPartsCommand = applicationContext.getBean(GetAllPartsCommand.class, clientConnector);
                    getAllPartsCommand.execute();
                }
                case "CREATE_PART" -> {
                    CreatePartCommand createPartCommand = applicationContext.getBean(CreatePartCommand.class, clientConnector);
                    createPartCommand.execute();
                }
                case "DELETE_PART" -> {
                    DeletePartCommand deletePartCommand = applicationContext.getBean(DeletePartCommand.class, clientConnector);
                    deletePartCommand.execute();
                }
                case "UPDATE_PART" -> {
                    UpdatePartCommand updatePartCommand = applicationContext.getBean(UpdatePartCommand.class, clientConnector);
                    updatePartCommand.execute();
                }
                case "CREATE_SCHEDULE" -> {
                    CreateScheduleCommand createScheduleCommand = applicationContext.getBean(CreateScheduleCommand.class, clientConnector);
                    createScheduleCommand.execute();
                }
                case "GET_WORKLOAD_INFO_TO_BARCHART" -> {
                    GetWorkLoadInfoToBarCharCommand getInfoToBarCharCommand = applicationContext.getBean(GetWorkLoadInfoToBarCharCommand.class, clientConnector);
                    getInfoToBarCharCommand.execute();
                }
                case "LOGIN" -> {
                    LoginCommand loginCommand = applicationContext.getBean(LoginCommand.class, clientConnector);
                    loginCommand.execute();
                }
                case "CREATE_USER" -> {
                    CreateUserCommand createUserCommand = applicationContext.getBean(CreateUserCommand.class, clientConnector);
                    createUserCommand.execute();
                }
                case "GET_ALL_USERS" -> {
                    GetAllUsersCommand getAllUsersCommand = applicationContext.getBean(GetAllUsersCommand.class, clientConnector);
                    getAllUsersCommand.execute();
                }
                case "DELETE_USER" -> {
                    DeleteUserCommand deleteUserCommand = applicationContext.getBean(DeleteUserCommand.class, clientConnector);
                    deleteUserCommand.execute();
                }
                case "UPDATE_USER" -> {
                    UpdateUserCommand updateUserCommand = applicationContext.getBean(UpdateUserCommand.class, clientConnector);
                    updateUserCommand.execute();
                }
                case "GET_REVENUE_INFO_TO_BARCHART" -> {
                    GetRevenueToBarChartCommand getRevenueToBarChartCommand = applicationContext.getBean(GetRevenueToBarChartCommand.class, clientConnector);
                    getRevenueToBarChartCommand.execute();
                }
                default -> throw new IllegalStateException("Unexpected value: " + command);
            }
        }
    }

    @Override
    public void run() {
        try {
            clientProcessing();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
        super.run();
    }
}
