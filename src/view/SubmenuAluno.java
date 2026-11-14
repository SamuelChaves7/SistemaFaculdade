package view;
import model.Curso;
import model.Aluno;
import model.Disciplina;
import controller.AlunoController;
import controller.CursoController;
import controller.DisciplinaController;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class SubmenuAluno {
    public static void Show() {

        System.out.println("\n");
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Gerenciamento de alunos");
            System.out.println("---------------");
            System.out.println("1 - Criar Aluno");
            System.out.println("2 - Ler Aluno");
            System.out.println("3 - Deletar Aluno");
            System.out.println("4 - Atualizar Aluno");
            System.out.println("5 - Voltar");
            System.out.println("---------------");
            System.out.print("Opção: ");

            opcao = scan.nextInt();

            switch (opcao) {

                case 1: {
                    List<Curso> cursos = CursoController.Read();

                    if (cursos.isEmpty()) {
                        System.out.println("Não há cursos cadastrados! Cadastre um curso primeiro.");
                    } else {
                        scan.nextLine();

                        System.out.print("Nome do aluno: ");
                        String nome = scan.nextLine();

                        System.out.print("Idade do aluno: ");
                        int idade = scan.nextInt();

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

                            Aluno aluno = new Aluno();
                            aluno.setNome(nome);
                            aluno.setIdade(idade);
                            aluno.setCurso(curso);

                            AlunoController.Create(aluno);
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.print("Digite a matrícula do aluno: ");
                    int matricula = scan.nextInt();

                    Optional<Aluno> alunoEncontrado = AlunoController.Read(matricula);

                    if (alunoEncontrado.isPresent()) {
                        Aluno a = alunoEncontrado.get();
                        int codigoCurso = a.getCurso().getCodigo();

                        System.out.println("----------------");
                        System.out.println("Matrícula: " + a.getMatricula());
                        System.out.println("Nome: " + a.getNome());
                        System.out.println("Idade: " + a.getIdade());

                        List<Curso> cursos = CursoController.Read();

                        for (Curso curso : cursos) {
                            if (curso.getCodigo() == codigoCurso) {
                                System.out.println("Curso: " + curso.getNome());
                                System.out.println("Turno: " + curso.getTurno());
                                break;
                            }
                        }

                        System.out.println("Disciplinas:");

                        List<Disciplina> disciplinas = DisciplinaController.Read();
                        boolean encontrouDisciplina = false;

                        for (Disciplina disciplina : disciplinas) {
                            if (disciplina.getCurso().getCodigo() == codigoCurso) {
                                System.out.println("- " + disciplina.getNome()
                                        + " (" + disciplina.getCargaHoraria() + " horas)");
                                encontrouDisciplina = true;
                            }
                        }

                        if (!encontrouDisciplina) {
                            System.out.println("Nenhuma disciplina cadastrada.");
                        }

                    } else {
                        System.out.println("Aluno não encontrado!");
                    }

                    break;
                }
                case 3: {
                    System.out.print("Digite a matrícula do aluno que deseja deletar: ");
                    int matricula = scan.nextInt();
                    if (AlunoController.Delete(matricula)) {
                        System.out.println("Aluno excluído com sucesso!");
                    } else {
                        System.out.println("Aluno não encontrado!");
                    }
                    break;
                }
                case 4: {
                    List<Aluno> alunos = AlunoController.Read();

                    if (alunos.isEmpty()) {
                        System.out.println("Não há alunos cadastrados para atualizar!");
                    } else {
                        System.out.print("Digite a matrícula do aluno que deseja atualizar: ");
                        int matricula = scan.nextInt();
                        scan.nextLine();
                        Optional<Aluno> alunoEncontrado = AlunoController.Read(matricula);

                        if (alunoEncontrado.isEmpty()) {
                            System.out.println("Aluno não encontrado!");
                        } else {
                            System.out.print("Novo nome: ");
                            String nome = scan.nextLine();

                            System.out.print("Nova idade: ");
                            int idade = scan.nextInt();

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

                                Aluno aluno = new Aluno();
                                aluno.setMatricula(matricula);
                                aluno.setNome(nome);
                                aluno.setIdade(idade);
                                aluno.setCurso(curso);

                                AlunoController.Update(aluno);
                            }
                        }

                    }
                    break;
                }

            }

        } while (opcao < 5);
        System.out.println("\n");
    }
}
