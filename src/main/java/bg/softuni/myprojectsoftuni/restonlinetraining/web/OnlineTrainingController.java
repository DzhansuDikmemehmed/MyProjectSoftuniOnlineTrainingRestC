package bg.softuni.myprojectsoftuni.restonlinetraining.web;


import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.AddOnlineTrainingDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.OnlineTrainingByCategoryDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.TrainingDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.enums.TrainingType;
import bg.softuni.myprojectsoftuni.restonlinetraining.service.OnlineTrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class OnlineTrainingController {
    private  final OnlineTrainingService onlineTrainingService;

    public OnlineTrainingController(OnlineTrainingService onlineTrainingService) {
        this.onlineTrainingService = onlineTrainingService;
    }


    @PostMapping("/add-onlineTraining")
    public ResponseEntity<TrainingDto> createOnlineTraining(@RequestBody AddOnlineTrainingDto dto){

        TrainingDto onlineTrainingDto = onlineTrainingService.createOnlineTraining(dto);

        return ResponseEntity.
                created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(onlineTrainingDto.id())
                                .toUri()

                )
                .body(onlineTrainingDto);

    }


    @GetMapping("/online-allTrainings")
    public ResponseEntity<List<TrainingDto>> getAllOnlineTrainings(){
        return ResponseEntity.ok(
                onlineTrainingService.getAllOnlineTrainings()
        );
    }

    @GetMapping("/onlineTraining/{id}")
    public ResponseEntity<TrainingDto> viewDetails(@PathVariable("id") Long id){
        return ResponseEntity.ok(onlineTrainingService.getOnlineTrainingDetails(id));

    }

    @DeleteMapping("/onlineTraining/delete/{id}")
    public ResponseEntity<TrainingDto> deleteOnlineTraining(@PathVariable("id") Long id){
        onlineTrainingService.deleteOnlineTraining(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/onlineTrainings/{category}")
    public ResponseEntity<List<OnlineTrainingByCategoryDto>> getOnlineTrainingByCategory(@PathVariable String category){
        TrainingType trainingType;
        try {
            trainingType = TrainingType.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }

       List<OnlineTrainingByCategoryDto> onlineTrainingByCategoryDtos = onlineTrainingService.findByType(trainingType);

    if (onlineTrainingByCategoryDtos.isEmpty()){
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.ok(onlineTrainingByCategoryDtos);
    }

    }

}
