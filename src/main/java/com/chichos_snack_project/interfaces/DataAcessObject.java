package com.chichos_snack_project.interfaces;

import java.sql.SQLException;

public interface DataAcessObject<T,K> {
    public void create(T t) throws SQLException;
    public T read(T t) throws SQLException;
    public void update(K k,T t) throws SQLException;
    public void delete(K k) throws SQLException;
    public void close() throws SQLException;
}
