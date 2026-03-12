package school.sptech.projetoindividual.RunException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RetornoApi extends RuntimeException {
    public RetornoApi(String mensagem) {
        super(mensagem);
    }
}
