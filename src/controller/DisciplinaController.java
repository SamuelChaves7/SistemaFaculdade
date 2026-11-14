
package controller;

import dao.DisciplinaDao;
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

    public static boolean Delete(int codigo) {
        return DisciplinaDao.Delete(codigo);
    }
}
