package iblutha.demo.springboot.api;

import java.util.List;

/**
 * Created by tibo on 08/11/2015.
 */
public interface Mapper<From ,To> {
    To map(From from);
    List<To> mapAll(List<From> from);
}
