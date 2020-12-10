package com.escapenavigator.slot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = AppConstants.REPOSITORY_PATH)
@RequiredArgsConstructor
public class DatabaseConfiguration {

    private final ApplicationContext springContext;
    private final MongoTemplate mongoTemplate;
    private final MongoConverter mongoConverter;

//    @Bean
//    public MongockSpring5.MongockInitializingBeanRunner mongockInitializingBeanRunner() {
//        String scanPath = this.getClass().getPackageName() + ".database.migrations";
//        return MongockSpring5.builder()
//                .setDriver(SpringDataMongo3Driver.withDefaultLock(mongoTemplate))
//                .addChangeLogsScanPackage(scanPath)
//                .setSpringContext(springContext)
//                .buildInitializingBeanRunner();
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup() {
        Object mappingContext = this.mongoConverter.getMappingContext();
        if (mappingContext instanceof MongoMappingContext) {
            MongoMappingContext mongoMappingContext = (MongoMappingContext) mappingContext;
            for (BasicMongoPersistentEntity<?> persistentEntity : mongoMappingContext.getPersistentEntities()) {
                Class<?> clazz = persistentEntity.getType();
                if (clazz.isAnnotationPresent(Document.class)) {
                    IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
                    IndexOperations indexOps = mongoTemplate.indexOps(clazz);
                    resolver.resolveIndexFor(clazz).forEach(indexOps::ensureIndex);
                }
            }
        }
    }

}
