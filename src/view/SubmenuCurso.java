package view;

import model.Curso;
import java.util.List;
import controller.CursoController;
import java.util.Scanner;

public class SubmenuCurso {

    public static void Show() {
        System.out.println("\n");
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Submenu - Cursos");
            System.out.println("----------------");
            System.out.println("1 - Criar Curso");
            System.out.println("2 - Ler Cursos");
            System.out.println("3 - Deletar Curso");
            System.out.println("4 - Atualizar Curso");
            System.out.println("5 - Voltar");
            System.out.println("----------------");
            System.out.print("Opção: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.print("Nome do curso: ");
                    scan.nextLine();
                    String nome = scan.nextLine();

                    System.out.print("Turno: ");
                    String turno = scan.nextLine();

                    Curso curso = new Curso();
                    curso.setNome(nome);
                    curso.setTurno(turno);

                    CursoController.Create(curso);
                    break;
                }
                case 2:
                    List<Curso> cursos = CursoController.Read();

                    if (cursos.isEmpty()) {
                        System.out.println("Não há cursos cadastrados!");
                    } else {
                        for (Curso c : cursos) {
                            System.out.println("----------------");
                            System.out.println("Código: " + c.getCodigo());
                            System.out.println("Nome: " + c.getNome());
                            System.out.println("Turno: " + c.getTurno());
                        }
                    }
                    break;
                case 3: {
                    System.out.print("Digite o código do curso que deseja deletar: ");
                    int codigo = scan.nextInt();

                    if (CursoController.Delete(codigo)) {
                        System.out.println("Curso excluído com sucesso!");
                    } else {
                        System.out.println("Curso não encontrado ou possui alunos/disciplinas vinculados!");
                    }
                    break;
                }
                case 4: {
                    List<Curso> listaCursos = CursoController.Read();

                    if (listaCursos.isEmpty()) {
                        System.out.println("Não há cursos cadastrados para atualizar!");
                    } else {
                        System.out.print("Digite o código do curso que deseja atualizar: ");
                        int codigo = scan.nextInt();
                        scan.nextLine();

                        System.out.print("Novo nome: ");
                        String nome = scan.nextLine();

                        System.out.print("Novo turno: ");
                        String turno = scan.nextLine();

                        Curso curso = new Curso();
                        curso.setCodigo(codigo);
                        curso.setNome(nome);
                        curso.setTurno(turno);

                        CursoController.Update(curso);
                    }
                    break;
                }
            }

        } while (opcao != 5);
        System.out.println("\n");
    }
}