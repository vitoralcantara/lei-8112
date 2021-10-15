import game.data.Elemento;
import game.data.JogoData;
import game.data.Requisitos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class TestConnection {

	/**
	 * IMPORTANTISSIMO: Este endereco deve ser configurado caso haja mudan�a de
	 * servidor. Por exemplo. Se o programa for testado em uma m�quina virtual
	 * local, deve-se mudar este endere�o para o endere�o localhost padr�o
	 * android.
	 */
	private static final String ENDERECO = "http://vitor.netne.net";

	private static Object fetch(String address) throws MalformedURLException,
			IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}

	public static Drawable ImageOperations(Context ctx, String url,
			String saveFilename) {
		try {
			InputStream is = (InputStream) fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static BufferedReader abrirConexao(String endereco) {
		try {

			URL serverAddress = new URL(endereco);
			HttpURLConnection connection;

			connection = (HttpURLConnection) serverAddress.openConnection();
			connection.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			return rd;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Elemento> obterListaDeJogos() {
		List<Elemento> jogos = new ArrayList<Elemento>();
		BufferedReader rd = abrirConexao(ENDERECO + "/jogos.html");
		try {
			while (rd.ready()) {
				String line = rd.readLine();
				StringTokenizer st = new StringTokenizer(line, "|");
				if (st.countTokens() != 2)
					break;
				String code = st.nextToken().trim();
				String nome = st.nextToken().trim();
				jogos.add(new Elemento(code, nome));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jogos;
	}

	public static String[] getImagensDeJogo(String jogoIndex) {
		String endereco = ENDERECO + "/" + jogoIndex + ".html";
		String prefixoFoto = ENDERECO + "/fotos/" + jogoIndex + "/";
		BufferedReader rd = abrirConexao(endereco);
		try {
			rd.readLine();
			rd.readLine();
			rd.readLine();
			String stringFotos = rd.readLine();
			StringTokenizer st = new StringTokenizer(stringFotos, "|");
			int qtd = st.countTokens();
			String[] listaFotos = new String[qtd];
			for (int i = 0; i < qtd; i++) {
				listaFotos[i] = prefixoFoto + st.nextToken().trim();
			}
			return listaFotos;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String[] getVideosDeJogo(String jogoIndex) {
		String endereco = ENDERECO + "/" + jogoIndex + ".html";
		String prefixoVideo = ENDERECO + "/videos/" + jogoIndex + "/";

		BufferedReader rd = abrirConexao(endereco);
		try {
			rd.readLine();
			rd.readLine();
			rd.readLine();
			rd.readLine();
			String stringFotos = rd.readLine();
			StringTokenizer st = new StringTokenizer(stringFotos, "|");
			int qtd = st.countTokens();
			String[] listaVideos = new String[qtd];
			for (int i = 0; i < qtd; i++) {
				listaVideos[i] = prefixoVideo + st.nextToken().trim();
			}
			return listaVideos;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static JogoData getDadosDeJogo(String jogoIndex) {
		String endereco = ENDERECO + "/" + jogoIndex + ".html";
		String prefixoFoto = ENDERECO + "/fotos/" + jogoIndex + "/";
		BufferedReader rd = abrirConexao(endereco);

		try {

			// Obtendo primeira linha de dados (informacoes basicas do jogo
			String jogoDados = rd.readLine();
			StringTokenizer st = new StringTokenizer(jogoDados, "|");

			String nomeDoJogo = st.nextToken().trim();
			String produtora = st.nextToken().trim();
			String estilo = st.nextToken().trim();
			int diaLancamento = Integer.valueOf(st.nextToken().trim());
			int mesLancamento = Integer.valueOf(st.nextToken().trim());
			int anoLancamento = Integer.valueOf(st.nextToken().trim());
			String iconeURL = prefixoFoto + st.nextToken().trim();

			// Obtendo as plataformas do jogo (segunda linha)
			jogoDados = rd.readLine();
			st = new StringTokenizer(jogoDados, "|");

			String[] plataformas = new String[st.countTokens()];
			for (int i = 0; i < plataformas.length; i++) {
				plataformas[i] = st.nextToken().trim();
			}

			// Obtendo os requisitos do jogo (terceira linha)
			jogoDados = rd.readLine();
			st = new StringTokenizer(jogoDados, "|");

			String processador = st.nextToken().trim();
			String mem�ria = st.nextToken().trim();
			String discoRigido = st.nextToken().trim();
			String directX = st.nextToken().trim();
			String placaVideo = st.nextToken().trim();
			Requisitos requisitos = new Requisitos(processador, mem�ria,
					discoRigido, directX, placaVideo);

			// Obtendo as urls das imagens do jogo (quarta linha)
			jogoDados = rd.readLine();
			st = new StringTokenizer(jogoDados, "|");

			String[] imagemURLs = new String[st.countTokens()];
			for (int i = 0; i < imagemURLs.length; i++) {
				imagemURLs[i] = st.nextToken().trim();
			}

			// Obtendo as urls dos videos do jogo (quinta linha)
			jogoDados = rd.readLine();
			st = new StringTokenizer(jogoDados, "|");

			String[] videoURLs = new String[st.countTokens()];
			for (int i = 0; i < videoURLs.length; i++) {
				videoURLs[i] = st.nextToken().trim();
			}
			// Obtendo o resumo do jogo (sexta linha)
			jogoDados = rd.readLine();
			st = new StringTokenizer(jogoDados, "|");

			String resumo = st.nextToken().trim();

			JogoData jd = new JogoData(nomeDoJogo, produtora, requisitos,
					imagemURLs, videoURLs, estilo, diaLancamento,
					mesLancamento, anoLancamento, plataformas, resumo, iconeURL);

			return jd;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
