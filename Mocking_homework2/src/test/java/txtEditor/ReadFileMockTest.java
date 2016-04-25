package txtEditor;

import static org.junit.Assert.*;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import events.NoFileToReadException;
import events.ReadFile;
import panels.PathPanel;

@SuppressWarnings("unused")
public class ReadFileMockTest {

	private final static Logger logger = LogManager.getLogger(ReadFileMockTest.class);

	/**
	 * TODO 4: Przetestuj metode ReadFile.fileInArea(JTextArea, String).
	 * Sprawdz, czy zwraca ona wyjatek zaleznie od danych wejsciowych. Mozesz
	 * uzyc mockow lub danych przygotowanych.
	 */

	@InjectMocks
	private ReadFile readFileMock = Mockito.mock(ReadFile.class);

	@Mock
	private ActionEvent actionEventMock;

	@Mock
	private TextField textFieldMock;

	@Mock
	private JTextArea jTextAreaMock;

	@Before
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fileInAreaTestSuccess() {
		Mockito.when(textFieldMock.getText()).thenReturn("C:\\Users\\ADDZIEDZ\\Desktop\\New Text Document.txt");
		ReadFile rf = new ReadFile();
		try {
			rf.fileInArea(jTextAreaMock, textFieldMock.getText());
			logger.debug("Test has passed - Given filepath was valid (for proper path case)");
			assertTrue(true);
		} catch (IOException e) {
			logger.debug("Test has not passed - Given filepath was invalid (for proper path case)");
			assertTrue(false);
		}
	}

	@Test
	public void fileInAreaTestFails() {
		Mockito.when(textFieldMock.getText()).thenReturn("dummyPath");
		ReadFile rf = new ReadFile();
		try {
			rf.fileInArea(jTextAreaMock, textFieldMock.getText());
			logger.debug("Test has not passed - Given filepath was valid (for improper path case)");
			assertTrue(false);
		} catch (IOException e) {
			logger.debug("Test has passed - Given filepath was invalid (for improper path case)");
			assertTrue(true);
		}
	}

	/*
	 * TODO 5: Przetestuj metode actionPerformed w klasie ReadFile. Chcemy
	 * sprawic, by PathPanel.sayFileOpened() nie zostalo wywolane. Utworz mock
	 * obiektu ActionEvent i wywolaj metode actionPerformed. Zamockuj tez
	 * odpowiednio TextField.getText(), tak by if w metodzie actionPerformed
	 * zwrocil true. Nie zapomnij o mocku dla metody areaInFile tak, by metoda
	 * zwrocila wyjatek.
	 */

	// @Test
	// public void actionPerformedTest() {
	// ReadFile rf = new ReadFile();
	// try {
	// Mockito.when(textFieldMock.getText()).thenReturn("C:\\Users\\ADDZIEDZ\\Desktop\\New
	// Text Document (2).txt");
	// Mockito.doThrow(NoFileToReadException.class).when(readFileMock).fileInArea(Mockito.any(JTextArea.class),
	// Mockito.any(String.class));
	// rf.actionPerformed(actionEventMock);
	// } catch (NoFileToReadException e) {
	// logger.debug("Exception has occured - verifying number of sayFileOpened
	// method calls");
	// PathPanel pathPanelMock = Mockito.mock(PathPanel.class);
	// Mockito.verify(pathPanelMock, Mockito.times(0)).sayFileOpened();
	// } catch (IOException e) {
	// logger.debug("Exception has occured - verifying number of sayFileOpened
	// method calls");
	// PathPanel pathPanelMock = Mockito.mock(PathPanel.class);
	// Mockito.verify(pathPanelMock, Mockito.times(0)).sayFileOpened();
	// }
	// }

}
