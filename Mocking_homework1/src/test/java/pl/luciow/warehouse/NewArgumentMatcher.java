package pl.luciow.warehouse;

import org.mockito.ArgumentMatcher;

import pl.luciow.warehouse.model.Mail;

public class NewArgumentMatcher extends ArgumentMatcher<Mail> {

	@Override
	public boolean matches(Object argument) {
		return ((Mail)argument).getContent()=="Success";
	}

}
