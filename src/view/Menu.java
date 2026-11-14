package view;
import java.util.Scanner;

public class Menu {

    public static void Show() {

        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu");
            System.out.println("---------------");
            System.out.println("1 - Gerenciar Alunos");
            System.out.println("2 - Gerenciar Disciplinas");
            System.out.println("3 - Gerenciar Cursos");
            System.out.println("4 - Sair");
            System.out.println("---------------");
            System.out.print("Opção: ");

            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    SubmenuAluno.Show();
                    break;
                case 2:
                    SubmenuDisciplina.Show();
                    break;
                case 3:
                    SubmenuCurso.Show();
                    break;
            }

        } while (opcao < 4);
    }
}
