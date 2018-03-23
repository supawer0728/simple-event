package com.parfait.study.simpleevent;

import com.parfait.study.simpleevent.model.SendableParameter;
import com.parfait.study.simpleevent.model.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class TestSpelParser {

    @Test
    public void parseName() {

        String name = "test";
        Member member = Member.create(name, "test@test.com", "012-2345-6789");

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("name");

        assertThat(expression.getValue(member), is(name));
    }

    @Test
    public void parseSendableParameter() {
        String name = "test";
        String email = "test@test.com";
        String phoneNo = "012-2345-6789";
        Member member = Member.create(name, email, phoneNo);
        SendableParameter sendableParameter = SendableParameter.create(email, phoneNo);

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("T(com.parfait.study.simpleevent.model.SendableParameter).create(email, phoneNo)");

        assertThat(expression.getValue(member), is(sendableParameter));
    }
}
