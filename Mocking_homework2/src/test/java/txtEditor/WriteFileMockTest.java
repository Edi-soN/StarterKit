package txtEditor;

import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import events.WriteFile;
import panels.PathPanel;

@SuppressWarnings("unused")
public class WriteFileMockTest {

	private final static Logger logger = LogManager.getLogger(WriteFileMockTest.class);

	/**
	 * TODO 2: Przetestuj metode actionPerformed w klasie WriteFile. Chcemy
	 * sprawdzic, czy PathPanel.sayFileSaved() zostalo wywolane raz. Utworz mock
	 * obiektu ActionEvent i wywolaj metode actionPerformed. Zamockuj tez
	 * odpowiednio TextField.getText(), tak by if w metodzie actionPerformed
	 * zwrocil true. Nie zapomnij o mocku dla metody areaInFile tak, by metoda
	 * nie probowala otwierac rzeczywistego pliku.
	 */

	@InjectMocks
	private WriteFile writeFileMock = Mockito.mock(WriteFile.class);

	@Mock
	private ActionEvent actionEventMock;

	@Mock
	private TextField textFieldMock;

	@Before
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}

	// @Test
	// public void actionPerformedTest() {
	// Mockito.when(textFieldMock.getText()).thenReturn("C:\\Users\\ADDZIEDZ\\Desktop\\New
	// Text Document (2).txt");
	//
	// Mockito.doNothing().when(writeFileMock).areaInFile(Mockito.any(JTextArea.class),
	// Mockito.any(String.class));
	//
	// WriteFile wf = new WriteFile();
	// wf.actionPerformed(actionEventMock);
	//
	// PathPanel.label.
	// PathPanel pathPanelMock = Mockito.mock(PathPanel.class);
	// Mockito.verify(pathPanelMock, Mockito.times(1)).sayFileSaved();
	//
	// }
}