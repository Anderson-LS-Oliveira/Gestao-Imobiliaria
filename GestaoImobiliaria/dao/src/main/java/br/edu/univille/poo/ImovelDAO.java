package br.edu.univille.poo;

import java.math.BigDecimal;
import java.sql.SQLException;

// Transformar as pessoas em sql
// sql para objetos do tipo Pessoa.
public class ImovelDAO extends BaseDAO{

    public void deletarPeloId(long id){
        String sql = "DELETE FROM Imovel WHERE id = ?;";
        // try-with-resources
        // encerra encerra automaticamente a conexão
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,id);

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Imovel de ID " + id +
                " foi deletado com sucesso!");
            } else {
                System.out.println("Nenhum imovel encontrado com ID: " + id);
            }

        }catch (SQLException e){
            System.out.println("Erro ao deletar imovel pelo id: "+id);
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void inserir(Imovel i){
        String sql = "INSERT INTO Imovel (endereco, quartos, valor)\n" + //
                        "VALUES \n" + //
                        "(?, ?, ?);";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setString(1,i.getEndereco());
            pre.setInt(2,i.getQuartos());
            pre.setBigDecimal(3,i.getValor());

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Imovel foi adicionado com sucesso!");
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir imovel.");
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void selectPeloId(long id){
        String sql = "SELECT * FROM Imovel WHERE id = ?;";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,id);
            try (var rs = pre.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getLong("id"));
                    System.out.println("Endereco: " + rs.getString("endereco"));
                    System.out.println("Quartos: " + rs.getString("quartos"));
                    System.out.println("Valor: " + rs.getString("valor"));
                } else {
                    System.out.println("Nenhum imovel encontrado pelo ID: "+ id);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao selecionar pelo id: "+id);
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void updatePeloID(long id, Imovel i){
        String sql = "UPDATE Imovel\n" + //
                        "SET endereco = ?, quartos = ?, valor = ?\n" + //
                        "WHERE id = ?;";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setString(1,i.getEndereco());
            pre.setInt(2,i.getQuartos());
            pre.setBigDecimal(3,i.getValor());
            pre.setLong(4, id);

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Imovel de ID " + id +
                " foi atualizado com sucesso!");
            } else {
                System.out.println("Não foi possivel atualizar imovel com ID: " + id);
            }

        }catch (SQLException e){
            System.out.println("Erro ao atualizar imovel.");
            System.out.println("Erro: "+e.getMessage());
        }
    }    

    public void listaCompleta(){
        String sql = "SELECT * FROM Imovel;";
        try(var con = con();
            var pre = con.prepareStatement(sql);
            var rs = pre.executeQuery()){
            
            while (rs.next()) {
            long id = rs.getLong("id");
            String endereco = rs.getString("endereco");
            int quartos = rs.getInt("quartos");
            BigDecimal valor = rs.getBigDecimal("valor");

            System.out.println("ID: " + id +
                               ", Endereço: " + endereco +
                               ", Quartos: " + quartos +
                               ", Valor: " + valor);
            }
        }catch (SQLException e){
            System.out.println("Erro: "+e.getMessage());
        }
    }


}
