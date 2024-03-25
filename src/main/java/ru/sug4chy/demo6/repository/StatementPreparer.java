package ru.sug4chy.demo6.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementPreparer {
    void prepare(PreparedStatement statement) throws SQLException;
}
