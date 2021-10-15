import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFotosCOD() {
		String[] fotos = TestConnection.getImagensDeJogo("0");
		assertEquals(fotos.length, 5);
		assertEquals(fotos[0], "http://vitor.netne.net/fotos/0/cod1.jpg");
		assertEquals(fotos[1], "http://vitor.netne.net/fotos/0/cod2.jpg");
		assertEquals(fotos[2], "http://vitor.netne.net/fotos/0/cod3.jpg");
		assertEquals(fotos[3], "http://vitor.netne.net/fotos/0/cod4.jpg");
		assertEquals(fotos[4], "http://vitor.netne.net/fotos/0/cod5.jpg");
	}

	@Test
	public void tesVideosCOD() {
		String[] videos = TestConnection.getVideosDeJogo("0");
		assertEquals(videos.length, 3);
		assertEquals(videos[0], "http://vitor.netne.net/videos/0/cod1.mp4");
		assertEquals(videos[1], "http://vitor.netne.net/videos/0/cod2.mp4");
		assertEquals(videos[2], "http://vitor.netne.net/videos/0/cod3.mp4");
	}

}
