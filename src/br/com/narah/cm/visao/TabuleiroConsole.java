package br.com.narah.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.narah.cm.excessao.SairException;
import br.com.narah.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try{
			
			boolean Continuar = true;
			
			while(Continuar) {
				
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/N): ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					Continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
			
		}catch(SairException e) {
			System.out.println("tchau!!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcançado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer>xy = Arrays.stream(digitado.split(","))
					.map(e -> Integer.parseInt(e)).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
				
			}
			
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!!!");
		} catch (Exception e) {
			System.out.println(tabuleiro);
			System.out.println("voce perdeu!!!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}

}
