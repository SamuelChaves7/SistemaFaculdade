
package view;

import model.Disciplina;
import model.Curso;
import controller.DisciplinaController;
import controller.CursoController;

import java.util.Scanner;
import java.util.List;

public class SubmenuDisciplina {

    public static void Show() {

        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Gerenciamento de disciplinas");
            System.out.println("----------------");
            System.out.println("1 - Criar Disciplina");
            System.out.println("2 - Ler Disciplinas");
            System.out.println("3 - Deletar Disciplina");
            System.out.println("4 - Atualizar Disciplina");
            System.out.println("5 - Voltar");
            System.out.println("----------------");
            System.out.print("Opção: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1: {
                    List<Curso> cursos = CursoController.Read();

                    if (cursos.isEmpty()) {
                        System.out.println("Não há cursos cadastrados! Cadastre um curso primeiro.");
                    } else {
                        scan.nextLine();

                        System.out.print("Nome da disciplina: ");
                        String nome = scan.nextLine();

                        System.out.print("Carga horária: ");
                        int cargaHoraria = scan.nextInt();

                        System.out.println("Cursos disponíveis:");

                        for (int i = 0; i < cursos.size(); i++) {
                            System.out.println((i + 1) + " - " + cursos.get(i).getNome());
                        }

                        System.out.print("Selecione o curso: ");
                        int opcaoCurso = scan.nextInt();

                        if (opcaoCurso < 1 || opcaoCurso > cursos.size()) {
                            System.out.println("Opção de curso inválida!");
                        } else {
                            Curso curso = cursos.get(opcaoCurso - 1);

                            Disciplina disciplina = new Disciplina();
                            disciplina.setNome(nome);
                            disciplina.setCargaHoraria(cargaHoraria);
                            disciplina.setCurso(curso);

                            DisciplinaController.Create(disciplina);
                        }
                    }
                    break;
                }
                case 2: {
                    List<Disciplina> disciplinas = DisciplinaController.Read();

                    if (disciplinas.isEmpty()) {
                        System.out.println("Não há disciplinas cadastradas!");
                    } else {
                        for (Disciplina d : disciplinas) {
                            System.out.println("----------------");
                            System.out.println("Código: " + d.getCodigo());
                            System.out.println("Nome: " + d.getNome());
                            System.out.println("Carga horária: " + d.getCargaHoraria());
                            System.out.println("Código do curso: " + d.getCurso().getCodigo());
                        }
                    }
                    break;
                }
                case 3: {
                    List<Disciplina> disciplinas = DisciplinaController.Read();

                    if (disciplinas.isEmpty()) {
                        System.out.println("Não há disciplinas cadastradas!");
                    } else {
                        System.out.print("Digite o código da disciplina: ");
                        int codigo = scan.nextInt();

                        DisciplinaController.Delete(codigo);
                    }
                    break;
                }
                case 4: {
                    List<Disciplina> disciplinas = DisciplinaController.Read();

                    if (disciplinas.isEmpty()) {
                        System.out.println("Não há disciplinas cadastradas!");
                    } else {
                        System.out.print("Digite o código da disciplina: ");
                        int codigo = scan.nextInt();
                        scan.nextLine();

                        System.out.print("Novo nome da disciplina: ");
                        String nome = scan.nextLine();

                        System.out.print("Nova carga horária: ");
                        int cargaHoraria = scan.nextInt();

                        List<Curso> cursos = CursoController.Read();

                        System.out.println("Cursos disponíveis:");

                        for (int i = 0; i < cursos.size(); i++) {
                            System.out.println((i + 1) + " - " + cursos.get(i).getNome());
                        }

                        System.out.print("Selecione o novo curso: ");
                        int opcaoCurso = scan.nextInt();

                        if (opcaoCurso < 1 || opcaoCurso > cursos.size()) {
                            System.out.println("Opção de curso inválida!");
                        } else {
                            Curso curso = cursos.get(opcaoCurso - 1);

                            Disciplina disciplina = new Disciplina();
                            disciplina.setCodigo(codigo);
                            disciplina.setNome(nome);
                            disciplina.setCargaHoraria(cargaHoraria);
                            disciplina.setCurso(curso);

                            DisciplinaController.Update(disciplina);
                        }
                    }
                    break;
                }
            }

        } while (opcao < 5);
    }
}
