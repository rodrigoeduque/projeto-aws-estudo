package br.com.rodrigoeduque.curso_aws_project01.controlller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class PessoaController {

    private static Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Pessoa Controller - name: {}", name);

        return ResponseEntity.ok("Name : " + name);

    }

}
