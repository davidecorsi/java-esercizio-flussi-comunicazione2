import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * L'applicazione deve copiare un file. Il file di destinazione deve essere fornito dall'utente. Nel caso il
 * file di destinazione già esiste, dovrà essere chiesto all'utente se vuole modificare il nome. Se l'utente
 * non lo vuole modificare il file di destinazione viene cancellato e ricreato con il nuovo contenuto.
 * Il file sorgente viene fornito tramite argomento nel lancio dell'applicazione, mentre il file di
 * destinazione viene richiesto all'utente una volta che l'applicazione è in esecuzione.
 */
public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Inserire il nome del file di destinazione ");
		File destinationFile = new File(input.nextLine());
		if(destinationFile.exists()) {
			int choose;
			do {
				System.out.println("Il file di destinazione già esiste, vuoi cambiarlo\n0 - Si\n1- No");
				choose = input.nextInt();
				input.nextLine();
			} while(choose != 0 && choose != 1);
			if(choose == 0) {
				System.out.print("Inserire il nuovo nome del file ");
				destinationFile = new File(input.nextLine());
				
			} else {
				destinationFile.delete();
			}
		}
		try(BufferedReader brd = new BufferedReader(new FileReader(args[0]));
			BufferedWriter bwt = new BufferedWriter(new FileWriter(destinationFile))) {
			String line;
			while((line = brd.readLine()) != null) {
				bwt.write(line);
				bwt.newLine();
			}
		} catch(IOException e) {
			System.out.println("Errore nella lettura del file " + e);
		}
	}
}
