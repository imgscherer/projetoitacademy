package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import entities.Bolsista;

public class Main {
	
	//M�todo que faz o print do menu.
	public static void menu() {
        System.out.println("\tBolsistas CAPES UAB");
        System.out.println("1. Consultar Bolsista Zero por Ano");
        System.out.println("2. Codificar Nomes");
        System.out.println("3. Consultar M�dia Anual");
        System.out.println("4. Ranking dos Valores das Bolsas");
        System.out.println("5. Sair");
        System.out.println("Digite a op��o desejada e aperte ENTER: ");
	}
	
	//M�todo que trata a String inserida pelo usu�rio, tirando caracteres especiais.
	public static String tratarString (String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	//M�todo que l� o arquivo armazenado no computador.
	public static List<Bolsista> leArquivo() {
		//Caminho do arquivo.
		String path = "C:\\Users\\Gabriel\\eclipse-workspace\\br-capes-bolsistas-uab.csv";
		
		//Cria��o do ArrayList que receber� os dados do arquivo.
		List<Bolsista> bolsistas = new ArrayList<Bolsista>();
		
		//Try Catch que far� a popula��o do ArrayList.
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				
				String[] vect = line.split(";");
				String nome = vect[0];
				String cpf = vect[1];
				String universidade = vect[2];
				Integer mesReferencia = Integer.parseInt(vect[3]);
				Integer anoReferencia = Integer.parseInt(vect[4]);
				String sgDiretoria = vect[5];
				String sgSistema = vect[6];
				String cdModalidade = vect[7];
				String dsPagamento = vect[8];
				String moeda = vect[9];
				Integer valorBolsa = Integer.parseInt(vect[10]);
				
				Bolsista bolsista = new Bolsista(nome, 
												 cpf, 
												 universidade, 
												 mesReferencia, 
												 anoReferencia, 
												 sgDiretoria, 
												 sgSistema, 
												 cdModalidade, 
												 dsPagamento, 
												 moeda, 
												 valorBolsa);
				bolsistas.add(bolsista);
				
				line = br.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return bolsistas;
	}
	
	//M�todo que retorna o primeiro bolsista de cada ano.
	public static Bolsista bolsistaZero(List<Bolsista> bolsistas) {
		Scanner ler = new Scanner(System.in);
		int ano;
		
		
		System.out.println("Consulta o Bolsista Zero.");
		System.out.println("Digite o Ano:");
		ano = ler.nextInt();
		
		//Percorre o ArrayList e retorna o primeiro encontrado para um objeto, fazendo um filtro com base no ano inserido.
		Optional<Bolsista> bolsista = bolsistas.stream().
		          								filter(i -> i.getAnoReferencia().equals(ano)).
		          								findFirst();
		
		//Tratamento de anos inv�lidos.
		if(ano > 2016 || ano < 2013) {
			System.out.println();
			System.out.println();
			System.out.println("Ano Inv�lido.");
			System.out.println("Selecione a op��o 1 e tente novamente.");
			System.out.println();
			System.out.println();
			System.out.println();
		}
		else {
			System.out.println();
			System.out.println();
			System.out.println("Nome: " + bolsista.get().getNome());
			System.out.println("CPF: " + bolsista.get().getCpf());
			System.out.println("Entidade de Ensino: " + bolsista.get().getUniversidade());
			System.out.println("Valor da Bolsa: R$" + bolsista.get().getValorBolsa());
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
		return null;
	}
	
	//M�todo que codifica os nomes.
	public static Bolsista codificaNomes (List<Bolsista> bolsistas) {
		
		Scanner ler = new Scanner(System.in);
		String nomePesquisa;
		String nomeTratado;
		StringBuilder nomeInvertido = new StringBuilder ();
		String nomeCodificado = "";
		char letra;
		
		
		System.out.println("Codificar Nomes.");
		System.out.println("Digite o Nome do Bolsista: ");
		
		//Recebe o nome do usu�rio e j� armazena em mai�sculo sem espa�os no inicio e no final.
		nomePesquisa = ler.nextLine().toUpperCase().trim();
		
		//Chama o m�todo que retira caracteres especiais do nome inserido pelo usu�rio.
		nomeTratado = tratarString(nomePesquisa);
		
		//Percorre o ArrayList e retorna o primeiro encontrado para um objeto, fazendo um filtro com base no nome inserido.
		Optional<Bolsista> bolsista = bolsistas.stream().
		                                        filter(i -> i.getNome().contains(nomeTratado)).
		                                        findFirst();
		
		//Separa a String em nome e sobrenome.
		String [] nomeSeparado = bolsista.get().getNome().split("\\s+");
		
		//La�o for que retorna o nome com a primeira e a �ltima letra trocadas e o nome invertido.
		for (int i = 0; i < nomeSeparado.length; i++) {
			String primeiraLetra = String.valueOf(nomeSeparado[i].charAt(0));
			String ultimaLetra = String.valueOf(nomeSeparado[i].charAt(nomeSeparado[i].length() -1));
			String nomeCortado = nomeSeparado[i].substring(1, nomeSeparado[i].length()-1);
			String nomeTrocado = ultimaLetra.concat(nomeCortado).concat(primeiraLetra);
			StringBuilder nomeInvertidoAux = new StringBuilder(nomeTrocado).reverse();
			
			nomeInvertido.append(nomeInvertidoAux + " ");
			
		}
		
		//La�o que percorre a String e faz a substitui��o das letras.
		for (int j = 0; j < nomeInvertido.length(); j++) {
			letra = nomeInvertido.charAt(j);
			
			if(nomeInvertido.charAt(j) != ' ') {
				if (letra >= 'A' && letra <= 'Z') {
					letra = (char)(letra + 1);
					if (letra > 'Z') {
						letra = (char)(letra + 'A'-'Z'-1);
					}
					nomeCodificado = nomeCodificado + letra;
				}
			}else {
				nomeCodificado = nomeCodificado + ' ';
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Nome: " + nomeCodificado);
		System.out.println("CPF: " + bolsista.get().getCpf());
		System.out.println("Entidade de Ensino: " + bolsista.get().getUniversidade());
		System.out.println("Valor da Bolsa: R$" + bolsista.get().getValorBolsa());
		System.out.println();
		System.out.println();
		System.out.println();
		
		return null;
	}
	
	//M�todo que retorna a m�dia anual dos valores de bolsas.
	public static double mediaAnual (List<Bolsista> bolsistas) {
		Scanner ler = new Scanner(System.in);
		int ano;
		
		System.out.println("Consulta a M�dia Anual das Bolsas.");
		System.out.println("Digite o Ano:");
		ano = ler.nextInt();
		
		//Percorre o ArrayList, filtrando por ano, adicionando a um map, fazendo a m�dia e retornando como um double.
		return bolsistas.stream().
				         filter(i -> i.getAnoReferencia().equals(ano)).
				         mapToInt(j -> j.getValorBolsa()).
				         average().
				         getAsDouble();
	}
	
	//M�todo que retorna os 3 maiores e 3 menores valores de bolsas.
	public static Bolsista valoresBolsas (List<Bolsista> bolsistas) {
		
		List<Bolsista> bolsistasParalela = new ArrayList<Bolsista>();
		
		Bolsista bolsista;
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("3 Maiores e 3 Menores Bolsas.");
		System.out.println();
		System.out.println("3 Maiores:");
		
		//La�o de repeti��o que percorre o ArrayList bolsistas, compararando os campos valorBolsa e retornando o valor m�ximo dessa compara��o.
		for(int i = 0; i < 3; i++) {
			bolsista = bolsistas.stream().
					 max((bolsista1, bolsista2) -> (Integer.compare(bolsista1.getValorBolsa(), bolsista2.getValorBolsa()))).
					 get();
			
			//Adiciona a uma lista paralela e remove da lista principal, para que esse que j� foi impresso n�o seja impresso novamente.
			bolsistasParalela.add(bolsista);
			
			bolsistas.remove(bolsista);
			
			System.out.println("Nome: " + bolsista.getNome());
			System.out.println("Entidade de Ensino: " + bolsista.getUniversidade());
			System.out.println("Valor da Bolsa: R$" + bolsista.getValorBolsa());
			System.out.println();
		}
		System.out.println();
		System.out.println("3 Menores:");
		
		//La�o de repeti��o que percorre o ArrayList bolsistas, compararando os campos valorBolsa e retornando o valor m�nimo dessa compara��o.
		for(int i = 0; i < 3; i++) {
			bolsista = bolsistas.stream().
					 min((bolsista1, bolsista2) -> (Integer.compare(bolsista1.getValorBolsa(), bolsista2.getValorBolsa()))).
					 get();
			
			//Adiciona a uma lista paralela e remove da lista principal, para que esse que j� foi impresso n�o seja impresso novamente.
			bolsistasParalela.add(bolsista);
			
			bolsistas.remove(bolsista);
			
			System.out.println("Nome: " + bolsista.getNome());
			System.out.println("Entidade de Ensino: " + bolsista.getUniversidade());
			System.out.println("Valor da Bolsa: R$" + bolsista.getValorBolsa());
			System.out.println();
		}
		
		return null;
	}
	
	//M�todo main.
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		int opcao;
		double media;
		
		//Do While com Switch Case para gerenciamento do menu, chamando os m�todos.
		do {
			menu();
			opcao = ler.nextInt();
			
			switch(opcao) {
			
			case 1:
				List<Bolsista> bolsista = leArquivo();
				
				Bolsista bolsistas = bolsistaZero (bolsista);
				
				break;
				
			case 2:
				bolsista = leArquivo();
				
				codificaNomes(bolsista);
				
				break;
		
			case 3:
				bolsista = leArquivo();
		
				media = mediaAnual(bolsista);
		
				System.out.printf("A m�dia dos valores de Bolsas R$%.2f", media);
				System.out.println();
				System.out.println();
				System.out.println();

				break;
				
			case 4:
				bolsista = leArquivo();
				
				bolsistas = valoresBolsas(bolsista);
				
				break;
				
			case 5:
				System.out.println("Programa Encerrado. Obrigado!");
				
				break;
			
			default:
				System.out.println("Op��o inv�lida.");
			}
		}
		while(opcao != 5);
	}
}
