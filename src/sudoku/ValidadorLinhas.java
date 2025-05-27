package sudoku;


public class ValidadorLinhas implements Runnable {
    private final int[][] sudoku;
    private boolean valido = true;

    public ValidadorLinhas(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            boolean[] encontrado = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int num = sudoku[i][j];
                if (num < 1 || num > 9 || encontrado[num - 1]) {
                    System.out.println("Linha " + (i + 1) + " inválida.");
                    valido = false;
                    return;
                }
                encontrado[num - 1] = true;
            }
        }
        System.out.println("Todas as linhas são válidas.");
    }
    
    public boolean isValido(){
        return valido;
    }
}
