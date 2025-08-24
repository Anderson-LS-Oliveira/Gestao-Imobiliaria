package br.edu.univille.poo;
import java.sql.SQLException;

// Transformar as pessoas em sql
// sql para objetos do tipo Pessoa.
public class ClienteDAO extends BaseDAO{

    public void deletarPeloId(long id){
        String sql = "DELETE FROM Cliente WHERE id = ?;";
        // try-with-resources
        // encerra encerra automaticamente a conexão
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,id);

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente de ID " + id +
                " foi deletado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com ID: " + id);
            }

        }catch (SQLException e){
            System.out.println("Erro ao deletar pelo id: "+id);
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void inserir(Cliente p){
        String sql = "INSERT INTO Cliente (nome, sobrenome, telefone, cpf) VALUES (?, ?, ?, ?);";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setString(1,p.getNome());
            pre.setString(2,p.getSobrenome());
            pre.setString(3,p.getTelefone());
            pre.setString(4,p.getCpf());

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente foi adicionado com sucesso!");
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir cliente.");
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void selectPeloId(long id){
        String sql = "SELECT * FROM Cliente WHERE id = ?;";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setLong(1,id);
            try (var rs = pre.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getLong("id"));
                    System.out.println("Nome: " + rs.getString("nome"));
                    System.out.println("Sobrenome: " + rs.getString("sobrenome"));
                    System.out.println("Telefone: " + rs.getString("telefone"));
                    System.out.println("CPF: " + rs.getString("cpf"));
                } else {
                    System.out.println("Nenhum cliente encontrado pelo ID: "+ id);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao selecionar pelo id: "+id);
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public void updatePeloID(long id, Cliente p){
        String sql = "UPDATE Cliente\n" + //
                        "SET nome = ?, sobrenome = ?, telefone = ?, cpf = ?\n" + //
                        "WHERE id = ?;";
        try(var con = con();
            var pre = con.prepareStatement(sql)){
            pre.setString(1,p.getNome());
            pre.setString(2,p.getSobrenome());
            pre.setString(3,p.getTelefone());
            pre.setString(4,p.getCpf());
            pre.setLong(5, id);

            int linhasAfetadas = pre.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente de ID " + id +
                " foi atualizado com sucesso!");
            } else {
                System.out.println("Não foi possivel atualizar cliente com ID: " + id);
            }

        }catch (SQLException e){
            System.out.println("Erro ao atualizar cliente.");
            System.out.println("Erro: "+e.getMessage());
        }
    }    

    public void listaCompleta(){
        String sql = "SELECT * FROM Cliente;";
        try(var con = con();
            var pre = con.prepareStatement(sql);
            var rs = pre.executeQuery()){
            
            while (rs.next()) {
            long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String sobrenome = rs.getString("sobrenome");
            String telefone = rs.getString("telefone");
            String cpf = rs.getString("cpf");

            System.out.println("ID: " + id + ", Nome: " + nome +
                               ", Sobrenome: " + sobrenome + ", Telefone: " + telefone +
                               "CPF: " + cpf);
            }
        }catch (SQLException e){
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
}
