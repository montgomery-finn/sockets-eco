/*
 * Exemplo de Servidor de Sockets em Java.
 * 
 * Neste exemplo, o servidor está configurado para responder a conexão de apenas um cliente.
 * Enquanto um cliente estiver conectado, outro cliente não poderá se conectar até que
 * a conexão atual seja finalizada.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.Date;

public class Servidor {
	private int port = 7000;
	private ServerSocket serverSocket;
	
	public Servidor() throws ServerException, IOException {
		// Cria o ServerSocket na porta especificada se estiver disponível
		serverSocket = new ServerSocket(port);
		
		System.out.println("Servidor iniciado na porta " + port);

		while (true) {

			// Aguarda uma conexão na porta especificada e cria retorna o socket que irá comunicar com o cliente
			Socket s = serverSocket.accept();
			String ip = s.getInetAddress().getHostAddress();
			System.out.println("Conectado com " + ip);

			// Cria um DataInputStream para o canal de entrada de dados do socket
			DataInputStream  in  = new DataInputStream(s.getInputStream());
			
			// Cria um DataOutputStream para o canal de saída de dados do socket
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			// Aguarda por algum dado e imprime a linha recebida quando recebe
			

			String str = "";
			do{	
				str = in.readUTF();
				out.writeUTF("servidor:" + str);
			}while( !str.equals("exit") ); 

			s.close();
			System.out.println("Desconectado de " + ip);
		}

		// Encerro o ServerSocket
		// serv.close();
	}

	public static void main(String[] args) {
		
		try {
			new Servidor();
		} catch (ServerException e) {
			System.out.println("A conexão com o cliente foi resetada!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}