package by.bsuir.service;

import java.io.IOException;
import java.sql.SQLException;

public interface ManageCommand {
    void execute() throws IOException, ClassNotFoundException, SQLException;
    interface A{}

    default void unExecute() {
    }
}
