package com.ws.masterhelpdesk.mail;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
	private final TemplateEngine templateEngine;

	String build(String body, String token) {
		Context context = new Context();
		context.setVariable("message", body);
		context.setVariable("token", token);
		return templateEngine.process("assesstmentMailTemplate", context);
	}
}
