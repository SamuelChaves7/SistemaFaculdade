
package dao;

import database.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Statement;
import model.Disciplina;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Curso;


public class DisciplinaDao {

    public static void CreateTable() {
        String sqlCreateTable = """
            create table if not exists disciplinas (
                codigo int primary key,
                nome varchar(100) not null,
                carga_horaria int not null,
                codigo_curso int not null,
                foreign key (codigo_curso) references curso(codigo)
            );
        """;

        try (Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            stmt.executeUpdate(sqlCreateTable);
            System.out.println("Tabela 'disciplinas' criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<Disciplina> disciplinaList = new ArrayList<>();
    public static void Create(Disciplina disciplina) {
        String sql = "insert into disciplinas (codigo, nome, carga_horaria, codigo_curso) values (?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, disciplina.getCodigo());
            stmt.setString(2, disciplina.getNome());
            stmt.setInt(3, disciplina.getCargaHoraria());
            stmt.setInt(4, disciplina.getCurso().getCodigo());

            stmt.executeUpdate();

            System.out.println("Disciplina cadastrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar disciplina no banco de dados", e);
        }
    }
    public static List<Disciplina> Read() {
        disciplinaList.clear();

        String sql = "select * from disciplinas";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();

                disciplina.setCodigo(rs.getInt("codigo"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));

                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo_curso"));

                disciplina.setCurso(curso);

                disciplinaList.add(disciplina);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar disciplinas: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar disciplinas no banco de dados", e);
        }

        return disciplinaList;
    }
    public static void Update(Disciplina disciplina) {
        String sql = "update disciplinas set nome = ?, carga_horaria = ?, codigo_curso = ? where codigo = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getCurso().getCodigo());
            stmt.setInt(4, disciplina.getCodigo());

            stmt.executeUpdate();

            System.out.println("Disciplina atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disciplina: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar disciplina no banco de dados", e);
        }
    }
    public static void Delete(int codigo) {
        String sql = "delete from disciplinas where codigo = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Disciplina excluída com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir disciplina: " + e.getMessage());
            throw new RuntimeException("Erro ao excluir disciplina no banco de dados", e);
        }
    }
}
