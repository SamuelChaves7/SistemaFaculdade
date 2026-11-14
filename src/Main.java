import dao.AlunoDao;
import dao.CursoDao;
import dao.DisciplinaDao;
import database.DatabaseConnection;

public class Main {

    public static void main(String[] args) {

        DatabaseConnection.getConnection();

        CursoDao.CreateTable();
        DisciplinaDao.CreateTable();
        AlunoDao.CreateTable();

        DatabaseConnection.closeConnection();

    }
}

