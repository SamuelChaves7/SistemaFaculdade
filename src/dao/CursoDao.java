
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
            codigo int auto_increment primary key,
            nome varchar(100) not null,
            turno varchar(30) not null
        );
    """;

        try (Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            stmt.executeUpdate(sqlCreateTable);

            stmt.executeUpdate(
                    "alter table curso modify codigo int not null auto_increment"
            );

            System.out.println("Tabela 'curso' criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<Curso> cursoList = new ArrayList<>();



    public static void Create(Curso curso) {
        String sql = "insert into curso (nome, turno) values (?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getTurno());

            stmt.executeUpdate();

            System.out.println("Curso cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
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

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Curso atualizado com sucesso!");
            } else {
                System.out.println("Curso não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }


    public static void Delete(int codigo) {
        String sql = "delete from curso where codigo = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Curso excluído com sucesso!");
            } else {
                System.out.println("Curso não encontrado!");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                System.out.println("Não foi possível excluir o curso, pois existem alunos ou disciplinas vinculadas a ele!");
            } else {
                System.out.println("Erro ao excluir curso: " + e.getMessage());
            }
        }
    }

}
