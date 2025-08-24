package br.edu.univille.poo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

// Transformar as pessoas em sql
// sql para objetos do tipo Pessoa.
public class Relatorios extends BaseDAO{
    
    //Listar os imóveis disponíveis para aluguel
    public void imoveisDisponiveis(){
        String sql = "SELECT i.*\n" + //
                        "FROM Imovel i\n" + //
                        "LEFT JOIN Contrato c \n" + //
                        "    ON i.id = c.idImovel\n" + //
                        "    AND CURDATE() BETWEEN c.dataInicio AND c.dataFim\n" + //
                        "WHERE c.id IS NULL;";
        try(var con = con();
            var pre = con.prepareStatement(sql);
            var rs = pre.executeQuery()){
            
            while (rs.next()) {
            long id = rs.getLong("id");
            String endereco = rs.getString("endereco");
            int quartos = rs.getInt("quartos");
            BigDecimal valor = rs.getBigDecimal("valor");

            System.out.println("ID: " + id + ", Endereço: " + endereco +
                               ", Quartos: " + quartos + ", Valor: " + valor);
            }
        }catch (SQLException e){
            System.out.println("Erro 2");
            System.out.println("Erro: "+e.getMessage());
        }
    }

    //Listar contratos ativos
    public void contratosAtivos(){
        String sql = "SELECT c.*, cl.nome AS cliente, i.quartos, i.valor\n" + //
                        "FROM Contrato c\n" + //
                        "JOIN Cliente cl ON c.idCliente = cl.id\n" + //
                        "JOIN Imovel i ON c.idImovel = i.id\n" + //
                        "WHERE CURDATE() BETWEEN c.dataInicio AND c.dataFim;";
        try(var con = con();
            var pre = con.prepareStatement(sql);
            var rs = pre.executeQuery()){
            
            while (rs.next()) {
            long id = rs.getLong("id");
            long idCliente = rs.getLong("idCliente");
            long idImovel = rs.getLong("idImovel");
            BigDecimal valor = rs.getBigDecimal("valor");
            LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
            LocalDate dataFim = rs.getDate("dataFim").toLocalDate();

            System.out.println("ID: " + id + ", ID Cliente: " + idCliente +
                               ", ID Imovel: " + idImovel + ", Valor: " + valor +
                               "Data Inicio: " + dataInicio + "Data Fim: " + dataFim);
            }
        }catch (SQLException e){
            System.out.println("Erro 2");
            System.out.println("Erro: "+e.getMessage());
        }
    }

    //Clientes com mais contratos
    public void clientesContratos(){
        String sql = "SELECT \n" + //
                        "    cl.id, \n" + //
                        "    cl.nome, \n" + //
                        "    cl.sobrenome, \n" + //
                        "    COUNT(c.id) AS total_contratos\n" + //
                        "FROM Cliente cl\n" + //
                        "LEFT JOIN Contrato c ON cl.id = c.idCliente\n" + //
                        "GROUP BY cl.id, cl.nome, cl.sobrenome\n" + //
                        "ORDER BY total_contratos DESC;";
        try(var con = con();
            var pre = con.prepareStatement(sql);
            var rs = pre.executeQuery()){
            
            while (rs.next()) {
            long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String sobrenome= rs.getString("sobrenome");
            int totalContratos = rs.getInt("total_contratos");

            System.out.println("ID: " + id + ", Nome: " + nome +
                               ", Sobrenome: " + sobrenome + ", Total Contratos: " + totalContratos);
            }
        }catch (SQLException e){
            System.out.println("Erro 2");
            System.out.println("Erro: "+e.getMessage());
        }
    }

    //Contratos expirando nos próximos 30 dias
    public void contratosExpirando(){
        String sql = "SELECT c.id, cl.nome \n" + //
                        "AS cliente, i.endereco, i.quartos, c.valor, c.dataInicio, c.dataFim\n" + //
                        "FROM Contrato c\n" + //
                        "JOIN Cliente cl ON c.idCliente = cl.id\n" + //
                        "JOIN Imovel i ON c.idImovel = i.id\n" + //
                        "WHERE c.dataFim BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY)\n" + //
                        "ORDER BY c.dataFim ASC;";
        try(var con = con();
            var pre = con.prepareStatement(sql);
            var rs = pre.executeQuery()){
            
            while (rs.next()) {
            long id = rs.getLong("id");
            String cliente = rs.getString("cliente");
            String endereco = rs.getString("endereco");
            int quartos = rs.getInt("quartos");
            BigDecimal valor = rs.getBigDecimal("valor");
            LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
            LocalDate dataFim = rs.getDate("dataFim").toLocalDate();

            System.out.println("ID: " + id + "| Cliente: " + cliente +
                               "| Endereco: " + endereco + "| Quartos: " + quartos + "| Valor: " + valor +
                               "| Data Inicio: " + dataInicio + "| Data Fim: " + dataFim + "\n");
            }
        }catch (SQLException e){
            System.out.println("Erro 2");
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
}
