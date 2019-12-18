package database.exp.aa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("database.exp.aa.mapper")
@SpringBootApplication
public class AaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AaApplication.class, args);
    }

}
