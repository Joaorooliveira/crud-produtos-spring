package com.product.api.crud_produtos.controller;

import com.product.api.crud_produtos.dto.request.UsuarioRequestDTO;
import com.product.api.crud_produtos.dto.response.UsuarioResponseDTO;
import com.product.api.crud_produtos.infra.security.DadosAutenticacao;
import com.product.api.crud_produtos.entity.Usuario;
import com.product.api.crud_produtos.infra.security.DadosTokenJWT;
import com.product.api.crud_produtos.infra.security.TokenService;
import com.product.api.crud_produtos.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AutenticacaoController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoController(UsuarioRepository usuarioRepository, AuthenticationManager manager, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.manager = manager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO request){
        Usuario usuario = new Usuario();
        usuario.setLogin(request.login());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuarioRepository.saveAndFlush(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponseDTO(usuario.getId(),usuario.getLogin()));
    }

}
