package events;

import java.io.IOException;

@SuppressWarnings("serial")
public class NoFileToReadException extends IOException {
	NoFileToReadException() {
	};

	NoFileToReadException(String text) {
		super(text);
	}
}
