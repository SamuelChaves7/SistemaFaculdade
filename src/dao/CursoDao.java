
package dao;

import database.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Statement;
import model.Curso;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class CursoDao {

    public static void CreateTable() {
        String sqlCreateTable = """
            create table if not exists curso (
                codigo int primary key,
                nome varchar(100) not null,
                turno varchar(30) not null
            );
        """;

        try (Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            stmt.executeUpdate(sqlCreateTable);
            System.out.println("Tabela 'cursos' criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<Curso> cursoList = new ArrayList<>();

    public static void Create(Curso curso) {
        String sql = "insert into cursos (codigo, nome, turno) values (?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, curso.getCodigo());
            stmt.setString(2, curso.getNome());
            stmt.setString(3, curso.getTurno());

            stmt.executeUpdate();

            System.out.println("Curso cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar curso no banco de dados", e);
        }
    }
    public static List<Curso> Read() {
        cursoList.clear();

        String sql = "select * from curso";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();

                curso.setCodigo(rs.getInt("codigo"));
                curso.setNome(rs.getString("nome"));
                curso.setTurno(rs.getString("turno"));

                cursoList.add(curso);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar cursos: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar cursos no banco de dados", e);
        }

        return cursoList;
    }
    public static void Update(Curso curso) {
        String sql = "update curso set nome = ?, turno = ? where codigo = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getTurno());
            stmt.setInt(3, curso.getCodigo());

            stmt.executeUpdate();

            System.out.println("Curso atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar curso no banco de dados", e);
        }
    }
    public static void Delete(int codigo) {
        String sql = "delete from curso where codigo = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Curso excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage());
            throw new RuntimeException("Erro ao excluir curso no banco de dados", e);
        }
    }
}
