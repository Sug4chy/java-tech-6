package ru.sug4chy.demo6.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {

    T handle(ResultSet set) throws SQLException;
}
