package controller;

import dao.CursoDao;
import model.Curso;
import java.util.List;
public class CursoController {

    public static void Create(Curso curso) {
        CursoDao.Create(curso);
    }
    public static List<Curso> Read() {
        return CursoDao.Read();
    }
    public static void Update(Curso curso) {
        CursoDao.Update(curso);
    }
    public static void Delete(int codigo) {
        CursoDao.Delete(codigo);
    }
}