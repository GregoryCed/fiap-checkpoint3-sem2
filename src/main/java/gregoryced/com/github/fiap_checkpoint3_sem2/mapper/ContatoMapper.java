package gregoryced.com.github.fiap_checkpoint3_sem2.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gregoryced.com.github.fiap_checkpoint3_sem2.dtos.ContatoRequestCreateDto;
import gregoryced.com.github.fiap_checkpoint3_sem2.dtos.ContatoRequestUpdateDto;
import gregoryced.com.github.fiap_checkpoint3_sem2.dtos.ContatoResponseDto;
import gregoryced.com.github.fiap_checkpoint3_sem2.model.Contatos;

@Component
public class ContatoMapper {
	
	@Autowired
    private ModelMapper modelMapper;

    public ContatoResponseDto toDto(Contatos contato) {
        return modelMapper.map(contato, ContatoResponseDto.class);
    }

    public Contatos toModel(ContatoRequestCreateDto dto) {
        return modelMapper.map(dto, Contatos.class);
    }

    public Contatos toModel(Long id, ContatoRequestUpdateDto dto) {
        Contatos result = modelMapper.map(dto, Contatos.class);
        result.setId(id);
        return result;
    } 
}
