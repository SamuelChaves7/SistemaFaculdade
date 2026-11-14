package controller;

import dao.AlunoDao;
import model.Aluno;
import java.util.List;

public class AlunoController {
    public static void Create(Aluno aluno) {
        AlunoDao.Create(aluno);
    }
    public static List<Aluno> Read() {
        return AlunoDao.Read();
    }
    public static void Update(Aluno aluno) {
        AlunoDao.Update(aluno);
    }
    public static void Delete(int matricula) {
        AlunoDao.Delete(matricula);
    }
}
