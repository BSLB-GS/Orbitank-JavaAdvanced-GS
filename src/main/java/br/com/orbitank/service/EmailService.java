package br.com.orbitank.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.api.url}")
    private String brevoApiUrl;

    @Value("${orbitank.mail.from}")
    private String mailFrom;

    public void sendVerificationCode(String to, String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", brevoApiKey);

        Map<String, Object> body = Map.of(
                "sender", Map.of(
                        "name", "Orbitank",
                        "email", mailFrom
                ),
                "to", List.of(
                        Map.of("email", to)
                ),
                "subject", "Orbitank - Recuperação de Senha",
                "htmlContent", """
                        <html>
                          <body>
                            <h2>Recuperação de senha - Orbitank</h2>
                            <p>Seu código de recuperação é:</p>
                            <h1>%s</h1>
                            <p>Este código expira em 10 minutos.</p>
                            <p>Se você não solicitou essa recuperação, ignore este e-mail.</p>
                          </body>
                        </html>
                        """.formatted(code)
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        restTemplate.exchange(
                brevoApiUrl,
                HttpMethod.POST,
                request,
                String.class
        );
    }
}