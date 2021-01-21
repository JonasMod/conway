package conway;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void testModelCreationSunnySide() {
		Model m = new Model(3, 3, "fffffffff");
		assertEquals("fffffffff", m.getState());
		Model m2 = new Model(3, 3, "fttfftttf");
		assertEquals("fttfftttf", m2.getState());
	}
	
	@Test
	void testModelThrowsOnTooSmallGrid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(0, 3, "fffffffff");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(3, 0, "fffffffff");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(-1, 3, "fffffffff");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(3, -1, "fffffffff");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(-1, -1, "fffffffff");
		});
	}
	
	@Test
	void testModelThrowsOnUnmatchingInput() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(3, 3, "fffffffffffffffff");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Model(3, 3, "fff");
		});
	}
	
	@Test
	void testModelAcceptsStupidTestInputAsFalse() {
		Model m = new Model(3, 3, "fabc£&/nn");
		assertEquals("fffffffff", m.getState());
		
		// t is the only true 'true'
		Model m2 = new Model(3, 3, "fabtt&/tn");
		assertEquals("fffttfftf", m2.getState());
	}

	@Test
	void testTickNothingHappensWhenAllAreDead() {
		Model m = new Model(15, 5, 
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff");
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff",
				m.getState());
	}

	@Test
	void testTickStillLifesRemainStill() {
		//block, beehive, tub
		Model m = new Model(15, 5, 
				  "fffffffffffffff"
				+ "fttfffttfffffff"
				+ "fttfftfftffftff"
				+ "ffffffttffftftf"
				+ "fffffffffffftff");
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fttfffttfffffff"
				+ "fttfftfftffftff"
				+ "ffffffttffftftf"
				+ "fffffffffffftff",
				m.getState());
	}
	
	@Test
	void testTickOscillatorsDoOscillate() {
		// blinker, beacon
		String unblinked =
				  "fffffffffffffff"
				+ "ffftffffttfffff"
				+ "ffftfffftffffff"
				+ "ffftffffffftfff"
				+ "ffffffffffttfff";
		String blinked =
				  "fffffffffffffff"
				+ "ffffffffttfffff"
				+ "fftttfffttfffff"
				+ "ffffffffffttfff"
				+ "ffffffffffttfff";
		
		Model m = new Model(15, 5, unblinked);
		m.tick();
		assertEquals(blinked, m.getState());
		m.tick();
		assertEquals(unblinked, m.getState());
		m.tick();
		assertEquals(blinked, m.getState());
	}
	
	@Test
	void testTickSpaceshipsBeMoving() {
		Model m = new Model(15, 6, 
				  "fffffffffffffff"
				+ "ffftfffffffffff"
				+ "fffftffffffffff"
				+ "fftttffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff");
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fftftffffffffff"
				+ "fffttffffffffff"
				+ "ffftfffffffffff"
				+ "fffffffffffffff",
				m.getState());
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffftffffffffff"
				+ "fftftffffffffff"
				+ "fffttffffffffff"
				+ "fffffffffffffff",
				m.getState());
	}
	
	@Test
	void testTickBehavesWellWhenSpaceshipMovesOutOfGrid() {
		Model m = new Model(15, 6, 
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffftffff"
				+ "ffffffffffftfff"
				+ "ffffffffftttfff");
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "ffffffffftftfff"
				+ "ffffffffffttfff",
				m.getState());
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "ffffffffffftfff"
				+ "ffffffffffttfff",
				m.getState());
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "ffffffffffttfff"
				+ "ffffffffffttfff",
				m.getState());
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "ffffffffffttfff"
				+ "ffffffffffttfff", //same state again, this is a static shape
				m.getState());
	}
	
	@Test
	void testTickBehavesWellWhenSpaceshipMovesOutOfGridViaCorner() {
		Model m = new Model(15, 6, 
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "ffffffffffffftf"
				+ "fffffffffffffft"
				+ "ffffffffffffttt");
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffftft"
				+ "ffffffffffffftt",
				m.getState());
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffft"
				+ "ffffffffffffftt",
				m.getState());
		m.tick();
		assertEquals(
				  "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "fffffffffffffff"
				+ "ffffffffffffftt"
				+ "ffffffffffffftt",
				m.getState());
	}
}
