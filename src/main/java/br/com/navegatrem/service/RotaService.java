package br.com.navegatrem.service;

import br.com.navegatrem.entity.EstacaoEntity;
import br.com.navegatrem.repository.EstacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RotaService {

    private final EstacaoRepository estacaoRepository;

    private static final List<String> ESTACOES_NOMES_LINHA_8 = Arrays.asList(
            "Itapevi", "Engenheiro Cardoso", "Sagrado Coração", "Jandira",
            "Jardim Silveira", "Jardim Belval", "Barueri", "Antonio João",
            "Santa Terezinha", "Carapicuíba", "General Miguel Costa", "Quitaúna",
            "Comandante Sampaio", "Osasco", "Presidente Altino",
            "Imperatriz Leopoldina", "Domingos de Moraes", "Lapa",
            "Palmeiras-Barra Funda", "Júlio Prestes"
    );

    private static final List<String> ESTACOES_NOMES_LINHA_9 = Arrays.asList(
            "Osasco", "Presidente Altino", "Ceasa", "Vila-Lobos-Jaguaré",
            "Cidade Universitária", "Pinheiros", "Hebraica-Rebouças", "Cidade Jardim",
            "Vila Olímpia", "Berrini", "Morumbi", "Granja Julieta", "João Dias",
            "Santo Amaro", "Socorro", "Jurubatuba", "Autódromo",
            "Primavera-Interlagos", "Grajaú", "Mendes-Vila Natal"
    );

    @Inject
    public RotaService(EstacaoRepository estacaoRepository) {
        this.estacaoRepository = estacaoRepository;
    }

    public List<EstacaoEntity> consultarRota(String nomeLinha, String nomeEstacaoOrigem, String nomeEstacaoDestino) {
        List<String> estacoesDaLinhaSelecionada;

        if ("Linha 8".equalsIgnoreCase(nomeLinha)) {
            estacoesDaLinhaSelecionada = ESTACOES_NOMES_LINHA_8;
        } else if ("Linha 9".equalsIgnoreCase(nomeLinha)) {
            estacoesDaLinhaSelecionada = ESTACOES_NOMES_LINHA_9;
        } else {
            throw new NotFoundException("Linha não reconhecida: " + nomeLinha);
        }

        int indiceOrigem = estacoesDaLinhaSelecionada.indexOf(nomeEstacaoOrigem);
        int indiceDestino = estacoesDaLinhaSelecionada.indexOf(nomeEstacaoDestino);

        if (indiceOrigem == -1) {
            throw new NotFoundException("Estação de origem '" + nomeEstacaoOrigem + "' não encontrada na " + nomeLinha);
        }
        if (indiceDestino == -1) {
            throw new NotFoundException("Estação de destino '" + nomeEstacaoDestino + "' não encontrada na " + nomeLinha);
        }

        List<String> nomesEstacoesDaRota;
        if (indiceOrigem <= indiceDestino) {
            nomesEstacoesDaRota = estacoesDaLinhaSelecionada.subList(indiceOrigem, indiceDestino + 1);
        } else {
            nomesEstacoesDaRota = new ArrayList<>(estacoesDaLinhaSelecionada.subList(indiceDestino, indiceOrigem + 1));
            Collections.reverse(nomesEstacoesDaRota);
        }

        return nomesEstacoesDaRota.stream()
                .map(nomeEstacao -> estacaoRepository.findByIdOptional(nomeEstacao)
                        .orElseThrow(() -> new NotFoundException("Detalhes da estação '" + nomeEstacao + "' não encontrados, embora esteja na lista da linha.")))
                .collect(Collectors.toList());
    }
}