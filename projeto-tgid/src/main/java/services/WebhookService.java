package services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final String WEBHOOK_URL = "https://webhook.site/ad054f56-7cf5-4582-93d9-9fe684bfdf7e";

    public void enviarCallback(String mensagem) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(WEBHOOK_URL, mensagem, String.class);
    }
}

