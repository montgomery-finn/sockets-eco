import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {
		
		// Cria o socket com o recurso desejado na porta especificada
		Socket s = new Socket("127.0.0.1", 7000);

		// Cria a Stream de saida de dados
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream os = new DataOutputStream(s.getOutputStream());
		
		Scanner teclado = new Scanner(System.in);
		
		String str = "";
		do {
			str = teclado.nextLine();
			os.writeUTF(str);
			System.out.println(in.readUTF());
		} while(!str.equals("exit"));

		// Encerra o socket cliente
		s.close();
	}
}
