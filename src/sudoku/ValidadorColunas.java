package sudoku;

public class ValidadorColunas implements Runnable{

    private int[][] sudoku;
    private boolean valido = true;
    
    public ValidadorColunas(int[][] sudoku) {
        this.sudoku = sudoku;
    }
    
    public void run() {
        for (int col = 0; col < 9; col++) {
            boolean[] encontrados = new boolean[9];
            for (int linha = 0; linha < 9; linha ++) {
                int num = sudoku[linha][col];
                if (num < 1 || num > 9 || encontrados[num - 1]) {
                    valido = false;
                    return;
                }
                encontrados[num - 1] = true;
            }
        }
        System.out.println("Todas as colunas são válidas. ");
    }
    
    public boolean isValido() {
        return valido;
    }
}