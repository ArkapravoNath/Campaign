package com.gok.campaign.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, "oAuth2Authentication");
            createCache(cm, com.gok.campaign.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.gok.campaign.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.gok.campaign.domain.User.class.getName());
            createCache(cm, com.gok.campaign.domain.Authority.class.getName());
            createCache(cm, com.gok.campaign.domain.User.class.getName() + ".authorities");
            createCache(cm, com.gok.campaign.domain.Campaign.class.getName());
            createCache(cm, com.gok.campaign.domain.QuestionSet.class.getName());
            createCache(cm, com.gok.campaign.domain.QuestionSet.class.getName() + ".questions");
            createCache(cm, com.gok.campaign.domain.Question.class.getName());
            createCache(cm, com.gok.campaign.domain.Question.class.getName() + ".questionSets");
            createCache(cm, com.gok.campaign.domain.AnswerSet.class.getName());
            createCache(cm, com.gok.campaign.domain.AnswerSet.class.getName() + ".answers");
            createCache(cm, com.gok.campaign.domain.Answer.class.getName());
            createCache(cm, com.gok.campaign.domain.CampType.class.getName());
            createCache(cm, com.gok.campaign.domain.Owner.class.getName());
            createCache(cm, com.gok.campaign.domain.Location.class.getName());
            createCache(cm, com.gok.campaign.domain.Location.class.getName() + ".zones");
            createCache(cm, com.gok.campaign.domain.Zone.class.getName());
            createCache(cm, com.gok.campaign.domain.Zone.class.getName() + ".zoneTypes");
            createCache(cm, com.gok.campaign.domain.Zone.class.getName() + ".locations");
            createCache(cm, com.gok.campaign.domain.ZoneType.class.getName());
            createCache(cm, com.gok.campaign.domain.ZoneType.class.getName() + ".zones");
            createCache(cm, com.gok.campaign.domain.File.class.getName());
            createCache(cm, com.gok.campaign.domain.DataRow.class.getName());
            createCache(cm, com.gok.campaign.domain.DataColumn.class.getName());
            createCache(cm, com.gok.campaign.domain.ColumnMaster.class.getName());
            createCache(cm, com.gok.campaign.domain.CitizenMedical.class.getName());
            createCache(cm, com.gok.campaign.domain.AdditionalSymptoms.class.getName());
            createCache(cm, com.gok.campaign.domain.AdditionalSymptoms.class.getName() + ".citizenMedicals");
            createCache(cm, com.gok.campaign.domain.AdditionalSymptoms.class.getName() + ".serviceReqs");
            createCache(cm, com.gok.campaign.domain.MedicalSymptom.class.getName());
            createCache(cm, com.gok.campaign.domain.ServiceReq.class.getName());
            createCache(cm, com.gok.campaign.domain.Essentials.class.getName());
            createCache(cm, com.gok.campaign.domain.FileParseRequest.class.getName());
            createCache(cm, com.gok.campaign.domain.EntityAuditEvent.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
