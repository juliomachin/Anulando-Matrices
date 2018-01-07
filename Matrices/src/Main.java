import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private ArrayList<Integer> alturaSoluciones = new ArrayList<Integer>();
	
	private int[][] matrizInicial = null;

	public static void main(String[] args) {
		Main m = new Main();
		System.out.println(m.empezar());
	}

	public Main() {
		entrada();
	}
	
	public int empezar() {
		ArrayList<int[][]> lista = new ArrayList<int[][]>();
		lista.add(matrizInicial);
		
		hijos(lista,matrizInicial);
		
		int solucion = 999999999;
		
		if(alturaSoluciones.isEmpty()) {
			System.out.println("Matriz sin solución.");
			System.exit(-1);
		}
		
		for (Iterator<Integer> iterator = alturaSoluciones.iterator(); iterator.hasNext();) {
			Integer num = (Integer) iterator.next();
			if(solucion >= num)
				solucion = num;
		}
		
		return solucion;
	}
	
	private void hijos(ArrayList<int[][]> lista, int[][] n) {
		ArrayList<int[][]> l = new ArrayList<int[][]>(lista);

		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n[i].length; j++) {
				int[][] nueva = cambiar(n, i, j);

				if(isNula(nueva)) {
					alturaSoluciones.add(l.size());
					return;
				}

				if(!contains(lista,nueva)) {
					l.add(nueva);

					hijos(l, nueva);

					l.remove(nueva);
				}else {
					return;
				}
			}
		}
	}
	
	public void entrada() {
		ArrayList<int[]> vectores = new ArrayList<int[]>();

		while(sc.hasNext()){
			String linea = sc.nextLine();

			String[] cortes = linea.split("\\s+");

			int[] numsLinea = new int[cortes.length];
			try{
				for (int i = 0; i < numsLinea.length; i++) {
					numsLinea[i] = Integer.parseInt(cortes[i]);
				}
			}catch(Exception e){
				System.out.println("Entrada inválida.");
				System.exit(-1);
			}

			vectores.add(numsLinea);
		}

		if(vectores.size() == 0) {
			System.out.println("Entrada inválida.");
			System.exit(-1);
		}
		
		int[][] matriz = new int[vectores.size()][vectores.get(0).length];

		boolean flag = true;
		
		int i = 0;
		for (Iterator<int[]> iterator = vectores.iterator(); iterator.hasNext();) {
			int[] is = (int[]) iterator.next();
			for (int j = 0; j < is.length; j++) {
				if(is[j] == 0 || is[j] == 1){
					if(is[j] == 0)
						matriz[i][j] = is[j];
					else{
						matriz[i][j] = is[j];
						flag = false;
					}
				}else{
					System.out.println("Entrada inválida.");
					System.exit(-1);
				}
			}
			i++;
		}
		
		if(flag){
			System.out.println("Matriz nula en la entrada.");
			System.exit(-1);
		}

		matrizInicial = matriz;
	}

	private int[][] cambiar(int[][] matriz, int x, int y) {
		int[][] nMatriz = new int[matriz.length][matriz[0].length];
		for (int i = 0; i < nMatriz.length; i++) {
			for (int j = 0; j < nMatriz[i].length; j++) {
				nMatriz[i][j] = matriz[i][j];
			}
		}

		if(matriz[x][y] == 1) {
			nMatriz[x][y] = 0;
		}else {
			nMatriz[x][y] = 1;
		}

		try {
			if(matriz[x+1][y] == 1) {
				nMatriz[x+1][y] = 0;
			}else {
				nMatriz[x+1][y] = 1;
			}
		}catch(IndexOutOfBoundsException e){}

		try {
			if(matriz[x-1][y] == 1) {
				nMatriz[x-1][y] = 0;
			}else {
				nMatriz[x-1][y] = 1;
			}
		}catch(IndexOutOfBoundsException e){}

		try {
			if(matriz[x][y+1] == 1) {
				nMatriz[x][y+1] = 0;
			}else {
				nMatriz[x][y+1] = 1;
			}
		}catch(IndexOutOfBoundsException e){}

		try {
			if(matriz[x][y-1] == 1) {
				nMatriz[x][y-1] = 0;
			}else {
				nMatriz[x][y-1] = 1;
			}
		}catch(IndexOutOfBoundsException e){}

		return nMatriz;
	}

	private boolean contains(ArrayList<int[][]> lista, int[][] matriz) {
		for (int i = 0; i < lista.size(); i++) {
			if( Arrays.deepEquals(lista.get(i), matriz) )
				return true;	
		}
		return false;
	}

	private boolean isNula(int[][] nula) {
		for (int i = 0; i < nula.length; i++) {
			for (int j = 0; j < nula.length; j++) {
				if(nula[i][j] == 1)
					return false;
			}
		}
		return true;
	}

}
