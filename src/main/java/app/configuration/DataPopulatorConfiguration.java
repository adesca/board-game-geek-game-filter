package app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.UnmarshallerRepositoryPopulatorFactoryBean;

@Configuration
public class DataPopulatorConfiguration {

    @Bean
    public AbstractRepositoryPopulatorFactoryBean repositoryPopulatorFactoryBean(
            @Value("${initial.data}") String initialDataLocation
    ) {

        GameRepositoryPopulatorFactoryBean factoryBean = new GameRepositoryPopulatorFactoryBean();
        Resource[] resources = {new ClassPathResource(initialDataLocation)};
        factoryBean.setResources(resources);

        return factoryBean;
    }
}
