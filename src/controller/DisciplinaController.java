package controller;
import dao.CursoDao;
import dao.DisciplinaDao;
import model.Curso;
import model.Disciplina;
import java.util.List;

public class DisciplinaController {
    public static void Create(Disciplina disciplina) {
        DisciplinaDao.Create(disciplina);
    }
    public static List<Disciplina> Read() {
        return DisciplinaDao.Read();
    }
    public static void Update(Disciplina disciplina) {
        DisciplinaDao.Update(disciplina);
    }
    public static void Delete(int codigo) {
        DisciplinaDao.Delete(codigo);
    }
}
