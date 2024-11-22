package gregoryced.com.github.fiap_checkpoint3_sem2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gregoryced.com.github.fiap_checkpoint3_sem2.dtos.ContatoRequestCreateDto;
import gregoryced.com.github.fiap_checkpoint3_sem2.dtos.ContatoRequestUpdateDto;
import gregoryced.com.github.fiap_checkpoint3_sem2.dtos.ContatoResponseDto;
import gregoryced.com.github.fiap_checkpoint3_sem2.mapper.ContatoMapper;
import gregoryced.com.github.fiap_checkpoint3_sem2.repository.ContatoRepository;
import gregoryced.com.github.fiap_checkpoint3_sem2.service.ContatoService;
import gregoryced.com.github.fiap_checkpoint3_sem2.views.ContatoFullView;
import gregoryced.com.github.fiap_checkpoint3_sem2.views.ContatoSimpleView;
import gregoryced.com.github.fiap_checkpoint3_sem2.views.ContatoViewType;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {
	
	@Autowired
   private  ContatoService contatoService;
	@Autowired
   private  ContatoMapper contatoMapper;
	@Autowired
   private  ContatoRepository contatoRepository;
   
   @GetMapping
   public ResponseEntity<List<ContatoResponseDto>> list() {
       List<ContatoResponseDto> dtos = contatoService.list()
           .stream()
           .map(e -> contatoMapper.toDto(e))
           .toList();
       
       return ResponseEntity.ok().body(dtos);
   }

   @PostMapping
   public ResponseEntity<ContatoResponseDto> create(@RequestBody ContatoRequestCreateDto dto) {        

       return ResponseEntity
       		.status(HttpStatus.CREATED)
       		.body(
       			contatoMapper.toDto(
       					contatoService.save(contatoMapper.toModel(dto)))
       			);
   }

   @PutMapping("{id}")
   public ResponseEntity<ContatoResponseDto> update(
                       @PathVariable Long id, 
                       @RequestBody ContatoRequestUpdateDto dto) {
       if (! contatoService.existsById(id)){
           throw new RuntimeException("Id inexistente");
       }                
       return ResponseEntity.ok()
       		.body(
       			contatoMapper.toDto(
       				contatoService.save(contatoMapper.toModel(id, dto)))
       		);
   }
   
   @DeleteMapping("{id}")
   public void delete(@PathVariable Long id) {
       if (! contatoService.existsById(id)){
       	throw new RuntimeException("Id inexistente");
       }

       contatoService.delete(id);
   }

   @GetMapping("{id}")
   public ResponseEntity<ContatoResponseDto> findById(@PathVariable Long id) {    	
   	return ResponseEntity.ok()
   			.body(
   				contatoService
   					.findById(id)
   					.map(e -> contatoMapper.toDto(e))
   					.orElseThrow(() -> new RuntimeException("Id inexistente"))
   			);    	  		     
   }
   
   @GetMapping("/find")
   public  ResponseEntity<?> findByNome(
               @RequestParam String nome, 
               ContatoViewType type) { 

       switch (type) {
           case FULL:
               return ResponseEntity.ok().body(contatoRepository.findAllByNomeContains(nome, ContatoFullView.class));                
           case SIMPLE:
               return ResponseEntity.ok().body(contatoRepository.findAllByNomeContains(nome, ContatoSimpleView.class));            
       }
       return ResponseEntity.noContent().build();
   }
	
	
}
