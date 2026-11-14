package view;
import model.Curso;
import model.Aluno;
import controller.AlunoController;
import controller.CursoController;
import java.util.Scanner;
import java.util.List;

public class SubmenuAluno {

    public static void Show() {

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
                    List<Aluno> alunos = AlunoController.Read();

                    if (alunos.isEmpty()) {
                        System.out.println("Não há alunos cadastrados!");
                    } else {
                        for (Aluno a : alunos) {
                            System.out.println("----------------");
                            System.out.println("Matrícula: " + a.getMatricula());
                            System.out.println("Nome: " + a.getNome());
                            System.out.println("Idade: " + a.getIdade());
                            System.out.println("Código do curso: " + a.getCurso().getCodigo());
                        }
                    }
                    break;
                }
                case 3: {
                    List<Aluno> alunos = AlunoController.Read();

                    if (alunos.isEmpty()) {
                        System.out.println("Não há alunos cadastrados para deletar!");
                    } else {
                        System.out.print("Digite a matrícula do aluno que deseja deletar: ");
                        int matricula = scan.nextInt();

                        AlunoController.Delete(matricula);
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
                    break;
                }

            }

        } while (opcao < 5);
    }
}
