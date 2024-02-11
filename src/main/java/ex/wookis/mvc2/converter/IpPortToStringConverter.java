package ex.wookis.mvc2.converter;

import ex.wookis.mvc2.domain.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("Convert IpPort Source = {}",source);
        return source.getIp()+":"+source.getPort();
    }
}
