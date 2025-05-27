package sudoku;


public class ValidadorSudoku {
    
    // Matriz fixa para teste
    static int[][] sudoku = {
        {5,3,4,6,7,8,9,1,2},
        {6,7,2,1,9,5,3,4,8},
        {1,9,8,3,4,2,5,6,7},
        {8,5,9,7,6,1,4,2,3},
        {4,2,6,8,5,3,7,9,1},
        {7,1,3,9,2,4,8,5,6},
        {9,6,1,5,3,7,2,8,4},
        {2,8,7,4,1,9,6,3,5},
        {3,4,5,2,8,6,1,7,9}
    };

    // Vetores para marcar se cada parte está válida
    static boolean[] linhasValidas = new boolean[9];
    static boolean[] colunasValidas = new boolean[9];
    static boolean[] subgradesValidas = new boolean[9];

    public static void main(String[] args) throws Exception{
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));

        System.out.println("Iniciando validação do Sudoku...");

        // Criar instâncias dos validadores
        ValidadorLinhas validadorLinhas = new ValidadorLinhas(sudoku);
        ValidadorColunas validadorColunas = new ValidadorColunas(sudoku);

        // Para os subgrids, precisamos criar 9 validadores diferentes
        ValidadorSubgrid[] validadorSubgrids = new ValidadorSubgrid[9];
        int index = 0;
        for (int linha = 0; linha < 9; linha += 3) {
            for (int coluna = 0; coluna < 9; coluna += 3) {
                validadorSubgrids[index] = new ValidadorSubgrid(sudoku, linha, coluna);
                index++;
            }
        }

        // Criar threads para cada validador
        Thread threadLinhas = new Thread(validadorLinhas);
        Thread threadColunas = new Thread(validadorColunas);

        Thread[] threadsSubgrids = new Thread[9];
        for (int i = 0; i < 9; i++) {
            threadsSubgrids[i] = new Thread(validadorSubgrids[i]);
        }

        // Iniciar as threads
        threadLinhas.start();
        threadColunas.start();
        for (int i = 0; i < 9; i++) {
            threadsSubgrids[i].start();
        }

        // Esperar as threads terminarem
        try {
            threadLinhas.join();
            threadColunas.join();
            for (int i = 0; i < 9; i++) {
                threadsSubgrids[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Checar os resultados
        boolean linhasValidas = validadorLinhas.isValido();
        boolean colunasValidas = validadorColunas.isValido();
        boolean subgridsValidos = true;
        for (int i = 0; i < 9; i++) {
            if (!validadorSubgrids[i].isValido()) {
                subgridsValidos = false;
                break;
            }
        }

        if (linhasValidas && colunasValidas && subgridsValidos) {
            System.out.println("Sudoku válido!");
        } else {
            System.out.println("Sudoku inválido!");
        }
    }

}
