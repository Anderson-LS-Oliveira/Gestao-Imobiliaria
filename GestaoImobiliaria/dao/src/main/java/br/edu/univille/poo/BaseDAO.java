package br.edu.univille.poo;

import java.sql.Connection;
import java.sql.SQLException;

//DAO -> Data Acess Object
public class BaseDAO {

    // protected/protegido
    // Todas as classes que estão no mesmo pacote
    // ou que faz herança pode acessar
    protected Connection con() throws SQLException {
        return ConnectionFactory.getInstance().get();
    }

}
