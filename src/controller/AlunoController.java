
package controller;

import dao.AlunoDao;
import model.Aluno;
import java.util.List;
import java.util.Optional;

public class AlunoController {

    public static void Create(Aluno aluno) {
        AlunoDao.Create(aluno);
    }

    public static List<Aluno> Read() {
        return AlunoDao.Read();
    }

    public static Optional<Aluno> Read(int matricula) {
        return AlunoDao.Read(matricula);
    }

    public static void Update(Aluno aluno) {
        Optional<Aluno> alunoEncontrado = AlunoDao.Read(aluno.getMatricula());

        if (alunoEncontrado.isPresent()) {
            AlunoDao.Update(aluno);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public static boolean Delete(int matricula) {
        return AlunoDao.Delete(matricula);
    }
}
