package game.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Jogos {

	private HashMap<String, JogoData> jogos = new HashMap<String, JogoData>();

	private List<String> jogoNames = new ArrayList<String>();

	public Jogos() {
		Requisitos mw2r = new Requisitos(
				"AMD 64 3200+, Intel Pentium� 4 ou superior",//
				"1 GB RAM", //
				"12 GB livre em disco", //
				"9.0c", //
				" 256 MB");

		String[] plataformas = { JogoData.XBOX360, JogoData.PS3, JogoData.PC };

		String[] mw2FotosURls = { "cod1.jpg", "cod2.jpg", "cod3.jpg",
				"cod4.jpg", "cod5.jpg" };

		String[] mw2VideosUrls = { "cod1.mp4", "cod2.mp4", "cod3.mp4" };

		String resumoMw2 = "Call of Duty: Modern Warfare 2 (tamb�m conhecido como Modern Warfare 2 ou MW2) � um jogo de tiro em primeira pessoa desenvolvido pela Infinity Ward e distribu�do pela Activision para as plataformas Microsoft Windows, PlayStation 3 e Xbox 360. Anunciado oficialmente em 11 de fevereiro de 2009, foi lan�ado mundialmente em 10 de novembro de 2009."
				+ "� o sexto lan�amento relacionado � s�rie Call of Duty, sendo uma sequ�ncia direta de Call of Duty 4: Modern Warfare, trazendo uma continua��o da hist�ria desenvolvida em seu predecessor. Foi lan�ado em conjun��o com dois outros jogos da s�rie: Call Of Duty: Modern Warfare: Mobilized, para Nintendo DS, e Call of Duty: Modern Warfare: Reflex, uma vers�o port�til de Call of Duty 4 adaptada pela Treyarch para o console Wii."
				+ "O jogo � situado cinco anos ap�s os eventos em Call of Duty 4. O modo campanha � dividido entre o grupo contra-terrorista multi nacional Task Force 141, que tem a miss�o de eliminar o ultranacionalista Vladimir Makarov, e uma tropa de Rangers, com a tarefa de defender os Estados Unidos de uma invas�o russa. Entre os cen�rios do jogo est�o o Afeganist�o, Rio de Janeiro, Sib�ria e Washington D.C.";

		JogoData mw2 = new JogoData("0",
				"Call of Duty: Modern Warfare 2",
				"Infinity Ward", //
				mw2r, //
				mw2FotosURls,//
				mw2VideosUrls,//
				JogoData.FPS, 10, 9, 2009, plataformas, resumoMw2,
				"codicon.png");

		String resumoBf3 = "Battlefield 3 d� sequ�ncia � consagrada franquia shooter da DICE, acrescentando � f�rmula tradicional da s�rie novas possibilidades estrat�gicas, bem como unidades in�ditas e um tratamento gr�fico diferenciado."
				+ "Para dar vaz�o ao caos �pico reinante no t�tulo, a desenvolvedora tamb�m acrescentou novos mapas, consideravelmente mais extensos do que os que se podia encontrar em t�tulos anteriores."
				+ "Uma das principais adi��es de BF 3 vem na forma de um modo campanha hist�ria completo e cheio de reviravoltas. Em uma das miss�es, por exemplo, os fuzileiros navais precisam abrir caminho atrav�s da cidade de Sulaymaniyah (Curdist�o) � embora a hist�ria n�o fique restrita ao Oriente M�dio, levando a guerra tamb�m para regi�es urbanas dos EUA e da Europa."
				+ "O clima b�lico ainda ganha consider�vel upgrade com a adi��o de novos ve�culos, com destaque para o retorno dos ca�as. Em rela��o �s classes de jogo, a DICE manteve as mesmas quatro presentes em jogos anteriores da franquia. Entretanto, Battlefield 3 traz diversos b�nus e equipamentos capazes de conferir especializa��es."
				+ "BF 3 n�o � apenas ca�tico, mas tamb�m bastante realista. Por tr�s de uma destrui��o sem precedentes dos elementos que forma o cen�rio, surge uma nova vers�o da engine Frostbite � convenientemente chamada de Frostbite 2. J� as anima��es representam a parte da EA no desenvolvimento, que disponibilizou a tecnologia utilizada nos jogos da s�rie FIFA para a constru��o dos personagens.";

		Requisitos battleReq = new Requisitos(
				"Core 2 Duo ou equivalente (2 n�cleos de 2 GHz)",//
				"2 GB",//
				"15 GB livres em disco",//
				"Directx 10.0", "256 MB");

		String[] bf3FotosURL = { "bf1.jpg", "bf2.jpg", "bf3.jpg", "bf4.jpg",
				"bf5.jpg", "bf6.jpg" };

		String[] bf3VideosURL = { "bf3.mp4" };

		JogoData battlefield3 = new JogoData("1", "Battlefield 3",//
				"EA Digital Illusions CE", //
				battleReq,//
				bf3FotosURL,//
				bf3VideosURL,//
				JogoData.FPS,//
				25,//
				10,//
				2011,//
				plataformas, resumoBf3, "bf3icon.jpg");

		jogos.put(battlefield3.getID(), battlefield3);
		jogos.put(mw2.getID(), mw2);
		jogoNames.add(battlefield3.getNomeDoJogo());
		jogoNames.add(mw2.getNomeDoJogo());
	}

	public JogoData getJogo(String key) {
		return jogos.get(key);
	}

	public List<String> getJogoNames() {
		return jogoNames;
	}

	public HashMap<String, JogoData> getJogos() {
		return jogos;
	}
}
