package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole( Tabuleiro tabuleiro) {
		this.tabuleiro=tabuleiro;
		executarJogo();
	}
	
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDojogo();
				System.out.println("Outra Partida ? (s/n)");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
			
		} catch (SairException e) {
			System.out.println("Tchau!");
		}finally {
			entrada.close();
		}
	}


	private void cicloDojogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x,y): ");
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
				.map(e->Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado(" 1- abrir ou 2 - (Des)Marcar");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alteracarMarcacao(xy.next(), xy.next());
				}
			}
			System.out.println("Voce Ganhou ! Parabens");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você Perdeu!");
		}
		
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		if( "sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
