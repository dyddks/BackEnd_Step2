package org.zerock.b01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // 도메인 클래스에 변화가 일어났을 때 감지하여 리스너에 알려줌
public class B01Application {

    public static void main(String[] args) {
        SpringApplication.run(B01Application.class, args);
    }

}
