import dao.AlunoDao;
import dao.CursoDao;
import dao.DisciplinaDao;
import database.DatabaseConnection;
import view.Menu;

public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnection.getConnection();

            CursoDao.CreateTable();
            DisciplinaDao.CreateTable();
            AlunoDao.CreateTable();

            Menu.Show();

        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
