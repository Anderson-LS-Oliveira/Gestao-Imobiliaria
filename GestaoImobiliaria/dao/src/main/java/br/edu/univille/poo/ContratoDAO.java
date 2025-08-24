package br.edu.univille.poo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

// Transformar as pessoas em sql
// sql para objetos do tipo Pessoa.
public class ContratoDAO extends BaseDAO{

    public void deletarPeloId(long id){
        String sql = "DELETE FROM Contrato WHERE id = ?;";
        // try-with-resources
        // encerra encerra automaticamente a conexão
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,id);

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Contrato de ID " + id +
                " foi deletado com sucesso!");
            } else {
                System.out.println("Nenhum contrato encontrado com ID: " + id);
            }

        }catch (SQLException e){
            System.out.println("Erro ao deletar contrato pelo id: "+id);
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void inserir(Contrato c){
        String sql = "INSERT INTO Contrato (idCliente, idImovel, valor, dataInicio, dataFim)\n" + //
                        "VALUES \n" + //
                        "(?, ?, ?, ?, ?);";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,c.getIdCliente());
            pre.setLong(2,c.getIdImovel());
            pre.setBigDecimal(3,c.getValor());
            pre.setObject(4,c.getDataInicio());
            pre.setObject(5,c.getDataFim());

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Contrato foi adicionado com sucesso!");
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir contrato.");
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void selectPeloId(long id){
        String sql = "SELECT * FROM Contrato WHERE id = ?;";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,id);
            try (var rs = pre.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getLong("id"));
                    System.out.println("ID Cliente: " + rs.getString("idCliente"));
                    System.out.println("ID Imovel: " + rs.getString("idImovel"));
                    System.out.println("Valor: " + rs.getString("valor"));
                    System.out.println("Data Inicio: " + rs.getString("dataInicio"));
                    System.out.println("Data Fim: " + rs.getString("dataFim"));
                } else {
                    System.out.println("Nenhum contrato encontrado pelo ID: "+ id);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao selecionar contrato pelo id: "+id);
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void updatePeloID(long id, Contrato c){
        String sql = "UPDATE Contrato\n" + //
                        "SET idCliente = ?, idImovel = ?, valor = ?, dataInicio = ?, ddataFim = ?\n" + //
                        "WHERE id = ?;";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,c.getIdCliente());
            pre.setLong(2,c.getIdImovel());
            pre.setBigDecimal(3,c.getValor());
            pre.setObject(4,c.getDataInicio());
            pre.setObject(5,c.getDataFim());
            pre.setLong(6, id);

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Contrato de ID " + id +
                " foi atualizado com sucesso!");
            } else {
                System.out.println("Não foi possivel atualizar contrato com ID: " + id);
            }

        }catch (SQLException e){
            System.out.println("Erro ao atualizar contrato pelo ID:  "+ id);
            System.out.println("Erro: "+e.getMessage());
        }
    }    

    public void listaCompleta(){
        String sql = "SELECT * FROM Contrato;";
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
            System.out.println("Erro: "+e.getMessage());
        }
    }


}
