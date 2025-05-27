package sudoku;


public class ValidadorSubgrid implements Runnable{
    private int[][] sudoku;
    private boolean valido = true;
    private int linhaInicio;
    private int colunaInicio;
    
    public ValidadorSubgrid(int[][] sudoku, int linhaInicio, int colunaInicio) {
        
        this.sudoku = sudoku;
        this.linhaInicio = linhaInicio;
        this.colunaInicio = colunaInicio;
    }
    
    @Override
    public void run() {
        boolean[] encontrado = new boolean[9];
        for (int i = linhaInicio; i < linhaInicio + 3; i++) {
            for (int j = colunaInicio; j < colunaInicio + 3; j++) {
                int num = sudoku[i][j];
                if (num < 1 || num > 9 || encontrado[num - 1]) {
                    valido = false;
                    return;
                }
                encontrado[num - 1] = true;
            }
        }
    }
    
    public boolean isValido() {
        return valido;
    }
}
