package br.senac.pi;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    public static Connection connect() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        Connection conexao = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=projeto_integrador;user=uprojetointegrador;password=projetosantos6263");
        
        return conexao;
    }  
}
