package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Informe o caminho do arquivo.csv: ");
		String caminhoArquivo = sc.nextLine();
		
		// Cria um objeto File para o arquivo CSV
        File arquivoCSV = new File(caminhoArquivo);
        
        // Obtém o diretório onde o arquivo CSV está localizado
        String diretorioCSV = arquivoCSV.getParent();
        
       boolean sucesso = new File(diretorioCSV + "\\out").mkdir();
        
        // Define o caminho para o novo arquivo "summary.csv" dentro da pasta 'out'
        String caminhoNovoArquivo = diretorioCSV + "\\out\\summary.csv";
		
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
               BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoNovoArquivo))) {
               
               String linha = br.readLine();
               
               while (linha != null) {
                   String[] dados = linha.split(",");
                   
                   String nome = dados[0];
                   double precoUnitario = Double.parseDouble(dados[1]);
                   int quantidade = Integer.parseInt(dados[2]);                    
                   double valorTotal = precoUnitario * quantidade;
                   
                   // Escreve no arquivo summary.csv
                   bw.write(nome + ", " + String.format("%.2f", valorTotal));
                   bw.newLine();
                   
                   linha = br.readLine();                
               }
               System.out.println("Criado com sucesso o novo arquivo.csv");
           }
           catch (IOException e) {
               System.err.println("Erro ao processar os arquivos: " + e.getMessage());
           }
       
           		
		sc.close();
			

	}

}
