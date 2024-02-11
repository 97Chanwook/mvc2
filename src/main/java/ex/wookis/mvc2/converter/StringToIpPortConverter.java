package ex.wookis.mvc2.converter;

import ex.wookis.mvc2.domain.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        log.info("Convert String Source = {}",source);

        String[] split = source.split(":");
        String ip = split[0];
        Integer port = Integer.parseInt(split[1]);

        return new IpPort(ip,port);
    }
}
