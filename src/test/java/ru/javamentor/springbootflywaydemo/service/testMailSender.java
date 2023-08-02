//package ru.javamentor.springbootflywaydemo.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import ru.javamentor.springbootflywaydemo.model.Film;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import static org.mockito.Mockito.any;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({Session.class, MimeMessage.class})
//public class testMailSender {
//    @Mock
//    private FilmService filmService;
//
//    @Test
//    public void testMailSender() throws Exception {
//        List<Film> films = new ArrayList<>();
//        films.add(new Film(1L, 1, "Film 1", 2021, 7.5, "Description 1"));
//        films.add(new Film(2L, 2, "Film 2", 2022, 8.0, "Description 2"));
//
//        String from = "test192rs@gmail.com";
//        String to = "test192rs@gmail.com";
//        String password = "kpaawbgmelbnwjna";
//        String host = "smtp.gmail.com";
//        String smtpPort = "465";
//        String subject = "Film report";
//        String text = "Please find attached film report";
//
//        // Создаем mock объекты для классов JavaMail API
//        Properties properties = new Properties();
//        Session session = PowerMockito.mock(Session.class);
//
//
//        Transport transport = Mockito.mock(Transport.class);
//
//        Mockito.doNothing().when(transport).send(Mockito.any(Message.class));
//
//        Mockito.when(session.getTransport("smtp")).thenReturn(transport);
//
//        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
//
//        Mockito.when(session.getInstance(any(Properties.class), any(Authenticator.class))).thenReturn(session);
//        Mockito.when(session.getTransport("smtp")).thenReturn(transport);
//        Mockito.when(session.getDebug()).thenReturn(true);
////        Mockito.whenNew(MimeMessage.class).withArguments(session).thenReturn(mimeMessage);
//
//
//        // Проверяем, что все необходимые методы JavaMail API были вызваны с правильными аргументами
//        Mockito.verify(session).setDebug(true);
//        Mockito.verify(session).getTransport("smtp");
//        Mockito.verify(mimeMessage).setFrom(new InternetAddress(from));
//        Mockito.verify(mimeMessage).addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//        Mockito.verify(mimeMessage).setSubject(subject);
//        Mockito.verify(mimeMessage).setText(text);
//        Mockito.verify(mimeMessage).setContent(any(Multipart.class));
//        Mockito.verify(transport).connect(host, Integer.parseInt(smtpPort), from, password);
//        Mockito.verify(transport).sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//        Mockito.verify(transport).close();
//    }
//}
