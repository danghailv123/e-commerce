package com.huce.it.ecommerce.layer.infrastructure.config;

import com.huce.it.ecommerce.unitity.Strings;
import com.huce.it.ecommerce.unitity.http.HostAndPort;
import lombok.Data;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
@Data
public class ElasticConfig {
    private final List<HostAndPort> hosts = new ArrayList<>();
    private UsernamePasswordCredentials authentication;

    @Autowired
    public ElasticConfig(Environment env) {
        String[] addresses = Objects.requireNonNull(env.getProperty("elastic.hosts")).split(",");
        if (addresses.length > 0) for (String address : addresses) hosts.add(HostAndPort.fromString(address));
        String user = env.getProperty("elastic.user", "");
        String pass = env.getProperty("elastic.pass", "");
        if (Strings.isNotNullOrEmptyAll(user, pass)) {
            authentication = new UsernamePasswordCredentials(user, pass);
        }
    }

    public Boolean isSecurity() {
        return authentication != null;
    }
}
