package dao;
import database.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Statement;
import model.Aluno;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Curso;

public class AlunoDao {

    public static void CreateTable() {
        String sqlCreateTable = """
        create table if not exists alunos (
            matricula int primary key,
            nome varchar(100) not null,
            idade int not null,
            codigo_curso int not null,
            foreign key (codigo_curso) references curso(codigo)
        );
    """;

        try (Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            stmt.executeUpdate(sqlCreateTable);
            System.out.println("Tabela 'alunos' criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<Aluno> alunoList = new ArrayList<>();
    public static void Create(Aluno aluno) {
        String sql = "insert into alunos (matricula, nome, idade, codigo_curso) values (?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getIdade());
            stmt.setInt(4, aluno.getCurso().getCodigo());

            stmt.executeUpdate();

            System.out.println("Aluno cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar aluno no banco de dados", e);
        }
    }
    public static List<Aluno> Read() {
        alunoList.clear();

        String sql = "select * from alunos";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));

                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo_curso"));

                aluno.setCurso(curso);

                alunoList.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar alunos no banco de dados", e);
        }

        return alunoList;
    }
    public static void Update(Aluno aluno) {
        String sql = "update alunos set nome = ?, idade = ?, codigo_curso = ? where matricula = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setInt(3, aluno.getCurso().getCodigo());
            stmt.setInt(4, aluno.getMatricula());

            stmt.executeUpdate();

            System.out.println("Aluno atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar aluno no banco de dados", e);
        }
    }
    public static void Delete(int matricula) {
        String sql = "delete from alunos where matricula = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, matricula);

            stmt.executeUpdate();

            System.out.println("Aluno excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
            throw new RuntimeException("Erro ao excluir aluno no banco de dados", e);
        }
    }
}