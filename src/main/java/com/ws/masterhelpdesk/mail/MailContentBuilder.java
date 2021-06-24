package com.ws.masterhelpdesk.mail;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
	private final TemplateEngine templateEngine;

	String build(String body) {
		Context context = new Context();
		context.setVariable("message", body);
		return templateEngine.process("assesstmentMailTemplate", context);
	}
}
